package com.web.controllers.items;

import com.domain.core.Item;
import com.domain.core.Shop;
import com.domain.core.ShopItem;
import com.domain.dao.ItemDao;
import com.domain.dao.ShopDao;
import com.domain.dao.ShopItemDao;
import com.web.controllers.AbstractServlet;
import com.web.util.FileServiceUtil;
import com.web.viewmodels.ItemVM;
import org.hibernate.exception.ConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/item-add"})
@MultipartConfig
public class ItemAdd extends AbstractServlet {

    @Override
    protected void doGet() throws ServletException, IOException {
        forward("item-add");
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        ItemVM itemVM = new ItemVM(getRequest());

        if (!itemVM.isValidate()) {
            LOGGER.info("Some fields- null or empty");
            setAttribute("itemVM", itemVM);
            forward("item-add");
            return;
        }

        Locale locale = (Locale) getRequest().getSession().getAttribute("locale");
        ResourceBundle bundle = ResourceBundle.getBundle("global", locale);
        //Get file from request
        Part filePart = getRequest().getPart("image-file");
        //Check file
        if (!FileServiceUtil.validateFileFromServlet(filePart)) {
            LOGGER.warn("Image: " + filePart + "- null or not available");
            String errorMessage = bundle.getString("admin.item.error.image");
            itemVM.getErrors().put("image", errorMessage);
            setAttribute("itemVM", itemVM);
            forward("item-add");
            return;
        }
        //Write file to server
        try {
            String resultFileName = FileServiceUtil.writeFileToServer(filePart);
            itemVM.setImageName(resultFileName);
        } catch (IOException e) {
            LOGGER.warn("filePart= " + filePart, e);
            forward("smth-wrong");
            return;
        }
        //convert vm to item
        Item item = itemVM.toItemModel();
        //create item
        try {
            createNewItem(item);
            LOGGER.info("user: " + getUser().getLogin() + "; item: " + item.getName() + "; status: done");
        } catch (ConstraintViolationException e) {
            LOGGER.warn("user: " + getUser().getLogin() + "; item: " + item.getName() + "; error= " + e.getMessage(), e);
            String errorMessage = bundle.getString("admin.item.error.create");
            itemVM.getErrors().put("createError", errorMessage);
            setAttribute("itemVM", itemVM);
            forward("item-add");
            return;
        }

        redirect("/items-control");

    }

    private void createNewItem(Item item) throws ConstraintViolationException {
        ItemDao itemDao = new ItemDao();
        itemDao.create(item);
        putItemOnAllShops(item);
    }

    private void putItemOnAllShops(Item item) {
        ShopDao shopDao = new ShopDao();
        List<Shop> shopList = shopDao.getAll();
        ShopItemDao shopItemDao = new ShopItemDao();
        for (Shop shop : shopList) {
            ShopItem shopItem = new ShopItem(item, shop, 0);
            shopItemDao.create(shopItem);
        }
    }
}
