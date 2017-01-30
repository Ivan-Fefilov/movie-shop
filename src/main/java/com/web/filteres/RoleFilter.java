package com.web.filteres;

import com.domain.core.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebFilter(displayName = "RoleFilter", urlPatterns = "/*")
public class RoleFilter implements Filter {
    private static List<String> restrictedURLs;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        restrictedURLs = new ArrayList<>();

        restrictedURLs.add("/shopItemsAdmin");
        restrictedURLs.add("/add-web");
        restrictedURLs.add("/edit-web");
        restrictedURLs.add("/delete-web");

        restrictedURLs.add("/items");
        restrictedURLs.add("/add-item");
        restrictedURLs.add("/edit-item");
        restrictedURLs.add("/delete-item");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getServletPath();

        if (!restrictedURLs.contains(url)) {
            filterChain.doFilter(request, response);
            return;
        }

        User user = (User) request.getSession().getAttribute("user");

        if (user != null && "Admin".equals(user.getRole().getName())) {
            filterChain.doFilter(request, response);
            return;
        }

        response.sendRedirect("/login");
    }

    @Override
    public void destroy() {

    }
}
