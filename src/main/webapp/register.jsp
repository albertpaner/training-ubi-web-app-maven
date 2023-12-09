<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
</head>
<body>
    <h2>User Registration Form</h2>
    <form action="register" method="post">
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br>
        <label for="ruoloId">Role ID:</label><br>
        <input type="number" id="ruoloId" name="ruoloId" required><br>
        <label for="nome">Name:</label><br>
        <input type="text" id="nome" name="nome" required><br>
        <label for="cognome">Surname:</label><br>
        <input type="text" id="cognome" name="cognome" required><br>
        <label for="valutatoreId">Evaluator ID:</label><br>
        <input type="number" id="valutatoreId" name="valutatoreId" required><br>
        <label for="dataNascita">Date of Birth:</label><br>
        <input type="date" id="dataNascita" name="dataNascita" required><br>
        <input type="submit" value="Register">
    </form>
</body>
</html>