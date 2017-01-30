package com.web.controllers;

import com.domain.core.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public abstract class AbstractServlet extends HttpServlet {

    private static String SERVLETS_ROOT = "WEB-INF/pages/%s.jsp";
    protected static final Logger LOGGER = Logger.getRootLogger();

    private HttpServletRequest request;
    private HttpServletResponse response;

    protected void forward(String path) throws ServletException, IOException {
        path = String.format(SERVLETS_ROOT, path);
        request.getRequestDispatcher(path).forward(request, response);
    }

    protected void redirect(String address) throws IOException {
        response.sendRedirect(address);
    }

    protected HttpServletRequest getRequest() {
        return request;
    }

    protected HttpServletResponse getResponse() {
        return response;
    }

    protected void setAttribute(String name, Object value) {
        request.setAttribute(name, value);
    }

    protected Object getAttribute(String name) {
        return request.getAttribute(name);
    }


    protected HttpSession getSession() {
        return request.getSession();
    }

    protected void saveUserToSession(User user) {
        getSession().setAttribute("user", user);
    }

    protected User getUser() {
        return (User) getSession().getAttribute("user");
    }

    protected void endSession() {
        getSession().removeAttribute("user");
    }

    protected void doGet() throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setupRequestResponse(request, response);
        doGet();
    }

    protected void doPost() throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setupRequestResponse(request, response);
        doPost();
    }

    private void setupRequestResponse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        this.request = request;
        this.response = response;
    }
}
