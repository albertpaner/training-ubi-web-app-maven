<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Distribute Evaluators</title>
</head>
<body>
    <h1>Evaluators</h1>

    <h2>Occupied Evaluators</h2>
    <ul>
        <c:forEach var="evaluator" items="${valutatori_occupati}">
            <li>${evaluator.nome} ${evaluator.cognome} - ${evaluator.email}</li>
        </c:forEach>
    </ul>

    <h2>Free Evaluators</h2>
    <ul>
        <c:forEach var="evaluator" items="${valutatori_disponibili}">
            <li>${evaluator.nome} ${evaluator.cognome} - ${evaluator.email}</li>
        </c:forEach>
    </ul>

    <!-- Form to trigger doPost method -->
    <form action="distributeEvaluators" method="post">
        <input type="submit" value="Rearrange Evaluators">
    </form>
</body>
</html>