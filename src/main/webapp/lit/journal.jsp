<%--
  Created by IntelliJ IDEA.
  User: aadwan
  Date: 11/22/18
  Time: 4:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <title>${journal.title}</title>
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

    table {
        margin: auto;
        width: 80% !important;
    }

</style>
<body>

<div class="jumbotron text-center">
    <h1>Literatum Online Library</h1>
    <div id="logout">
        <a href="/lit/logout">
            <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-log-out"></span> Log out
            </button>
        </a>
    </div>
</div>
<br><br>
<div><h2 class="text-center"><b>${journal.title}</b></h2></div>
<div><h3 class="text-center">ISSN: ${journal.id}</h3></div>
<div><h3 class="text-center">Publisher: ${journal.publisherName}</h3></div>
<div class="container">
    <table class="table table-hover table-bordered text-center" align="c">
        <thead class="text-center">
        <tr>
            <th>Issue Title</th>
            <th>Type</th>
            <th>Publication Date</th>
            <th>Volume</th>
            <th>Number</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${issues}" var="issue">
            <tr>
                <td><a href="/lit/issue/${issue.id}">${issue.title}</a></td>
                <td>${issue.issueType}</td>
                <td>${issue.issuePubDate}</td>
                <td>${issue.volume}</td>
                <td>${issue.issueNumber}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<footer class="container-fluid text-center">
    <p>© 2018 - Atypon Systems</p>
</footer>

</body>
</html>