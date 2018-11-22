<%@ page import="model.Publication" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: aadwan
  Date: 11/22/18
  Time: 12:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Journals</title>
</head>
<body>
<h1>Journals</h1>
<c:forEach var="journal" items="${journals}">
    <a href="/lit/journal/<c:out value="${journal.id}"/>"><c:out value="${journal.title}"/></a><br/>
</c:forEach>
</body>
</html>
