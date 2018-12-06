<%@ page import="webadmintool.UploadHandler" %>
<%@ page language="java" isELIgnored="false" %>


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
            alert("Publication has been uploaded successfully")
        } else if (message == 'false') {
            alert("An error has occurred while uploading the publication, please check that your submission is valid")
        }
    }
</script>
<script>showAlert('${uploadSuccessful}')</script>
<%session.removeAttribute("uploadSuccessful"); %>

<div class="jumbotron text-center">
    <h1>Literatum Web Admin Tool</h1>
    <div id="logout">
        <a href="/wat/logout"><button type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-log-out"></span> Log out
        </button>
        </a>
    </div>
    <br>
    <div><p><a href="/wat"><b>Home</b></a></p></div>
</div>
<br><br><br>
<div class="container">
    <div class="row">
        <div class="col-sm-4 col-md-offset-5">
            <form class="form-horizontal" action='/wat/upload' method="POST" enctype="multipart/form-data">
                <fieldset>
                    <div id="legend">
                        <h3 class="">Upload new publication</h3>
                    </div>
                    <div class="control-group">
                        <!-- Username -->
                        <label class="control-label" for="pub">Publication file:</label>
                        <div class="controls">
                            <input type="file" id="pub" name="pub" class="input-xlarge"
                                   required>
                        </div>
                    </div>
                    <br>
                    <div class="control-group">
                        <div class="controls">
                            <button class="btn btn-success">Upload</button>
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