package com.myFood.order.domain.service;

import com.myFood.order.domain.model.OrderSaleRules;
import com.myFood.order.infra.persistence.repository.OrderSaleRulesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author reinaldo.locatelli
 */
@Service
public class OrderSaleRulesService extends AbstractService<OrderSaleRules,Long>{

    @Autowired
    private OrderSaleRulesRepository orderSaleRulesRepository;

    @Override
    protected JpaRepository<OrderSaleRules, Long> getRepository() {
        return orderSaleRulesRepository;
    }

    public List<OrderSaleRules> findByOrderSnackId(Long id) {
        return orderSaleRulesRepository.findByOrderSnackId(id);
    }

}
