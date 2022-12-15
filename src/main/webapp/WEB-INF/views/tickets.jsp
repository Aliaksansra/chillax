<%@ page import="javax.swing.*" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="errorPage.jsp" %>
<jsp:include page="header.jsp"/>
<title><spring:message code="tickets"/></title>
<div class="content">
    <p class="alert alert-info w-50 p-3 text-center"><strong><spring:message
            code="payment.success"/></strong></p>
    <button class='btn btn-danger right' onclick='window.printDiv();'><spring:message code="button.print"/></button>
    <h3><spring:message code="tickets.title"/></h3><br/>
    <div id="DivIdToPrint" class="content">
        <c:forEach items="${tickets}" var="tickets">
            <c:url var="myurl" value="/qrCode">
                <c:param name="guid" value="${tickets.guid}"/>
            </c:url>
            <p>
                <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xlink = "http://www.w3.org/1999/xlink" width="100%" viewBox="0 0 800 250"
                     class="border border-secondary rounded">
                    <image x="630" y="80" width="170" height="170" xlink:href='${myurl}'></image>
                    <text x="15" y="45" fill="#ED6E46" font-size="40" font-family="'Leckerli One', cursive">
                        <spring:message
                                code="title.index"/></text>
                    <text x="280" y="35" fill="#ED6E46" font-size="25" font-family="'Leckerli One', cursive">
                        <spring:message code="ticket"/></text>
                    <text x="15" y="80" fill="black" font-size="25"
                          font-family="'Leckerli One', cursive">${tickets.event.title}</text>
                    <text x="160" y="120" fill="black" font-size="25"
                          font-family="'Leckerli One', cursive">${tickets.event.place.placeName}</text>
                    <text x="160" y="150" fill="black" font-size="20"
                          font-family="'Leckerli One', cursive"><spring:message
                            code="admin.place.address"/>: ${tickets.event.place.city}, ${tickets.event.place.address}</text>
                    <text x="160" y="180" fill="black" font-size="20"
                          font-family="'Leckerli One', cursive"><spring:message code="ticket.datetime"/> <fmt:formatDate
                            value="${tickets.event.datetimeOfEvent}" pattern="dd-MM-yyyy hh:mm"/></text>
                    <text x="160" y="210" fill="black" font-size="20"
                          font-family="'Leckerli One', cursive"><spring:message
                            code="ticket.price"/>: ${tickets.event.price} BYN
                    </text>
                    <text x="600" y="30" fill="black" font-size="12" font-family="'Leckerli One', cursive">
                        <spring:message
                                code="order.id"/>: ${tickets.order.idOrders}</text>
                    <text x="600" y="45" fill="black" font-size="12" font-family="'Leckerli One', cursive">
                        <spring:message
                                code="order.data"/>: <fmt:formatDate value="${tickets.order.dateOfBooking}"
                                                                     pattern="dd-MM-yyyy"/></text>
                </svg>
            </p>
            <br/>
            <hr/>
        </c:forEach>
    </div>
</div>
</div>
<jsp:include page="footer.jsp"/>