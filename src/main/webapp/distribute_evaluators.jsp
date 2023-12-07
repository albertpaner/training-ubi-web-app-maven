<%@ page import = "java.util.List"%>
<%@ page import = "model.Dto.EvalCountDto" %>
<!DOCTYPE html>
<html>
<head>
    <title>Evaluators</title>
</head>
<body>
    <form action="distributeEvaluators" method="get">
        <h1>Evaluators Occupied <%= session.getAttribute("flg") %></h1>
    <table border="1" aria-describedby="descriptionOccupied">
            <caption id="descriptionOccupied">These are occupied evaluators.</caption>
        <tr>
            <th> Details</th>
        </tr>
        <%--
        <%
                    List<EvalCountDto> evaluatorsOccupied = (List<EvalCountDto>)request.getAttribute("valutatori_occupati");
                    for (EvalCountDto count : evaluatorsOccupied) {
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
            List<EvalCountDto> evaluatorsFree = (List<EvalCountDto>)request.getAttribute("valutatori_disponibili");
            for (EvalCountDto count : evaluatorsFree) {
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