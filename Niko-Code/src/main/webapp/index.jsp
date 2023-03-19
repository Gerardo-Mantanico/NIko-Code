<%-- 
    Document   : index
    Created on : 27/02/2023, 14:06:37
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
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
                <img src="https://images.unsplash.com/photo-1607227063002-677dc5fdf96f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80" alt="">
            </div>
            <div class="container-login">
                <h1 class="title"> Login to you account</h1>
                <div class="container-form">
                    <form method="POST" action="ServletLogin"  class="form">
                        <label for="text" class="label">User Name</label>
                        <input type="text" class="input" name="User_name" placeholder="Gerardo7897" required>
                        <label for="password" class="label">Password:</label>
                        <input type="password" name="password" placeholder="********" class="input input-password" id="password" required>
                        <c:forEach items="${msj}" var="mensaje">
                             <h5> <c:out value="${mensaje}" ></c:out></h5>
                        </c:forEach>
                        <form method="GET" action="../ListaDatos">
                        <button type="submit" class="button">Sign up</button>
                        </form>
                    </form>
            </div>
            </div>
        </div>
    </div>
    
</body>
