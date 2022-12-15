<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="../errorPage.jsp" %>
<!DOCTYPE HTML5>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="/img/icon/favicon.ico">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="/resources/css/admin.css" rel="stylesheet">
    <title><spring:message code="admin"/></title>
    <script type='text/javascript' src='/resources/libs/jquery-3.3.1.js'></script>
    <script type="text/javascript" src="/resources/js/header.js"></script>
</head>
<body>
<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="/"><spring:message code="title.index"/></a>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="<c:url value="/logout"/>"><spring:message code="index.logout"/></a>
        </li>
    </ul>
</nav>
<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link active" href="<c:url value='/admin'/>">
                            <span data-feather="home"></span>
                            <spring:message code="admin"/><span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <div class="nav-link dropright">
                            <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                <span data-feather="book"></span>
                                <spring:message code="admin.orders"/>
                            </button>
                            <div class="dropdown-menu">
                                <a class="nav-link" href="/admin/orders/false">
                                    <spring:message code="index.orders.unpaid"/>
                                </a>
                                <a class="nav-link" href="/admin/orders/true">
                                    <spring:message code="index.orders.paid"/>
                                </a>
                            </div>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/event/new">
                            <span data-feather="sun"></span>
                            <spring:message code="admin.event.add"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/users">
                            <span data-feather="users"></span>
                            <spring:message code="admin.user"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/types">
                            <span data-feather="layers"></span>
                            <spring:message code="admin.types.menu"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/places">
                            <span data-feather="navigation"></span>
                            <spring:message code="admin.place"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/roles">
                            <span data-feather="triangle"></span>
                            <spring:message code="admin.role.menu"/>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <strong><a class="lang link" href="?language=ru"><img src='/img/flags/ru.png'/></a><a class="link"
                                                                                                  href="?language=en"><img
                    src='/img/flags/en.png'/></a></strong>
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">