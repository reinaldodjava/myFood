package com.myFood.order.infra.persistence.repository;

import com.myFood.order.domain.model.OrderSnackIngredient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author reinaldo.locatelli
 */
public interface OrderSnackIngredientRepository extends JpaRepository<OrderSnackIngredient, Long> {

    public List<OrderSnackIngredient> findByOrderSnackId(Long orderSnackId);

}
