<%--@elvariable id="item" type="com.domain.core.Item"--%>
<%--@elvariable id="items" type="java.util.List"--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="global">
    <fmt:message key="title.items" var="title"/>
    <fmt:message key="admin.item.add" var="add"/>
    <fmt:message key="admin.item.edit" var="edit"/>
    <fmt:message key="admin.item.delete" var="delete"/>
    <fmt:message key="admin.item.vendorCode" var="vendorСode"/>
    <fmt:message key="admin.item.name" var="itemName"/>
</fmt:bundle>

<t:master-page title="${title}">
    <div class="container">
        <p>
            <a class="btn btn-primary" href="<c:url value="/item-add"/>">${add}</a>
        </p>
        <table class="table table-striped table-hover">
            <tr>
                <th>${vendorСode}</th>
                <th>${itemName}</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${items}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>
                        <c:url value="/item-edit" var="editURL">
                            <c:param name="id" value="${item.id}"/>
                        </c:url>
                        <a class="btn btn-default" href="<c:out value="${editURL}"/>">${edit}</a>
                    </td>
                    <td>
                        <form action="<c:url value="/item-delete"/>" method="post">
                            <input hidden name="id" value="${item.id}">
                            <input class="btn btn-default" type="submit" value="${delete}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</t:master-page>
