package com.myFood.order.domain.model.dto;

import com.myFood.order.domain.model.Snack;
import com.myFood.order.domain.model.SnackIngredient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author reinaldo.locatelli
 */
public class SnackDTO extends Snack implements Serializable {

    List<SnackIngredientDTO> ingredients = new ArrayList<>();

    public SnackDTO(Snack s, List<SnackIngredient> ingredients) {
        setDescription(s.getDescription());
        setId(s.getId());
        setEnabled(s.isEnabled());
        ingredients.stream().forEach(i -> {
            this.ingredients.add(new SnackIngredientDTO(i));
        });
    }

    public SnackDTO() {
    }

    public List<SnackIngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<SnackIngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
    
    
}
