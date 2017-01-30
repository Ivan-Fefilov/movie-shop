<%--@elvariable id="userSettingsVM" type="com.web.viewmodels.UserSettigsVM"--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="global">
    <fmt:message key="title.settings" var="settingsTitle"/>
    <fmt:message key="settings.save" var="save"/>

    <fmt:message key="registration.contactInfo" var="contactInfo"/>
    <fmt:message key="registration.firstName" var="firstNamePlaceholder"/>
    <fmt:message key="registration.lastName" var="lastNamePlaceholder"/>
    <fmt:message key="registration.phone" var="phonePlaceholder"/>
    <fmt:message key="registration.email" var="emailPlaceholder"/>

    <fmt:message key="registration.addressInfo" var="addressInfo"/>
    <fmt:message key="registration.country" var="countryPlaceholder"/>
    <fmt:message key="registration.city" var="cityPlaceholder"/>
    <fmt:message key="registration.zipcode" var="postcodePlaceholder"/>
    <fmt:message key="registration.street" var="streetPlaceholder"/>
    <fmt:message key="registration.house" var="housePlaceholder"/>
    <fmt:message key="registration.apartment" var="apartmentPlaceholder"/>
</fmt:bundle>

<c:set var="errorCSSClass" value="has-error"/>

<c:set var="haveLoginError" value="${userSettingsVM.errors.login != null ? errorCSSClass : ''}"/>
<c:set var="havePasswordError" value="${userSettingsVM.errors.password != null ? errorCSSClass : ''}"/>
<c:set var="haveConfirmError" value="${userSettingsVM.errors.confirm != null ? errorCSSClass : ''}"/>

<c:set var="haveFirstNameError" value="${userSettingsVM.errors.firstName != null ? errorCSSClass : ''}"/>
<c:set var="haveLastNameError" value="${userSettingsVM.errors.lastName != null ? errorCSSClass : ''}"/>
<c:set var="haveEmailError" value="${userSettingsVM.errors.email != null ? errorCSSClass : ''}"/>
<c:set var="havePhoneError" value="${userSettingsVM.errors.phoneNumber != null ? errorCSSClass : ''}"/>

<c:set var="haveCountryError" value="${userSettingsVM.errors.country != null ? errorCSSClass : ''}"/>
<c:set var="haveCityError" value="${userSettingsVM.errors.city != null ? errorCSSClass : ''}"/>
<c:set var="haveStreetError" value="${userSettingsVM.errors.street != null ? errorCSSClass : ''}"/>
<c:set var="haveHomeError" value="${userSettingsVM.errors.homeNumber != null ? errorCSSClass : ''}"/>
<c:set var="haveApartmentError" value="${userSettingsVM.errors.apartmentNumber != null ? errorCSSClass : ''}"/>
<c:set var="havePostIndexError" value="${userSettingsVM.errors.postIndex != null ? errorCSSClass : ''}"/>

<t:master-page title='${settingsTitle}'>
    <div class="container">
        <div class="col-sm-8 col-sm-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">${settingsTitle}</h3>
                </div>
                <div class="panel-body">
                    <form action="<c:url value="/user-settings"/>" method="post">
                        <div class="panel panel-heading">${contactInfo}</div>
                        <div class="row">
                            <div class="form-group col-sm-6 ${haveFirstNameError}">
                                <input type="text" class="form-control" placeholder="${firstNamePlaceholder}"
                                       value="${userSettingsVM.firstName}" name="firstName">
                                <label class="control-label">${userSettingsVM.errors.firstName}</label>
                            </div>
                            <div class="form-group col-sm-6 ${haveLastNameError}">
                                <input type="text" class="form-control" placeholder="${lastNamePlaceholder}"
                                       value="${userSettingsVM.lastName}" name="lastName">
                                <label class="control-label">${userSettingsVM.errors.lastName}</label>
                            </div>
                        </div>
                        <div class="form-group ${havePhoneError}">
                            <input type="tel" class="form-control" placeholder="${phonePlaceholder}"
                                   value="${userSettingsVM.phoneNumber}" name="phoneNumber">
                            <label class="control-label">${userSettingsVM.errors.phoneNumber}</label>
                        </div>
                        <div class="form-group ${haveEmailError}">
                            <input type="email" class="form-control" placeholder="${emailPlaceholder}"
                                   value="${userSettingsVM.email}" name="email">
                            <label class="control-label">${userSettingsVM.errors.email}</label>
                        </div>
                        <div class="panel panel-heading">${addressInfo}</div>
                        <div class="row">
                            <div class="form-group col-sm-6 ${haveCountryError}">
                                <input type="text" class="form-control" placeholder="${countryPlaceholder}"
                                       value="${userSettingsVM.country}" name="country">
                                <label class="control-label">${userSettingsVM.errors.country}</label>
                            </div>
                            <div class="form-group col-sm-6 ${haveCityError}">
                                <input type="text" class="form-control" placeholder="${cityPlaceholder}"
                                       value="${userSettingsVM.city}" name="city">
                                <label class="control-label">${userSettingsVM.errors.city}</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-6 ${haveStreetError}">
                                <input type="text" class="form-control" placeholder="${streetPlaceholder}"
                                       value="${userSettingsVM.street}" name="street">
                                <label class="control-label">${userSettingsVM.errors.street}</label>
                            </div>
                            <div class="form-group col-sm-6 ${haveHomeError}">
                                <input type="number" class="form-control" placeholder="${housePlaceholder}"
                                       value="${userSettingsVM.homeNumber}" name="homeNumber">
                                <label class="control-label">${userSettingsVM.errors.homeNumber}</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-6 ${haveApartmentError}">
                                <input type="number" class="form-control" placeholder="${apartmentPlaceholder}"
                                       value="${userSettingsVM.apartmentNumber}" name="apartmentNumber">
                                <label class="control-label">${userSettingsVM.errors.apartmentNumber}</label>
                            </div>
                            <div class="form-group col-sm-6 ${havePostIndexError}">
                                <input type="number" class="form-control" placeholder="${postcodePlaceholder}"
                                       value="${userSettingsVM.postIndex}" name="postIndex">
                                <label class="control-label">${userSettingsVM.errors.postIndex}</label>
                            </div>
                        </div>
                        <input type="submit" value="${save}" class="btn btn-primary btn-block">
                    </form>
                </div>
            </div>
        </div>
    </div>
</t:master-page>
