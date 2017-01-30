package com.web.controllers.shops;

import com.domain.core.Shop;
import com.domain.dao.ShopDao;
import com.web.controllers.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "DeleteShop", urlPatterns = "/shop-delete")
public class ShopDelete extends AbstractServlet {
    @Override
    protected void doPost() throws ServletException, IOException {
        Long shopId = null;

        try {
            shopId = Long.parseLong(getRequest().getParameter("shopId"));
            ShopDao shopDao = new ShopDao();
            Shop shopToDelete = shopDao.getShopWithItems(shopId);
            shopDao.delete(shopToDelete);

        } catch (NumberFormatException | NullPointerException ignore) {
            // TODO: 12.12.2016 add logger
        }

        redirect("items-storage");
    }
}
