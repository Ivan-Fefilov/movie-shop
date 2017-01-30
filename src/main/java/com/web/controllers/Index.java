package com.web.controllers;


import com.domain.core.Item;
import com.domain.dao.ItemDao;
import com.web.viewmodels.Pagination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Index", urlPatterns = {"/index"})
public class Index extends AbstractServlet {

    @Override
    protected void doGet() throws ServletException, IOException {
        String str = getRequest().getParameter("page");
        Integer page = null;

        try {
            page = Integer.parseInt(str);
        } catch (NumberFormatException ignore) {

        }

        if (page == null) {
            page = 1;
        }

        ItemDao dao = new ItemDao();
        List<Item> items = dao.getAll();
        if (!items.isEmpty()) {
            int pageSize = 8;
            double numberOfPages = (double) items.size() / pageSize;
            int count = (int) Math.ceil(numberOfPages);
            Pagination pagination = new Pagination(page, count, "index");
            int numberOfItemsOnPage = page * pageSize > items.size() ? items.size() : page * pageSize;
            items = items.subList((page - 1) * pageSize, numberOfItemsOnPage);
            setAttribute("pagination", pagination);
        }
        setAttribute("items", items);
        forward("index");
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        redirect("/");
    }
}
