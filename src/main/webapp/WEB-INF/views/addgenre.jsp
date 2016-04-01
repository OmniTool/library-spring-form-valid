<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE PUBLIC "-//W3C//DTD HTML 4.01 Transitional//RU" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Добавление</title>
    <style>
        <%@include file='/resources/css/style.css' %>
    </style>
</head>
<body>
<div class="parent topspace">
    <div class="block">
        <p><h1 class="centred leftspace"><a href="/index">Библиотека</a></h1></p>
        <p><h2 class="centred"><a href="/genres">Жанры</a> > Добавление</h2></p>
    </div>
</div>
<div class="parent big_topspace">
    <p class="message centred">
        <c:set var="message" value="${message}"/>
        <c:if test="${message == true}">
            <spring:message code="warning.exists" />
        </c:if>
    </p>
    <p>
    <div class="block">
        <form:form method="post" commandName="entity" cssClass="centred">
                <p><form:input path="title" type="text" value="${entity.title}" maxlength="64" placeholder="Название жанра" required="true" pattern=".*\S.*" title="Введите название жанра"/></p>
                <p><form:errors path="title" cssClass="message" delimiter=", "/></p>
                <p><form:textarea path="description" placeholder="Описание жанра" value="${entity.description}"/></p>
                <button formaction="addgenre">Добавить</button>
        </form:form>
    </div>
    </p>
</div>
</body>
</html>










