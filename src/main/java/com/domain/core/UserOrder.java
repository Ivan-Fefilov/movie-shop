package com.domain.core;

import com.domain.core.enums.PayStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USERORDER")
public class UserOrder extends AbstractModel {

    private User user;
    private Date date;
    private PayStatus payStatus;
    private double cost;
    private List<OrderItem> orderItemList;

    public UserOrder() {
    }

    public UserOrder(User user, List<OrderItem> orderItemList, PayStatus payStatus, double cost) {
        this.user = user;
        this.orderItemList = orderItemList;
        this.payStatus = payStatus;
        this.date = new Date();
        this.cost = cost;

        for (OrderItem orderItem : orderItemList) {
            orderItem.setUserOrder(this);
        }
    }

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public PayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"), nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // TODO: 14.12.2016 Check fetch type and cascade OrderItem
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userOrder", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserOrder)) return false;

        UserOrder userOrder = (UserOrder) o;

        if (Double.compare(userOrder.getCost(), getCost()) != 0) return false;
        if (getUser() != null ? !getUser().equals(userOrder.getUser()) : userOrder.getUser() != null) return false;
        if (getDate() != null ? !getDate().equals(userOrder.getDate()) : userOrder.getDate() != null) return false;
        if (getPayStatus() != userOrder.getPayStatus()) return false;
        return getOrderItemList() != null ? getOrderItemList().equals(userOrder.getOrderItemList()) : userOrder.getOrderItemList() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getUser() != null ? getUser().hashCode() : 0;
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getPayStatus() != null ? getPayStatus().hashCode() : 0);
        temp = Double.doubleToLongBits(getCost());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getOrderItemList() != null ? getOrderItemList().hashCode() : 0);
        return result;
    }
}
