<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="../errorPage.jsp" %>
<jsp:include page="headerAdmin.jsp"/>
<title><spring:message code="admin.types"/></title>
<script type="text/javascript" src="/resources/js/places.js"></script>
<h1 class="h2"><spring:message code="admin.places"/></h1>
</div>
<div id="errorAddPlace" class="hide alert alert-danger" role="alert"><spring:message code="error.place.create"/></div>
<div id="errorUpdatePlace" class="hide alert alert-danger" role="alert"><spring:message
        code="error.place.update"/></div>
<div id="errorDeletePlace" class="hide alert alert-danger" role="alert"><spring:message
        code="error.place.delete"/></div>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th class="width100"><spring:message code="number"/></th>
            <th class="width200"><spring:message code="admin.place.city"/></th>
            <th class="width300"><spring:message code="admin.place.address"/></th>
            <th class="width300"><spring:message code="admin.place.name"/></th>
            <th class="width100"></th>
            <th class="width100"></th>
        </tr>
        </thead>
    </table>
    <c:forEach items="${places}" var="places" varStatus="loop">
        <form id="form${loop.index}">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th class="width100"></th>
                    <th class="width200"></th>
                    <th class="width300"></th>
                    <th class="width300"></th>
                    <th class="width100"></th>
                    <th class="width100"></th>
                </tr>
                </thead>
                <tr>
                    <td>${places.idPlace}</td>
                    <td id="tdCity${loop.index}"><p id="city${loop.index}" class="show width200">${places.city}</p>
                        <input id="updateCity${loop.index}" type="text" class="hide form-control width200"
                               placeholder="${places.city}" pattern="[^0-9]{2,30}"
                               oninput="setCustomValidity('')"
                               oninvalid="this.setCustomValidity('<spring:message code="city.invalid"/>')" required/>
                    </td>
                    <td id="tdAddress${loop.index}"><p id="address${loop.index}" class="show width300">${places.address}</p>
                        <input id="updateAddress${loop.index}" type="text" class="hide form-control width300"
                               placeholder="${places.address}" oninput="setCustomValidity('')"
                               oninvalid="this.setCustomValidity('<spring:message code="address.empty"/>')" required/></td>
                    <td id="tdPlaceName${loop.index}"><p id="placeName${loop.index}"
                                                         class="show width300">${places.placeName}</p>
                        <input id="updatePlaceName${loop.index}" type="text" class="hide form-control width300"
                               placeholder="${places.placeName}" oninput="setCustomValidity('')"
                               oninvalid="this.setCustomValidity('<spring:message code="placeName.empty"/>')" required/></td>
                    <td><input type="submit" id="placeUpdateOk${loop.index}" class="hide btn btn-primary custom-width"
                               value="OK"/>
                        <a id="updatePlaceClose${loop.index}" onclick="closeUpdate(${loop.index})"
                           class="hide btn btn-primary width100 margin-left-5"><spring:message code="button.close"/></a>
                        <a id="updatePlaceBtn${loop.index}" onclick="updatePlace(${places.idPlace}, ${loop.index})"
                           class="show btn btn-primary width100"><spring:message code="button.update"/></a></td>
                    <td>
                        <a class="btn btn-danger custom-width"
                           onclick="deletePlace(${places.idPlace})"><spring:message code="button.delete"/></a>
                    </td>
                </tr>
            </table>
        </form>
    </c:forEach>
</div>
<hr>
<h2><spring:message code="admin.place.add"/>:</h2><br>
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
<form id="addPlace">
    <div class="row">
        <div id="divAddCity" class="form-group col-md-4">
            <h5><spring:message code="admin.place.cityInput"/></h5>
            <input id="city" type="text" class="form-control" pattern="[^0-9]{2,30}"
                   oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="city.invalid"/>')"
                   placeholder="<spring:message code="admin.place.city"/>" required/>
        </div>
    </div>
    <div class="row">
        <div id="divAddAddress" class="form-group col-md-4">
            <h5><spring:message code="admin.place.addressInput"/></h5>
            <input id="address" type="text" class="form-control" oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="address.empty"/>')"
                   placeholder="<spring:message code="admin.place.address"/>" required/>
        </div>
    </div>
    <div class="row">
        <div id="divAddPlaceName" class="form-group col-md-4">
            <h5><spring:message code="admin.place.nameInput"/></h5>
            <input id="placeName" type="text" class="form-control" oninput="setCustomValidity('')"
                   oninvalid="this.setCustomValidity('<spring:message code="placeName.empty"/>')"
                   placeholder="<spring:message code="admin.place.name"/>" required/>
            <input type="submit" value="<spring:message code="button.add"/>"
                   class="btn btn-primary custom-width right"/>
        </div>
    </div>
</form>
<jsp:include page="footerAdmin.jsp"/>