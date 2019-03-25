package com.myFood.order.application.controller;

import com.myFood.order.application.util.MD5;
import com.myFood.order.domain.model.dto.UserPasswordDTO;
import com.myFood.order.domain.service.OrderService;
import com.myFood.order.domain.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author reinaldo.djava
 */
@RestController
@RequestMapping(path = "auth")
@Api(value = "autenticação", tags = "Autenticação")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PermitAll
    @PostMapping
    @ApiOperation(value = "Recurso para autenticar o usuário.")        
    public ResponseEntity<?> authenticate(@RequestBody @Valid UserPasswordDTO userPasswordDTO) {
        userPasswordDTO.setPassword(MD5.encryptMD5(userPasswordDTO.getPassword()));
        
        return new ResponseEntity<>(userService.authenticate(userPasswordDTO), HttpStatus.OK);
    }

}
