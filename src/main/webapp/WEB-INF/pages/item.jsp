<%--@elvariable id="item" type="com.domain.core.Item"--%>
<%--@elvariable id="user" type="com.domain.core.User"--%>
<%--@elvariable id="orderItem" type="com.domain.core.OrderItem"--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="global">
    <fmt:message key="item.addToCart" var="addToCart"/>
    <fmt:message key="item.shops" var="shops"/>
    <fmt:message key="admin.item.genre" var="genre"/>
    <fmt:message key="admin.item.year" var="year"/>
    <fmt:message key="admin.item.producer" var="producer"/>
    <fmt:message key="admin.item.production" var="production"/>
    <fmt:message key="admin.item.actors" var="actors"/>
    <fmt:message key="admin.item.quality" var="quality"/>
    <fmt:message key="admin.item.description" var="description"/>
    <fmt:message key="admin.item.price" var="price"/>
    <fmt:message key="admin.shop.item.count" var="count"/>
</fmt:bundle>

<c:url value="/images" var="imageUrl">
    <c:param name="id" value="${item.image}"/>
</c:url>

<t:master-page title="${item.name}">
    <div class="container">
        <div class="media">
            <div class="media-left media-middle">
                <img src="${imageUrl}" alt="${item.image}" class="media-object">
            </div>
            <div class="media-body">
                <div class="shop-cart-button pull-right">
                    <form action="<c:url value="/cart-add-item"/>" method="post">
                        <input type="hidden" name="itemId" value="${item.id}">
                        <button type="submit" class="btn btn-danger">
                            ${addToCart} <span class="glyphicon glyphicon-shopping-cart"></span>
                        </button>
                    </form>
                    <div class="movie-price">${price}: ${item.price}</div>
                </div>
                <h2 class="media-heading">${item.name}</h2>
                <table class="table table-striped">
                    <tbody>
                    <tr>
                        <td>${count}</td>
                        <td>${item.count}</td>
                    </tr>
                    <tr>
                        <td>${genre}</td>
                        <td>${item.genre}</td>
                    </tr>
                    <tr>
                        <td>${year}</td>
                        <td>${item.year}</td>
                    </tr>
                    <tr>
                        <td>${producer}</td>
                        <td>${item.producer}</td>
                    </tr>
                    <tr>
                        <td>${production}</td>
                        <td>${item.production}</td>
                    </tr>
                    <tr>
                        <td>${actors}</td>
                        <td>${item.actors}</td>
                    </tr>
                    <tr>
                        <td>${quality}</td>
                        <td>${item.quality}</td>
                    </tr>
                    <tr>
                        <td>${description}</td>
                        <td>${item.description}</td>
                    </tr>
                    <tr>
                        <td>${shops}</td>
                        <td>
                            <c:forEach items="${item.shopItems}" var="shopItem">
                                ${shopItem.shop.name} <span class="badge"> ${shopItem.count} </span>
                            </c:forEach>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</t:master-page>
