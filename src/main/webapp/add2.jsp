<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Addition</title>
</head>
<body>
    <form method="post" action="add2">
        <label for="num1">Number 1:</label>
        <input type="number" id="num1" name="num1" required><br>
        <label for="num2">Number 2:</label>
        <input type="number" id="num2" name="num2" required><br>
        <input type="submit" value="Add">
    </form>

    <p>The sum is <%= session.getAttribute("sum") %>.</p>
</body>
</html>