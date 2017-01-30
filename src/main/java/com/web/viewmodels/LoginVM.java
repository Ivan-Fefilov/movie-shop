package com.web.viewmodels;

import com.web.viewmodels.binding.Param;

import javax.servlet.http.HttpServletRequest;

public class LoginVM extends AbstractVM {
    @Param
    public String login;
    @Param
    public String password;

    protected LoginVM() {
        super();
    }

    public LoginVM(HttpServletRequest request) {
        super(request);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
