<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="errorPage.jsp" %>
<jsp:include page="header.jsp"/>
<title><spring:message code="cart"/></title>
<script type="text/javascript" src="/resources/js/cart.js"></script>
<div id="errorConfirmOrder" class="hide alert alert-danger" role="alert"><spring:message code="error.order.confirm"/></div>
<div class="content">
    <h2><spring:message code="cart"/>:</h2>
    <p id="order"></p>
    <table class="table">
        <tr>
            <th><spring:message code="number"/></th>
            <th><spring:message code="event.name"/></th>
            <th><spring:message code="event.place"/></th>
            <th><spring:message code="event.date"/></th>
            <th><spring:message code="cart.tickets.number"/></th>
            <th><spring:message code="cart.price"/></th>
            <th width="100"></th>
        </tr>
        <tr>
            <td id="number"></td>
            <td id="title"></td>
            <td id="place"></td>
            <td id="date"></td>
            <td id="tickets"></td>
            <td id="price"></td>
            <td id="delete"></td>
        </tr>
    </table>
    <hr>
    <br>
    <h4 id="cost" class="show"><spring:message code="cart.price.total"/></h4>
    <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
    <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
    <div class="hide alert alert-warning w-50 p-3 text-center" id="emptyCartMessage"><strong><spring:message
            code="cart.empty"/></strong></div>
    <sec:authorize access="isAuthenticated()">
        <button class='show btn btn-danger float-right' id='confirm'><spring:message code="button.order"/></button>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        <button class='show btn btn-danger float-right' id='anonym'><spring:message code="button.order"/></button>
        <div id="links" class="hide">
            <p class="alert alert-danger w-50 p-3 text-center" id="anonymMessage"><strong><spring:message
                    code="user.logIn.not"/></strong></p>
            <button class="navbar-toggler btn-lg" type="button" data-toggle="collapse"
                    data-target="#navbarToggleExternalContent"
                    aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                <spring:message code="index.login"/>
            </button>
            <a href="/registration" class="btn btn-warning"><spring:message code="title.registration"/></a>
        </div>
    </sec:authorize>
</div>
</div>
<jsp:include page="footer.jsp"/>