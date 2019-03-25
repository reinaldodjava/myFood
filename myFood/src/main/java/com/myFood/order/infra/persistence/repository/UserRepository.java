package com.myFood.order.infra.persistence.repository;

import com.myFood.order.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author reinaldo.locatelli
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginAndPasswordAndEnabled(String login, String password, boolean enabled);
    
}
