package com.jack.karate.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Order {

    private Long id;

    private String orderDetail;

    public Order() {
    }

    public Order(Long id, String orderDetail) {
        this.id = id;
        this.orderDetail = orderDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equal(id, order.id) &&
                Objects.equal(orderDetail, order.orderDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, orderDetail);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("orderDetail", orderDetail)
                .toString();
    }
}
