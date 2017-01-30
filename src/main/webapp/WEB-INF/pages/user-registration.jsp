<%--@elvariable id="registrationVM" type="com.web.viewmodels.RegistrationVM"--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:bundle basename="global">
    <fmt:message key="title.registration" var="registrationTitle"/>
    <fmt:message key="registration.signUp" var="signUp"/>

    <fmt:message key="registration.login" var="loginPlaceholder"/>
    <fmt:message key="registration.password" var="passwordPlaceholder"/>
    <fmt:message key="registration.passwordConfirm" var="passwordConfirmPlaceholder"/>

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

<c:set var="haveLoginError" value="${registrationVM.errors.login != null ? errorCSSClass : ''}"/>
<c:set var="havePasswordError" value="${registrationVM.errors.password != null ? errorCSSClass : ''}"/>
<c:set var="haveConfirmError" value="${registrationVM.errors.confirm != null ? errorCSSClass : ''}"/>

<c:set var="haveFirstNameError" value="${registrationVM.errors.firstName != null ? errorCSSClass : ''}"/>
<c:set var="haveLastNameError" value="${registrationVM.errors.lastName != null ? errorCSSClass : ''}"/>
<c:set var="haveEmailError" value="${registrationVM.errors.email != null ? errorCSSClass : ''}"/>
<c:set var="havePhoneError" value="${registrationVM.errors.phoneNumber != null ? errorCSSClass : ''}"/>

<c:set var="haveCountryError" value="${registrationVM.errors.country != null ? errorCSSClass : ''}"/>
<c:set var="haveCityError" value="${registrationVM.errors.city != null ? errorCSSClass : ''}"/>
<c:set var="haveStreetError" value="${registrationVM.errors.street != null ? errorCSSClass : ''}"/>
<c:set var="haveHomeError" value="${registrationVM.errors.homeNumber != null ? errorCSSClass : ''}"/>
<c:set var="haveApartmentError" value="${registrationVM.errors.apartmentNumber != null ? errorCSSClass : ''}"/>
<c:set var="havePostIndexError" value="${registrationVM.errors.postIndex != null ? errorCSSClass : ''}"/>

<t:master-page title='${registrationTitle}'>
    <div class="container">
        <div class="col-sm-8 col-sm-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">${registrationTitle}</h3>
                </div>
                <div class="panel-body">
                    <form action="<c:url value="/user-registration"/>" method="post">
                        <div class="form-group ${haveLoginError}">
                            <input type="text" class="form-control" placeholder="${loginPlaceholder}"
                                   value="${registrationVM.login}" name="login">
                            <label class="control-label">${registrationVM.errors.login}</label>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-6 ${havePasswordError}">
                                <input type="password" class="form-control" placeholder="${passwordPlaceholder}"
                                       value="${registrationVM.password}" name="password">
                                <label class="control-label">${registrationVM.errors.password}</label>
                            </div>
                            <div class="form-group col-sm-6 ${havePasswordError}">
                                <input type="password" class="form-control" placeholder="${passwordConfirmPlaceholder}"
                                       value="${registrationVM.confirm}" name="confirm">
                                <label class="control-label">${registrationVM.errors.confirm}</label>
                            </div>
                        </div>
                        <div class="panel panel-heading">${contactInfo}</div>
                        <div class="row">
                            <div class="form-group col-sm-6 ${haveFirstNameError}">
                                <input type="text" class="form-control" placeholder="${firstNamePlaceholder}"
                                       value="${registrationVM.firstName}" name="firstName">
                                <label class="control-label">${registrationVM.errors.firstName}</label>
                            </div>
                            <div class="form-group col-sm-6 ${haveLastNameError}">
                                <input type="text" class="form-control" placeholder="${lastNamePlaceholder}"
                                       value="${registrationVM.lastName}" name="lastName">
                                <label class="control-label">${registrationVM.errors.lastName}</label>
                            </div>
                        </div>
                        <div class="form-group ${havePhoneError}">
                            <input type="tel" class="form-control" placeholder="${phonePlaceholder}"
                                   value="${registrationVM.phoneNumber}" name="phoneNumber">
                            <label class="control-label">${registrationVM.errors.phoneNumber}</label>
                        </div>
                        <div class="form-group ${haveEmailError}">
                            <input type="email" class="form-control" placeholder="${emailPlaceholder}"
                                   value="${registrationVM.email}" name="email">
                            <label class="control-label">${registrationVM.errors.email}</label>
                        </div>
                        <div class="panel panel-heading">${addressInfo}</div>
                        <div class="row">
                            <div class="form-group col-sm-6 ${haveCountryError}">
                                <input type="text" class="form-control" placeholder="${countryPlaceholder}"
                                       value="${registrationVM.country}" name="country">
                                <label class="control-label">${registrationVM.errors.country}</label>
                            </div>
                            <div class="form-group col-sm-6 ${haveCityError}">
                                <input type="text" class="form-control" placeholder="${cityPlaceholder}"
                                       value="${registrationVM.city}" name="city">
                                <label class="control-label">${registrationVM.errors.city}</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-6 ${haveStreetError}">
                                <input type="text" class="form-control" placeholder="${streetPlaceholder}"
                                       value="${registrationVM.street}" name="street">
                                <label class="control-label">${registrationVM.errors.street}</label>
                            </div>
                            <div class="form-group col-sm-6 ${haveHomeError}">
                                <input type="number" class="form-control" placeholder="${housePlaceholder}"
                                       value="${registrationVM.homeNumber}" name="homeNumber">
                                <label class="control-label">${registrationVM.errors.homeNumber}</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-sm-6 ${haveApartmentError}">
                                <input type="number" class="form-control" placeholder="${apartmentPlaceholder}"
                                       value="${registrationVM.apartmentNumber}" name="apartmentNumber">
                                <label class="control-label">${registrationVM.errors.apartmentNumber}</label>
                            </div>
                            <div class="form-group col-sm-6 ${havePostIndexError}">
                                <input type="number" class="form-control" placeholder="${postcodePlaceholder}"
                                       value="${registrationVM.postIndex}" name="postIndex">
                                <label class="control-label">${registrationVM.errors.postIndex}</label>
                            </div>
                        </div>
                        <input type="submit" value="${signUp}" class="btn btn-primary btn-block">
                    </form>
                </div>
            </div>
        </div>
    </div>
</t:master-page>