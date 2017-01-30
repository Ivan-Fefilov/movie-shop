<%--@elvariable id="items" type="java.util.List"--%>
<%--@elvariable id="pagination" type="com.web.viewmodels.Pagination"--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="global">
    <fmt:message key="title.main" var="title"/>
</fmt:bundle>

<t:master-page title="${title}">
    <div class="container">
        <%@include file="../fragments/pagination.jspf" %>
        <div class="row">
            <c:forEach items="${items}" var="item">
                <%@include file="../fragments/item.jspf" %>
            </c:forEach>
        </div>
    </div>
</t:master-page>