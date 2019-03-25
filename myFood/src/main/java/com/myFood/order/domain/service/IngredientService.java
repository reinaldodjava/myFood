package com.myFood.order.domain.service;

import com.myFood.order.domain.model.Ingredient;
import com.myFood.order.infra.persistence.repository.IngredientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author reinaldo.locatelli
 */
@Service
public class IngredientService extends AbstractService<Ingredient,Long>{

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAllEnabled() {
        return ingredientRepository.findByEnabled(true);
    }

    @Override
    protected JpaRepository<Ingredient, Long> getRepository() {
        return ingredientRepository;
    }

}
