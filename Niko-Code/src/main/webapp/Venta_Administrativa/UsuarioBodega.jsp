<%-- 
    Document   : UsuarioBodega
    Created on : 28/02/2023, 17:25:23
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="../css/VentanaAdmin.css">
            <title> Create Warehouse Users</title>
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
                    </nav>

                </div>
            </head>
            <div class="container-createDelete">
                <div class="container">
                    <div class="logo">
                        <img src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8dXNlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=1400&q=60" alt="woman">
                    </div>
                    <div class="container-form">
                        <h1 class="title"> Create Warehouse User</h1>
                        <form method="POST" action="../ServletCreate" class="form"> 
                            <label for="tex" class="label">Name:</label>
                            <input type="text" name="name" class="input" placeholder="GerardoTax">
                            <label for="tex" class="label">Username:</label>
                            <input type="text" name="user_name" class="input" placeholder="Gtax419holis">
                            <label for="password" class="label" >Password:</label>
                            <input type="password" name="password" minlength="8"  class="input" placeholder="*******">
                            <label for="email" class="label" >Email address:</label>
                            <input type="email" name="email" class="input" placeholder="GerardoTax@gmail.com">
                            <label for="text" class="label">Stores:</label>
                            <input type="text" name="store" class="input" placeholder="2,3">                
                            <button class="button">Save</button>
                        </form>

                    </div>
                </div>
                <div class="container-list">
                    <table>
                        <thead>
                          <tr>
                            <th>User Name </th>
                            <th>Password</th>
                            <th>Area</th>
                            <th></th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                            <th scope="row"> <button class="button-secundary">Delete </button></th>
                          </tr>
                          <tr>

                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fadsfsdfsdfdsfsdfsdft</td>
                            <th scope="row"> <button class="button-secundary">Delete </button></th>
                          </tr>
                        </tbody>
                    </table>     
                </div>

            </div>
        </body>
    </html>
