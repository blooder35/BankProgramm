<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lanser35
  Date: 29.03.2019
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>listTransactions</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
Расходы:
<table border="1">
    <td>Идентификатор</td>
    <td>Сумма</td>
    <td>Отправитель</td>
    <td>Получатель</td>
    <td>Дата и время</td>
    <tr></tr>
    <c:forEach items="${transactionSenderList}" var="transaction">
        <td><c:out value="${transaction.id}"/></td>
        <td><c:out value="${transaction.amount}"/></td>
        <td><c:out value="${transaction.sender}"/></td>
        <td><c:out value="${transaction.recipient}"/></td>
        <td><c:out value="${transaction.dateAndTime}"/></td>
        <tr></tr>
    </c:forEach>
</table>
<br>
Приходы:
<table border="1">
    <td>Идентификатор</td>
    <td>Сумма</td>
    <td>Отправитель</td>
    <td>Получатель</td>
    <td>Дата и время</td>
    <tr></tr>
    <c:forEach items="${transactionRecipientList}" var="transaction">
        <td><c:out value="${transaction.id}"/></td>
        <td><c:out value="${transaction.amount}"/></td>
        <td><c:out value="${transaction.sender}"/></td>
        <td><c:out value="${transaction.recipient}"/></td>
        <td><c:out value="${transaction.dateAndTime}"/></td>
        <tr></tr>
    </c:forEach>
</table>
</body>
</html>
