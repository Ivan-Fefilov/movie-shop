<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<t:master-page title='${Error}'>
    <div class="jumbotron" >
        <div class="container text-center">
            <h1 class="glyphicon glyphicon-wrench">Упс...что то пошло не так!</h1>
            <p>В ближайшее время мы устраним проблему и приведем систему в рабочее состояние.</p>
            <p>А пока можите попробовать еще раз.</p>
            <p><a class="btn btn-primary btn-lg"  role="button" href="<c:url value="/index"/>">На главную</a></p>
        </div>
    </div>
</t:master-page>
