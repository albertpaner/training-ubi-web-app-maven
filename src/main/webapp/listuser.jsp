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
            for (CountDto dto : evaluatorsOccupied) {
        %>
        <tr>
            <td><%= dto.toString() %></td>
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
            for (CountDto dto : evaluatorsFree) {
        %>
        <tr>
            <td><%= dto.toString() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>