<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="errorPage.jsp" %>
<jsp:include page="header.jsp"/>
<title><spring:message code="title.registration"/></title>
<script type="text/javascript" src="/resources/js/users.js"></script>
<body>
<div class="content">
    <div id="errorRegister" class="hide alert alert-danger" role="alert"><spring:message
            code="error.user.register"/></div>
    <h2><spring:message code="title.registration"/></h2>
    <input type="hidden" id="csrfTokenRegistration" value="${_csrf.token}"/>
    <input type="hidden" id="csrfHeaderRegistration" value="${_csrf.headerName}"/>
    <form id="register">
        <div class="row">
            <div id="divInputName" class="form-group col-md-7">
                <h5><spring:message code="form.registration.inputName"/></h5>
                <input id="yourName" type="text" class="form-control" pattern="[A-Za-zА-Яа-я-]{2,16}"
                       oninput="setCustomValidity('')"
                       oninvalid="this.setCustomValidity('<spring:message code="username.invalid"/>')" required
                       placeholder="<spring:message code="form.registration.name"/>"/>
            </div>
        </div>
        <div class="row">
            <div id="divInputSurname" class="form-group col-md-7">
                <h5><spring:message code="form.registration.inputSurname"/></h5>
                <input id="yourSurname" type="text" class="form-control" pattern="[A-Za-zА-Яа-я-]{2,30}"
                       oninput="setCustomValidity('')"
                       oninvalid="this.setCustomValidity('<spring:message code="surname.invalid"/>')" required
                       placeholder="<spring:message code="form.registration.surname"/>"/>
            </div>
        </div>
        <div class="row">
            <div id="divInputLogin" class="form-group col-md-7">
                <h5><spring:message code="form.inputLogin"/></h5>
                <input id="yourLogin" type="text" class="form-control" pattern="[A-Za-z0-9_-]{2,16}"
                       oninput="setCustomValidity('')"
                       oninvalid="this.setCustomValidity('<spring:message code="login.invalid"/>')" required
                       placeholder="<spring:message code="user.login"/>"/>
            </div>
        </div>
        <div class="row">
            <div id="divInputPassword" class="form-group col-md-7">
                <h5><spring:message code="form.inputPassword"/></h5>
                <input id="yourPassword" type="password" class="form-control" pattern="[A-Za-z0-9_-]{2,16}"
                       oninput="setCustomValidity('')"
                       oninvalid="this.setCustomValidity('<spring:message code="password.invalid"/>')" required
                       placeholder="<spring:message code="user.password"/>"/>
            </div>
        </div>
        <div class="row">
            <div id="divInputEmail" class="form-group col-md-7">
                <h5><spring:message code="form.registration.inputEmail"/></h5>
                <input id="yourEmail" type="email" class="form-control" oninput="setCustomValidity('')"
                       oninvalid="this.setCustomValidity('<spring:message code="email.notValid"/>')" required
                       placeholder="<spring:message code="form.registration.email"/>"/>
            </div>
        </div>
        <div class="row">
            <div id="divInputPhone" class="form-group col-md-7">
                <h5><spring:message code="form.registration.inputPhoneNumber"/></h5>
                <input id="yourPhone" type="text" class="form-control"
                       pattern="^(\+375|80)(29|25|44|33)(\d{3})(\d{2})(\d{2})$"
                       oninput="setCustomValidity('')"
                       oninvalid="this.setCustomValidity('<spring:message code="phone.invalid"/>')"
                       placeholder="<spring:message code="form.registration.phone"/>"/>
                <a href="<c:url value='/'/>" class="btn close-style custom-width right "><spring:message
                        code="button.close"/></a>
                <input type="submit" value="<spring:message code="button.register"/>"
                       class="btn btn-warning custom-width right"/>
            </div>
        </div>
    </form>
</div>
<div id="registerModal" class="modal fade">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="success"/></h4>
            </div>
            <div class="modal-body">
                <p><spring:message code="success.register"/></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
