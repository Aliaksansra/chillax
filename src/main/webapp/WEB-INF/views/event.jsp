<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="errorPage.jsp" %>
<jsp:include page="header.jsp"/>
<title><spring:message code="title.event"/></title>
<script type="text/javascript" src="/resources/js/book.js"></script>
<script type="text/javascript" src="/resources/js/comments.js"></script>
<div id="bookSuccess" class="hide alert alert-success text-center">
    <p><spring:message code="cart.book.success"/></p>
</div>
<h2 id="message"></h2>
<div class="content">
    <h2>${event.title}</h2>
    <img src="/img/${event.poster}" class="img-thumbnail img-event"><br>
    <h5><spring:message code="event.date"/></h5>
    <p><fmt:formatDate value="${event.datetimeOfEvent}" pattern="dd-MM-yyyy hh:mm"/></p>
    <hr>
    <h5><spring:message code="event.price"/></h5>
    <p>${event.price} BYN</p>
    <hr>
    <h5><spring:message code="event.place"/></h5>
    <p>${event.place.placeName}</p>
    <p>${event.place.city}, ${event.place.address}</p><br clear="all">
    <hr>
    <h5><spring:message code="event.description"/></h5>
    <p>${event.description}</p>
    <hr>
    <h5><spring:message code="event.tickets.remainder"/></h5>
    <p id="remainder">${remainderOfTickets}</p>
    <input type="button" class="counter" value="-" id="minus"/>
    <input id="amount" type="number" min="1" class="number" value="1"/>
    <input type="button" class="counter" value="+" id="plus"/>
    <input type="hidden" id="idEvent" value="${event.idEvent}"/>
    <button class="btn btn-warning custom-width" id="book"><spring:message code="button.book"/></button>
    <hr>
    <br>
    <h5><spring:message code="event.comments"/></h5>
    <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
    <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
    <div id="errorAddComment" class="hide alert alert-danger" role="alert"><spring:message code="error.comment.create"/></div>
    <div id="errorUpdateComment" class="hide alert alert-danger" role="alert"><spring:message code="error.comment.update"/></div>
    <div id="errorDeleteComment" class="hide alert alert-danger" role="alert"><spring:message code="error.comment.delete"/></div>
    <sec:authorize access="isAnonymous()">
        <div class="alert alert-warning" role="alert"><spring:message code="comment.anonym"/></div>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <div class="row">
            <div class="form-group col-md-7">
                <span class="span-comment" id="login">${login}</span>
                <input type="text" id="newComment" class="form-control input-lg"
                       placeholder="<spring:message code="comment.add"/>"
                       required/>
            </div>
        </div>
    </sec:authorize>
    <c:forEach items="${comments}" var="comments" varStatus="loop">
        <div class="div-comment row">
            <input type="hidden" id="idComments" value="${comments.idComments}"/>
            <div class="col-lg-1, col-md-1"><img class="left" src="/img/icon/avatar.png"></div>
            <div class="col-lg-8, col-md-8"><span class="span-comment">${comments.login}, <fmt:formatDate
                    value="${comments.dateOfComment}" pattern="dd-MM-yyyy"/></span><br>
                <p id="current${loop.index}" class="inline size-25">${comments.comment}</p>
                <input id="update${loop.index}" type="text" class="hide form-control input-lg"
                       placeholder="${comments.comment}" required/>
                <c:if test="${login == comments.login || login == 'admin'}">
                    <button class="btn comment-btn" onclick="deleteComment(${comments.idComments})"><spring:message
                            code="button.delete"/></button>
                </c:if>
                <c:if test="${login == comments.login}">
                    <button id="updateButton${loop.index}" class="inline btn comment-btn" type="button"
                            onclick="updateComment(${comments.idComments}, ${loop.index})"><spring:message
                            code="button.update"/></button>
                    <button id="closeUpdate${loop.index}" onclick="closeUpdate(${loop.index})"
                            class="hide btn comment-btn"><spring:message code="button.close"/></button>
                </c:if>
            </div>
        </div>
        <hr>
    </c:forEach>
</div>
</div>
<jsp:include page="footer.jsp"/>