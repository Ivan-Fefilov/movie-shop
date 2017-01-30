package com.web.controllers.orders;

import com.web.controllers.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/orders")
public class OrderShowAll extends AbstractServlet {
    @Override
    protected void doGet() throws ServletException, IOException {
        //Orders will be taken from session, with user.getUserOrders

        forward("orders");
    }
}
