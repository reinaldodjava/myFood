package com.myFood.order.application.controller;

import com.myFood.order.domain.model.dto.CrudSnackDTO;
import com.myFood.order.domain.service.SnackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author reinaldo.djava
 */
@RestController
@RequestMapping(path = "snack")
@Api(value = "lanches", tags = "Lanches")
public class SnackController {

    @Autowired
    private SnackService snackService;

    @GetMapping
    @ApiOperation(value = "Recurso para buscar todos os lanches cadastrados.")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(snackService.findAllSnack(), HttpStatus.OK);
    }

    @GetMapping("enabled")
    @ApiOperation(value = "Recurso para buscar todos os lanches ativos.")
    public ResponseEntity<?> findAllEnabled() {
        return new ResponseEntity<>(snackService.findAllEnabled(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Recurso para criar um novo lanche.")
    public ResponseEntity<?> create(@RequestBody @Valid CrudSnackDTO crudSnackDTO) {
        return new ResponseEntity<>(snackService.create(crudSnackDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Recurso para alterar um lanche.")
    public ResponseEntity<?> update(@RequestBody @Valid CrudSnackDTO crudSnackDTO, @PathVariable("id") Long id) {
        crudSnackDTO.setId(id);
        return new ResponseEntity<>(snackService.update(crudSnackDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Recurso para remover um lanche.  Esta ação só é possível caso o ingrediente não tiver vínculo com outros registros.")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        snackService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
