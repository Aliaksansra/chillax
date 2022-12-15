<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="../errorPage.jsp" %>
<jsp:include page="headerAdmin.jsp"/>
<title><spring:message code="admin.orders"/></title>
<script type="text/javascript" src="/resources/js/orders.js"></script>
<h1 class="h2"><spring:message code="admin.orders"/>:</h1>
</div>
<div id="errorDeleteOrder" class="hide alert alert-danger" role="alert"><spring:message
        code="error.order.delete"/></div>
<div id="errorGetOrder" class="hide alert alert-danger" role="alert"><spring:message
        code="error.order.get"/></div>
<div id="errorGetUser" class="hide alert alert-danger" role="alert"><spring:message
        code="error.user.get"/></div>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="order.id"/></th>
            <th><spring:message code="cart.price"/></th>
            <th><spring:message code="order.data"/></th>
            <th><spring:message code="order.discount"/></th>
            <th width="100"></th>
            <th width="100"></th>
        </tr>
        </thead>
        <c:forEach items="${orders}" var="order">
            <tbody>
            <tr>
                <td class="idOrder">${order.idOrders}</td>
                <td>${order.totalPrice} BYN</td>
                <td><fmt:formatDate value="${order.dateOfBooking}" pattern="dd-MM-yyyy"/></td>
                <td>${order.discount} %</td>
                <td>
                    <button onclick="moreAboutUser(${order.idUser})" id="moreAboutUser"
                            class=" btn btn-danger custom-width" data-toggle="modal" data-target="#userInfo">
                        <spring:message code="button.more.user"/></button>
                </td>
                <td>
                    <input type="hidden" id="orderGuid" value="${order.guid}"/>
                    <button onclick="moreAboutOrder(${order.paid})" id="more"
                            class=" btn btn-danger custom-width" data-toggle="modal" data-target="#orderInfo">
                        <spring:message code="button.more.order"/></button>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
<div class="modal fade bd-example-modal-lg" id="orderInfo" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="order"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table id="modaltable" class=" table">
                    <thead class="thead-inverse">
                    <tr>
                        <th><spring:message code="number"/></th>
                        <th><spring:message code="event.name"/></th>
                        <th><spring:message code="event.place"/></th>
                        <th><spring:message code="event.date"/></th>
                        <th><spring:message code="cart.tickets.number"/></th>
                        <th><spring:message code="order.tickets.price"/></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tr>
                        <td class="orderInfo" scope="row" id="eventNumber"></td>
                        <td class="orderInfo" id="eventTitle"></td>
                        <td class="orderInfo" id="eventPlace"></td>
                        <td class="orderInfo" id="eventDate"></td>
                        <td class="orderInfo" id="countOfTickets"></td>
                        <td class="orderInfo" id="ticketsPrice"></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message
                        code="button.close"/></button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade bd-example-modal-lg" id="userInfo" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="order"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="table">
                    <thead class="thead-inverse">
                    <tr>
                        <th><spring:message code="user.login"/></th>
                        <th><spring:message code="admin.user.name"/></th>
                        <th><spring:message code="admin.user.surname"/></th>
                        <th><spring:message code="admin.user.email"/></th>
                        <th><spring:message code="admin.user.phone"/></th>
                    </tr>
                    </thead>
                    <tr>
                        <td class="orderInfo" id="loginOfUser"></td>
                        <td class="orderInfo" id="nameOfUser"></td>
                        <td class="orderInfo" id="surnameOfUser"></td>
                        <td class="orderInfo" id="emailOfUser"></td>
                        <td class="orderInfo" id="phoneOfUser"></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message
                        code="button.close"/></button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footerAdmin.jsp"/>