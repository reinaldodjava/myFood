package com.myFood.order.infra.persistence.repository;

import com.myFood.order.domain.model.Ingredient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author reinaldo.locatelli
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByEnabled(boolean enabled);
    
}
