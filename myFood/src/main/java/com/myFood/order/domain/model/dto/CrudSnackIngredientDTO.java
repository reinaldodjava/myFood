package com.myFood.order.domain.model.dto;

import com.myFood.order.domain.model.OrderSnackIngredient;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author reinaldo.locatelli
 */
public class CrudSnackIngredientDTO implements Serializable {

    private Long id;
    
    private BigDecimal quantity;

    @ApiModelProperty(hidden = true)
    private String description;    
    
    @ApiModelProperty(hidden = true)
    private BigDecimal price;

    public CrudSnackIngredientDTO() {
    }

    public CrudSnackIngredientDTO(OrderSnackIngredient orderSnackIngredient) {
        setOrderSnackIngredient(orderSnackIngredient);
    }
    
    @ApiModelProperty(hidden = true)
    public void setOrderSnackIngredient(OrderSnackIngredient orderSnackIngredient) {
        this.id=orderSnackIngredient.getIngredient().getId();
        this.quantity=orderSnackIngredient.getQuantity();
        this.description=orderSnackIngredient.getDescription();
        this.price=orderSnackIngredient.getPrice();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
