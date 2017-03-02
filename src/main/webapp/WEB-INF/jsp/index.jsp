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
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <style type="text/css">

  .file-upload {
       position: relative; /* Даем возможность делать позиционирование, внутри данного элемента */
       overflow: hidden; /* Все что выходит за пределы - скрываем */
       width: 20%; /* Задаем ширину кнопки выбора файла */
       height: 20px; /* Задаем высоту кнопки выбора файла */
       background: #6da047;
       border-radius: 3px;
       padding: 8px 4px;
       color: #fff;
       text-align: center;
  }
  .file-upload:hover {
       background: #7aad55;
  }
  .file-upload input[type="file"]{
      display: none; /* Обязательно скрываем настоящий Input File */
  }
  .file-upload label {
       /* Растягиваем label на всю возможную площадь блока .file-upload */
       display: block;
       position: absolute;
       top: 0;
       left: 0;
       width: 100%;
       height: 100%;
       cursor: pointer;
  }
  .file-upload span {
       line-height: 36px; /* Делаем вертикальное выравнивание текста, который написан на кнопке */
  }

    </style>
    <script>
    function check(){
        var files = $("#choose_btn")[0].files;

        for (var i = 0; i < files.length; i++)
        {
         alert(files[i].name);
        }


    }
    </script>
</head>
<body>
<h1> ${code} </h1>
<div class="main">
    <form class="common" enctype="multipart/form-data" method="post" action="/upload">
       <div class="file-upload">
            <label>
                 <input id="choose_btn" type="file" min="1" max="9999" name="file[]" multiple="true" onchange="check()"/>
                 <span>Выберите файл</span>
            </label>
       </div>
       <input type="text" id="filename" class="filename" disabled>
       <input type="hidden" name="sessionId" value="${sessionId}">
       <input class="btn" type="submit" name="submit" />

    </form>
</div>
</body>
</html>