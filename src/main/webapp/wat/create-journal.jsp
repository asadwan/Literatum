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
<html>
<head>
    <title>Create Journal</title>
</head>
<body>
<h2>Create Journal</h2>


<script>
    function showAlert(message) {
        if(message == 'true') {
            alert("Journal has been created successfully")
        } else if (message == 'false') {
            alert("An error has occurred while creating the journal, please check that your input is correct")
        }
    }
</script>
<script>showAlert('${creationSuccessful}')</script>

<form method="post" action="<c:url value="/wat/create-journal"/>">
    ISSN:<br>
    <input type="text" name="issn" value="">
    <br>
    Journal Title:<br>
    <input type="text" name="title" value="">
    <br>
    Publisher Name:<br>
    <input type="text" name="publisher" value="">
    <br><br>
    <input type="submit" value="Submit" onclick=>
</form>

<a href="/wat">Web Admin Tool Home</a>
</body>
</html>
