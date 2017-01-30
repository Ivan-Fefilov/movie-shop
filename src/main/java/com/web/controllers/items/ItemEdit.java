package com.web.controllers.items;

import com.domain.core.Item;
import com.domain.dao.ItemDao;
import com.web.controllers.AbstractServlet;
import com.web.util.FileServiceUtil;
import com.web.viewmodels.ItemVM;
import org.hibernate.exception.ConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/item-edit"})
@MultipartConfig
public class ItemEdit extends AbstractServlet {

    @Override
    protected void doGet() throws ServletException, IOException {
        Long itemId = null;
        Item item = null;
        try {
            itemId = Long.parseLong(getRequest().getParameter("id"));
            ItemDao itemDao = new ItemDao();
            item = itemDao.read(itemId);
        } catch (NumberFormatException e) {
            LOGGER.warn("Item ID is invalid (not number)", e);
            forward("smth-wrong");
            return;
        } catch (NullPointerException e) {
            LOGGER.warn("Item with this ID: " + itemId + "is not found!", e);
            forward("smth-wrong");
            return;
        }
        //convert item to VM
        ItemVM itemVM = new ItemVM(item);
        itemVM.setId(item.getId());
        setAttribute("itemVM", itemVM);

        forward("item-edit");
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        ItemVM itemVM = new ItemVM(getRequest());

        try {
            itemVM.setId(Long.parseLong(getRequest().getParameter("id")));
        } catch (NumberFormatException e) {
            LOGGER.warn("Item ID is invalid (not number)", e);
            forward("smth-wrong");
            return;
        }

        if (!itemVM.isValidate()) {
            setAttribute("itemVM", itemVM);
            forward("item-edit");
            return;
        }

        ItemDao itemDao = new ItemDao();
        Item editedItem = itemDao.read(itemVM.getId());

        //Get file and write to folder
        Part filePart = getRequest().getPart("image-file");
        //If there isn't file in input form, do nothing
        //Else = > write new file, replace fileName in itemVM.
        if (FileServiceUtil.validateFileFromServlet(filePart)) {
            //Write new file
            String resultFileName = FileServiceUtil.writeFileToServer(filePart);
            //Delete old file
            FileServiceUtil.deleteFileFromServer(editedItem.getImage());
            //Change name
            itemVM.setImageName(resultFileName);
        }

        Item item = itemVM.toItemModel();
        item.setId(editedItem.getId());

        try {
            itemDao.update(item);
            LOGGER.info("user: " + getUser().getLogin() + "; item: " + item.getName() + "; status: done");
        } catch (ConstraintViolationException e) {
            LOGGER.warn("user: " + getUser().getLogin() + "; item: " + item.getName() + "; error= " + e.getMessage(), e);
            Locale locale = (Locale) getRequest().getSession().getAttribute("locale");
            ResourceBundle bundle = ResourceBundle.getBundle("global", locale);
            String errorMessage = bundle.getString("admin.item.error.create");
            itemVM.getErrors().put("createError", errorMessage);
            setAttribute("itemVM", itemVM);
            forward("item-edit");
            return;
        }

        redirect("/items-control");
    }


}
