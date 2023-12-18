
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
</head>
<body>
    <h2>User Registration Form</h2>
    <form action="/register" method="post">
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br>
        <c:if test="${empty param.email}">
            <p>Email is required</p>
        </c:if>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br>
        <c:if test="${empty param.password}">
            <p>Password is required</p>
        </c:if>
        <label for="nome">Name:</label><br>
        <input type="text" id="nome" name="nome" required><br>
        <c:if test="${empty param.nome}">
            <p>Name is required</p>
        </c:if>
        <label for="cognome">Surname:</label><br>
        <input type="text" id="cognome" name="cognome" required><br>
        <c:if test="${empty param.cognome}">
            <p>Surname is required</p>
        </c:if>
        <label for="dataNascita">Date of Birth:</label><br>
        <input type="date" id="dataNascita" name="dataNascita" required><br>
        <c:if test="${empty param.dataNascita}">
            <p>Date of Birth is required</p>
        </c:if>
        <input type="submit" value="Register">
    </form>
</body>
</html>