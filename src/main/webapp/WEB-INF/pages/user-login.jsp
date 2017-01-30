<%--@elvariable id="loginVM" type="com.web.viewmodels.LoginVM"--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="global">
    <fmt:message key="title.login" var="title"/>
    <fmt:message key="login.signIn" var="SignIn"/>
    <fmt:message key="login.loginPlaceholder" var="loginPlaceholder"/>
    <fmt:message key="login.passwordPlaceholder" var="passwordPlaceholder"/>
</fmt:bundle>

<c:set var="errorCSSClass" value="has-error"/>

<c:set var="haveLoginError" value="${loginVM.errors.login != null ? errorCSSClass : ''}"/>
<c:set var="havePasswordError" value="${loginVM.errors.password != null ? errorCSSClass : ''}"/>

<t:master-page title='${title}'>
    <div class="container-fluid">
        <form class="form-signin" action="<c:url value="/user-login"/>" method="post">
            <div class="form-group ${haveLoginError}">
                <input name="login" type="text" id="inputLogin" class="form-control"
                       value="${loginVM.login}" placeholder="${loginPlaceholder}" required autofocus>
                <label class="control-label" for="inputLogin">${loginVM.errors.login}</label>
            </div>
            <div class="form-group ${havePasswordError}">
                <input name="password" type="password" id="inputPassword" class="form-control"
                       value="${loginVM.password}" placeholder="${passwordPlaceholder}" required>
                <label class="control-label" for="inputPassword">${loginVM.errors.password}</label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">${SignIn}</button>
        </form>
    </div>
</t:master-page>
