<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");

%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>

<c:set var="ctx" value="${pageContext['request'].contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>FileUploader</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<c:choose>
    <c:when test="${empty error}">
        <h1>Ваш номер клиента:</h1>
        <h1 class="client_code">${code}</h1>
    </c:when>
    <c:otherwise>
        <h1>${error}</h1>
    </c:otherwise>
</c:choose>
<form enctype="multipart/form-data" method="post" action="/">
    <input class="btn" type="submit" name="submit" value="Вернуться"/>
</form>

</body>
</html>