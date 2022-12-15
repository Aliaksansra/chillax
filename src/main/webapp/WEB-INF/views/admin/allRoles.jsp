<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="../errorPage.jsp" %>
<jsp:include page="headerAdmin.jsp"/>
<title><spring:message code="admin.roles"/></title>
<script type="text/javascript" src="/resources/js/roles.js"></script>
<h1 class="h2"><spring:message code="admin.roles"/></h1>
</div>
<div id="errorAddRole" class="hide alert alert-danger" role="alert"><spring:message code="error.role.create"/></div>
<div id="errorDeleteRole" class="hide alert alert-danger" role="alert"><spring:message code="error.role.delete"/></div>
<div id="errorUpdateRole" class="hide alert alert-danger" role="alert"><spring:message code="error.role.update"/></div>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="number"/></th>
            <th><spring:message code="admin.role"/></th>
            <th width="100"></th>
            <th width="100"></th>
        </tr>
        </thead>
        <c:forEach items="${roles}" var="roles" varStatus="loop">
            <tbody>
            <tr>
                <td>${roles.idRole}</td>
                <td id="tdUpdateRole${loop.index}"><p id="role${loop.index}" class="show">${roles.role}</p>
                    <c:if test="${roles.idRole > 2}">
                    <form class="form-group col-md-6" id="updateRole${loop.index}">
                        <input id="updateRoleName${loop.index}" type="text" class="hide form-control"
                               placeholder="${roles.role}" pattern="[^0-9/s]{2,16}"
                               oninput="setCustomValidity('')"
                               oninvalid="this.setCustomValidity('<spring:message code="role.invalid"/>')"
                               required/>
                        <input type="submit" id="updateRoleOk${loop.index}" class="hide btn btn-primary custom-width"
                               value="OK"/> <a id="updateRoleClose${loop.index}"
                                               onclick="closeRoleUpdate(${loop.index})"
                                               class="hide btn btn-primary custom-width margin-left-5"><spring:message
                            code="button.close"/></a></form>

                </td>
                <td><a id="updateRoleBtn${loop.index}" onclick="updateRole(${roles.idRole}, ${loop.index})"
                       class="show btn btn-primary width100"><spring:message code="button.update"/></a></td>
                <td><a class="btn btn-danger custom-width" onclick="deleteRole(${roles.idRole})"><spring:message
                        code="button.delete"/></a></td>
                </c:if>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
<hr>
<h2><spring:message code="admin.role.add"/>:</h2><br>
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
<div class="row">
    <div id="divAddRole" class="form-group col-md-4">
        <form id="addRole">
            <h5><spring:message code="admin.role.input"/></h5>
            <input id="role" class="form-control" type="text" placeholder="<spring:message code="admin.role"/>"
                   pattern="[^0-9/s]{2,16}"
                   oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="role.invalid"/>')"
                   required/><br/>
            <input type="submit" value="<spring:message code="button.add"/>"
                   class="btn btn-primary custom-width right"/>
        </form>
    </div>
</div>
<jsp:include page="footerAdmin.jsp"/>