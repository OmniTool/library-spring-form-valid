<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        <%@include file='/resources/css/style.css' %>
    </style>
</head>
<body>
<h1>Поиск книги по заголовку</h1>
<form method="POST">
    <p><input type="text" value="" maxlength="64" placeholder="Название книги" name="title" pattern=".*\S.*" title="Введите название книги" required></p>
    <p><button formaction="findbookbyname">Найти</button></p>
</form>
</body>
</html>
