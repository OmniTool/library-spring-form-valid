<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Книги</title>
    <style>
        <%@include file='/resources/css/style.css' %>
    </style>
</head>
<body>
<div class="parent topspace">
    <div class="block">
        <p><h1 class="centred leftspace"><a href="/index">Библиотека</a></h1></p>
        <p><h2 class="centred"><a href="/books">Книги</a></h2></p>
    </div>
</div>
<div class="parent big_topspace">
    <p>
    <form method="POST">
        <p><input type="text" value="" maxlength="64" placeholder="Название" name="title" pattern=".*\S.*" title="Введите название">
            <button formaction="findbookbyname">Найти</button>
    </form>
    </p>
    <p class="topspace">
    <form>
        <p><button formaction="addbook">Добавить</button></p>
    </form>
    </p>
    <p class="topspace">
        <c:forEach var="item" items="${list}">
    <p>
        <a href="/findbook/${item.id}" class="content">${item.title}</a>
    </p>
    </c:forEach>
    </p>
</div>
</body>
</html>