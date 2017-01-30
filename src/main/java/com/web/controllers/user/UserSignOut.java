package com.web.controllers.user;

import com.domain.core.User;
import com.web.controllers.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/user-signout")
public class UserSignOut extends AbstractServlet {

    @Override
    protected void doGet() throws ServletException, IOException {
        User user = (User) getSession().getAttribute("user");
        endSession();
        LOGGER.info("user=" + user.getLogin() + "status=done.");
        redirect("/");
    }
}
