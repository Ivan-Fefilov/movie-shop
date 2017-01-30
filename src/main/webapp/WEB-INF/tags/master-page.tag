<%--@elvariable id="user" type="com.domain.core.User"--%>

<%@tag description="This is a master page" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="title" %>

<fmt:bundle basename="global">
    <fmt:message key="nav.main" var="navMain"/>
    <fmt:message key="nav.admin" var="navAdminPanel"/>
    <fmt:message key="nav.admin.items" var="navItems"/>
    <fmt:message key="nav.admin.stores" var="navStores"/>
    <fmt:message key="nav.signIn" var="navSignIn"/>
    <fmt:message key="nav.signUp" var="navSignUp"/>
    <fmt:message key="nav.signOut" var="navSignOut"/>
    <fmt:message key="nav.settings" var="navSettings"/>
    <fmt:message key="nav.orders" var="navOrders"/>
    <fmt:message key="nav.cart" var="navCart"/>
    <fmt:message key="nav.enLang" var="enLang"/>
    <fmt:message key="nav.ruLang" var="ruLang"/>
</fmt:bundle>

<c:set var="user" value="${sessionScope.user}"/>
<c:set var="selectedLanguage" value="${sessionScope['locale'] == 'en' ? enLang : ruLang}"/>

<c:url value="/change-locale" var="enLocaleUrl">
    <c:param name="language" value="en"/>
</c:url>
<c:url value="/change-locale" var="ruLocaleUrl">
    <c:param name="language" value="ru"/>
</c:url>

<html>
<head>
    <title>${title}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap imports, jQuery needed for bootstrap.min.js -->
    <link rel="stylesheet" href="<c:url value="/bootstrap-3.3.7/css/bootstrap.min.css"/>"/>
    <script src="<c:url value="/jQuery/jquery-3.1.1.min.js"/>"></script>
    <script src="<c:url value="/bootstrap-3.3.7/js/bootstrap.min.js"/>"></script>
    <!-- /Bootstrap -->
    <script src="<c:url value="/jQuery/jquery.ellipsis.js"/>"></script>
    <script>
        $(".ellipsis").ellipsis();
    </script>
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>"/>
</head>
<body>
<div class="jumbotron">
    <div class="container text-center">
        <h1>Online Film Store</h1>
    </div>
</div>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/"/>">${navMain}</a>
        </div>
        <ul class="nav navbar-nav">
            <c:if test="${user != null && user.role.name=='Admin'}">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            ${navAdminPanel} <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="/items-control"/>">${navItems}</a></li>
                        <li><a href="<c:url value="/items-storage"/>">${navStores}</a></li>
                    </ul>
                </li>
            </c:if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="<c:url value="/cart"/>">
                <span class="glyphicon glyphicon-shopping-cart"></span> ${navCart}</a>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <span class="glyphicon glyphicon-flag"></span> ${selectedLanguage}
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="<c:out value="${enLocaleUrl}"/>">${enLang}</a></li>
                    <li><a href="<c:out value="${ruLocaleUrl}"/>">${ruLang}</a></li>
                </ul>
            </li>
            <c:choose>
                <c:when test="${user == null}">
                    <li><a href="<c:url value="/user-registration"/>">
                        <span class="glyphicon glyphicon-user"></span> ${navSignUp}</a>
                    </li>
                    <li>
                        <a href="<c:url value="/user-login"/>">
                            <span class="glyphicon glyphicon-log-in"></span> ${navSignIn}</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="glyphicon glyphicon-user"></span> ${user.login}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/orders"/>">
                                <span class="glyphicon glyphicon-th-list"></span> ${navOrders}</a>
                            </li>
                            <li><a href=<c:url value="/user-settings"/>>
                                <span class="glyphicon glyphicon-wrench"></span> ${navSettings}</a>
                            </li>
                            <li><a href=<c:url value="/user-signout"/>>
                                <span class="glyphicon glyphicon-log-out"></span> ${navSignOut}</a>
                            </li>
                        </ul>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>

<jsp:doBody/>

<footer class="footer text-center">
    <p class="text-muted">Copyright</p>
</footer>
</body>
</html>