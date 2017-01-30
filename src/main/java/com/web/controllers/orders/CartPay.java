package com.web.controllers.orders;

import com.domain.core.OrderItem;
import com.domain.core.ShopItem;
import com.domain.core.User;
import com.domain.core.UserOrder;
import com.domain.core.enums.PayStatus;
import com.domain.dao.ShopItemDao;
import com.domain.dao.UserOrderDao;
import com.web.controllers.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/cart-pay")
public class CartPay extends AbstractServlet {
    @Override
    protected void doPost() throws ServletException, IOException {

        User user = (User) getRequest().getSession().getAttribute("user");
        String userName = "guest-user(not logged in)";

        if (user == null) {
            LOGGER.info("user=" + userName + "; status=failed; User have to be logged in to pay.");
            redirect("/user-login");
            return;
        }

        userName = user.getLogin();

        // TODO: 17.12.2016 Refactor, replace Map with class(VM or Model)
        //Get data from session
        Map<ShopItem, Integer> cartItems = (Map<ShopItem, Integer>) getSession().getAttribute("orderItems");
        List<OrderItem> orderItems = convertToOrderItems(cartItems);

        ShopItemDao shopItemDao = new ShopItemDao();
        List<ShopItem> storageItems = new ArrayList<>();
        boolean orderIsValid = true;
        int orderCost = 0;

        // Check storage have enough items ont it
        // Calculate order cost
        for (OrderItem orderItem : orderItems) {
            ShopItem storageItem = shopItemDao.read(orderItem.getShopItem().getId());

            int storageItemCount = storageItem.getCount();
            int orderItemCount = orderItem.getShopItem().getCount();
            int leftOnStorage = storageItemCount - orderItemCount;

            if (leftOnStorage < 0) {
                orderIsValid = false;
                break;
            }

            storageItem.setCount(leftOnStorage);
            storageItems.add(storageItem);

            Double itemPrice = Double.parseDouble(orderItem.getShopItem().getItem().getPrice());
            orderCost += orderItem.getCount() * itemPrice;
        }

        boolean enoughMoney = true;
        if (!enoughMoney) {
            LOGGER.warn("user=" + userName + "; status=failed; reason=not enough money; cost=" + orderCost);
            getSession().setAttribute("moneyError", "Not enough money!");
            redirect("/orders");
            return;
        }

        if (!orderIsValid) {
            LOGGER.warn("user=" + userName + "; status=failed; reason=not enough items on storage");
            getSession().setAttribute("moneyError", "Not enough items on storages!");
            redirect("/orders");
            return;
        }

        //Save data to DB
        UserOrderDao userOrderDao = new UserOrderDao();
        UserOrder userOrder = new UserOrder(user, orderItems, PayStatus.PAY, orderCost);
        userOrderDao.create(userOrder);

        for (ShopItem storageItem : storageItems) {
            shopItemDao.update(storageItem);
        }

        //Update session
        LOGGER.info("user=" + userName + "; status=done; orderId=" + userOrder.getId());
        user.getUserOrderList().add(userOrder);
        cartItems.clear();
        getSession().setAttribute("orderItems", cartItems);
        redirect("/orders");
    }

    private List<OrderItem> convertToOrderItems(Map<ShopItem, Integer> map) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (Map.Entry<ShopItem, Integer> entry : map.entrySet()) {
            OrderItem cartItem = new OrderItem(entry.getValue(), entry.getKey());
            orderItems.add(cartItem);
        }

        return orderItems;
    }
}
