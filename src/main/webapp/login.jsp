
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div style="display: flex; justify-content: center;">
  <h1>User Login Form</h1>
  <form action="%=request.getContextPath()%>/login" method="post">
   <table style="width: 100%">
    <tr>
     <td>UserName</td>
     <td><input type="text" name="email" /></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="password" /></td>
    </tr>

   </table>
   <input type="submit" value="Submit" />
  </form>
 </div>
</body>
</html>