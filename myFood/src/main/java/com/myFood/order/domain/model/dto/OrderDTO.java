package com.myFood.order.domain.model.dto;

import com.myFood.order.domain.model.Order;
import com.myFood.order.domain.model.OrderSnack;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author reinaldo.locatelli
 */
public class OrderDTO implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(hidden = true)
    private Date date;

    @ApiModelProperty(hidden = true)
    private BigDecimal price;

    private List<OrderSnackDTO> snacks = new ArrayList<>();

    public OrderDTO() {
    }

    
    public OrderDTO(Order order) {
        setOrder(order);
    }

    @ApiModelProperty(hidden = true)
    public void setOrder(Order order) {
        this.id = order.getId();
        this.date=order.getDate();
        this.price=order.getPrice();
    }

    public List<OrderSnackDTO> getSnacks() {
        return snacks;
    }

    public void setSnacks(List<OrderSnackDTO> snacks) {
        this.snacks = snacks;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ApiModelProperty(hidden = true)
    public void setOrderSnack(List<OrderSnack> orderSnacks) {
        orderSnacks.stream().forEach(os-> {
            this.snacks.add(new OrderSnackDTO(os));
        });
        
    }

}
