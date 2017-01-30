<%--@elvariable id="cost" type="java.lang.Double"--%>
<%--@elvariable id="orderItems" type="java.util.TreeMap"--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<fmt:bundle basename="global">
    <fmt:message key="title.cart" var="title"/>
    <fmt:message key="cart.item.vendorCode" var="itemVendorCode"/>
    <fmt:message key="cart.item.price" var="itemPrice"/>
    <fmt:message key="cart.item.name" var="itemName"/>
    <fmt:message key="cart.item.number" var="itemNumber"/>
    <fmt:message key="cart.cost" var="orderCost"/>
    <fmt:message key="cart.pay" var="orderPay"/>
    <fmt:message key="cart.clean" var="orderClean"/>
    <fmt:message key="cart.empty" var="cartIsEmpty"/>
    <fmt:message key="cart.item.store" var="itemStore"/>
</fmt:bundle>

<t:master-page title="title">
    <div class="container">
        <table class="table table-striped table-hover">
            <tr>
                <th>${itemVendorCode}</th>
                <th>${itemName}</th>
                <th>${itemStore}</th>
                <th>${itemPrice}</th>
                <th></th>
                <th>${itemNumber}</th>
                <th></th>
            </tr>

            <c:forEach items="${orderItems}" var="orderItem">
                <tr>
                    <td>${orderItem.key.item.id}</td>
                    <td>${orderItem.key.item.name}</td>
                    <td>${orderItem.key.shop.name}</td>
                    <td>${orderItem.key.item.price}</td>
                    <td>
                        <form action="<c:url value="/cart-remove-item"/>" method="post">
                            <input type="hidden" name="shopItemId" value="${orderItem.key.id}">
                            <button type="submit" class="btn btn-default">
                                <span class="glyphicon glyphicon-minus"></span>
                            </button>
                        </form>
                    </td>
                    <td>${orderItem.value}</td>
                    <td>
                        <form action="<c:url value="/cart-add-item"/>" method="post">
                            <input type="hidden" name="itemId" value="${orderItem.key.item.id}">
                            <button type="submit" class="btn btn-default">
                                <span class="glyphicon glyphicon-plus"></span>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:choose>
            <c:when test="${orderItems != null && not empty orderItems}">
                <p>${orderCost}: ${cost}</p>

                <form action="<c:url value="/cart-clear"/>" method="post">
                    <button class="btn btn-lg btn-primary" type="submit">${orderClean}</button>
                </form>
                <form action="<c:url value="/cart-pay"/>" method="post">
                    <button class="btn btn-lg btn-primary" type="submit">${orderPay}</button>
                </form>
            </c:when>
            <c:otherwise>
                ${cartIsEmpty}
            </c:otherwise>
        </c:choose>
    </div>
</t:master-page>