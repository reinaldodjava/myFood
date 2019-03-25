package com.myFood.order.infra.persistence.repository;

import com.myFood.order.domain.model.Order;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author reinaldo.locatelli
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findByOrderByIdDesc(Pageable pageable);

}
