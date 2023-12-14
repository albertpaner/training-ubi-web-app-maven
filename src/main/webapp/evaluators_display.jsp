<%@ page import="model.dto.EvalCountDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.dto.UtenteDto" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Evaluators Display</title>
</head>
<body>
    <table>
        <tr>
            <th>User ID</th>
            <th>Email</th>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        <c:forEach var="evaluator" items="${evaluatorsOld}">
            <tr>
                <td>${evaluator.userId}</td>
                <td>${evaluator.userEmail}</td>
                <td>${evaluator.firstName}</td>
                <td>${evaluator.lastName}</td>
            </tr>
        </c:forEach>
    </table>

    <form action="display_evaluators" method="post">
        <label for="mansione">Mansione:</label>
        <input type="text" id="mansione" name="mansione" required>
        <input type="submit" value="Submit">
    </form>
</body>
</html>