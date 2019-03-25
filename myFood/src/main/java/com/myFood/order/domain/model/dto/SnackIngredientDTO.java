package com.myFood.order.domain.model.dto;

import com.myFood.order.domain.model.Ingredient;
import com.myFood.order.domain.model.SnackIngredient;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author reinaldo.locatelli
 */

public class SnackIngredientDTO extends Ingredient implements Serializable {

    private BigDecimal quantity;

    public SnackIngredientDTO() {
    }

    public SnackIngredientDTO(SnackIngredient i) {
        if (i.getIngredient() != null) {
            setDescription(i.getIngredient().getDescription());
            setPrice(i.getIngredient().getPrice());
            setId(i.getIngredient().getId());
            setEnabled(i.getIngredient().isEnabled());
        }
        setQuantity(i.getQuantity());
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

}
