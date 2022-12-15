<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="../errorPage.jsp" %>
<jsp:include page="headerAdmin.jsp"/>
<title><spring:message code="admin.types"/></title>
<script type="text/javascript" src="/resources/js/types.js"></script>
<h1 class="h2"><spring:message code="admin.types"/></h1>
</div>
<div id="errorAddType" class="hide alert alert-danger" role="alert"><spring:message code="error.type.create"/></div>
<div id="errorUpdateType" class="hide alert alert-danger" role="alert"><spring:message code="error.type.update"/></div>
<div id="errorDeleteType" class="hide alert alert-danger" role="alert"><spring:message code="error.type.delete"/></div>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="number"/></th>
            <th><spring:message code="admin.type"/></th>
            <th width="100"></th>
            <th width="100"></th>
        </tr>
        </thead>
        <c:forEach items="${types}" var="types" varStatus="loop">
            <tbody>
            <tr>
                <td>${types.idType}</td>
                <td id="tdUpdateType${loop.index}"><p id="typeName${loop.index}" class="show">${types.nameOfType}</p>
                    <form class="form-group col-md-6" id="updateType${loop.index}">
                        <input id="updateTypeName${loop.index}" type="text" class="hide form-control"
                               placeholder="${types.nameOfType}" min="2" max="16" oninput="setCustomValidity('')"
                               oninvalid="this.setCustomValidity('<spring:message code="nameOfType.invalid"/>')"
                               required/>
                        <input type="submit" id="typeUpdateOk${loop.index}" class="hide btn btn-primary custom-width"
                               value="OK"/><a id="updateTypeClose${loop.index}" onclick="closeUpdate(${loop.index})"
                                              class="hide btn btn-primary custom-width margin-left-5"><spring:message
                            code="button.close"/></a></form>
                </td>
                <td><a id="updateTypeBtn${loop.index}" onclick="updateType(${types.idType}, ${loop.index})"
                       class="show btn btn-primary width100"><spring:message code="button.update"/></a></td>
                <td><a class="btn btn-danger custom-width" onclick="deleteType(${types.idType})"><spring:message
                        code="button.delete"/></a></td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
<hr>
<h2><spring:message code="admin.type.add"/></h2><br>
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
<div class="row">
    <div id="divAddType" class="form-group col-md-4">
        <form id="addType">
            <input id="nameOfType" class="form-control" type="text" min="2" max="16"
                   oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="nameOfType.invalid"/>')"
                   placeholder="<spring:message code="admin.type.name"/>" required/><br/>
            <input type="submit" value="<spring:message code="button.add"/>"
                   class="btn btn-primary custom-width right"/>
        </form>
    </div>
</div>
<jsp:include page="footerAdmin.jsp"/>