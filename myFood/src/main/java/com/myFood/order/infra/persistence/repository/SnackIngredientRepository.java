package com.myFood.order.infra.persistence.repository;

import com.myFood.order.domain.model.Ingredient;
import com.myFood.order.domain.model.SnackIngredient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author reinaldo.locatelli
 */
public interface SnackIngredientRepository extends JpaRepository<SnackIngredient, Long> {

    List<SnackIngredient> findBySnackIdAndIngredientEnabled(Long snackId, boolean ingredientEnabled);
    
}
