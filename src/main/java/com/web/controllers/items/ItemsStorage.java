package com.web.controllers.items;

import com.domain.core.Shop;
import com.domain.core.ShopItem;
import com.domain.dao.ShopDao;
import com.domain.dao.ShopItemDao;
import com.web.controllers.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShopItemsAdmin", urlPatterns = "/items-storage")
public class ItemsStorage extends AbstractServlet {

    @Override
    protected void doGet() throws ServletException, IOException {
        Long shopId = null;
        try {
            shopId = Long.parseLong(getRequest().getParameter("shopId"));
        } catch (NumberFormatException ignore) {
        }

        ShopDao shopDao = new ShopDao();
        //Select all shops without fetching items
        List<Shop> shops = shopDao.getAll();
        setAttribute("shopsSelect", shops);

        if (shopId == null && shops.size() != 0) {
            shopId = shops.get(0).getId();
        }

        //Fetch items for one web
        Shop shop = shopDao.getShopWithItems(shopId);
        setAttribute("shop", shop);

        forward("items-storage-control");
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        Long itemId = null;
        Long shopId = null;
        Integer count;

        try {
            itemId = Long.parseLong(getRequest().getParameter("itemId"));
            count = Integer.parseInt(getRequest().getParameter("itemCount"));
            shopId = Long.parseLong(getRequest().getParameter("shopId"));

            ShopItemDao shopItemDao = new ShopItemDao();
            ShopItem shopItem = shopItemDao.read(itemId);

            shopItem.setCount(count);
            shopItemDao.update(shopItem);

            setAttribute("editedItem", shopItem);
            putShopToRequestFromDb(shopItem.getShop().getId());

        } catch (NumberFormatException e) {
            LOGGER.warn("Some of fields values is invalid (not number)", e);
            forward("smth-wrong");
            return;
        } catch (NullPointerException e) {
            LOGGER.warn("Item with ID: " + itemId + " is not found!", e);
            getRequest().setAttribute("error", "Something went wrong!");
            putShopToRequestFromDb(shopId);
            forward("items-storage-control");
            return;
        }
        forward("items-storage-control");
    }

    private void putShopToRequestFromDb(Long shopId) {
        ShopDao shopDao = new ShopDao();

        //Fetch items for one web
        Shop shop = shopDao.getShopWithItems(shopId);
        setAttribute("shop", shop);

        //Select all shops without fetching items
        List<Shop> shops = shopDao.getAll();
        setAttribute("shopsSelect", shops);
    }
}
