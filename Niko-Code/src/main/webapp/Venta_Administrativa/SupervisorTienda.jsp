<%-- 
    Document   : SupervisorTienda
    Created on : 28/02/2023, 17:20:04
    Author     : HP
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="clases.UsuarioSupervisor"%>
<%@page import="BaseDatos.EditarDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="css/VentanaAdmin.css">
            <title> Create Supervisory User</title>
        </head>
        <body>
            <head>
                <div class="encabezado">
                     <nav class="nav">
                         <a class="a-encabezado" href="Ventanas?accion=admin"> Home</a>
                        <a class="a-encabezado" href="Ventanas?accion=tienda">Store Users</a>
                        <a class="a-encabezado" href="Ventanas?accion=bodega">Warehouse Users</a>
                        <a class="a-encabezado" href="Ventanas?accion=supervisor">Supervisory User</a>
                        <a class="a-encabezado" href="">Reports</a>
                    </nav>

                </div>
            </head>
            <div class="container-createDelete">
                <div class="container">
                    <div class="logo">
                        <img src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8dXNlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=1400&q=60" alt="woman">
                    </div>
                    <div class="container-form">
                        <h1 class="title"> Create Supervisory User</h1>
                        <form method="POST" action="ServletCreate" class="form"> 
                            <label for="tex" class="label">Name:</label>
                            <input type="text" name="name" class="input" placeholder="GerardoTax" required>
                            <label for="tex" class="label">Username:</label>
                            <input type="text" name="user_name" class="input" placeholder="Gtax419holis" required>
                            <label for="password" class="label" >Password:</label>
                            <input type="password" name="password" class="input" placeholder="*******" required>
                            <label for="email" class="label" >Email address:</label>
                            <input type="email" name="email" class="input" placeholder="GerardoTax@gmail.com" required>
                            <button class="button" name="button" value="Supervisor" >Save</button>
                        </form>

                    </div>
                </div>
                <div class="container-list">
                    <table>
                        <thead>
                          <tr>
                            <th>Name </th>
                            <th>User Name</th>
                            <th>Password</th>
                            <th>E-mail</th>
                            <th></th>
                          </tr>
                        </thead>
                        <tbody>       
                            <c:forEach items="${lista}" var="supervisor">
                             <tr>
                                 <td> <c:out value="${supervisor.nombre}" ></c:out></td>
                                <td> <c:out value="${supervisor.nombreUsuario}" ></c:out></td> 
                                <td> <c:out value="${supervisor.contraseña}" ></c:out></td>
                                <td> <c:out value="${supervisor.email}" ></c:out></td>   
                                <th scope="row"> <button value="<c:out value="${usuario.codigo}" ></c:out>"  class="button-secundary">Edit </button></th>
                                <th scope="row"> <button    value="<c:out value="${usuario.codigo}" ></c:out>"  class="button-secundary"></button></th>
                             </c:forEach>
                                </tr>
                        </tbody>
                    </table>     
                </div>

            </div>
        </body>
    </html>
