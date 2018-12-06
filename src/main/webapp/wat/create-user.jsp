<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aadwan
  Date: 12/3/18
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <title>Welcome to the Web Admin Tool</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
    html {
        position: relative;
        min-height: 100%;
    }

    footer {
        background-color: #555;
        color: white;
        padding: 15px;
        position: absolute;
        left: 0;
        bottom: 0;
        height: 72px;
        width: 100%;
        overflow: hidden;
    }
</style>

<body>
<script>
    function showAlert(message) {
        if (message == 'true') {
            alert("User has been successfully created")
        } else if (message == 'false') {
            alert('${userCreationError}')
        }
    }
</script>
<script>showAlert('${userCreated}')</script>
<%session.removeAttribute("userCreated"); %>
<%session.removeAttribute("userCreationError");%>

<div class="jumbotron text-center">
    <h1>Literatum Web Admin Tool</h1>
    <div id="logout">
        <a href="/wat/logout">
            <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-log-out"></span> Log out
            </button>
        </a>
    </div>
    <br>
    <div><p><a href="/wat"><b>Home</b></a></p></div>
</div>
<br>
<div class="container">
    <div class="row">
        <div class="col-sm-4 col-md-offset-5">
            <form class="form-horizontal" action='/wat/create-user' method="POST">
                <fieldset>
                    <div id="legend">
                        <h3 class="">Create User</h3>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="name">Full Name:</label>
                        <div class="controls">
                            <input type="text" id="name" name="name" class="input-xlarge"
                                   required>
                        </div>
                        <label class="control-label" for="username">Username:</label>
                        <div class="controls">
                            <input type="text" id="username" name="username" class="input-xlarge"
                                   required>
                        </div>
                        <label class="control-label" for="password">Password:</label>
                        <div class="controls">
                            <input type="password" id="password" name="password" class="input-xlarge"
                                   required>
                        </div>
                        <label class="control-label">Type:</label>
                        <div class="controls">
                            <input type="radio" name="user-type" value="normal" checked required> Normal
                            <input type="radio" name="user-type" value="admin" required> Admin
                        </div>
                        <label class="control-label">Licenses:</label>
                        <div>
                            <input type="checkbox" name="licenses" value="29e1bf2a2ccc1166b8110f06271615a4"> Access to
                            journal "Academic Forensic Pathology"<br>
                            <input type="checkbox" name="licenses" value="aee4578858103bfda5f3dad95bf97dd1"> Access to
                            journal "Behavioral Disorders"<br>
                            <input type="checkbox" name="licenses" value="d3e79a29edfd981686b24067b8f0f764"> Access to
                            journal "AADE in Practice"<br>
                        </div>
                    </div>
                    <br>
                    <div class="control-group">
                        <div class="controls">
                            <button class="btn btn-success">Create</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <p>© 2018 - Atypon Systems</p>
</footer>

</body>
</html>

