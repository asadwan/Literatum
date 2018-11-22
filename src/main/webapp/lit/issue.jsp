<%--
  Created by IntelliJ IDEA.
  User: aadwan
  Date: 11/22/18
  Time: 4:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${issue.title}</title>
</head>
<body>
<h1>${issue.title}</h1>
<h5>Journal: ${journal.title}</h5>
<h5>Publisher: ${journal.publisherName}</h5>
<h5>Type: ${issue.issueType}</h5>
<h3>Articles:</h3>
<table border="1pt">
    <tr>
        <th>Article Title</th>
        <th>Article Category</th>
    </tr>
    <c:forEach items="${articles}" var="article">
        <tr>
            <td><a href="/lit/article/${article.id}">${article.title}</a></td>
            <td>${article.articleCategory}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
