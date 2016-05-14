<%@ page import="entity.Film" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" data-leadgen="yes" data-advpp="yes">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Simple Film Studio project">
    <meta name="author" content="TK">

    <link rel="icon" href="static/img/film-icon.png">

    <title>Film Studio|Films</title>

    <link href="static/css/common.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="static/css/justified-nav.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>

<div class="container">
    <%@ include file="header.jsp" %>
    <div class="jumbotron">
        <h1>Films</h1>
    </div>

    <div class="row">
        <div class="col-lg-1">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#add-film-modal">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                Add Film
            </button>
        </div>
    </div>
    <hr/>
    <%--<div class="row">--%>
    <%List<Film> films = (List<Film>) request.getAttribute("films");%>
    <%for (int i = 0; i < films.size(); i++) {%>
    <%Film f = films.get(i);%>
    <%if (i == 0 || (i) % 3 == 0) {%>
    <div class="row">
        <%}%>
        <div class="col-lg-4">
            <h2><%=f.getTitle()%>
            </h2>
            <p><b>Duration: </b> <%=f.getFormattedDuration()%>
            </p>
            <p><b>Year: </b> <%=f.getYear()%>
            </p>
            <p><b>Director: </b> <%=f.getDirector().getFullName()%>
            </p>
            <!--<p><b>Description: </b></p>-->
            <p><%=f.getDescription()%>
            </p>
        </div>
        <%if ((i != 0 && (i + 1) % 3 == 0) || i == films.size() - 1) {%>
    </div>
    <hr/>
    <%}%>
    <%}%>
    <%--</div>--%>

    <div class="modal fade" id="add-film-modal" role="dialog" aria-labelledby="add-film-modal-title">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="add-film-modal-title">New Film</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="add-film-title" class="control-label">Title:</label>
                            <input AUTOFOCUS type="text" class="form-control" id="add-film-title" name="title">
                        </div>
                        <div class="form-group">
                            <label for="add-film-year" class="control-label">Year:</label>
                            <input type="text" placeholder="yyyy" class="form-control" id="add-film-year"
                                   name="title">
                        </div>
                        <div class="form-group">
                            <label for="add-film-duration" class="control-label">Duration:</label>
                            <input type="text" placeholder="hh:mm" class="form-control" id="add-film-duration"
                                   name="title">
                        </div>
                        <div class="form-group">
                            <label for="add-film-director-id" class="control-label">Director:</label>
                            <select class="form-control" id="add-film-director-id" name="director_id">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>

                        </div>
                        <div class="form-group">
                            <label for="add-film-description" class="control-label">Description:</label>
                            <textarea class="form-control" id="add-film-description" rows="5"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-success">Save film</button>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>

</div> <!-- /container -->
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script language="JavaScript"></script>
</body>
</html>
