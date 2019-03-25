package com.myFood.order.domain.model.dto;

import com.myFood.order.domain.model.SaleRules;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author reinaldo.locatelli
 */
public class OrderSaleRulesPriceDTO implements Serializable {
    
    private List<OrderSaleRulesDTO> orderSaleRulesDTOs = new ArrayList<>();
    private BigDecimal price = BigDecimal.ZERO;

    public OrderSaleRulesPriceDTO() {
    }
    
    public List<OrderSaleRulesDTO> getOrderSaleRulesDTOs() {
        return orderSaleRulesDTOs;
    }
    
    public void setOrderSaleRulesDTOs(List<OrderSaleRulesDTO> orderSaleRulesDTOs) {
        this.orderSaleRulesDTOs = orderSaleRulesDTOs;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public void addSaleRule(SaleRules saleRules) {
        OrderSaleRulesDTO orderSaleRulesDTO = new OrderSaleRulesDTO(saleRules);
        orderSaleRulesDTOs.add(orderSaleRulesDTO);
    }

    public void addSaleRule(List<SaleRules> saleRules) {
        saleRules.stream().forEach(sr -> {
            addSaleRule(sr);
        });
    }
    
}
