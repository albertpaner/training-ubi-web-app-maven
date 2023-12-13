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

        .rigaTabellaOccupati:nth-child(even) {
            background-color: #bd0a0a71;
        }

        .rigaTabellaOccupati:nth-child(odd) {
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
    <form action="distribute_evaluators" method="get">
        <input type="submit" value="preview"/>
        <select name="soglia1" id="soglia1">
            <option value="2" selected>2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>
    </form>
</div>
<div class="containerTabelle">

    <%

        List<EvalCountDto> occupati = (List<EvalCountDto>) request.getAttribute("occupati");
        List<EvalCountDto> disponibili = (List<EvalCountDto>) request.getAttribute("disponibili");

        if (occupati != null && disponibili != null) {

    %>
    <table>
        <tr style="background-color: #c0f9ff">
            <th>Nome</th>
            <th>Cognome</th>
            <th>Email</th>
            <th>Count</th>
        </tr>

        <%
            if (occupati != null) {
                for (EvalCountDto evaluator : occupati) {
        %>
        <tr style="" class="rigaTabellaOccupati">

            <td><%= evaluator.getNome() %>
            </td>
            <td><%= evaluator.getCognome() %>
            </td>
            <td><%= evaluator.getEmail() %>
            </td>
            <td><%= evaluator.getCount() %>
            </td>
        </tr>
        <%
                }

            }

        %>
        <%
            if (disponibili != null) {
                for (EvalCountDto evaluator : disponibili) {
        %>
        <tr>
            <td><%= evaluator.getNome() %>
            </td>
            <td><%= evaluator.getCognome() %>
            </td>
            <td><%= evaluator.getEmail() %>
            </td>
            <td><%= evaluator.getCount() %>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <%
        }
    %>

    <%
    List<UtenteDto> waiting = (List<UtenteDto>) request.getAttribute("waiting");

    if (waiting != null) {
%>
<table>
    <tr style="background-color: #c0f9ff">
        <th>Nome</th>
        <th>Cognome</th>
        <th>Email</th>
    </tr>

    <%
        for (UtenteDto user : waiting) {
    %>
    <tr>
        <td><%= user.getFirstName() %></td>
        <td><%= user.getLastName() %></td>
        <td><%= user.getUserEmail() %></td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
</div>

<div class="containerButtons">
    <form action="distribute_evaluators" method="post">
        <input type="submit" value="distribute"/>
        <select name="soglia2" id="soglia2">
            <option value="2" selected>2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>
    </form>
</div>


</body>
</html>