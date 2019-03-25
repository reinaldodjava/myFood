package com.myFood.order.domain.model;

import com.myFood.order.domain.model.dto.SnackIngredientDTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author reinaldo.locatelli
 */
@Entity
@Table(name = "snack_ingredient")
public class SnackIngredient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "snackIngredientSeq", sequenceName = "snack_ingredient_seq", allocationSize = 1)
    @GeneratedValue(generator = "snackIngredientSeq", strategy = GenerationType.AUTO)    
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "quantity")
    private BigDecimal quantity;
    
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ingredient ingredient;
    
    @JoinColumn(name = "snack_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Snack snack;

    public SnackIngredient() {
    }

    public SnackIngredient(Long snackId, Ingredient ingredient, BigDecimal quantity) {
        this.ingredient = ingredient;
        this.snack = new Snack(snackId);
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Snack getSnack() {
        return snack;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final SnackIngredient other = (SnackIngredient) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
