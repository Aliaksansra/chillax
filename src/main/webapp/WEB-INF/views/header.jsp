<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE HTML5>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="/resources/css/style.css" rel="stylesheet">
    <link rel="icon" href="/img/icon/favicon.ico">
    <script type='text/javascript' src='/resources/libs/jquery-3.3.1.js'></script>
    <script type="text/javascript" src="/resources/js/header.js"></script>
</head>
<body>
<c:if test="${param.error != null}">
<div class="alert alert-danger">
    <p><spring:message code="index.login.invalid"/></p>
</div>
</c:if>
<sec:authorize access="isAuthenticated()">
<img class="img-width" src="/img/icon/man.jpg"> <span class="size-25 weight" id="currentUser"></span><br>
<a href="<c:url value="/logout"/>" class="logout-btn btn btn-secondary btn-sm"><spring:message code="index.logout"/></a><br>
</sec:authorize>
<div class="navbar-header header">
    <div class="pos-f-t">
        <div class="collapse login" id="navbarToggleExternalContent">
            <sec:authorize access="isAnonymous()">
                <div class="panel-heading">
                    <div class="sidebar-header"><h5><spring:message code="index.login"/></h5></div>
                </div>
                <form role="form" name='loginForm' action="<c:url value='/login'/>" method='POST'>
                    <table>
                        <tr>
                            <td><spring:message code="form.inputLogin"/></td>
                            <td><input type='text' name='username' placeholder="<spring:message code="user.login"/>">
                            </td>
                        </tr>
                        <tr>
                            <td><spring:message code="form.inputPassword"/></td>
                            <td><input type='password' name='password'
                                       placeholder="<spring:message code="user.password"/>"></td>
                        </tr>
                        <tr>
                            <td colspan='2'>
                                <button class="btn btn-warning pull-right" type="submit"><spring:message
                                        code="index.login"/></button>
                                <a href="/registration" class="btn btn-info"><spring:message
                                        code="title.registration"/></a>
                            </td>
                        </tr>
                    </table>
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </form>
                <p>//для проверки -> ADMIN - логин: admin, пароль: admin. USER - логин alex, пароль: 123</p>
            </sec:authorize>
        </div>
        <nav class="navbar navbar-light coll">
            <sec:authorize access="isAnonymous()">
                <button class="navbar-toggler btn-lg" type="button" data-toggle="collapse"
                        data-target="#navbarToggleExternalContent"
                        aria-controls="navbarToggleExternalContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <spring:message code="index.login"/>
                </button>
            </sec:authorize>
        </nav>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark rounded">
        <h3><a href="/" class="navbar-brand"><spring:message code="title.index"/></a></h3>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/"><spring:message code="link.home"/><span
                            class="sr-only">(current)</span></a>
                </li>
                <c:forEach items="${types}" var="type">
                    <c:url var="typeurl" value="/index">
                        <c:param name="type" value="${type.idType}"/>
                        <c:param name="pageSize" value="${pageSize}"/>
                    </c:url>
                    <li class="nav-item"><a class="nav-link" href="${typeurl}" role="button">${type.nameOfType}</a></li>
                </c:forEach>
                <sec:authorize access="hasRole('ADMIN')">
                    <li class="nav-item"><a class="nav-link" href="<c:url value='/admin'/>"><spring:message
                            code="admin"/></a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <spring:message code="index.orders"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a href="/orders/false" class="orders dropdown-item btn"><spring:message
                                    code="index.orders.unpaid"/></a>
                            <a href="/orders/true" class="orders dropdown-item btn" id="paid"><spring:message
                                    code="index.orders.paid"/></a>
                        </div>
                    </li>
                </sec:authorize>
            </ul>
            <a href="<c:url value='/cart'/>" id="cart"><span class="size-25 weight" id="count"></span><img
                    src='/img/cart/cart.png'></a>
        </div>
    </nav>
    <div class="content">
        <strong><a class="lang link" href="?language=ru"><img src='/img/flags/ru.png'/></a><a class="link"
                                                                                              href="?language=en"><img
                src='/img/flags/en.png'/></a></strong>
        <div class="margin-8"></div>