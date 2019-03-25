package com.myFood.order.domain.service;

import com.myFood.order.domain.model.OrderSnackIngredient;
import com.myFood.order.infra.persistence.repository.OrderSnackIngredientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author reinaldo.locatelli
 */
@Service
public class OrderSnackIngredientService extends AbstractService<OrderSnackIngredient,Long>{

    @Autowired
    private OrderSnackIngredientRepository orderSnackIngredientRepository;

    @Override
    protected JpaRepository<OrderSnackIngredient, Long> getRepository() {
        return orderSnackIngredientRepository;
    }
    
    public List<OrderSnackIngredient> findByOrderSnackId(Long orderSnackId){
        return orderSnackIngredientRepository.findByOrderSnackId(orderSnackId);
    }

}
