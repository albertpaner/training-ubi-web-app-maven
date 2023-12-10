<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Distribute Evaluators </title>
</head>
<body>
<!-- Form to trigger doGet method -->
<form method="get" action="distribute_evaluators">
    <h1>Evaluator Display</h1>
    <p>flag : <%= session.getAttribute("flg") %></p>
    <h2>Occupied Evaluators</h2>
    <ul>
        <c:forEach var="evaluator" items="${requestScope.occupati}">
            <li>${evaluator.nome} ${evaluator.cognome} - ${evaluator.email}</li>
        </c:forEach>
    </ul>
    <h2>Free Evaluators</h2>
    <ul>
        <c:forEach var="evaluator" items="${requestScope.disponibili}">
            <li>${evaluator.nome} ${evaluator.cognome} - ${evaluator.email}</li>
        </c:forEach>
    </ul>
</form>
<!-- Form to trigger doPost method -->
<form method="post" action="distribute_evaluators" >
    <input type="submit" value="Rearrange Evaluators">
</form>
</body>
</html>