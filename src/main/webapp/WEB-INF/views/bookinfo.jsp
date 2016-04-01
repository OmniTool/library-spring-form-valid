<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>${entity.title}</title>
    <style>
        <%@include file='/resources/css/style.css' %>
    </style>
</head>
<body>
<div class="parent topspace">
    <div class="block">
        <p><h1 class="centred leftspace"><a href="/index">Библиотека</a></h1></p>
        <p><h2 class="centred"><a href="/books">Книги</a> > ${entity.title}</h2></p>
    </div>
</div>
<div class="parent big_topspace">
    <p>
    <form method="GET">
        <p><input TYPE="button" VALUE="Изменить"
                  onclick="window.location.href='/editbook/${entity.id}'"></p>
    </form>

    <form method="GET">
        <p><input TYPE="button" VALUE="Удалить"
                  onclick="window.location.href='/removebook/${entity.id}'"></p>
    </form>
    </p>
    <p>
    <div class="block">
        <p ><h3 class="centred">Год публикации</h3></p>
    <p class="centred">${entity.pubYear}</p>
    <p class="topspace"><h3 class="centred">Жанр</h3></p>
    <p class="centred"><a href="/findgenre/${entity.genre.id}" class="content">${entity.genre.title}</a></p>
    <p class="topspace"><h3 class="centred">Авторы</h3></p>
    <p class="centred">
        <c:forEach var="item" items="${entity.authorsList}">
            <p class="centred">
                <a href="/findauthor/${item.author.id}" class="content">${item.author.firstName} ${item.author.middleName} ${item.author.secondName}</a>
            </p>
        </c:forEach>
    </p>
</div>
</p>
</div>
</body>
</html>