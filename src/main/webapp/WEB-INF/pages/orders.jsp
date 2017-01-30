<%--@elvariable id="user" type="com.domain.core.User"--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="global">
    <fmt:message key="title.orders" var="title"/>
    <fmt:message key="orders.code" var="orderCode"/>
    <fmt:message key="orders.date" var="orderDate"/>
    <fmt:message key="orders.status" var="orderStatus"/>
    <fmt:message key="orders.cost" var="orderCost"/>
    <fmt:message key="orders.openOrder" var="orderOpen"/>
</fmt:bundle>

<t:master-page title="${title}">
    <div class="container">
        <table class="table table-striped table-hover">
            <tr>
                <th>${orderCode}</th>
                <th>${orderDate}</th>
                <th>${orderStatus}</th>
                <th>${orderCost}</th>
                <th></th>
            </tr>
            <c:forEach items="${user.userOrderList}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>
                        <fmt:formatDate value="${order.date}" var="orderDate" pattern="dd/MM/yyyy HH:mm"/>
                            ${orderDate}
                    </td>
                    <td>${order.payStatus}</td>
                    <td>${order.cost}</td>
                    <td>
                        <c:url value="/order" var="orderUrl">
                            <c:param name="orderId" value="${order.id}"/>
                        </c:url>
                        <a class="btn btn-default" href="${orderUrl}">${orderOpen}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</t:master-page>