<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lanser35
  Date: 28.03.2019
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>listAccounts</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
${message}
<br>
<div>
    <table border="1">
        <td>Номер счета</td>
        <td>Дата открытия</td>
        <td>Баланс</td>
        <td>Пополнение</td>
        <td>Перевод</td>
        <td>Просмотр транзакций</td>
        <td>Закрытие</td>
        <tr></tr>
        <c:forEach items="${accountsList}" var="account">
            <td><c:out value="${account.id}"/></td>
            <td><c:out value="${account.dateOfOpening}"/></td>
            <td><c:out value="${account.balance}"/></td>
            <td>
                <form method="GET" action="/transaction/newTransaction">
                    <input type="hidden" name="recipient" value="${account.id}"/>
                    <input type="submit" value="Deposit"/>
                </form>
            </td>
            <td>
                <form method="GET" action="/transaction/newTransaction">
                    <input type="hidden" name="sender" value="${account.id}"/>
                    <input type="submit" value="Send"/>
                </form>
            </td>
            <td>
                <form method="POST" action="/transaction/listAccount">
                    <input type="hidden" name="accountID" value="${account.id}">
                    <input type="submit" value="List account transactions">
                </form>
            </td>
            <td>
                <form method="POST" action="/accounts/closeAccount">
                    <input type="hidden" name="accountID" value="${account.id}">
                    <input type="hidden" name="ownerID" value="${account.ownerID}">
                    <input type="submit" value="Delete account">
                </form>
            </td>
            <tr></tr>
        </c:forEach>
    </table>
</body>
</html>
