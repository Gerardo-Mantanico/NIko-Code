<%-- 
    Document   : MenuTienda
    Created on : 13/03/2023, 14:08:17
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="css/EstiloTienda.css"/>
         <link rel="stylesheet" href="css/ReporteBodega.css">
        <title>JSP Page</title>
    </head>
    <body>
        <header class="header">
        
        <H1>Bodega Central</H1>
         
        <a class="a-header" href="Ventanas?accion=crearEncvio"> Crear Envio</a>
        <a class="a-header" href="Ventanas?accion=SolucionarIncidencias">Solucionar Inicidencias</a>
        <a class="a-header" href="Ventanas?accion=EstadoDevolucion">Aceptar Devoluciones </a>
        <a class="a-header" href="Ventanas?accion=reporteBodega">Informes</a>
        <a class="a-header" href="Ventanas?accion=inicio"> Salir</a>
    </header>
    </body>
</html>
