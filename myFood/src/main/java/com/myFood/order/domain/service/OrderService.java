package com.myFood.order.domain.service;

import com.myFood.order.application.exception.CustomException;
import com.myFood.order.domain.model.Ingredient;
import com.myFood.order.domain.model.Order;
import com.myFood.order.domain.model.OrderSaleRules;
import com.myFood.order.domain.model.OrderSnack;
import com.myFood.order.domain.model.OrderSnackIngredient;
import com.myFood.order.domain.model.SaleRuleTypeEnum;
import com.myFood.order.domain.model.SaleRules;
import com.myFood.order.domain.model.Snack;
import com.myFood.order.domain.model.SnackIngredient;
import com.myFood.order.domain.model.dto.CrudSnackDTO;
import com.myFood.order.domain.model.dto.OrderDTO;
import com.myFood.order.domain.model.dto.OrderSaleRulesPriceDTO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.myFood.order.infra.persistence.repository.OrderRepository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author reinaldo.locatelli
 */
@Service
public class OrderService extends AbstractService<Order, Long> {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SnackService snackService;

    @Autowired
    private SaleRulesService saleRulesService;

    @Autowired
    private OrderSaleRulesService orderSaleRulesService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderSnackService orderSnackService;

    @Autowired
    private OrderSnackIngredientService orderSnackIngredientService;

    @Autowired
    private IngredientService ingredientService;

    @Override
    protected JpaRepository<Order, Long> getRepository() {
        return orderRepository;
    }

    public List<OrderDTO> findLastOrders() {
        List<OrderDTO> orderDTOs = new ArrayList<>();

        List<Order> orders = findByOrderByIdDesc();;


        orders.stream().forEach(o -> {
            OrderDTO orderDTO = new OrderDTO(o);
            orderDTOs.add(orderDTO);
            
            List<OrderSnack> orderSnacks = orderSnackService.findByOrderId(o.getId());
            orderDTO.setOrderSnack(orderSnacks);
            
            orderDTO.getSnacks().stream().forEach(os->{
                List<OrderSnackIngredient> orderSnackIngredients = orderSnackIngredientService.findByOrderSnackId(os.getOrderSnackId());
                os.setOrderSnackIngredient(orderSnackIngredients);
                
                List<OrderSaleRules> orderSaleRuleses = orderSaleRulesService.findByOrderSnackId(os.getOrderSnackId());
                os.setOrderSaleRules(orderSaleRuleses);                
            });
            
        });

        return orderDTOs;
    }

    public List<Order> findByOrderByIdDesc() {
        Pageable pageable = PageRequest.of(0, 15);
        return orderRepository.findByOrderByIdDesc(pageable);
    }

    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        List<SaleRules> saleRuleses = saleRulesService.findAllEnabled();

        Order order = new Order();
        order.setDate(new Date());
        order.setPrice(BigDecimal.ZERO);
        orderService.save(order);

        List<OrderSnack> orderSnacks = new ArrayList<>();

        orderDTO.getSnacks().stream().forEach(s -> {

            Optional<Snack> optionalSnack = snackService.findById(s.getId());
            if (!optionalSnack.isPresent()) {
                throw new CustomException("Lanche não encontrado! id : " + s.getId(), HttpStatus.BAD_REQUEST);
            }
            Snack snack = optionalSnack.get();

            OrderSnack orderSnack = new OrderSnack();
            orderSnack.setOrder(order);
            orderSnack.setSnack(snack);
            orderSnack.setSnackDescription(snack.getDescription());
            orderSnack.setPrice(BigDecimal.ZERO);
            orderSnackService.save(orderSnack);

            List<OrderSnackIngredient> orderSnackIngredients = new ArrayList();

            s.getIngredients().stream().forEach(i -> {
                Optional<Ingredient> optionalIngredient = ingredientService.findById(i.getId());
                if (!optionalIngredient.isPresent()) {
                    throw new CustomException("Ingrediente não encontrado! id : " + i.getId(), HttpStatus.BAD_REQUEST);
                }
                Ingredient ingredient = optionalIngredient.get();
                OrderSnackIngredient orderSnackIngredient = new OrderSnackIngredient();
                orderSnackIngredient.setDescription(ingredient.getDescription());
                orderSnackIngredient.setIngredient(ingredient);
                orderSnackIngredient.setOrderSnack(orderSnack);
                orderSnackIngredient.setQuantity(i.getQuantity());
                orderSnackIngredient.setPrice(ingredient.getPrice());
                orderSnackIngredients.add(orderSnackIngredient);
                orderSnackIngredientService.save(orderSnackIngredient);

                i.setOrderSnackIngredient(orderSnackIngredient);
            });

            OrderSaleRulesPriceDTO orderSaleRulesPriceDTO = processOrderSnackPrice(orderSnackIngredients, saleRuleses);

            orderSnack.setPrice(orderSaleRulesPriceDTO.getPrice());
            orderSnackService.save(orderSnack);
            orderSnacks.add(orderSnack);

            orderSaleRulesPriceDTO.getOrderSaleRulesDTOs().stream().forEach(osrp -> {
                OrderSaleRules orderSaleRules = new OrderSaleRules();
                orderSaleRules.setDescription(osrp.getDescription());
                orderSaleRules.setName(osrp.getName());
                orderSaleRules.setOrderSnack(orderSnack);
                orderSaleRulesService.save(orderSaleRules);
            });

            s.setOrderSnack(orderSnack);
            s.setPromotions(orderSaleRulesPriceDTO.getOrderSaleRulesDTOs());
        });

        Optional<BigDecimal> optionalTotalOrder = orderSnacks.stream().map(OrderSnack::getPrice).reduce(BigDecimal::add);
        if (optionalTotalOrder.isPresent()) {
            order.setPrice(optionalTotalOrder.get());
            orderService.save(order);
        }
        orderDTO.setOrder(order);

        return orderDTO;
    }

    private OrderSaleRulesPriceDTO processOrderSnackPrice(List<OrderSnackIngredient> orderSnackIngredients, List<SaleRules> saleRuleses) {
        OrderSaleRulesPriceDTO orderSaleRulesPriceDTO = new OrderSaleRulesPriceDTO();

        List<Ingredient> ingredients = new ArrayList<>();

        orderSnackIngredients.stream().forEach(osi -> {
            ingredients.add(osi.getIngredient());
        });

        // Obter percentual de desconto pela regra HAVE_NOT_HAVE (Tem ou não tem ingredientes)
        BigDecimal discountPercentValue = BigDecimal.ZERO;

        List<SaleRules> saleRulesHaveNotHave = saleRuleses.stream().filter(sr
                -> sr.getRuleType().equals(SaleRuleTypeEnum.HAVE_NOT_HAVE) && ((sr.getIngredientIdHave() != null && sr.getIngredientIdNotHave() != null && ingredients.contains(new Ingredient(sr.getIngredientIdHave())) && !ingredients.contains(new Ingredient(sr.getIngredientIdNotHave())))
                || (sr.getIngredientIdHave() == null && sr.getIngredientIdNotHave() != null && !ingredients.contains(new Ingredient(sr.getIngredientIdNotHave())))
                || (sr.getIngredientIdHave() != null && sr.getIngredientIdNotHave() == null && ingredients.contains(new Ingredient(sr.getIngredientIdHave()))))
        ).collect(Collectors.toList());

        orderSaleRulesPriceDTO.addSaleRule(saleRulesHaveNotHave);

        Optional<BigDecimal> optionalDiscountPercentValue = saleRulesHaveNotHave.stream().map(SaleRules::getDiscountPercentValue).reduce(BigDecimal::add);

//        Optional<BigDecimal> optionalDiscountPercentValue = saleRuleses.stream().filter(sr;
//                -> sr.getRuleType().equals(SaleRuleTypeEnum.HAVE_NOT_HAVE) && ((sr.getIngredientIdHave() != null && sr.getIngredientIdNotHave() != null && ingredients.contains(new Ingredient(sr.getIngredientIdHave())) && !ingredients.contains(new Ingredient(sr.getIngredientIdNotHave())))
//                || (sr.getIngredientIdHave() == null && sr.getIngredientIdNotHave() != null && !ingredients.contains(new Ingredient(sr.getIngredientIdNotHave())))
//                || (sr.getIngredientIdHave() != null && sr.getIngredientIdNotHave() == null && ingredients.contains(new Ingredient(sr.getIngredientIdHave()))))
//        ).map(SaleRules::getDiscountPercentValue)
//                .reduce(BigDecimal::add);
        if (optionalDiscountPercentValue.isPresent()) {
            discountPercentValue = optionalDiscountPercentValue.get();
        }

        orderSnackIngredients.stream().forEach(osi -> {
            //Verificar se os ingredientes caem na regra TAKE_PAY (leve 3 pague 2)
            Optional<SaleRules> optionalSaleRules = saleRuleses.stream().filter(sr -> (sr.getRuleType().equals(SaleRuleTypeEnum.TAKE_PAY) && osi.getQuantity().compareTo(sr.getTakeQuantity()) != -1 &&osi.getIngredient().getId().equals(sr.getIngredientIdTake()))).findFirst();

            //Caso cair na regra TAKE_PAY altera a quantidade para a quantidade que será cobrada
            if (optionalSaleRules.isPresent()) {
                SaleRules saleRules = optionalSaleRules.get();

                orderSaleRulesPriceDTO.addSaleRule(saleRules);

                BigDecimal quantitySaleRules = osi.getQuantity().divide(saleRules.getTakeQuantity(), 0, RoundingMode.DOWN);
                osi.setQuantity(osi.getQuantity().subtract(quantitySaleRules.multiply(saleRules.getTakeQuantity())).add(quantitySaleRules.multiply(saleRules.getPayForQuantity())));
            }
        });

        //Calculando total a ser pago
        BigDecimal totalValue = BigDecimal.ZERO;
        Optional<BigDecimal> optionalTotal = orderSnackIngredients.stream().map(OrderSnackIngredient::getTotal).reduce(BigDecimal::add);
        if (optionalTotal.isPresent()) {
            totalValue = optionalTotal.get();
        }

        orderSaleRulesPriceDTO.setPrice(totalValue.subtract(totalValue.multiply(discountPercentValue.divide(new BigDecimal(100L)))));

        return orderSaleRulesPriceDTO;
    }

}
