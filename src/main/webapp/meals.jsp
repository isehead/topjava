<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

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
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${mealToList}" var="mealTo">
        <tr style="${mealTo.excess ? 'color: red' : 'color: green'}">
            <td width="200">
                <javatime:format value="${mealTo.dateTime}" pattern="yyyy-MM-dd HH:mm:ss" style="MS"/>
            </td>
            <td width="200">${mealTo.description}</td>
            <td width="200">${mealTo.calories}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>