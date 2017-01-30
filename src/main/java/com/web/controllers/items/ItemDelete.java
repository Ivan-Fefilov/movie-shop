package com.web.controllers.items;

import com.domain.core.Item;
import com.domain.dao.ItemDao;
import com.web.controllers.AbstractServlet;
import com.web.util.FileServiceUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = {"/item-delete"})
public class ItemDelete extends AbstractServlet {

    @Override
    protected void doPost() throws ServletException, IOException {
        Long itemId = null;
        try {
            itemId = Long.parseLong(getRequest().getParameter("id"));
            ItemDao dao = new ItemDao();
            Item item = dao.read(itemId);
            //Delete from db
            dao.deleteItemById(itemId);
            //Delete image from server
            FileServiceUtil.deleteFileFromServer(item.getImage());
            LOGGER.info("user: " + getUser().getLogin() + "; item: " + item.getName() + "; status: done");
        } catch (NumberFormatException e) {
            LOGGER.warn("Item ID is invalid (not number)", e);
            forward("smth-wrong");
            return;
        } catch (NullPointerException e) {
            LOGGER.warn("Item with this ID: " + itemId + "is not found!", e);
            forward("smth-wrong");
            return;
        }
        redirect("/items-control");
    }
}
