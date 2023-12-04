<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
    <h1>User List</h1>
    <ul>
        <% 
            List<UtenteBean> users = (List<UtenteBean>) request.getAttribute("users");
            for (UtenteBean user : users) {
        %>
            <li><%= user.getEmail() %></li>
        <% } %>
    </ul>
</body>
</html>