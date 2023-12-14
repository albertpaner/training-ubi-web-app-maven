<%@ page import="model.dto.EvalCountDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.dto.UtenteDto" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<!DOCTYPE html>
<html>
<head>
    <style>
        * {
            font-family: "JetBrains Mono", monospace;
            padding: 0;
            margin: 0;
        }

        body {
            background-color: #e8e1d7;
        }

        table {
            margin-top: 3rem;
            width: 40%;
            border-radius: 0.3rem;
            border-bottom: solid 0.3rem black;
            border: solid black 0.1rem;
        }

        .rigaTabellalist:nth-child(even) {
            background-color: #bd0a0a71;
        }

        .rigaTabellalist:nth-child(odd) {
            background-color: #f20c0c9e;
        }

        th,
        td {
            text-align: left;
            padding: 8px;
            border-collapse: collapse;
        }

        th {
            color: black;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:nth-child(odd) {
            background-color: #ffffff;
        }

        tr:hover {
            background-color: #ddd;
        }

        .containerTabelle {
            margin: 0 1rem 0 1rem;
            display: flex;
            justify-content: space-around;
            align-items: center;
            width: 100%;
        }

        .containerButtons {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
        }

        input[type=submit], select {
            background: white;
            border: solid 1px black;
            border-radius: 0.2rem;
            padding: 0.375em 1.125em;
            font-size: 1rem;
            margin: 1rem;
        }

        input[type=submit]:hover {
            background: #c0f9ff;
        }

        input[type=submit]::-webkit-inner-spin-button {
            opacity: 1;
        }


    </style>
</head>
<body>
<div class="containerButtons">
    <form action="esame_lawrence" method="get">
        <input type="submit" value="view"/>
    </form>
</div>
<div class="containerTabelle">

    <%

        List<EvalCountDto> listDto = (List<EvalCountDto>)request.getAttribute("list");

        if (listDto != null) {

    %>
    <table>
        <tr style="background-color: #c0f9ff">
            <th>Nome</th>
            <th>Cognome</th>
            <th>Email</th>
        </tr>

        <%
                for (EvalCountDto evaluator : listDto) {
        %>
        <script> </script>

        <tr >

            <td><%= evaluator.getNome() %>
            </td>
            <td><%= evaluator.getCognome() %>
            </td>
            <td><%= evaluator.getEmail() %>
            </td>
        </tr>

        <%
                    }
            }
        %>
    </table>

</div>


</body>
</html>