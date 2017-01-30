package com.domain.core;

import javax.persistence.*;

@Entity
@Table(name = "ORDERITEM")
public class OrderItem extends AbstractModel {

    private ShopItem shopItem;
    private UserOrder userOrder;
    private int count;

    public OrderItem() {
    }

    public OrderItem(int count, ShopItem shopItem) {
        this.count = count;
        this.shopItem = shopItem;
    }

    @Column(nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // TODO: 14.12.2016 Check fetch and cascade ShopItem
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shopitem_id", foreignKey = @ForeignKey(name = "fk_shopitem_id"))
    public ShopItem getShopItem() {
        return shopItem;
    }

    public void setShopItem(ShopItem shopItem) {
        this.shopItem = shopItem;
    }

    // TODO: 14.12.2016 Check fetch and cascade UserOrder
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userorder_id", foreignKey = @ForeignKey(name = "fk_userorder_id"))
    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;

        OrderItem orderItem = (OrderItem) o;

        if (getCount() != orderItem.getCount()) return false;
        if (getShopItem() != null ? !getShopItem().equals(orderItem.getShopItem()) : orderItem.getShopItem() != null)
            return false;
        return getUserOrder() != null ? getUserOrder().equals(orderItem.getUserOrder()) : orderItem.getUserOrder() == null;

    }

    @Override
    public int hashCode() {
        int result = getShopItem() != null ? getShopItem().hashCode() : 0;
        result = 31 * result + (getUserOrder() != null ? getUserOrder().hashCode() : 0);
        result = 31 * result + getCount();
        return result;
    }
}
