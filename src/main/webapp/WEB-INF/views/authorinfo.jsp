<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>${entity.firstName} ${entity.middleName} ${entity.secondName}</title>
    <style>
        <%@include file='/resources/css/style.css' %>
    </style>
</head>
<body>
<div class="parent topspace">
    <div class="block">
        <p><h1 class="centred leftspace"><a href="/index">Библиотека</a></h1></p>
        <p><h2 class="centred"><a href="/author/list">Авторы</a> > ${entity.firstName} ${entity.middleName} ${entity.secondName}</h2></p>
    </div>
</div>
<div class="parent big_topspace">
    <p>
    <form method="GET">
        <p><input TYPE="button" VALUE="Изменить"
                  onclick="window.location.href='/author/edit/${entity.id}'"></p>
    </form>
    <form method="GET">
        <p><input TYPE="button" VALUE="Удалить"
                  onclick="window.location.href='/author/remove/${entity.id}'"></p>
    </form>
    </p>
    <p>
        <div class="block">
    <p><h3 class="centred">Год рождения</h3></p>
    <p class="centred">${entity.birthYear}</p>
    <p class="topspace"><h3 class="centred">Биография</h3></p>
    <p class="centred content">${entity.biography}</p>
    <p class="topspace"><h3 class="centred">Книги</h3></p>
    <p class="centred">
        <c:forEach var="item" items="${currentListBook}">
    <p class="centred">
        <a href="/book/${item.id}" class="content">${item.title}</a>
    </p>
    </c:forEach>
    </p>
    </div>
    </p>
</div>
</body>
</html>