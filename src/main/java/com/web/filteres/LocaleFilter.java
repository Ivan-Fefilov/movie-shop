package com.web.filteres;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

@WebFilter(displayName = "LocaleFilter", urlPatterns = "/*")
public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        boolean isFindCookies = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("locale".equals(cookie.getName())) {
                    Locale locale = new Locale(cookie.getValue());
                    Config.set(request.getSession(), Config.FMT_LOCALE, locale);
                    request.getSession().setAttribute("locale", locale);
                    isFindCookies = true;
                }
            }
        }
        if (!isFindCookies) {
            Locale locale = Locale.getDefault();
            request.getSession().setAttribute("locale", locale);
        }

        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
