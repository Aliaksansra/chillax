<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="errorPage.jsp" %>
<jsp:include page="header.jsp"/>
<title><spring:message code="index.orders"/></title>
<script type="text/javascript" src="/resources/js/orders.js"></script>
<div class="content">
    <div id="errorDeleteOrder" class="hide alert alert-danger" role="alert"><spring:message
            code="error.order.delete"/></div>
    <div id="errorGetOrder" class="hide alert alert-danger" role="alert"><spring:message
            code="error.order.get"/></div>
    <c:if test="${!paid}">
        <div class="alert alert-warning" role="alert">
            <spring:message code="payment.message"/>
        </div>
    </c:if>
    <h2><spring:message code="index.orders"/>:</h2>
    <table class="table">
        <thead class="thead-inverse">
        <tr>
            <th><spring:message code="number"/></th>
            <th><spring:message code="order.data"/></th>
            <th><spring:message code="cart.price"/></th>
            <th><spring:message code="order.discount"/></th>
            <th width="100"></th>
            <th width="100"></th>
        </tr>
        </thead>
        <c:forEach items="${userOrders}" var="order" varStatus="loop">
            <tr>
                <td scope="row">${loop.index + 1}</td>
                <td><fmt:formatDate value="${order.dateOfBooking}" pattern="dd-MM-yyyy"/></td>
                <td>${order.totalPrice} BYN</td>
                <td>${order.discount} %</td>
                <td>
                    <input type="hidden" id="orderGuid" value="${order.guid}"/>
                    <button onclick="moreAboutOrder(${order.paid})" id="more"
                            class=" btn btn-danger custom-width" data-toggle="modal" data-target="#exampleModalCenter">
                        <spring:message code="button.more"/></button>
                </td>
                <c:if test="${!order.paid}">
                    <td><a class="btn btn-danger custom-width" onclick="deleteOrder()"><spring:message
                            code="button.order.cancel"/></a></td>
                </c:if>
                <c:if test="${order.paid}">
                    <td><a class="btn btn-danger custom-width" href="/orderedTickets/${order.guid}"><spring:message
                            code="button.order.tickets"/></a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
    <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
</div>
<div class="modal fade bd-example-modal-lg" id="exampleModalCenter" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle"><spring:message code="order"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table id="modaltable" class="table">
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
                <c:if test="${!paid}">
                    <button id="pay" type="button" class="btn btn-primary"><spring:message code="button.pay"/></button>
                </c:if>
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message
                        code="button.close"/></button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>