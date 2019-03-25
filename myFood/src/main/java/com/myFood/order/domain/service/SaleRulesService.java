package com.myFood.order.domain.service;

import com.myFood.order.application.exception.CustomException;
import com.myFood.order.domain.model.SaleRuleTypeEnum;
import com.myFood.order.domain.model.SaleRules;
import com.myFood.order.infra.persistence.repository.SaleRulesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author reinaldo.locatelli
 */
@Service
public class SaleRulesService extends AbstractService<SaleRules, Long> {

    @Autowired
    private SaleRulesRepository saleRulesRepository;

    public List<SaleRules> findAllEnabled() {
        return saleRulesRepository.findByEnabled(true);
    }

    @Override
    protected JpaRepository<SaleRules, Long> getRepository() {
        return saleRulesRepository;
    }

    @Override
    public SaleRules save(SaleRules saleRules) {
        if (saleRules.getRuleType() == null) {
            throw new CustomException("Deve-se informar o tipo da promoção", HttpStatus.BAD_REQUEST);
        }

        if (saleRules.getRuleType().equals(SaleRuleTypeEnum.HAVE_NOT_HAVE) && (saleRules.getIngredientIdTake() != null || saleRules.getTakeQuantity() != null || saleRules.getPayForQuantity() != null)) {
            throw new CustomException("Os campos ingredientIdTake, takeQuantity e payForQuantity pertencem ao outro tipo de regra", HttpStatus.BAD_REQUEST);
        }

        if (saleRules.getRuleType().equals(SaleRuleTypeEnum.TAKE_PAY) && (saleRules.getDiscountPercentValue() != null || saleRules.getIngredientIdHave() != null || saleRules.getIngredientIdNotHave() != null)) {
            throw new CustomException("Os campos discountPercentValue, ingredientIdHave e ingredientIdNotHave pertencem ao outro tipo de regra", HttpStatus.BAD_REQUEST);
        }

        return super.save(saleRules);
    }

}
