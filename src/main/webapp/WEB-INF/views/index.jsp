<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="errorPage.jsp" %>
<jsp:include page="header.jsp"/>
<script type="text/javascript" src="/resources/js/event.js"></script>
<title><spring:message code="title.index"/></title>
<div id="errorDeleteEvent" class="hide alert alert-danger" role="alert"><spring:message
        code="error.event.delete"/></div>
<input type="hidden" id="csrfTokenIndex" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeaderIndex" value="${_csrf.headerName}"/>
<div class="btn-group right margin-right">
    <button type="button" class="btn btn-warning"><spring:message code="button.pagesize"/></button>
    <button type="button" class="btn btn-warning dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <span class="sr-only">Toggle Dropdown</span>
    </button>
    <div class="dropdown-menu">
        <c:forEach begin="4" step="4" end="28" var="pageSize">
            <c:url var="pageSizeUrl" value="/index">
                <c:param name="type" value="${currentType}"/>
                <c:param name="pageSize" value="${pageSize}"/>
            </c:url>
            <a class="dropdown-item" href="${pageSizeUrl}">${pageSize}</a>
        </c:forEach>
    </div>
</div>
<h2><spring:message code="index.event"/>:</h2>
<div class="row">
    <c:forEach items="${events}" var="events">
        <div class="event_block col-lg-3 col-md-3 col-sm-3 col-xs-6">
            <a href="/event/${events.idEvent}"><img src='/img/${events.poster}' alt="Image"></a>
            <h4 class="vertical-align h4-index">${events.title}</h4>
            <p><fmt:formatDate value="${events.datetimeOfEvent}" pattern="dd-MM-yyyy hh:mm"/></p>
            <p>${events.price} BYN</p>
            <p class="height30">${events.place.placeName}</p><br>
            <sec:authorize access="hasRole('ADMIN')">
                <a href="<c:url value='/admin/event/update/${events.idEvent}'/>" class="btn btn-danger btn-sm"><spring:message
                        code="button.update"/></a>
                <button onclick="deleteEvent(${events.idEvent})" class="btn btn-danger btn-sm"><spring:message
                        code="button.delete"/></button>
            </sec:authorize>
        </div>
        <br/>
        <hr/>
    </c:forEach>
</div>
<div class="margin-8"></div>
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
    <c:forEach begin="0" end="${count}" var="page">
        <c:url var="myurl" value="/index">
            <c:param name="type" value="${currentType}"/>
            <c:param name="page" value="${page+1}"/>
            <c:param name="pageSize" value="${pageSize}"/>
        </c:url>
        <a href="${myurl}" class="pagin btn btn-warning" role="button">${page+1}</a>
    </c:forEach>
</div>
</div>
<jsp:include page="footer.jsp"/>
