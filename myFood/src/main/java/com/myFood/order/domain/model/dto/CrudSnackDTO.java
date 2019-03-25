package com.myFood.order.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myFood.order.domain.model.Snack;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author reinaldo.locatelli
 */
public class CrudSnackDTO extends Snack implements Serializable {

    List<CrudSnackIngredientDTO> ingredients = new ArrayList<>();

    @JsonIgnore
    public Snack getSnack(){
        return new Snack(super.getId(), super.getDescription(), super.isEnabled());
    }
    
    public List<CrudSnackIngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<CrudSnackIngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
    
    
}
