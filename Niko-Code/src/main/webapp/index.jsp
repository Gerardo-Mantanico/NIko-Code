<%-- 
    Document   : index
    Created on : 27/02/2023, 14:06:37
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/Home.css"/>
    <title>Login</title>
</head>
<body>
    <div class="login">
        <div class="container">
            <div class="logo">
                <img src="https://images.unsplash.com/photo-1509042239860-f550ce710b93?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&auto=format&fit=crop&w=1400&q=60" alt="">
            </div>
            <div class="container-login">
                <h1 class="title"> Login to you account</h1>
                <div class="container-form">
                    <form method="POST" action="LoginServlet"  class="form">
                        <label for="text" class="label">User Name</label>
                        <input type="text" class="input" name="User_name" placeholder="Gerardo7897">
                        <label for="password" class="label">Password:</label>
                        <input type="password" name="password" placeholder="********" class="input input-password" id="password">
                        <button class="button">Sign up</button>
                    </form>
            </div>
            </div>
        </div>
    </div>
    
</body>
