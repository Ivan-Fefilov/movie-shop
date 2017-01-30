package com.web.controllers.orders;

import com.domain.core.User;
import com.domain.core.UserOrder;
import com.domain.dao.UserOrderDao;
import com.web.controllers.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/order")
public class OrderShowOne extends AbstractServlet {
    @Override
    protected void doGet() throws ServletException, IOException {

        String orderIdString = getRequest().getParameter("orderId");
        User user = (User) getRequest().getSession().getAttribute("user");
        String userName = "guest-user(not logged in)";

        if (user != null) {
            userName = user.getLogin();
        }

        try {
            Long orderId = Long.parseLong(orderIdString);

            UserOrderDao userOrderDao = new UserOrderDao();
            UserOrder order = userOrderDao.read(orderId);

            LOGGER.info("user=" + userName + "; status=done; user opened order with id=" + orderIdString);
            setAttribute("order", order);
            forward("order");

        } catch (NumberFormatException e) {
            LOGGER.warn("user=" + userName + "; status=failed; reason=invalid id(not number); id=" + orderIdString);
            forward("smth-wrong");
        } catch (NullPointerException e) {
            LOGGER.warn("user=" + userName + "; status=failed; reason=order not found; id=" + orderIdString);
            forward("smth-wrong");
        }
    }
}
