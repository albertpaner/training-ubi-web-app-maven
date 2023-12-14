<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Valutatore</title>
</head>
<body>

<h1>Nuovo Valutatore Creato</h1>

<%
    String email = (String) request.getAttribute("email");
    String nome = (String) request.getAttribute("nome");
    String cognome = (String) request.getAttribute("cognome");
    Timestamp dataUltMod = (Timestamp) request.getAttribute("data_ult_mod");
    Timestamp dataCreaz = (Timestamp) request.getAttribute("data_crea");
    Timestamp dataUltAcc = (Timestamp) request.getAttribute("data_ult_acc");

    if (email != null && nome != null && cognome != null && dataUltMod != null && dataCreaz != null && dataUltAcc != null) {
%>
    <h3>Email: <%= email %></h3>
    <h3>Nome: <%= nome %></h3>
    <h3>Cognome: <%= cognome %></h3>
    <h3>Data Ultima Modifica: <%= dataUltMod %></h3>
    <h3>Data Creazione: <%= dataCreaz %></h3>
    <h3>Data Ultimo Accesso: <%= dataUltAcc %></h3>
<%
    } else {
%>
    <h1>Nessun nuovo valutatore disponibile</h1>
<%
    }
%>
</body>
</html>
