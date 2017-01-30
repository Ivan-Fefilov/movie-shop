package com.web.controllers.orders;

import com.domain.core.ShopItem;
import com.web.controllers.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/cart")
public class CartShow extends AbstractServlet {
    @Override
    protected void doGet() throws ServletException, IOException {

        Map<ShopItem, Integer> orderItems = (Map<ShopItem, Integer>) getSession().getAttribute("orderItems");
        double cost = 0;
        if (orderItems != null) {
            for (Map.Entry<ShopItem, Integer> entry : orderItems.entrySet()) {
                cost += entry.getValue() * Double.parseDouble(entry.getKey().getItem().getPrice());
            }
        }

        setAttribute("cost", cost);
        forward("cart");
    }
}
