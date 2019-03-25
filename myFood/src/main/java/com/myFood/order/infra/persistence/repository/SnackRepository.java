package com.myFood.order.infra.persistence.repository;

import com.myFood.order.domain.model.Snack;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author reinaldo.locatelli
 */
public interface SnackRepository extends JpaRepository<Snack, Long> {

    List<Snack> findByEnabled(boolean enabled);
    
}
