package com.web.controllers.orders;

import com.domain.core.User;
import com.web.controllers.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/cart-clear")
public class CartClear extends AbstractServlet {
    @Override
    protected void doPost() throws ServletException, IOException {

        User user = (User) getRequest().getSession().getAttribute("user");
        String userName = "guest-user(not logged in)";

        if (user != null) {
            userName = user.getLogin();
        }

        LOGGER.info("user=" + userName + "; status=done; cart cleared by user");
        getSession().removeAttribute("orderItems");
        redirect("/cart");
    }
}
