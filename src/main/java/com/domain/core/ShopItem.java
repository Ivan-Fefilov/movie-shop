package com.domain.core;

import javax.persistence.*;


@Entity
@Table(name = "SHOP_ITEM")
public class ShopItem extends AbstractModel {

    private Item item;
    private Shop shop;
    private int count;

    protected ShopItem() {
    }

    public ShopItem(Item item, Shop shop, int count) {
        this.item = item;
        this.shop = shop;
        this.count = count;
    }

    @ManyToOne
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_item_id"), nullable = false)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne
    @JoinColumn(name = "shop_id", foreignKey = @ForeignKey(name = "fk_shop_id"), nullable = false)
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Column(nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopItem)) return false;

        ShopItem shopItem = (ShopItem) o;

        if (!getItem().equals(shopItem.getItem())) return false;
        return getShop().equals(shopItem.getShop());

    }

    @Override
    public int hashCode() {
        int result = getItem().hashCode();
        result = 31 * result + getShop().hashCode();
        return result;
    }
}
