package com.myFood.order.infra.persistence.repository;

import com.myFood.order.domain.model.OrderSaleRules;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author reinaldo.locatelli
 */
public interface OrderSaleRulesRepository extends JpaRepository<OrderSaleRules, Long> {

    public List<OrderSaleRules> findByOrderSnackId(Long id);

}
