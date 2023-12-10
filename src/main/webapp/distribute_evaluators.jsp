<%@ page import="model.dto.EvalCountDto" %>
<%@ page import="java.util.List" %>
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
    <%
        List<EvalCountDto> occupati = (List<EvalCountDto>) request.getAttribute("occupati");
        if (occupati != null) {
            for (EvalCountDto evaluator : occupati) {
    %>
    <li><%= evaluator.getNome() %> <%= evaluator.getCognome() %> - <%= evaluator.getEmail() %> - <%= evaluator.getCount() %></li>
    <%
            }
        }
    %>
    <h2>Free Evaluators</h2>
    <ul>
        <%
            List<EvalCountDto> disponibili = (List<EvalCountDto>) request.getAttribute("disponibili");
            if (disponibili != null) {
                for (EvalCountDto evaluator : disponibili) {
        %>
        <li><%= evaluator.getNome() %> <%= evaluator.getCognome() %> - <%= evaluator.getEmail() %> - <%= evaluator.getCount() %></li>
        <%
                }
            }
        %>
    </ul>
</form>
<!-- Form to trigger doPost method -->
<form method="post" action="distribute_evaluators">
    <input type="submit" value="Rearrange Evaluators">
</form>
</body>
</html>