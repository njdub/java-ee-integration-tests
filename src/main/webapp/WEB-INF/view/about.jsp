<%@ page import="java.util.Random" %>
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

    <title>Film Studio|About</title>

    <!-- Bootstrap core CSS -->
    <link href="static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="static/css/common.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="static/css/justified-nav.css" rel="stylesheet">
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>--%>
    <script src="static/jquery/jquery-2.1.3.js"></script>
</head>

<body>

<div class="container">
    <%@ include file="header.jsp" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <h1>Love Cats</h1>
    </div>
    <hr/>
    <div class="img-responsive">
        <img src="static/img/cats/cat-<%=new Random().nextInt(8) + 1%>.jpg" alt="Cat is sleeping now, come later"
             class="center-block cat-image">
    </div>
    <%@include file="footer.jsp" %>

</div> <!-- /container -->
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script language="JavaScript"></script>
</body>
</html>
