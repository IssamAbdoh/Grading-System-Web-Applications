<html>
<head>
    <title>Login Page</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <jsp:include page="../../cssLoader.jsp"></jsp:include>

    <!--
    <link rel="stylesheet" href="../styles/style.css" type="text/css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    -->
</head>
<body class="container mt-3">

<h2>Sign in</h2>
<h6>Spring MVC</h6>

<form action="/login.springmvc" method="POST">
    <div class="mb-3 mt-3">
        <label for="email" class="form-label">Email :</label>
        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Password :</label>
        <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
    </div>
    <p><span style="color: red; ">${errorMessage}</span></p>
    <div class="text-center">
        <button type="submit" class="btn btn-primary btn-lg">Submit</button>
    </div>
</form>

<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
<!--
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
-->
</body>
</html>