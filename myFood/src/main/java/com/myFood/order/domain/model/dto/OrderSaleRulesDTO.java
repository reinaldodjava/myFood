package com.myFood.order.domain.model.dto;

import com.myFood.order.domain.model.OrderSaleRules;
import com.myFood.order.domain.model.SaleRules;
import java.io.Serializable;

/**
 *
 * @author reinaldo.locatelli
 */
public class OrderSaleRulesDTO implements Serializable {

    private String name;
    private String description;

    public OrderSaleRulesDTO() {
    }

    public OrderSaleRulesDTO(OrderSaleRules osr) {
        this.name = osr.getName();
        this.description = osr.getDescription();
    }

    public OrderSaleRulesDTO(SaleRules saleRules) {
        this.name = saleRules.getName();
        this.description = saleRules.getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
