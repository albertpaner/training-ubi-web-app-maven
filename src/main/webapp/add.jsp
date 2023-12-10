<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Addition NO SERVLETS</title>
</head>
<body>
    <form method="post">
        <label for="num1">Number 1:</label>
        <input type="number" id="num1" name="num1" required><br>
        <label for="num2">Number 2:</label>
        <input type="number" id="num2" name="num2" required><br>
        <input type="submit" value="Add">
    </form>

    <%
        String strNum1 = request.getParameter("num1");
        String strNum2 = request.getParameter("num2");
        if (strNum1 != null && strNum2 != null) {
            int num1 = Integer.parseInt(strNum1);
            int num2 = Integer.parseInt(strNum2);
            int sum = num1 + num2;
    %>
        <p>The sum of <%= num1 %> and <%= num2 %> is <%= sum %>.</p>
    <%
        }
    %>
</body>
</html>
