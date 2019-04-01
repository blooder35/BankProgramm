<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Lanser35
  Date: 28.03.2019
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    <spring:form method="get" action="/newClient/registrationForm">--%>
<%--    <spring:button>Register new Client</spring:button>--%>
<%--    </spring:form>--%>
<form action="/newClient/registrationForm" method="get">
    <input type="submit" value="Register new client"/>
</form>
<form action="/list/clients" method="get">
    <input type="submit" value="List existing clients"/>
</form>
<form action="/list/alikeClientsForm" method="get">
    <input type="submit" value="List alike clients">
</form>
<form action="/transaction/newTransaction" method="get">
    <input type="submit" value="New transaction"/>
</form>
</body>
</html>
