package com.web.controllers.items;

import com.domain.core.Item;
import com.domain.dao.ItemDao;
import com.web.controllers.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/item-show")
public class ItemShow extends AbstractServlet {

    @Override
    protected void doGet() throws ServletException, IOException {
        Long itemId = null;
        try {
            itemId = Long.parseLong(getRequest().getParameter("itemId"));
            ItemDao itemDao = new ItemDao();
            Item item = itemDao.read(itemId);
            setAttribute("item", item);
        } catch (NumberFormatException e) {
            LOGGER.warn("Item ID is invalid (not number)", e);
            forward("smth-wrong");
            return;
        } catch (NullPointerException e) {
            LOGGER.warn("Item with this ID: " + itemId + "is not found!", e);
            forward("smth-wrong");
            return;
        }
        forward("item");
    }
}
