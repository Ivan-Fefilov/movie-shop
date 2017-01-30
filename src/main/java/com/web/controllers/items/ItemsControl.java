package com.web.controllers.items;

import com.domain.core.Item;
import com.domain.dao.ItemDao;
import com.web.controllers.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/items-control")
public class ItemsControl extends AbstractServlet {
    @Override
    protected void doGet() throws ServletException, IOException {
        ItemDao dao = new ItemDao();
        List<Item> items = dao.getAll();
        setAttribute("items", items);
        forward("items-control");
    }
}
