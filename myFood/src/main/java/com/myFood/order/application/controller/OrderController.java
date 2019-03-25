package com.myFood.order.application.controller;

import com.myFood.order.domain.model.dto.OrderDTO;
import com.myFood.order.domain.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author reinaldo.djava
 */
@RestController
@RequestMapping(path = "order")
@Api(value = "ordens de venda", tags = "Ordens de Venda")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ApiOperation(value = "Recurso para criar uma nova ordem de venda.")
    public ResponseEntity<?> create(@RequestBody @Valid OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.create(orderDTO), HttpStatus.CREATED);
    }
    
    @GetMapping
    @ApiOperation(value = "Recurso para buscar as últimas 15 ordens lançadas no sistema.")
    public ResponseEntity<?> findLastOrders() {
        return new ResponseEntity<>(orderService.findLastOrders(), HttpStatus.OK);
    }    
}
