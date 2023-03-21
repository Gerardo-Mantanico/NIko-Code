<%-- 
    Document   : MenuAdmin
    Created on : 20/03/2023, 10:55:35
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/Ventana.css">
        <link rel="stylesheet" href="../css/Ventana.css">
        <style>
             .button-secundary {
                 width: 100%;
                }
            </style>
        <title>JSP Page</title>
    </head>
    <body>
        <head>
                <div class="encabezado">
                    <nav class="nav">
                        <a class="a-encabezado" href="Ventanas?accion=admin"> Inicio</a>
                        <a class="a-encabezado" href="Ventanas?accion=tienda">Usuario de Tienda</a>
                        <a class="a-encabezado" href="Ventanas?accion=bodega">Usuario de Bodega</a>
                        <a class="a-encabezado" href="Ventanas?accion=supervisor">Supervisor</a>
                        <a class="a-encabezado" href="Ventanas?accion=reporteAdmin">Informes</a>
                    </nav>
                    <form action="ServletCreate" method="post" class="form-file" enctype="multipart/form-data">
                        <input type="file" name="fileInput">
                        <input value="cargarArchivo"  name="button" type="submit">
                    </form>
                    <a class="a-encabezado" href="index.jsp"> Salir</a>
                </div>
            </head>
    </body>
</html>
