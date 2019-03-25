package com.myFood.order.application.controller;

import com.myFood.order.domain.model.Ingredient;
import com.myFood.order.domain.service.IngredientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping(path = "ingredient")
@Api(value = "ingredientes", tags = "Ingredientes")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    @ApiOperation(value = "Recurso para buscar todos o ingredientes cadastrados.")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(ingredientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("enabled")
    @ApiOperation(value = "Recurso para buscar todos o ingredientes ativos.")
    public ResponseEntity<?> findAllEnabled() {
        return new ResponseEntity<>(ingredientService.findAllEnabled(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Recurso para criar um novo ingrediente.")
    public ResponseEntity<?> create(@RequestBody @Valid Ingredient ingredient) {
        return new ResponseEntity<>(ingredientService.save(ingredient), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Recurso para alterar um ingrediente.")
    public ResponseEntity<?> update(@RequestBody @Valid Ingredient ingredient, @PathVariable("id") Long id) {
        ingredient.setId(id);
        return new ResponseEntity<>(ingredientService.save(ingredient), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Recurso para remover um ingrediente. Esta ação só é possível caso o ingrediente não tiver vínculo com outros registros.")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        ingredientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
