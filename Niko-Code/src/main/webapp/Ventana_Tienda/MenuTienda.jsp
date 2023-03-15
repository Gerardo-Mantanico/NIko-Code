<%-- 
    Document   : MenuTienda
    Created on : 13/03/2023, 14:08:17
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
  // obtiene el valor del atributo de sesión llamado "nombreUsuario"
  String tienda = (String) session.getAttribute("tienda");
  String IdUsuario= (String) session.getAttribute("codigoUsuario");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="css/EstiloTienda.css"/>

        <title>JSP Page</title>
    </head>
    <body>
        <header class="header">
        
        <H1>Tienda: <%= tienda %></H1>
         
        <a class="a-header" href="PedidosListProducto?button=agregar"> Crear Pedido</a>
        <a class="a-header" href="Ventanas?accion=incidencias&valor=<%= tienda %>"> Incidencias</a>
        <a class="a-header" href="Ventanas?accion=RecibirEnvios&valor=<%= tienda %>">Recibir Envios</a>
        <a class="a-header" href="Ventanas?accion=devolucion&valor=<%= tienda %>">Devolucion</a>
        <a class="a-header" href="Ventanas?accion=inicio"> Salir</a>
        
        <c:forEach items="${codigoUsuario}" var="id">
        <a class="a-header"> <H3>   Id Usuario: <%= IdUsuario %></H3></a>
        </c:forEach>
    </header>
    </body>
</html>
