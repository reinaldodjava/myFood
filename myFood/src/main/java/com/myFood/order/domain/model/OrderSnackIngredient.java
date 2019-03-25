package com.myFood.order.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
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
@Table(name = "order_snack_ingredient")
public class OrderSnackIngredient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "orderSnackIngredientSeq", sequenceName = "order_snack_ingredient_seq", allocationSize = 1)
    @GeneratedValue(generator = "orderSnackIngredientSeq", strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "quantity")
    private BigDecimal quantity;

    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    @Basic(optional = false)
    @Column(name = "price")
    private BigDecimal price;

    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ingredient ingredient;

    @JoinColumn(name = "order_snack_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OrderSnack orderSnack;

    public OrderSnackIngredient() {
    }

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public BigDecimal getTotal() {
        return price.multiply(quantity);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public OrderSnack getOrderSnack() {
        return orderSnack;
    }

    public void setOrderSnack(OrderSnack orderSnack) {
        this.orderSnack = orderSnack;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final OrderSnackIngredient other = (OrderSnackIngredient) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
