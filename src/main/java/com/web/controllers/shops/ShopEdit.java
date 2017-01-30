package com.web.controllers.shops;

import com.domain.core.Shop;
import com.domain.dao.ShopDao;
import com.web.controllers.AbstractServlet;
import com.web.viewmodels.ShopVM;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "EditShop", urlPatterns = "/shop-edit")
public class ShopEdit extends AbstractServlet {
    @Override
    protected void doGet() throws ServletException, IOException {
        Long shopId = null;

        try {
            shopId = Long.parseLong(getRequest().getParameter("shopId"));
        } catch (NumberFormatException ignore) {
        }

        if (shopId == null) {
            //Error page?
        }

        ShopDao shopDao = new ShopDao();
        Shop shop = shopDao.read(shopId);

        ShopVM shopBean = new ShopVM(shop.getId(), shop.getName(), shop.getAddress());
        getRequest().setAttribute("shopBean", shopBean);
        forward("shop-edit");
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        Long shopId = null;

        try {
            shopId = Long.parseLong(getRequest().getParameter("id"));
        } catch (NumberFormatException ignore) {
        }

        if (shopId == null) {
            //Error page?
        }

        ShopVM shopBean = new ShopVM(getRequest());

        if (!shopBean.isValidate()) {
            getRequest().setAttribute("shopBean", shopBean);
            forward("shop-edit");
            return;
        }

        ShopDao shopDao = new ShopDao();
        Shop editedShop = shopDao.read(shopId);

        editedShop.setName(shopBean.getName());
        editedShop.setAddress(shopBean.getAddress());
        shopDao.update(editedShop);

        redirect("items-storage?shopId=" + editedShop.getId());
    }
}
