package com.myFood.order.infra.persistence.repository;

import com.myFood.order.domain.model.SaleRules;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author reinaldo.locatelli
 */
public interface SaleRulesRepository extends JpaRepository<SaleRules, Long> {

    List<SaleRules> findByEnabled(boolean enabled);
    
}
