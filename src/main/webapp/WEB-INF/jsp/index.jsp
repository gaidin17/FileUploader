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
    <script src="/js/jquery-3.1.1.min.js"></script>
    <script>
        function check(){
            var files = $("#choose_btn")[0].files;
            $("#target").text("Вы выбрали " + files.length + " файлов");
        }

    </script>
</head>
<body>
<h1>Выберите файлы для отправки</h1>
<div class="main">
    <form class="common" enctype="multipart/form-data" method="post" action="/upload">
        <div class="file-upload">
            <label>
                <input id="choose_btn" type="file" min="1" max="9999" name="file[]" multiple="true" onchange="check()"/>
                <span>Выберите файл</span>
            </label>
        </div>
        <input type="hidden" name="sessionId" value="${sessionId}">
        <input class="btn btn_submit" type="submit" name="submit"/></br>
        <span id="target"></span>
    </form>

</div>

</body>
</html>