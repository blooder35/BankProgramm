<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lanser35
  Date: 28.03.2019
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AlikeClientsForm</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
Введите данные которые Вам известны о клиенте
<spring:form modelAttribute="clientData" method="get" action="/list/alikeClients">
    <spring:input path="name" placeholder="Имя"/>
    <spring:input path="surName" placeholder="Фамилия"/>
    <spring:input path="patrName" placeholder="Отчество"/>
    <spring:input path="passportNumber" placeholder="Серия и номер паспорта"/>
    <spring:input type="date" path="dateOfBirth"/>
    <spring:button id="submitRegistrationButton">GetAlikeClients</spring:button>
</spring:form>
</body>
</html>
