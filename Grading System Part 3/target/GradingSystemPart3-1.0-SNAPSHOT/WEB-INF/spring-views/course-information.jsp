<%--
  Created by IntelliJ IDEA.
  User: essam
  Date: 2022-04-25
  Time: 3:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${course} class information</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <jsp:include page="../../cssLoader.jsp"></jsp:include>

</head>
<body class="container mt-3">
<h1>${course} class information :</h1>
<h6>Spring MVC</h6>

<div class="container mt-3">
    <div class="list-group">
        <a href="" class="list-group-item list-group-item-action list-group-item-success">Your mark in ${course}
            is ${student_mark}</a>
        <a href="" class="list-group-item list-group-item-action list-group-item-success">Class Average
            is ${class_average}</a>
        <a href="" class="list-group-item list-group-item-action list-group-item-success">Class Median
            is ${class_median}</a>
        <a href="" class="list-group-item list-group-item-action list-group-item-success">Highest Mark
            is ${class_highest}</a>
        <a href="" class="list-group-item list-group-item-action list-group-item-success">Lowest Mark
            is ${class_lowest}</a>
    </div>

    <br>

    <div class="d-grid">
        <!-- button to return to the previous page -->
        <a class="btn btn-outline-success btn-lg btn-block" onCLick="history.back()">Back</a>
    </div>
</div>

<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>

</body>
</html>
