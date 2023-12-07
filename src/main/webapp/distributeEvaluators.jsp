<%@ page import = "java.util.List"%>
<%@ page import = "model.Dto.CountDto" %>
<!DOCTYPE html>
<html>
<head>
    <title>Evaluators</title>
</head>
<body>
    <form action="UserServlet" method="get">
        <h1>Evaluators Occupied <%= session.getAttribute("flg") %></h1>
    <table border="1" aria-describedby="descriptionOccupied">
            <caption id="descriptionOccupied">These are occupied evaluators.</caption>
        <tr>
            <th> Details</th>
        </tr>
        <%--
        <%
                    List<CountDto> evaluatorsOccupied = (List<CountDto>)request.getAttribute("valutatori_occupati");
                    for (CountDto count : evaluatorsOccupied) {
                %>
                <tr>
                    <td><%= count.toString() %></td>
                </tr>
                <% } %>
        --%>
    </table>

    <h1>Evaluators Available</h1>
    <table border="1" aria-describedby="descriptionAvailable">
        <caption id="descriptionAvailable">These are available evaluators.</caption>
        <tr>
            <th> Details</th>
        </tr>
        <%--
        <%
            List<CountDto> evaluatorsFree = (List<CountDto>)request.getAttribute("valutatori_disponibili");
            for (CountDto count : evaluatorsFree) {
        %>
        <tr>
            <td><%= count.toString() %></td>
        </tr>
        <% } %>
        --%>
    </table>
    
    </form>   
</body>
</html>