package com.web.controllers.shops;

import com.domain.core.Item;
import com.domain.core.Shop;
import com.domain.core.ShopItem;
import com.domain.dao.ItemDao;
import com.domain.dao.ShopDao;
import com.domain.dao.ShopItemDao;
import com.web.controllers.AbstractServlet;
import com.web.viewmodels.ShopVM;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddShop", urlPatterns = "/shop-add")
public class ShopAdd extends AbstractServlet {
    @Override
    protected void doGet() throws ServletException, IOException {
        forward("shop-add");
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        ShopVM shopBean = new ShopVM(getRequest());

        if (!shopBean.isValidate()) {
            getRequest().setAttribute("shopBean", shopBean);
            forward("shop-add");
            return;
        }

        ShopDao shopDao = new ShopDao();
        Shop newShop = new Shop(shopBean.getName(), shopBean.getAddress());
        shopDao.create(newShop);

        ItemDao itemDao = new ItemDao();
        ShopItemDao shopItemDao = new ShopItemDao();

        List<Item> items = itemDao.getAll();
        List<ShopItem> shopItems = new ArrayList<>();

        for (Item item : items) {
            ShopItem shopItem = new ShopItem(item, newShop, 0);
            shopItemDao.create(shopItem);
        }

        redirect("items-storage?shopId=" + newShop.getId());
    }
}
