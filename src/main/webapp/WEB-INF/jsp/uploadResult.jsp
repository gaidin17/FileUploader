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
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>
<body>
<c:choose>
    <c:when test="${empty error}">
        <p>Ваш номер клиента:</p>
        <h3>${code}</h3>
    </c:when>
    <c:otherwise>
        <p>${error}</p>
    </c:otherwise>
</c:choose>
<form enctype="multipart/form-data" method="post" action="/">
    <input class="btn" type="submit" name="submit" value="Вернуться"/>
</form>

</body>
</html>