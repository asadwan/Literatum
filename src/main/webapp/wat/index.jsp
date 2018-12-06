<%--
  Created by IntelliJ IDEA.
  User: aadwan
  Date: 11/21/18
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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

    #logout {
        right: 0;
    }

</style>
<body>

<div class="jumbotron text-center">
    <h1>Literatum Web Admin Tool</h1>
    <div id="logout">
        <a href="/wat/logout"><button type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-log-out"></span> Log out
        </button>
        </a>
    </div>
</div>
<br><br><br>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h3><a href="/wat/upload">Upload new publication</a></h3>
            <p>Use this option to upload a new publication to the e-Library.</p>
        </div>
        <div class="col-sm-4">
            <h3><a href="/wat/create-journal">Create new journal</a></h3>
            <p>Add new a journal to the system.</p>
        </div>
        <div class="col-sm-4">
            <h3><a href="/wat/create-user">Create new user</a></h3>
            <p>Create new users, assign them privileges and give them licenses.</p>
        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <p>© 2018 - Atypon Systems</p>
</footer>

</body>
</html>

