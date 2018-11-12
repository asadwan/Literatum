<%@ page import="webadmintool.UploadHandler" %>
<%@ page language="java" isELIgnored="false" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="/upload" method="post" enctype="multipart/form-data">
    <label for="pub">Publication:</label>
    <input type="file" name="pub" id="pub"><br>
    <input type="submit" value="Upload">
</form>

</body>
</html>