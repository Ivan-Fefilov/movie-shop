<%--@elvariable id="order" type="com.domain.core.UserOrder"--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="global">
    <fmt:message key="title.order" var="title"/>
    <fmt:message key="order.vendorCode" var="orderVendorCode"/>
    <fmt:message key="order.price" var="orderPrice"/>
    <fmt:message key="order.store" var="orderStore"/>
    <fmt:message key="order.name" var="orderName"/>
    <fmt:message key="order.number" var="orderNumber"/>
    <fmt:message key="order.cost" var="orderCost"/>
</fmt:bundle>

<t:master-page title="title">
    <div class="container">
        <table class="table table-striped table-hover">
            <tr>
                <th>${orderVendorCode}</th>
                <th>${orderName}</th>
                <th>${orderStore}</th>
                <th>${orderPrice}</th>
                <th>${orderNumber}</th>
            </tr>
            <c:forEach items="${order.orderItemList}" var="orderItem">
                <tr>
                    <td>${orderItem.shopItem.item.id}</td>
                    <td>${orderItem.shopItem.item.name}</td>
                    <td>${orderItem.shopItem.shop.name}</td>
                    <td>${orderItem.shopItem.item.price}</td>
                    <td>${orderItem.count}</td>
                </tr>
            </c:forEach>
        </table>
        <div>${orderCost}: ${order.cost}</div>
    </div>
</t:master-page>