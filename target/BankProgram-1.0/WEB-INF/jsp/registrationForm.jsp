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
    <title>RegistrationForm</title>
    <script src="<c:url value="/resources/js/registrationForm.js"/>"></script>
</head>
<body>
<jsp:include page="menu.jsp"/>
${message}
<spring:form modelAttribute="newClient" method="post" action="/newClient/register">
    <spring:input path="name" placeholder="Имя" required="true"/>
    <spring:input path="surName" placeholder="Фамилия" required="true"/>
    <spring:input path="patrName" placeholder="Отчество" required="true"/>
    <spring:input path="passportNumber" placeholder="Серия и номер паспорта" required="true"/>
    <spring:input type="date" path="dateOfBirth" required="true"/>
    <spring:button id="submitRegistrationButton">RegisterClient</spring:button>
</spring:form>
</body>
</html>
