package com.web.controllers.user;

import com.domain.core.User;
import com.domain.core.UserRole;
import com.domain.dao.UserDao;
import com.domain.dao.UserRoleDao;
import com.web.controllers.AbstractServlet;
import com.web.util.ValidationUtil;
import com.web.viewmodels.RegistrationVM;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/user-registration"})
public class UserRegistration extends AbstractServlet {

    protected void doGet() throws ServletException, IOException {
        forward("user-registration");
    }

    protected void doPost() throws IOException, ServletException {
        RegistrationVM registrationVM = new RegistrationVM(getRequest());

        if (!registrationVM.isValidate()) {
            LOGGER.warn("registration " + "status=failed. reason=registration's fields are not valid");
            getRequest().setAttribute("registrationVM", registrationVM);
            forward("user-registration");
            return;
        }

        Locale locale = (Locale) getSession().getAttribute("locale");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("global", locale);

        UserDao dao = new UserDao();
        boolean loginUsed = dao.getUserByLogin(registrationVM.getLogin()) != null;
        boolean emailUsed = dao.getUserByEmail(registrationVM.getEmail()) != null;

        if (loginUsed) {
            LOGGER.warn("user=" + registrationVM.getLogin() + "status=failed. reason=login already exist");
            registrationVM.setError("login", resourceBundle.getString("registration.error.loginNotAvailable"));
            getRequest().setAttribute("registrationVM", registrationVM);
            forward("user-registration");
            return;
        }

        if (!ValidationUtil.isEmailValid(registrationVM.getEmail())) {
            LOGGER.warn("user=" + registrationVM.getEmail() + "status=failed. reason=email is not correct");
            registrationVM.setError("email", resourceBundle.getString("registration.error.emailNotCorrect"));
            getRequest().setAttribute("registrationVM", registrationVM);
            forward("/user-registration");
            return;
        }

        if (emailUsed) {
            LOGGER.warn("user=" + registrationVM.getEmail() + "status=failed. reason=email already exist");
            registrationVM.setError("email", resourceBundle.getString("registration.error.emailNotAvailable"));
            getRequest().setAttribute("registrationVM", registrationVM);
            forward("user-registration");
            return;
        }

        if (!ValidationUtil.isPhoneValid(registrationVM.getPhoneNumber())) {
            LOGGER.warn("user=" + registrationVM.getPhoneNumber() + "status=failed. reason=phone is not correct");
            registrationVM.setError("phoneNumber", resourceBundle.getString("registration.error.phoneNotCorrect"));
            getRequest().setAttribute("registrationVM", registrationVM);
            forward("user-registration");
            return;
        }

        if (!registrationVM.getPassword().equals(registrationVM.getConfirm())) {
            LOGGER.warn("user=" + registrationVM.getPassword() + "status=failed. reason=password is not confirm");
            registrationVM.setError("password", resourceBundle.getString("registration.error.passwordNotConfirm"));
            getRequest().setAttribute("registrationVM", registrationVM);
            forward("user-registration");
            return;
        }

        registerUser(registrationVM);
        LOGGER.info("user=" + registrationVM.getLogin() + "status=done");
        redirect("/user-login");
    }

    private void registerUser(RegistrationVM registrationVM) {
        String mdPassword = DigestUtils.md5Hex(registrationVM.getPassword());
        registrationVM.setPassword(mdPassword);
        User user = registrationVM.toUser();

        UserRoleDao roleDao = new UserRoleDao();
        UserRole role = roleDao.getRoleByName("User");
        user.setRole(role);

        UserDao dao = new UserDao();
        dao.create(user);
    }
}
