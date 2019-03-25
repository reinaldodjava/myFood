package com.myFood.order.domain.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author reinaldo.locatelli
 */
@Entity
@Table(name = "sale_rules")
public class SaleRules implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "saleRuleSeq", sequenceName = "sale_rules_seq", allocationSize = 1)
    @GeneratedValue(generator = "saleRuleSeq", strategy = GenerationType.AUTO)           
    @Column(name = "id")
    @ApiModelProperty(hidden = true)    
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    
    @Basic(optional = false)
    @Column(name = "rule_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private SaleRuleTypeEnum ruleType;
    
    @Column(name = "discount_percent_value")
    private BigDecimal discountPercentValue;
    
    @Column(name = "take_quantity")
    private BigDecimal takeQuantity;
    
    @Column(name = "pay_for_quantity")
    private BigDecimal payForQuantity;
    
    @Basic(optional = false)
    @Column(name = "enabled")
    private boolean enabled;
    
    @Column(name = "not_have_ingredient_id")
    private Long ingredientIdNotHave;
    
    @Column(name = "have_ingredient_id")    
    private Long ingredientIdHave;
    
    @Column(name = "take_ingredient_id")
    private Long ingredientIdTake;

    public SaleRules() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SaleRuleTypeEnum getRuleType() {
        return ruleType;
    }

    public void setRuleType(SaleRuleTypeEnum ruleType) {
        this.ruleType = ruleType;
    }

    public BigDecimal getDiscountPercentValue() {
        return discountPercentValue;
    }

    public void setDiscountPercentValue(BigDecimal discountPercentValue) {
        this.discountPercentValue = discountPercentValue;
    }

    public BigDecimal getTakeQuantity() {
        return takeQuantity;
    }

    public void setTakeQuantity(BigDecimal takeQuantity) {
        this.takeQuantity = takeQuantity;
    }

    public BigDecimal getPayForQuantity() {
        return payForQuantity;
    }

    public void setPayForQuantity(BigDecimal payForQuantity) {
        this.payForQuantity = payForQuantity;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getIngredientIdNotHave() {
        return ingredientIdNotHave;
    }

    public void setIngredientIdNotHave(Long ingredientIdNotHave) {
        this.ingredientIdNotHave = ingredientIdNotHave;
    }

    public Long getIngredientIdHave() {
        return ingredientIdHave;
    }

    public void setIngredientIdHave(Long ingredientIdHave) {
        this.ingredientIdHave = ingredientIdHave;
    }

    public Long getIngredientIdTake() {
        return ingredientIdTake;
    }

    public void setIngredientIdTake(Long ingredientIdTake) {
        this.ingredientIdTake = ingredientIdTake;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SaleRules other = (SaleRules) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
