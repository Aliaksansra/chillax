<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="errorPage.jsp" %>
<jsp:include page="header.jsp"/>
<title><spring:message code="payment"/></title>
<link rel="stylesheet" href="/resources/css/creditly.css">
<script type="text/javascript" src="/resources/js/payment.js"></script>
<script type="text/javascript" src="/resources/libs/creditly.js"></script>
<div class="content">
    <div class="alert alert-info"><h5><spring:message code="cart.order.success"/></h5></div>
    <div class="alert alert-warning" role="alert"><h5>
        <spring:message code="payment.message"/></h5>
    </div>
    <br>
    <h4><spring:message code="payment.number"/>${order.idOrders}</h4>
    <hr>
    <h4><spring:message code="payment.price"/>${order.totalPrice} BYN
        <spring:message code="payment.discount"/>${order.discount}%</h4>
    <hr>
    <br>
    <h2><spring:message code="payment.title"/></h2><br>
    <p><spring:message code="payment.fillCard"/></p>
    <form class="creditly-card-form">
        <div class="top-level-wrapper blue-theme-wrapper">
            <section class="creditly-wrapper blue-theme">
                <div class="credit-card-wrapper">
                    <div class="first-row form-group">
                        <div class="col-sm-8 controls">
                            <label class="control-label"><spring:message code="payment.card.number"/></label>
                            <input class="number credit-card-number form-control"
                                   type="text" name="number"
                                   pattern="(\d*\s){3}\d*"
                                   inputmode="numeric" autocomplete="cc-number" autocompletetype="cc-number"
                                   x-autocompletetype="cc-number"
                                   placeholder="&#149;&#149;&#149;&#149; &#149;&#149;&#149;&#149; &#149;&#149;&#149;&#149; &#149;&#149;&#149;&#149;">
                        </div>
                        <div class="col-sm-4 controls">
                            <label class="control-label"><spring:message code="payment.card.cvv"/></label>
                            <input class="security-code form-control" ·
                                   inputmode="numeric"
                                   pattern="\d*"
                                   type="text" name="security-code"
                                   placeholder="&#149;&#149;&#149;">
                        </div>
                    </div>
                    <div class="second-row form-group">
                        <div class="col-sm-8 controls">
                            <label class="control-label"><spring:message code="payment.card.owner"/></label>
                            <input class="billing-address-name form-control"
                                   type="text" name="name"
                                   placeholder="John Smith">
                        </div>
                        <div class="col-sm-4 controls">
                            <label class="control-label"><spring:message code="payment.card.valid"/></label>
                            <input class="expiration-month-and-year form-control"
                                   type="text" name="expiration-month-and-year"
                                   placeholder="MM / YY">
                        </div>
                    </div>
                    <div class="card-type"></div>
                </div>
            </section>
            <input type="hidden" id="orderGuid" value="${order.guid}"/>
            <button class="creditly-blue-theme-submit"><span><spring:message code="button.pay"/></span></button>
            <a class="btn close-style" href="<c:url value='/' />"><spring:message code="button.close"/></a>
            <p class="hide alert alert-danger w-50 p-3 text-center" id="failMessage"><strong><spring:message
                    code="payment.error"/></strong></p>
            <p>
        </div>
    </form>
    //для проверки номер карты: 4255 2001 8201 8440
</div>
</div>
<jsp:include page="footer.jsp"/>