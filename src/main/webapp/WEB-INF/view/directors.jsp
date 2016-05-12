<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" data-leadgen="yes" data-advpp="yes">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Simple Film Studio project">
    <meta name="author" content="TK">

    <link rel="icon" href="static/img/film-icon.png">

    <title>Film Studio|Directors</title>

    <!-- Bootstrap core CSS -->
    <link href="static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="static/datepicker/css/datepicker.css" rel="stylesheet">
    <link href="static/notification/css/bootstrap-notify.css" rel="stylesheet">
    <link href="static/notification/css/styles/alert-blackgloss.css" rel="stylesheet">


    <link href="static/css/justified-nav.css" rel="stylesheet">
    <link href="static/css/common.css" rel="stylesheet">
    <link href="static/css/director.css" rel="stylesheet">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="static/datepicker/js/bootstrap-datepicker.js"></script>
    <script src="static/notification/js/bootstrap-notify.js"></script>
</head>

<body>

<div class="container">
    <%@ include file="header.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <h1>Directors</h1>
    </div>


    <div class="row">
        <div class="col-lg-1">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#add-director-modal">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                Add Director
            </button>
        </div>
    </div>
    <hr/>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td>Larry</td>
                <td>the Bird</td>
                <td>@twitter</td>
            </tr>
            </tbody>
        </table>
    </div>


    <div class="modal fade" id="add-director-modal" role="dialog" aria-labelledby="add-director-modal-title">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="add-director-modal-title">New Director</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="add-director-first-name" class="control-label">First Name:</label>
                            <input AUTOFOCUS type="text" class="form-control" id="add-director-first-name"
                                   name="first_name">
                        </div>
                        <div class="form-group">
                            <label for="add-director-last-name" class="control-label">Last Name:</label>
                            <input type="text" class="form-control" id="add-director-last-name" name="last_name">
                        </div>
                        <div class="form-group datepicker-input-group">
                            <label for="add-film-birth-day" class="control-label">Birth day:</label>
                            <div class="datepicker">
                                <input class="form-control" id="add-film-birth-day" type="text"
                                       placeholder="dd-mm-yyyy">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-success">Save Director</button>
                </div>
            </div>
        </div>
    </div>

    <%@include file="footer.jsp" %>

    <div alert-message="Some text" class='notifications bottom-right'></div>

</div> <!-- /container -->
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script src="static/js/director.js"></script>
</body>
</html>