package com.myFood.order.domain.service;

import com.myFood.order.domain.model.OrderSnack;
import com.myFood.order.infra.persistence.repository.OrderSnackRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author reinaldo.locatelli
 */
@Service
public class OrderSnackService extends AbstractService<OrderSnack,Long>{

    @Autowired
    private OrderSnackRepository orderSnackRepository;

    @Override
    protected JpaRepository<OrderSnack, Long> getRepository() {
        return orderSnackRepository;
    }
    
    public List<OrderSnack> findByOrderId(Long orderId){
        return orderSnackRepository.findByOrderId(orderId);
    }

}
