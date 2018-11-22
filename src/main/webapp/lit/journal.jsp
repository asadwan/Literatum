<%--
  Created by IntelliJ IDEA.
  User: aadwan
  Date: 11/22/18
  Time: 3:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${requestScope.journal.title}</title>
</head>
<body>
<h1>${requestScope.journal.title}</h1>
<h5>Journal ISSN: ${requestScope.journal.id}</h5>
<h5>Publisher: ${requestScope.journal.publisherName}</h5>
<h3>Issues:</h3>
<table border="1pt">
    <tr>
        <th>Issue Title</th>
        <th>Type</th>
        <th>Publication Date</th>
        <th>Volume</th>
        <th>Number</th>
    </tr>
        <c:forEach items="${issues}" var="issue">
         <tr>
             <td><a href="/lit/issue/${issue.id}">${issue.title}</a></td>
             <td>${issue.issueType}</td>
             <td>${issue.issuePubDate}</td>
             <td>${issue.volume}</td>
             <td>${issue.issueNumber}</td>
         </tr>
        </c:forEach>
</table>
</body>
</html>
