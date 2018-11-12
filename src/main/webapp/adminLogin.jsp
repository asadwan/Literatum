<%--
  Created by IntelliJ IDEA.
  User: aadwan
  Date: 11/6/18
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/login" method="post">

    <div>
        <h1>Admin Login</h1>
        <br><br><br>
    </div>

    <div class="container">
        <label for=><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="username" required>
        <br>
        <label for=><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required>
        <br>
        <button type="submit">Login</button>
    </div>
</form>

</body>
</html>
