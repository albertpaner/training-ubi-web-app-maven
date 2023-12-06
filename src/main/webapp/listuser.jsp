<%@ page import = "java.util.List", "model.Dto" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Evaluators</title>
</head>
<body>
    <h1>Evaluators Occupied</h1>
    <table border="1">
        <tr>
            <th> Details</th>
        </tr>
        <%
            List<CountDto> evaluatorsOccupied = request.getAttribute("valutatori_occupati");
            for (CountDto count : evaluatorsOccupied) {
        %>
        <tr>
            <td><%= count.toString() %></td>
        </tr>
        <% } %>
    </table>

    <h1>Evaluators Free</h1>
    <table border="1">
        <tr>
            <th> Details</th>
        </tr>
        <%
            List<CountDto> evaluatorsFree = request.getAttribute("valutatori_disponibili");
            for (CountDto count : evaluatorsFree) {
        %>
        <tr>
            <td><%= count.toString() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>