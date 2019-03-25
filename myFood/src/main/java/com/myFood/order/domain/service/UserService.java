package com.myFood.order.domain.service;

import com.myFood.order.application.exception.CustomException;
import com.myFood.order.application.util.Token;
import com.myFood.order.domain.model.User;
import com.myFood.order.domain.model.dto.UserPasswordDTO;
import com.myFood.order.infra.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author reinaldo.locatelli
 */
@Service
public class UserService extends AbstractService<User, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    public String authenticate(UserPasswordDTO userPasswordDTO) {

        User user = userRepository.findByLoginAndPasswordAndEnabled(userPasswordDTO.getUser(), userPasswordDTO.getPassword(), true);

        if (user == null) {
            throw new CustomException("Não autenticado", HttpStatus.UNAUTHORIZED);
        }

        return Token.createToken(user.getId(), user.getLogin());
    }

}
