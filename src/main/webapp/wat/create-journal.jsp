<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="javax.crypto.BadPaddingException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aadwan
  Date: 11/21/18
  Time: 9:50 AM
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
            alert('${journalCreationMessage}')
        } else if (message == 'false') {
            alert('${journalCreationMessage}')
        }
    }
</script>
<script>showAlert('${creationSuccessful}')</script>
<%session.removeAttribute("uploadSuccessful"); %>
<%session.removeAttribute("journalCreationMessage");%>

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
            <form class="form-horizontal" action='/wat/create-journal' method="POST" id="create-journal-form">
                <fieldset>
                    <div id="legend">
                        <h2 class="">Create Journal</h2>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="issn">ISSN:</label>
                        <div class="controls">
                            <input type="text" id="issn" name="issn" class="input-xlarge"
                                   required>
                        </div>
                        <label class="control-label" for="title">Journal Title:</label>
                        <div class="controls">
                            <input type="text" id="title" name="title" class="input-xlarge"
                                   required>
                        </div>
                        <label class="control-label" for="publisher">Publisher:</label>
                        <div class="controls">
                            <input type="text" id="publisher" name="publisher" class="input-xlarge"
                                   required>
                        </div>
                        <label class="control-label" for="desc">description:</label>
                        <div class="controls">
                              <textarea name="desc" id="desc" form="create-journal-form" placeholder="Enter description here..."></textarea>
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
