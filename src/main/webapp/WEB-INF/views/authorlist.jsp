<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Авторы</title>
    <style>
        <%@include file='/resources/css/style.css' %>
    </style>
</head>
<body>
<div class="parent topspace">
    <div class="block">
        <p><h1 class="centred leftspace"><a href="/index">Библиотека</a></h1></p>
        <p><h2 class="centred"><a href="/author/list">Авторы</a></h2></p>
    </div>
</div>
<div class="parent big_topspace">
    <p>
    <form method="POST">
        <input type="text" value="" maxlength="64" placeholder="Фамилия" name="secondName" pattern=".*\S.*" title="Введите фамилию" class="searchfield">
        <input type="text" value="" maxlength="64" placeholder="Имя" name="firstName" pattern=".*\S.*" title="Введите имя" class="searchfield">
        <input type="text" value="" maxlength="64" placeholder="Отчество" name="middleName" pattern=".*\S.*" title="Введите отчество" class="searchfield">
        <button formaction="/author/find">Найти</button>
    </form>
    </p>
    <p class="topspace">
    <form>
        <p><button formaction="/author/add">Добавить</button></p>
    </form>
    </p>
    <p class="topspace">
        <c:forEach var="item" items="${list}">
    <p>
        <a href="/author/${item.id}" class="content">${item.firstName} ${item.middleName} ${item.secondName}</a>
    </p>
    </c:forEach>
    </p>
</div>
</body>
</html>





