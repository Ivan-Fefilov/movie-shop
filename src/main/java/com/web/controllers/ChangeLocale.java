package com.web.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

@WebServlet(urlPatterns = "/change-locale")
public class ChangeLocale extends AbstractServlet {

    @Override
    protected void doGet() throws ServletException, IOException {
        String language = getRequest().getParameter("language");
        if (language != null) {
            Locale locale = new Locale(language);
            Config.set(getSession(), Config.FMT_LOCALE, locale);

            Cookie cookie = new Cookie("locale", language);
            getResponse().addCookie(cookie);
        }
        redirect(getRequest().getHeader("referer"));
    }
}