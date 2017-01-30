package com.web.controllers.orders;

import com.domain.core.ShopItem;
import com.domain.core.User;
import com.domain.dao.ShopItemDao;
import com.web.controllers.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/cart-remove-item")
public class CartRemoveItem extends AbstractServlet {
    @Override
    protected void doPost() throws ServletException, IOException {

        String itemIdString = getRequest().getParameter("shopItemId");
        User user = (User) getRequest().getSession().getAttribute("user");
        String userName = "guest-user(not logged in)";

        if (user != null) {
            userName = user.getLogin();
        }

        try {
            Long shopItemId = Long.parseLong(itemIdString);
            ShopItemDao shopItemDao = new ShopItemDao();
            ShopItem shopItem = shopItemDao.read(shopItemId);

            //tried get order from session or create new and sort
            Map<ShopItem, Integer> orderItems = (Map<ShopItem, Integer>) getSession().getAttribute("orderItems");

            //remove
            if (orderItems != null) {
                int count = orderItems.get(shopItem);
                if (count > 1) {
                    orderItems.put(shopItem, count - 1);
                } else {
                    orderItems.remove(shopItem);
                }
            }
            //set map back into session
            getSession().setAttribute("orderItems", orderItems);
            redirect(getRequest().getHeader("referer"));

        } catch (NumberFormatException e) {
            LOGGER.warn("user=" + userName + "; status=failed; reason=invalid id(not number); id=" + itemIdString);
            forward("smth-wrong");
        } catch (NullPointerException e) {
            LOGGER.warn("user=" + userName + "; status=failed; reason=shopItem not found; id=" + itemIdString);
            forward("smth-wrong");
        }
    }
}

