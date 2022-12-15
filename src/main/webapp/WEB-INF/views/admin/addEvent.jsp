<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="../errorPage.jsp" %>
<jsp:include page="headerAdmin.jsp"/>
<title><spring:message code="admin.event.add"/></title>
<script type="text/javascript" src="/resources/js/event.js"></script>
</div>
<div id="errorAddEvent" class="hide alert alert-danger" role="alert"><spring:message code="error.event.create"/></div>
<div id="successAddEvent" class="hide alert alert-success" role="alert"><spring:message
        code="success.event.created"/></div>
<div id="messageNotUniquePoster" class="hide alert alert-warning" role="alert"><spring:message
        code="event.poster.notunique"/></div>
<h2><spring:message code="admin.event.add"/>:</h2><br/>
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
<form id="addEvent">
    <div class="row">
        <div id="divAddPoster" class="form-group col-md-4">
            <h5><spring:message code="event.posterInput"/></h5>
                <input id="file" type="file" name="file" class="form-control"/>
        </div>
    </div>
    <div class="row">
        <div id="divAddTitle" class="form-group col-md-4">
            <h5><spring:message code="event.nameInput"/></h5>
            <input id="title" type="text" class="form-control" oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="title.empty"/>')"
                   placeholder="<spring:message code="event.name"/>" required/>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-4">
            <h5><spring:message code="event.descriptionInput"/></h5>
            <textarea id="description" class="form-control"
                      placeholder="<spring:message code="event.description"/>"></textarea>
        </div>
    </div>
    <div class="row">
        <div id="divAddEventDate" class="form-group col-md-4">
            <h5><spring:message code="event.dateInput"/></h5>
            <input type="datetime-local" id="datetimeOfEvent" class="form-control" oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="date.empty"/>')" required/>
        </div>
    </div>
    <div class="row">
        <div id="divAddPrice" class="form-group col-md-4">
            <h5><spring:message code="event.priceInput"/></h5>
            <input type="number" id="price" class="form-control"
                   placeholder="<spring:message code="event.price.example"/>" pattern="^[1-9][0-9.]{0,10}"
                   oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="price.invalid"/>')" required/>
        </div>
    </div>
    <div class="row">
        <div id="divAddAllTickets" class="form-group col-md-4">
            <h5><spring:message code="event.ticketsInput"/></h5>
            <input type="number" id="allTickets" class="form-control"
                   placeholder="<spring:message code="event.tickets"/>" pattern="^[1-9][0-9]{0,10}"
                   oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="allTickets.invalid"/>')"
                   required/>
        </div>
    </div>
    <div class="row">
        <div id="divAddEventPlace" class="form-group col-md-4">
            <h5><spring:message code="admin.place.name"/>:</h5>
            <form:select path="place" id="place" items="${place}" multiple="true" itemLabel="placeName"
                         itemValue="idPlace" class="form-control input-sm"/><a href="<c:url value='/admin/places'/>"
                                                                               class="btn btn-danger btn-sm"><spring:message
                code="button.add.place"/></a>
        </div>
    </div>
    <div class="row">
        <div id="divAddEventType" class="form-group col-md-4">
            <h5><spring:message code="admin.type"/>:</h5>
            <form:select path="type" id="type" items="${type}" multiple="true" itemLabel="nameOfType"
                         itemValue="idType" class="form-control input-sm"/><a href="<c:url value='/admin/types'/>"
                                                                              class="btn btn-danger btn-sm"><spring:message
                code="button.add.type"/></a><br><br>
            <input type="submit" value="<spring:message code="button.add"/>"
                   class="btn btn-primary custom-width right"/>
        </div>
    </div>
</form>
<jsp:include page="footerAdmin.jsp"/>