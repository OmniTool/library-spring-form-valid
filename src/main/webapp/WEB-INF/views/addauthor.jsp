<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE PUBLIC "-//W3C//DTD HTML 4.01 Transitional//RU" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${pageName}</title>
    <style>
        <%@include file='/resources/css/style.css' %>
        <%@include file='/resources/css/multi-select.css' %>
    </style>
    <script src="/resources/js/jquery-2.1.0.js" type="text/javascript"></script>
    <script src="/resources/js/jquery.multi-select.js" type="text/javascript"></script>
</head>
<body>
<div class="parent topspace">
    <div class="block">
        <p><h1 class="centred leftspace"><a href="/index">Библиотека</a></h1></p>
        <p><h2 class="centred"><a href="/authors">Авторы</a> > Добавление</h2></p>
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
            <p><form:input path="secondName" type="text" value="${entity.secondName}" maxlength="64" placeholder="Фамилия" required="true" pattern=".*\S.*" title="Введите фамилию"/></p>
            <p><form:input path="firstName" type="text" value="${entity.firstName}" maxlength="64" placeholder="Имя" required="true" pattern=".*\S.*" title="Введите имя"/></p>
            <p><form:input path="middleName" type="text" value="${entity.middleName}" maxlength="64" placeholder="Отчество" pattern=".*\S.*" title="Введите отчество"/></p>
            <p><form:input path="birthYear" type="text" value="${entity.birthYear}" placeholder="Год рождения" pattern="-?\d{4}" title="Введите год в формате ГГГГ" required="true"/></p>
            <p><form:textarea path="biography" placeholder="Биография" value="${entity.biography}"/></p>
            <p><form:errors path="biography" cssClass="message" delimiter=", "/></p>
            <p><select id="my-select" size="5" name="listBook" class="listMulticatch" multiple>
                <option disabled>Выберете книги</option>
                <c:forEach var="opt" items="${sourceListBook}">
                    <p>
                        <c:set var="optionId" scope="session" value="${opt.id}"/>
                        <c:set var="isSelected" scope="session" value='false'/>

                        <c:forEach var="selected" items="${currentListBook}">
                            <c:set var="targetId" scope="session" value="${selected.id}"/>
                            <c:choose>
                                <c:when test="${optionId==targetId}">
                                    <c:set var="isSelected" scope="session" value='true'/>
                                </c:when>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${isSelected=='true'}">
                                <option selected value="${opt.id}">${opt.title}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${opt.id}">${opt.title}</option>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </c:forEach>
            </select></p>
            <button formaction="addauthor">Добавить</button>
        </form:form>
    </div>
    </p>
</div>
<script>
    $('#my-select').multiSelect();
</script>
</body>
</html>











