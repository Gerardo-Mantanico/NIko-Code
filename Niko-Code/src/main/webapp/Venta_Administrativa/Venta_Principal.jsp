<%-- 
    Document   : Venta_Principal
    Created on : 22/02/2023, 22:56:48
    Author     : dell
--%>

<%@page import="clases.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BaseDatos.EditarDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="../css/Ventana.css">
            <title>Create and delete user</title>
        </head>
        <body>
            <head>
                <div class="encabezado">
                    <nav class="nav">
                        <a class="a-encabezado" href="Venta_Principal.jsp"> Home</a>
                        <a class="a-encabezado" href="UsuariosTienda.jsp">Store Users</a>
                        <a class="a-encabezado" href="UsuarioBodega.jsp">Warehouse Users</a>
                        <a class="a-encabezado" href="SupervisorTienda.jsp">Supervisory User</a>
                        <a class="a-encabezado" href="">Reports</a>
                        <input class="inputt" type="file"> 
                    </nav>

                </div>
            </head>
            <div class="container-createDelete">
                <div class="container">
                    <div class="logo">
                        <img src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8dXNlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=1400&q=60" alt="woman">
                    </div>
                    <div class="container-form">
                        <h1 class="title"> Create Admin User </h1>
                        <form method="POST" action="../ServletCreate" class="form"> 
                            <label for="tex" class="label">Name:</label>
                            <input type="text" name="name" class="input" placeholder="GerardoTax" required>
                            <label for="tex" class="label">Username:</label>
                            <input type="text"  value="wicho" name="user_name" class="input" placeholder="Gtax419holis" required>
                            <label for="password" class="label" >Password:</label>
                            <input type="password" name="password" class="input" placeholder="*******" required>
                            <button class="button" value="admin" name="button" >Create</button>
                        </form>

                    </div>
                </div>
                <div class="container-list">
                    <table>
                        <thead>
                          <tr>
                            <th>Name</th>
                            <th>User Name</th>
                            <th>Password</th>
                            <th></th>
                          </tr>
                        </thead>
                        <tbody>
                            <tr>
                               <% EditarDB db=new EditarDB();
                                ArrayList<Usuario> modelList;
                                modelList=db.listUsuarioTienda("user_admin");
                                for(int i = 0; i < modelList.size(); i++) {  
                                %>
                                 <td><% out.println(modelList.get(i).getNombre());%></td>
                                 <td><% out.println(modelList.get(i).getNombreUsuario());%></td>
                                 <td><%out.println(modelList.get(i).getContraseña());%></td>
                            <th scope="row"> <button class="button-secundary">Edit </button></th>
                            <th scope="row"> <button class="button-secundary">Delete </button></th>
                          </tr>
                          <%}%>
                        </tbody>
                    </table>     
                </div>
            </div>
        </body>
    </html>
