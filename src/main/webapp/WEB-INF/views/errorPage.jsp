<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE HTML5>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<h1>Database error!</h1>
<i>Unfortunately, your operation failed. Error : <%= exception.toString() %></i>
</body>
</html>