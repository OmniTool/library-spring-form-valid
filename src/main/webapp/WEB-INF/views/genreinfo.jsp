<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE PUBLIC "-//W3C//DTD HTML 4.01 Transitional//RU" "http://www.w3.org/TR/html4/loose.dtd">
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
        <p><h2 class="centred"><a href="/genre/list">Жанры</a> > ${entity.title}</h2></p>
    </div>
</div>
<div class="parent big_topspace">
    <p>
        <form method="GET">
    <p><input TYPE="button" VALUE="Изменить"
              onclick="window.location.href='/genre/edit/${entity.id}'"></p>
    </form>
    <form method="GET">
        <p><input TYPE="button" VALUE="Удалить"
                  onclick="window.location.href='/genre/remove/${entity.id}'"></p>
    </form>
    </p>
    <p>
    <div class="block">
        <p class="message centred">
            <c:set var="message" value="${message}"/>
            <c:if test="${message == true}">
                <spring:message code="warning.used" />
            </c:if>
        </p>
        <p class="centred">
            <c:forEach var="item" items="${listBooks}">
        <p class="centred">
            <a href="/book/${item.id}" class="content">${item.title}</a>
        </p>
        </c:forEach>
    </p>
</div>
</p>
    <p>
        <div class="block">
            <p><h3 class="centred">Описание</h3></p>
            <p class="centred content">${entity.description}</p>
        </div>
    </p>
</div>
</body>
</html>