package com.web.controllers.orders;

import com.domain.core.Item;
import com.domain.core.ShopItem;
import com.domain.core.User;
import com.domain.dao.ItemDao;
import com.web.controllers.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(urlPatterns = "/cart-add-item")
public class CartAddItem extends AbstractServlet {
    @Override
    protected void doPost() throws ServletException, IOException {

        String itemIdString = getRequest().getParameter("itemId");
        User user = getUser();
        String userName = "guest-user(not logged in)";

        if (user != null) {
            userName = user.getLogin();
        }

        try {
            Long itemId = Long.parseLong(itemIdString);
            ItemDao itemDao = new ItemDao();
            Item item = itemDao.read(itemId);

            //tried get order from session or create new and sort
            Map<ShopItem, Integer> shopCart = (Map<ShopItem, Integer>) getSession().getAttribute("orderItems");
            if (shopCart == null) {
                shopCart = new TreeMap<>(new Comparator<ShopItem>() {
                    @Override
                    public int compare(ShopItem o1, ShopItem o2) {
                        if (o1.getItem().getId() == o2.getItem().getId()) {
                            return Long.compare(o1.getShop().getId(), o2.getShop().getId());
                        }
                        return Long.compare(o1.getItem().getId(), o2.getItem().getId());
                    }
                });
            }

            //get shops with item
            List<ShopItem> shopItems = item.getShopItems();

            boolean itemAddedToCart = false;
            //add new item unit into map
            for (ShopItem shopItem : shopItems) {
                if (shopItem.getCount() != 0) {
                    if (!shopCart.containsKey(shopItem)) {
                        shopCart.put(shopItem, 1);
                        itemAddedToCart = true;
                        break;
                    }
                    int count = shopCart.get(shopItem);
                    if (count < shopItem.getCount()) {
                        shopCart.put(shopItem, count + 1);
                        itemAddedToCart = true;
                        break;
                    }
                }
            }

            //set map back into session
            if (itemAddedToCart) {
                LOGGER.info("user=" + userName + "; status=item added to cart; item id=" + itemIdString);
            } else {
                LOGGER.warn("user=" + userName + "; status=not enough items on storage; item id=" + itemIdString);
            }
            getSession().setAttribute("orderItems", shopCart);
            redirect(getRequest().getHeader("referer"));

        } catch (NumberFormatException e) {
            LOGGER.warn("user=" + userName + "; status=failed; reason=invalid id(not number); id=" + itemIdString);
            forward("smth-wrong");
        } catch (NullPointerException e) {
            LOGGER.warn("user=" + userName + "; status=failed; reason=item not found; id=" + itemIdString);
            forward("smth-wrong");
        }
    }
}

