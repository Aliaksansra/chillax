<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="../errorPage.jsp" %>
<jsp:include page="headerAdmin.jsp"/>
<title><spring:message code="admin.event.update"/></title>
<script type="text/javascript" src="/resources/js/event.js"></script>
</div>
<div id="errorUpdateEvent" class="hide alert alert-danger" role="alert"><spring:message
        code="error.event.update"/></div>
<div id="messageNotUniquePoster" class="hide alert alert-warning" role="alert"><spring:message
        code="event.poster.notunique"/></div>
<h2><spring:message code="admin.event.update"/>:</h2><br/>
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
<form id="updateEvent">
    <input type="hidden" id="updateIdEvent" value="${event.idEvent}"/>
    <div class="row">
        <div id="divUpdatePoster" class="form-group col-md-4">
            <h5><spring:message code="event.poster"/></h5>
            <input id="file" type="file" name="file" class="form-control"/>
        </div>
    </div>
    <div class="row">
        <div id="divUpdateTitle" class="form-group col-md-4">
            <h5><spring:message code="event.name"/>:</h5>
            <input id="updateTitle" type="text" class="form-control" placeholder="${event.title}"
                   oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="title.empty"/>')" required/>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-4">
            <h5><spring:message code="event.description"/>:</h5>
            <textarea id="updateDescription" class="form-control" placeholder="${event.description}"></textarea>
        </div>
    </div>
    <div class="row">
        <div id="divUpdateEventDate" class="form-group col-md-4">
            <h5><spring:message code="event.date"/>:</h5>
            <input type="datetime-local" id="updateDatetimeOfEvent" class="form-control" oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="date.empty"/>')" required/>
        </div>
    </div>
    <div class="row">
        <div id="divUpdatePrice" class="form-group col-md-4">
            <h5><spring:message code="event.price"/>:</h5>
            <input type="number" id="updatePrice" class="form-control" placeholder="${event.price}"
                   pattern="^[1-9][0-9.]{0,10}" oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="price.invalid"/>')" required/>
        </div>
    </div>
    <div class="row">
        <div id="divUpdateAllTickets" class="form-group col-md-4">
            <h5><spring:message code="event.tickets"/>:</h5>
            <input type="number" id="updateAllTickets" class="form-control" placeholder="${event.allTickets}"
                   pattern="^[1-9][0-9]{0,10}"
                   oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="allTickets.invalid"/>')" required/>
        </div>
    </div>
    <div class="row">
        <div id="divUpdateEventPlace" class="form-group col-md-4">
            <h5><spring:message code="event.place"/>:</h5>
            <form:select path="place" id="updatePlace" items="${place}" multiple="true" itemLabel="placeName"
                         itemValue="idPlace" class="form-control input-sm" placeholder="${event.place}"/><a
                href="<c:url value='/admin/places'/>" class="btn btn-danger btn-sm"><spring:message
                code="button.add.place"/></a>
        </div>
    </div>
    <div class="row">
        <div id="divUpdateEventType" class="form-group col-md-4">
            <h5><spring:message code="admin.type"/>:</h5>
            <form:select path="type" id="updateType" items="${type}" multiple="true" itemLabel="nameOfType"
                         itemValue="idType" class="form-control input-sm" placeholder="${event.type}"/><a
                href="<c:url value='/admin/types'/>" class="btn btn-danger btn-sm"><spring:message
                code="button.add.type"/></a><br><br>
            <a class="btn btn-primary custom-width margin-left-5 right" href="<c:url value='/' />"><spring:message
                    code="button.close"/></a><input type="submit" value="<spring:message code="button.update"/>"
                                                    class="btn btn-primary custom-width right"/>
        </div>
    </div>
</form>
<div id="successMsgModal" class="modal fade">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="success"/></h4>
            </div>
            <div class="modal-body">
                <p><spring:message code="success.event.updated"/></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footerAdmin.jsp"/>