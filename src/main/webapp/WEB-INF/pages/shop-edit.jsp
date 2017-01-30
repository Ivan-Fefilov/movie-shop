<%--@elvariable id="shopBean" type="com.web.viewmodels.ShopVM"--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="global">
    <fmt:message key="admin.shop.editTitle" var="title"/>
    <fmt:message key="admin.shop.edit" var="edit"/>
    <fmt:message key="admin.shop.name" var="name"/>
    <fmt:message key="admin.shop.address" var="address"/>
</fmt:bundle>

<c:set var="errorCSSClass" value="has-error"/>

<c:set var="nameError" value="${shopBean.errors.name != null ? errorCSSClass : ''}"/>
<c:set var="addressError" value="${shopBean.errors.address != null ? errorCSSClass : ''}"/>

<t:master-page title="${title}">
    <div class="container">
        <div class="col-sm-8 col-sm-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">${title}</h3>
                </div>
                <div class="panel-body">
                    <form action="<c:url value="/shop-edit"/>" method="post">
                        <input hidden name="id" value="${shopBean.id}">
                        <div class="form-group col-sm-12 ${nameError}">
                            <input type="text" class="form-control" placeholder="${name}"
                                   value="${shopBean.name}" name="name">
                            <label class="control-label">${shopBean.errors.name}</label>
                        </div>
                        <div class="form-group col-sm-12 ${addressError}">
                            <textarea class="form-control" placeholder="${address}"
                                      name="address">${shopBean.address}</textarea>
                            <label class="control-label">${shopBean.errors.address}</label>
                        </div>
                        <input type="submit" value="${edit}" class="btn btn-primary btn-block">
                    </form>
                </div>
            </div>
        </div>
    </div>
</t:master-page>