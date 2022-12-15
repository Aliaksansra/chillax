<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="../errorPage.jsp" %>
<jsp:include page="headerAdmin.jsp"/>
<title><spring:message code="admin.users"/></title>
<script type="text/javascript" src="/resources/js/users.js"></script>
<h1 class="h2"><spring:message code="admin.users"/>:</h1>
</div>
<div id="errorAddUser" class="hide alert alert-danger" role="alert"><spring:message code="error.user.create"/></div>
<div id="errorDeleteUser" class="hide alert alert-danger" role="alert"><spring:message code="error.user.delete"/></div>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="number"/></th>
            <th><spring:message code="user.login"/></th>
            <th><spring:message code="admin.user.name"/></th>
            <th><spring:message code="admin.user.surname"/></th>
            <th><spring:message code="admin.user.email"/></th>
            <th><spring:message code="admin.user.phone"/></th>
            <th><spring:message code="admin.role"/></th>
            <th width="100"></th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="users">
            <tbody>
            <tr>
                <td>${users.idUser}</td>
                <td>${users.login}</td>
                <td>${users.name}</td>
                <td>${users.surname}</td>
                <td>${users.email}</td>
                <td>${users.phone}</td>
                <td>${users.role.role}</td>
                <c:if test="${users.idUser > 1}">
                    <td><a class="btn btn-danger custom-width" onclick="deleteUser(${users.idUser})"><spring:message
                            code="button.delete"/></a></td>
                </c:if>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
<hr>
<h2><spring:message code="admin.user.add"/>:</h2><br>
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
<form id="addUser">
    <div class="row">
        <div id="divAddUsername" class="form-group col-md-4">
            <h5><spring:message code="form.registration.inputName"/></h5>
            <input id="username" type="text" class="form-control" placeholder="<spring:message code="admin.user.name"/>"
                   pattern="[A-Za-zА-Яа-я-]{2,16}" oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="username.invalid"/>')" required/>
        </div>
    </div>
    <div class="row">
        <div id="divAddSurname" class="form-group col-md-4">
            <h5><spring:message code="form.registration.inputSurname"/></h5>
            <input id="surname" type="text" class="form-control"
                   placeholder="<spring:message code="admin.user.surname"/>"
                   pattern="[A-Za-zА-Яа-я-]{2,30}" oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="surname.invalid"/>')" required/>
        </div>
    </div>
    <div class="row">
        <div id="divAddLogin" class="form-group col-md-4">
            <h5><spring:message code="form.inputLogin"/></h5>
            <input id="addLogin" type="text" class="form-control" placeholder="<spring:message code="user.login"/>"
                   pattern="[A-Za-z0-9_-]{2,16}"
                   oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="login.invalid"/>')" required/>
        </div>
    </div>
    <div class="row">
        <div id="divAddPassword" class="form-group col-md-4">
            <h5><spring:message code="form.inputPassword"/></h5>
            <input id="addPassword" type="password" class="form-control"
                   placeholder="<spring:message code="user.password"/>"
                   pattern="[A-Za-z0-9_-]{2,16}" oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="password.invalid"/>')" required/>
        </div>
    </div>
    <div class="row">
        <div id="divAddEmail" class="form-group col-md-4">
            <h5><spring:message code="form.registration.inputEmail"/></h5>
            <input id="email" type="email" class="form-control"
                   placeholder="<spring:message code="form.registration.email"/>"
                   oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="email.notValid"/>')" required/>
        </div>
    </div>
    <div class="row">
        <div id="divAddPhone" class="form-group col-md-4">
            <h5><spring:message code="form.registration.inputPhoneNumber"/></h5>
            <input id="phone" type="text" class="form-control" pattern="^(\+375|80)(29|25|44|33)(\d{3})(\d{2})(\d{2})$"
                   oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="phone.invalid"/>')"
                   placeholder="<spring:message code="form.registration.phone"/>"/>
        </div>
    </div>
    <div class="row">
        <div id="divAddUserRole" class="form-group col-md-4">
            <h5><spring:message code="admin.role"/>:</h5>
            <form:select path="role" id="userRole" items="${role}" multiple="true" itemLabel="role"
                         itemValue="idRole" class="form-control input-sm"/><a href="<c:url value='/admin/roles'/>"
                                                                              class="btn btn-danger btn-sm"><spring:message
                code="button.add.role"/></a><br>
            <input type="submit" value="<spring:message code="button.add"/>"
                   class="btn btn-primary custom-width right"/>
        </div>
    </div>
</form>
<jsp:include page="footerAdmin.jsp"/>