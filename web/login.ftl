<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Authorization</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="styles/auth.css" rel="stylesheet">

</head>

<body>

<div class="container">

    <form action="login" method="post" class="form-signin">
        <h2 class="form-signin-heading">Sign in</h2>
        <input type="email" class="form-control" placeholder="Email" name="email" required autofocus>
        <input type="password" class="form-control" placeholder="Password" name="password" required>
        <label class="checkbox">
            <input type="checkbox" value="remember-me"> Remember me
        </label>
        <#if notExist!false == true>
            <br>
            <span>User with given credentials does not exist</span>
        </#if>
        <button class="btn btn-lg btn-dark btn-block" type="submit">Sign in</button>
    </form>

</div>

</body>
</html>