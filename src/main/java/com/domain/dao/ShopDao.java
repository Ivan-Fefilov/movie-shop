package com.domain.dao;

import com.domain.core.Shop;

public class ShopDao extends GenericDao<Shop> {

    public ShopDao() {
        super(Shop.class);
    }

    public Shop getShopWithItems(Long id) {
        openCurrentSession();
        Shop shop = (Shop) getCurrentSession()
                .createQuery("select shop from Shop shop left join fetch shop.shopItems items where shop.id=:shopId")
                .setParameter("shopId", id)
                .uniqueResult();
        closeCurrentSession();
        return shop;
    }
}
