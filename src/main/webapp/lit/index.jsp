<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aadwan
  Date: 11/27/18
  Time: 3:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome to Literatum</title>
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
    <h1>Literatum Online Library</h1>
    <div id="logout">
        <a href="/lit/logout"><button type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-log-out"></span> Log out
        </button>
        </a>
    </div>
</div>
<br><br>
<div><h1 class="text-center">Journals</h1></div>
<div class="container">
    <div class="row">
        <c:forEach var="journal" items="${journals}">
            <div class="col-sm-4">
                <h3><a href="/lit/journal/<c:out value="${journal.id}"/>"><c:out value="${journal.title}"/></a></h3>
                <p style="text-align: justify">${journal.description}</p>
            </div>
        </c:forEach>
    </div>
</div>

<footer class="container-fluid text-center">
    <p>© 2018 - Atypon Systems</p>
</footer>

</body>
</html>
