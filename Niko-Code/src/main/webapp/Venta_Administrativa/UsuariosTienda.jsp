<%-- 
    Document   : UsuariosTienda
    Created on : 28/02/2023, 17:01:22
    Author     : HP
--%>

<%@page import="clases.UsuarioTienda"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BaseDatos.EditarDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="MenuAdmin.jsp" %>
<!DOCTYPE html>
 <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="css/VentanaAdmin.css"/>
            <style>
                .button-secundary {
                  width: 100%;}
            </style>
            <title>Store Users</title>
        </head>
        <body>
            <div class="container-createDelete">
                <div class="container">
                    <div class="logo">
                        <img src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8dXNlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=1400&q=60" alt="woman">
                    </div>
                    <div class="container-form">
                        <h1 class="title"> Crear usuario de tienda</h1>
                        <form method="POST" action="ServletCreate" class="form"> 
                            <label for="tex" class="label">Nombre:</label>
                            <input type="text" name="name" class="input" placeholder="GerardoTax" required>
                            <label for="text" class="label">Tienda:</label>
                            <input type="text" name="store" class="input" placeholder="2" required>
                            <label for="tex" class="label">Nombre de usuario:</label>
                            <input type="text" name="user_name" class="input" placeholder="Gtax419holis" required>
                            <label for="password" class="label" >Contraseña:</label>
                            <input type="password" name="password" class="input" placeholder="*******" required>
                            <label for="email" class="label">Direccion de correo electronico:</label>
                            <input type="email" name="email" class="input" placeholder="GerardoTax@gmail.com" required>
                            <c:forEach items="${msj}" var="mensaje">
                                <h5> <c:out value="${mensaje}" ></c:out></h5>
                            </c:forEach>
                            <button class="button" value="Tienda" name="button" >Guardar</button>
                        </form>
                    </div>
                </div>
                <div class="container-list">
                    <table>
                        <thead>
                          <tr>
                            <th>Nombre</th>
                            <th>Tienda</th>
                            <th>Nombre de usuario</th>
                            <th>Contraseña</th>
                            <th>Correo elecctronico</th>
                            <th></th>
                          </tr>
                        </thead>
                        <tbody>       
                            <c:forEach items="${lista}" var="usuario">
                             <tr>
                                <td> <c:out value="${usuario.nombre}" ></c:out></td>
                                <td> <c:out value="${usuario.tienda}" ></c:out></td>
                                <td> <c:out value="${usuario.nombreUsuario}" ></c:out></td>
                                <td>*********</td>
                                <td> <c:out value="${usuario.email }" ></c:out></td>  
                                <th scope="row"> <button value="<c:out value="${usuario.codigo}" ></c:out>"  class="button-secundary">Editar </button></th>
                                <th scope="row"> <button    value="<c:out value="${usuario.codigo}" ></c:out>"  class="button-secundary">Desactivar </button></th>
                             </c:forEach>
                                </tr>
                        </tbody>
                    </table>     
                </div>

            </div>
        </body>
    </html>