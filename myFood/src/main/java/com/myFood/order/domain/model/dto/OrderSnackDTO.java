package com.myFood.order.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myFood.order.domain.model.OrderSaleRules;
import com.myFood.order.domain.model.OrderSnack;
import com.myFood.order.domain.model.OrderSnackIngredient;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author reinaldo.locatelli
 */
public class OrderSnackDTO implements Serializable {

    private Long id;

    @ApiModelProperty(hidden = true)
    private BigDecimal price;

    @ApiModelProperty(hidden = true)
    private String description;

    private List<CrudSnackIngredientDTO> ingredients = new ArrayList<>();

    @ApiModelProperty(hidden = true)
    private List<OrderSaleRulesDTO> promotions = new ArrayList<>();
    
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Long orderSnackId;
    
    public OrderSnackDTO() {
    }

    public OrderSnackDTO(OrderSnack orderSnack) {
        setOrderSnack(orderSnack);
    }

    @ApiModelProperty(hidden = true)
    public void setOrderSnack(OrderSnack orderSnack) {
        this.price = orderSnack.getPrice();
        this.description = orderSnack.getSnackDescription();
        this.id = orderSnack.getSnack().getId();
        this.price = orderSnack.getPrice();
        this.orderSnackId = orderSnack.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CrudSnackIngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<CrudSnackIngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OrderSaleRulesDTO> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<OrderSaleRulesDTO> promotions) {
        this.promotions = promotions;
    }
    
    @ApiModelProperty(hidden = true)
    public void setOrderSnackIngredient(List<OrderSnackIngredient> orderSnackIngredients) {
        orderSnackIngredients.stream().forEach(osi -> {
            this.ingredients.add(new CrudSnackIngredientDTO(osi));
        });
    }

    @ApiModelProperty(hidden = true)
    public void setOrderSaleRules(List<OrderSaleRules> orderSaleRuleses) {
        orderSaleRuleses.stream().forEach(osr -> {
            this.promotions.add(new OrderSaleRulesDTO(osr));
        });
    }

    public Long getOrderSnackId() {
        return orderSnackId;
    }

    public void setOrderSnackId(Long orderSnackId) {
        this.orderSnackId = orderSnackId;
    }
    
    

}
