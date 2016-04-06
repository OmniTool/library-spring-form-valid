<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE PUBLIC "-//W3C//DTD HTML 4.01 Transitional//RU" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
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
        <p><h2 class="centred"><a href="/book/list">Книги</a> > Добавление</h2></p>
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
            <p><form:input path="title" type="text" value="${entity.title}" maxlength="64" placeholder="Название книги" required="true" pattern=".*\S.*" title="Введите название книги"/></p>
            <p><form:errors path="title" cssClass="message" delimiter=", "/></p>
            <p><form:input path="pubYear" type="text" value="${entity.pubYear}" placeholder="Год публикации" pattern="-?\d{4}" title="Введите год в формате ГГГГ" required="true"/></p>
            <p><form:errors path="pubYear" cssClass="message" delimiter=", "/></p>
            <p><select class="listMulticatch" size="1" name="genereId">
                <option disabled>Жанр</option>
                <c:forEach var="opt" items="${sourceListGenre}">
                    <p>
                        <c:set var="optionId" scope="session" value="${opt.id}"/>
                        <c:set var="targetId" scope="session" value="${entity.genreId}"/>
                        <c:choose>
                            <c:when test="${optionId==targetId}">
                                <option selected value="${opt.id}">${opt.title}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${opt.id}">${opt.title}</option>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </c:forEach>
            </select></p>
            <p><select id="my-select" size="5" name="listAuthor" class="listMulticatch" multiple>
                <option disabled>Выберете авторов</option>
                <c:forEach var="opt" items="${sourceListAuthor}">
                    <p>
                        <c:set var="optionId" scope="session" value="${opt.id}"/>
                        <c:set var="isSelected" scope="session" value='false'/>

                        <c:forEach var="selected" items="${currentListAuthor}" >
                            <c:set var="targetId" scope="session" value="${selected.id}"/>
                            <c:choose>
                                <c:when test="${optionId==targetId}">
                                    <c:set var="isSelected" scope="session" value='true'/>
                                </c:when>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${isSelected=='true'}">
                                <option selected value="${opt.id}">${opt.firstName} ${opt.middleName} ${opt.secondName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${opt.id}">${opt.firstName} ${opt.middleName} ${opt.secondName}</option>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </c:forEach>
            </select></p>
            <p><button formaction="/book/add">Добавить</button></p>
        </form:form>
    </div>
    </p>
</div>
<script>
    $('#my-select').multiSelect();
</script>
</body>
</html>
