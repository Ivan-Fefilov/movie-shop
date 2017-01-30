<%--@elvariable id="itemVM" type="com.web.viewmodels.ItemVM"--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="global">
    <fmt:message key="admin.item.editItem" var="title"/>
    <fmt:message key="admin.item.name" var="name"/>
    <fmt:message key="admin.item.producer" var="producer"/>
    <fmt:message key="admin.item.actors" var="actors"/>
    <fmt:message key="admin.item.production" var="production"/>
    <fmt:message key="admin.item.quality" var="quality"/>
    <fmt:message key="admin.item.year" var="year"/>
    <fmt:message key="admin.item.genre" var="genre"/>
    <fmt:message key="admin.item.price" var="price"/>
    <fmt:message key="admin.item.description" var="description"/>
    <fmt:message key="admin.item.save" var="save"/>
    <fmt:message key="admin.item.replaceImage" var="replaceImage"/>
</fmt:bundle>

<c:set var="errorCSSClass" value="has-error"/>

<c:set var="haveNameError" value="${itemVM.errors.name != null ? errorCSSClass : ''}"/>
<c:set var="haveProducerError" value="${itemVM.errors.producer!= null ? errorCSSClass : ''}"/>
<c:set var="haveActorsError" value="${itemVM.errors.actors != null ? errorCSSClass : ''}"/>
<c:set var="haveProductionError" value="${itemVM.errors.production != null ? errorCSSClass : ''}"/>
<c:set var="haveQualityError" value="${itemVM.errors.quality != null ? errorCSSClass : ''}"/>
<c:set var="haveYearError" value="${itemVM.errors.year != null ? errorCSSClass : ''}"/>
<c:set var="haveGenreError" value="${itemVM.errors.genre != null ? errorCSSClass : ''}"/>
<c:set var="havePriceError" value="${itemVM.errors.price != null ? errorCSSClass : ''}"/>
<c:set var="haveDescriptionError" value="${itemVM.errors.description != null ? errorCSSClass : ''}"/>
<c:set var="haveImageError" value="${itemVM.errors.image != null ? errorCSSClass : ''}"/>

<c:url value="/images" var="imageUrl">
    <c:param name="id" value="${itemVM.imageName}"/>
</c:url>

<t:master-page title="${title}">
    <div class="container">
        <c:if test="${itemVM.errors.createError != null}">
            <div class="alert alert-danger">${itemVM.errors.createError}</div>
        </c:if>
        <form action="<c:url value="/item-edit"/>" method="post" enctype="multipart/form-data">
            <input hidden name="imageName" value="${itemVM.imageName}">
            <div class="media">
                <div class="media-left media-top">
                    <img src="${imageUrl}" alt="${itemVM.imageName}" class="media-object img-responsive item-image">
                    <div class="form-group add-image-button ${haveImageError}">
                        <label class="btn btn-default">
                                ${replaceImage}<input type="file" name="image-file" style="display:none;"
                                                      onchange="$('#upload-file-info').html($(this).val());"/>
                        </label>
                        <span class='label label-info' id="upload-file-info"></span>
                        <label class="control-label">${itemVM.errors.image}</label>
                    </div>
                </div>
                <div class="media-body">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">${title}</h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-group col-sm-6 ${haveNameError}">
                                <input type="text" class="form-control" placeholder="${name}" value="${itemVM.name}"
                                       name="name">
                                <label class="control-label">${itemVM.errors.name}</label>
                            </div>
                            <div class="form-group col-sm-6 ${haveProducerError}">
                                <input type="text" class="form-control" placeholder="${producer}"
                                       value="${itemVM.producer}" name="producer">
                                <label class="control-label">${itemVM.errors.producer}</label>
                            </div>
                            <div class="form-group col-sm-6 ${haveActorsError}">
                                <input type="text" class="form-control" placeholder="${actors}"
                                       value="${itemVM.actors}"
                                       name="actors">
                                <label class="control-label">${itemVM.errors.actors}</label>
                            </div>
                            <div class="form-group col-sm-6 ${haveProductionError}">
                                <input type="text" class="form-control" placeholder="${production}"
                                       value="${itemVM.production}" name="production">
                                <label class="control-label">${itemVM.errors.production}</label>
                            </div>
                            <div class="form-group col-sm-6 ${haveQualityError}">
                                <input type="text" class="form-control" placeholder="${quality}"
                                       value="${itemVM.quality}"
                                       name="quality">
                                <label class="control-label">${itemVM.errors.quality}</label>
                            </div>
                            <div class="form-group col-sm-6 ${haveYearError}">
                                <input type="text" class="form-control" placeholder="${year}" value="${itemVM.year}"
                                       name="year">
                                <label class="control-label">${itemVM.errors.year}</label>
                            </div>
                            <div class="form-group col-sm-6 ${haveGenreError}">
                                <input type="text" class="form-control" placeholder="${genre}" value="${itemVM.genre}"
                                       name="genre">
                                <label class="control-label">${itemVM.errors.genre}</label>
                            </div>
                            <div class="form-group col-sm-6 ${havePriceError}">
                                <input type="text" class="form-control" placeholder="${price}" value="${itemVM.price}"
                                       name="price">
                                <label class="control-label">${itemVM.errors.price}</label>
                            </div>
                            <div class="form-group col-sm-12 ${haveDescriptionError}">
                            <textarea class="form-control" placeholder="${description}" rows="3"
                                      name="description">${itemVM.description}</textarea>
                                <label class="control-label">${itemVM.errors.description}</label>
                            </div>
                            <input type="hidden" value="${itemVM.id}" name="id">
                            <input type="submit" value="${save}" class="btn btn-primary btn-block">
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</t:master-page>