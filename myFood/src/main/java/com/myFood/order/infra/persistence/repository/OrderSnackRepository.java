package com.myFood.order.infra.persistence.repository;

import com.myFood.order.domain.model.OrderSnack;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author reinaldo.locatelli
 */
public interface OrderSnackRepository extends JpaRepository<OrderSnack, Long> {

    public List<OrderSnack> findByOrderId(Long orderId);

}
