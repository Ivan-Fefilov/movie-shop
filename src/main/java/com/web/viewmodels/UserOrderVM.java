package com.web.viewmodels;

import com.domain.core.User;
import com.domain.core.UserOrder;

import javax.servlet.http.HttpServletRequest;

public class UserOrderVM extends AbstractVM {

    private UserOrder userOrder;
    private User user;

    protected UserOrderVM() {
        super();
    }

    public UserOrderVM(HttpServletRequest request) {
        super(request);
        user = (User) request.getAttribute("user");
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
