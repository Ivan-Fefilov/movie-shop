<%--@elvariable id="shop" type="com.domain.core.Shop"--%>
<%--@elvariable id="shopsSelect" type="java.util.List<Shop>"--%>
<%--@elvariable id="shopSelect" type="com.domain.core.Shop"--%>
<%--@elvariable id="error" type="java.lang.String"--%>
<%--@elvariable id="editedItem" type="com.domain.core.ShopItem"--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="global">
    <fmt:message key="admin.shop.add" var="addShop"/>
    <fmt:message key="admin.shop.edit" var="editShop"/>
    <fmt:message key="admin.shop.delete" var="deleteShop"/>
    <fmt:message key="admin.shop.name" var="nameShop"/>
    <fmt:message key="admin.shop.item.vendorCode" var="vendorCodeItem"/>
    <fmt:message key="admin.shop.item.name" var="nameItem"/>
    <fmt:message key="admin.shop.item.count" var="countItem"/>
    <fmt:message key="admin.shop.deleteNotification" var="deleteNotification"/>
</fmt:bundle>

<c:url value="/shop-edit" var="shopEdit">
    <c:param name="shopId" value="${shop.id}"/>
</c:url>

<t:master-page title="Admin Shop items">
    <div class="container">
        <form action="<c:url value="/items-storage"/>" method="get" id="change-shop">
            <select class="form-control select-shop" name="shopId" onchange="this.form.submit()">
                <c:forEach items="${shopsSelect}" var="shopSelect">
                    <c:choose>
                        <c:when test="${shopSelect.id == shop.id}">
                            <option selected value="${shopSelect.id}">${shopSelect.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${shopSelect.id}">${shopSelect.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </form>
        <div class="shop-edit-buttons">
            <a class="btn btn-default" href="<c:url value="/shop-add"/>">${addShop}</a>
            <a class="btn btn-default" href="${shopEdit}">${editShop}</a>
            <form action="<c:url value="/shop-delete"/>" method="post"
                  onsubmit="return confirm('${deleteNotification}')">
                <input hidden name="shopId" value="${shop.id}">
                <input class="btn btn-default" type="submit" value="${deleteShop}">
            </form>
        </div>
        <c:if test="${error != null}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <c:if test="${editedItem != null}">
            <div class="alert alert-success">
                Item count was edited! Item id: ${editedItem.id}; name: ${editedItem.item.name}
            </div>
        </c:if>
        <table class="shop-items-table table table-striped table-condensed table-hover">
            <thead>
            <tr>
                <th>${vendorCodeItem}</th>
                <th>${nameItem}</th>
                <th>${nameShop}</th>
                <th class="pull-right">${countItem}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${shop.shopItems}" var="item">
                <tr>
                    <td>${item.item.id}</td>
                    <td>${item.item.name}</td>
                    <td>${item.shop.name}</td>
                    <td>
                        <form action="<c:url value="/items-storage"/>" method="post">
                            <input hidden name="itemId" value="${item.id}">
                            <input hidden name="shopId" value="${shop.id}">
                            <input class="form-control" type="number" min="0" name="itemCount" value="${item.count}">
                            <input class="btn btn-default" type="submit" value="Submit">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</t:master-page>