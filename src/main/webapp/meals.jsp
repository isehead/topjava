<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<br><br>

<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Exceeded</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${mealToList}" var="mealTo">
        <tr>
            <td width="200">${mealTo.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}</td>
            <td width="200">${mealTo.description}</td>
            <td width="200">${mealTo.calories}</td>
            <td width="200">${mealTo.excess}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>