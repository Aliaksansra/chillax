<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="header.jsp"/>
<title>Error 403</title>
<br>
<h2>HTTP Status 403 - Access is denied</h2>
<hr><br>
<c:choose>
    <c:when test="${empty username}">
        <h3><spring:message code="error.access.denied"/></h3>
    </c:when>
    <c:otherwise>
        <h3>${username} <br/><spring:message code="error.access.denied"/></h3>
    </c:otherwise>
</c:choose>
</div>
<jsp:include page="footer.jsp"/>