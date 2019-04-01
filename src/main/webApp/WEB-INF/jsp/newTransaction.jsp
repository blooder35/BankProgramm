<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lanser35
  Date: 30.03.2019
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>new Transaction</title>
    <script src="<c:url value="/resources/js/newTransaction.js"/>"></script>
</head>
<body>
<jsp:include page="menu.jsp"/>
*При пополнении средств наличными в поле отправитель укажите "0".
<table>
    <td>Отправитель</td>
    <td>Получатель</td>
    <td>Сумма</td>
    <td></td>
    <tr></tr>
    <spring:form modelAttribute="newTransaction" method="post" action="/transaction/addNewTransaction">
        <td><spring:input path="sender" placeholder="Отправитель" required="true"/></td>
        <td><spring:input path="recipient" placeholder="Получатель" required="true"/></td>
        <td><spring:input path="amount" placeholder="Сумма" required="true"/></td>
        <td><spring:button id="submitTransactionButton">Transaction</spring:button></td>
        <tr></tr>
    </spring:form>
</table>
</body>
</html>
