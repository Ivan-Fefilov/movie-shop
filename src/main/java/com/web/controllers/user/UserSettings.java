package com.web.controllers.user;

import com.domain.core.User;
import com.domain.core.UserAddress;
import com.domain.core.UserInfo;
import com.domain.dao.UserDao;
import com.web.controllers.AbstractServlet;
import com.web.util.ValidationUtil;
import com.web.viewmodels.UserSettingsVM;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/user-settings"})
public class UserSettings extends AbstractServlet {

    @Override
    protected void doGet() throws ServletException, IOException {
        User user = (User) getSession().getAttribute("user");
        UserSettingsVM userSettingsVM = new UserSettingsVM(user);
        getRequest().setAttribute("userSettingsVM", userSettingsVM);
        forward("/user-settings");
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        User user = (User) getSession().getAttribute("user");

        if (user == null) {
            LOGGER.warn("status=failed; reason=user is null");
            redirect("/user-login");
        }

        UserSettingsVM userSettingsVM = new UserSettingsVM(getRequest());
        Locale locale = (Locale) getSession().getAttribute("locale");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("global", locale);

        UserDao dao = new UserDao();
        boolean emailUsed = dao.getUserByEmail(userSettingsVM.getEmail()) != null && !getUser().getInfo().getEmail().equals(userSettingsVM.getEmail());

        if (!userSettingsVM.isValidate()) {
            LOGGER.warn("status=failed; reason=usersetting's fields is not valid");
            getRequest().setAttribute("userSettingsVM", userSettingsVM);
            forward("user-settings");
            return;
        }

        if (emailUsed) {
            LOGGER.warn("user=" + userSettingsVM.getEmail() + "status=failed. reason=email already exist");
            userSettingsVM.setError("email", resourceBundle.getString("registration.error.emailNotAvailable"));
            getRequest().setAttribute("userSettingsVM", userSettingsVM);
            forward("user-settings");
            return;
        }

        if (!ValidationUtil.isEmailValid(userSettingsVM.getEmail())) {
            LOGGER.warn("user=" + userSettingsVM.getEmail() + "status=failed. reason=email is not correct");
            userSettingsVM.setError("email", resourceBundle.getString("registration.error.emailNotCorrect"));
            getRequest().setAttribute("userSettingsVM", userSettingsVM);
            forward("user-settings");
            return;
        }

        if (!ValidationUtil.isPhoneValid(userSettingsVM.getPhoneNumber())) {
            LOGGER.warn("user=" + userSettingsVM.getPhoneNumber() + "status=failed. reason=phone is not correct");
            userSettingsVM.setError("phoneNumber", resourceBundle.getString("registration.error.phoneNotCorrect"));
            getRequest().setAttribute("userSettingsVM", userSettingsVM);
            forward("user-settings");
            return;
        }

        dao.update(getUserFromBean(userSettingsVM));

        getRequest().setAttribute("userSettingsVM", userSettingsVM);
        forward("user-settings");
    }

    private User getUserFromBean(UserSettingsVM settingsBean) {
        User user = (User) getSession().getAttribute("user");

        UserAddress address = new UserAddress(settingsBean.getCountry(), settingsBean.getCity(), settingsBean.getStreet(), settingsBean.getHomeNumber(), settingsBean.getApartmentNumber(), settingsBean.getPostIndex());
        address.setId(user.getInfo().getAddress().getId());

        UserInfo info = new UserInfo(settingsBean.getFirstName(), settingsBean.getLastName(), settingsBean.getPhoneNumber(), settingsBean.getEmail(), address);
        info.setId(user.getInfo().getId());

        user.setInfo(info);
        return user;
    }
}