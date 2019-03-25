package com.myFood.order.application.controller;

import com.myFood.order.domain.model.SaleRules;
import com.myFood.order.domain.service.SaleRulesService;
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
@RequestMapping(path = "sale-rules")
@Api(value = "regras de promoções", tags = "Regras de Promoções")
public class SaleRulesController {

    @Autowired
    private SaleRulesService saleRulesService;

    @GetMapping
    @ApiOperation(value = "Recurso para buscar todas as regras de promoções cadastradas.")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(saleRulesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("enabled")
    @ApiOperation(value = "Recurso para buscar todas as regras de promoções ativas.")
    public ResponseEntity<?> findAllEnabled() {
        return new ResponseEntity<>(saleRulesService.findAllEnabled(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Recurso para criar uma nova regras de promoção. Existem 2 tipos de regras, a 'HAVE_NOT_HAVE' e a 'TAKE_PAY'. Quando for cadastrar uma regra do tipo 'HAVE_NOT_HAVE' NÃO precisa informar os seguintes atributos ingredientIdTake, takeQuantity e payForQuantity. Quando a regra for do tipo 'TAKE_PAY' NÃO precisa informar os atributos discountPercentValue, ingredientIdHave e ingredientIdNotHave")
    public ResponseEntity<?> create(@RequestBody @Valid SaleRules saleRules) {
        return new ResponseEntity<>(saleRulesService.save(saleRules), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Recurso para alterar uma regras de promoção. Existem 2 tipos de regras, a 'HAVE_NOT_HAVE' e a 'TAKE_PAY'. Quando for cadastrar uma regra do tipo 'HAVE_NOT_HAVE' NÃO precisa informar os seguintes atributos ingredientIdTake, takeQuantity e payForQuantity. Quando a regra for do tipo 'TAKE_PAY' NÃO precisa informar os atributos discountPercentValue, ingredientIdHave e ingredientIdNotHave")
    public ResponseEntity<?> update(@RequestBody @Valid SaleRules saleRules, @PathVariable("id") Long id) {
        saleRules.setId(id);
        return new ResponseEntity<>(saleRulesService.save(saleRules), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Recurso para remover uma regras de promoção.")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        saleRulesService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
