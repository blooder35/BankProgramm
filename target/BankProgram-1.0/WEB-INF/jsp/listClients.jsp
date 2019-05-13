<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Lanser35
  Date: 28.03.2019
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>listClients</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
${message}
<table border="1">
    <td>Имя</td>
    <td>Фамилия</td>
    <td>Отчество</td>
    <td>Серия/номер паспорта</td>
    <td>Дата рождения</td>
    <td>Список счетов</td>
    <td>Список транзакций</td>
    <td>Создать новый счёт</td>
    <tr></tr>
    <c:forEach items="${clientsList}" var="client">
        <td><c:out value="${client.name}"/></td>
        <td><c:out value="${client.surName}"/></td>
        <td><c:out value="${client.patrName}"/></td>
        <td><c:out value="${client.passportNumber}"/></td>
        <td><c:out value="${client.dateOfBirth}"/></td>
        <td>
            <form method="GET" action="/accounts/">
                <input type="hidden" name="clientID" value="${client.id}"/>
                <input type="submit" value="List accounts"/>
            </form>
        </td>
        <td>
            <form method="POST" action="/transaction/listClient">
                <input type="hidden" name="clientID" value="${client.id}"/>
                <input type="submit" value="List transactions"/>
            </form>
        </td>
        <td>
            <form method="POST" action="/accounts/newAccount">
                <input type="hidden" name="clientID" value="${client.id}"/>
                <input type="submit" value="Create account"/>
            </form>
        </td>
        <tr></tr>
    </c:forEach>
</table>
</body>
</html>
