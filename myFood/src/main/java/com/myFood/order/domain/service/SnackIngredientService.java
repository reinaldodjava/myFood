package com.myFood.order.domain.service;

import com.myFood.order.domain.model.SnackIngredient;
import com.myFood.order.infra.persistence.repository.SnackIngredientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author reinaldo.locatelli
 */
@Service
public class SnackIngredientService extends AbstractService<SnackIngredient,Long> {

    @Autowired
    private SnackIngredientRepository snackIngredientRepository;

    public List<SnackIngredient> findBySnackId(Long snackId) {
        return snackIngredientRepository.findBySnackIdAndIngredientEnabled(snackId, true);
    }

    @Override
    protected JpaRepository<SnackIngredient, Long> getRepository() {
        return snackIngredientRepository;
    }


}
