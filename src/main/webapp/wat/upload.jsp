<%@ page import="webadmintool.UploadHandler" %>
<%@ page language="java" isELIgnored="false" %>
<html>
<head>
    <title>Upload new publication</title>
</head>
<body>
<script>
    function showAlert(message) {
        if(message == 'true') {
            alert("Publication has been uploaded successfully")
        } else if (message == 'false') {
            alert("An error has occurred while uploading the publication, please check that your submission is valid")
        }
    }
</script>
<script>showAlert('${uploadSuccessful}')</script>

<h2>Upload a new publication</h2>
<form action="/wat/upload" method="post" enctype="multipart/form-data">
    <label for="pub">Publication:</label>
    <input type="file" name="pub" id="pub"><br>
    <input type="submit" value="Upload">
</form>

<a href="/wat">Web Admin Tool Home</a>
</body>
</html>