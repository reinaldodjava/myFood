package com.myFood.order.domain.model;

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
@Table(name = "order_snack")
public class OrderSnack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "orderSnackSeq", sequenceName = "order_snack_seq", allocationSize = 1)
    @GeneratedValue(generator = "orderSnackSeq", strategy = GenerationType.AUTO)        
    @Column(name = "id")    
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "snack_description")
    private String snackDescription;
    
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Order order;
    
    @JoinColumn(name = "snack_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Snack snack;
    
    @Basic(optional = false)
    @Column(name = "price")
    private BigDecimal price;    

    public OrderSnack() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSnackDescription() {
        return snackDescription;
    }

    public void setSnackDescription(String snackDescription) {
        this.snackDescription = snackDescription;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Snack getSnack() {
        return snack;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final OrderSnack other = (OrderSnack) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
