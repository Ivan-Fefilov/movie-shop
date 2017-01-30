package com.web.controllers.user;

import com.domain.core.User;
import com.domain.dao.UserDao;
import com.web.controllers.AbstractServlet;
import com.web.viewmodels.LoginVM;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/user-login"})
public class UserLogin extends AbstractServlet {

    @Override
    protected void doGet() throws ServletException, IOException {
        forward("user-login");
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        LoginVM loginVM = new LoginVM(getRequest());

        if (!loginVM.isValidate()) {
            LOGGER.warn("login " + "status=failed. reason=login's fields are not valid");
            getRequest().setAttribute("loginVM", loginVM);
            forward("user-login");
            return;
        }

        UserDao dao = new UserDao();
        User user = dao.getUserByLogin(loginVM.getLogin());
        String mdPassword = DigestUtils.md5Hex(loginVM.getPassword());

        Locale locale = (Locale) getSession().getAttribute("locale");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("global", locale);

        boolean userExist = user != null;
        boolean correctPassword = false;

        //Check user login
        if (!userExist) {
            LOGGER.warn("user=" + loginVM.getLogin() + "; status=failed; reason=not exist");
            loginVM.setError("login", resourceBundle.getString("login.error.login"));
        } else {
            correctPassword = Objects.equals(user.getPassword(), mdPassword);
        }

        //Check user password
        if (userExist && !correctPassword) {
            LOGGER.warn("user=" + loginVM.getLogin() + "; status=failed; reason=wrong password:" + mdPassword);
            loginVM.setError("password", resourceBundle.getString("login.error.password"));
        }

        if (!userExist || !correctPassword) {
            LOGGER.warn("user=" + loginVM.getLogin() + "; status=failed; reason=wrong password: " + mdPassword + " or user is not exist:");
            getRequest().setAttribute("loginVM", loginVM);
            forward("/user-login");
            return;
        }

        LOGGER.info("user=" + user.getLogin() + "; status=done");
        saveUserToSession(user);
        redirect("/");
    }
}
