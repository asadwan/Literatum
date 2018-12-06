<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aadwan
  Date: 11/27/18
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <title>Welcome to Literatum Online Library</title>
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

<div class="jumbotron text-center">
    <h1>Literatum Online Library</h1>
</div>
<br><br><br>
<div class="container">
    <div class="row">
        <div class="col-sm-4 col-md-offset-5">
            <form class="form-horizontal" action='login' method="POST">
                <fieldset>
                    <div id="legend">
                        <legend class="">Login</legend>
                    </div>
                    <div class="control-group">
                        <!-- Username -->
                        <label class="control-label" for="username">Username</label>
                        <div class="controls">
                            <input type="text" id="username" name="username" placeholder="" class="input-xlarge"
                                   required>
                        </div>
                    </div>
                    <div class="control-group">
                        <!-- Password-->
                        <label class="control-label" for="password">Password</label>
                        <div class="controls">
                            <input type="password" id="password" name="password" placeholder="" class="input-xlarge"
                                   required>
                        </div>
                    </div>
                    <div class="error">
                        <c:if test="${sessionScope.normalUserFailedLogin == true}">
                            <p style="color: red">Incorrect username and/or password</p>
                            <c:remove var="normalUserFailedLogin" scope="session"/>
                        </c:if>
                    </div>
                    <br>
                    <div class="control-group">
                        <div class="controls">
                            <button class="btn btn-success">Login</button>
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
