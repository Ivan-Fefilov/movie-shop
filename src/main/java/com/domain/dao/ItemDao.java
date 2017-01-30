package com.domain.dao;

import com.domain.core.Item;

public class ItemDao extends GenericDao<Item> {

    public ItemDao() {
        super(Item.class);
    }

    public void deleteItemById(long id) {
        openCurrentSessionWithTransaction();
        Item item = getCurrentSession().get(Item.class, id);
        getCurrentSession().delete(item);
        closeCurrentSessionWithTransaction();
    }
}
