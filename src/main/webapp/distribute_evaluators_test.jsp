<%@ page import="model.dto.EvalCountDto" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<!DOCTYPE html>
<html>
  <head>
    <style>
      * {
        font-family: "JetBrains Mono", monospace;
      }
      table {
        border-collapse: collapse;
        width: 40%;
      }

      th,
      td {
        text-align: left;
        padding: 8px;
      }

      th {
        color: white;
      }

      tr:nth-child(even) {
        background-color: #f2f2f2;
      }

      tr:hover {
        background-color: #ddd;
      }
      .containerTabelle {
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
      input {
        background: white;
        border: solid 1px black;
        padding: 0.375em 1.125em;
        font-size: 1rem;
        margin: 1rem;
      }
      input:hover {
        background: #c0f9ff;
      }

      
    </style>
  </head>
  <body>
    <div class="containerTabelle">

        <%
        List<EvalCountDto> occupati = (List<EvalCountDto>)request.getAttribute("occupati");
        if (occupati != null) {
            %>
            <table>
                <tr style="background-color: green">
                    <th>Nome</th>
                    <th>Cognome</th>
                    <th>Email</th>
                    <th>Count</th>
                </tr>
            <%
            for (EvalCountDto evaluator : occupati) {
    %>
    <tr > <td><%= evaluator.getNome() %></td><td><%= evaluator.getCognome() %></td> <td><%= evaluator.getEmail() %> </td> <td><%= evaluator.getCount() %> </td> </tr></table>
    <%
            }
        }
    %>

    <%
    List<EvalCountDto> occupati = (List<EvalCountDto>)request.getAttribute("occupati");
    if (occupati != null) {
        %>
        <table>
            <tr style="background-color: red">
                <th>Nome</th>
                <th>Cognome</th>
                <th>Email</th>
                <th>Count</th>
            </tr>
        <%
        for (EvalCountDto evaluator : occupati) {
%>
<tr > <td><%= evaluator.getNome() %></td><td><%= evaluator.getCognome() %></td> <td><%= evaluator.getEmail() %> </td> <td><%= evaluator.getCount() %> </td> </tr></table>
<%
        }
    }
%>
    </div>

    
    <div class="containerButtons">
      <form action="" method="put">
        <input type="submit" value="preview" />
        <select name="" id="">
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>
      </form>
      <form action="" method="post">
        <input type="submit" value="save" />
      </form>
    </div>
<!--
    <div class="containerTabelle">
        <%
        List<EvalCountDto> occupati = (List<EvalCountDto>)request.getAttribute("occupati");
        if (occupati != null) {
            %>
            <table>
                <tr style="background-color: orange">
                    <th>Nome</th>
                    <th>Cognome</th>
                    <th>Email</th>
                    <th>Count</th>
                </tr>
            <%
            for (EvalCountDto evaluator : occupati) {
    %>
    <tr > <td><%= evaluator.getNome() %></td><td><%= evaluator.getCognome() %></td> <td><%= evaluator.getEmail() %> </td> <td><%= evaluator.getCount() %> </td> </tr></table>
    <%
            }
        }
    %>
      </div>-->
  </body>
</html>
