package com.myFood.order.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author reinaldo.locatelli
 */
@Entity
@Table(name = "order_sale_rules")
public class OrderSaleRules implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "orderSaleRulesSeq", sequenceName = "order_sale_rules_seq", allocationSize = 1)
    @GeneratedValue(generator = "orderSaleRulesSeq", strategy = GenerationType.AUTO)    
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    
    @JoinColumn(name = "order_snack_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OrderSnack orderSnack;

    public OrderSaleRules() {
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

    public OrderSnack getOrderSnack() {
        return orderSnack;
    }

    public void setOrderSnack(OrderSnack orderSnack) {
        this.orderSnack = orderSnack;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final OrderSaleRules other = (OrderSaleRules) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
