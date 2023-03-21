<%-- 
    Document   : Reportes
    Created on : 20/03/2023, 13:22:36
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="MenuAdmin.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="css/EstiloReporte.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-inicio">
                <div class="container-reporte">
                    <H3 class="title"  >Reporte de las 5 tiendas con mas pedidos</H3>
                    <label for="date" class="label">Fecha</label>
                    <form action="ServletReportes" method="POST">
                    <input type="date" name="date"  class="input-date" required>
                    <button class="input" name="button" value="reporteTienda"> buscar</button>
                    </form>
                    <table>
                        <thead>
                          <tr class="tr">
                            <th>Tienda</th>
                            <th>Direccion</th>
                            <th>Tipo</th>
                          </tr>
                        </thead>
                        <c:forEach items="${listaTienda}"  var="tienda">
                            <tbody>
                            <td><c:out  value="${tienda.codigo}"> </c:out> </td>
                            <td><c:out value="${tienda.direccion}"></c:out> </td>
                            <td><c:out value="${tienda.tipo}"></c:out> </td>

                        </c:forEach>
                    </table>     
                </div>
                <div class="container-reporte">
                    <H3 class="title"  >Reporte de los 5 Usuarios con mas envios</H3>
                    <label for="date" class="label">Fecha</label>
                    <form action="ServletReportes" method="POST">
                    <input type="date" name="date"  class="input-date" required>
                    <button class="input" name="button" value="reporteBodega"> buscar</button>
                    </form>
                    <table>
                        <thead>
                          <tr class="tr">
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>Nombre de usuario</th>
                            <th>Envios Generados</th>
                          </tr>
                        </thead>
                        <c:forEach items="${lista}"  var="bodega">
                            <tbody>
                                <td><c:out  value="${bodega.codigo}"> </c:out> </td>
                                <td><c:out value="${bodega.nombre}"></c:out> </td>
                                <td><c:out value="${bodega.nombreUsuario}"></c:out> </td>
                                <td><c:out value="${bodega.email}"></c:out> </td>
                            </tbody>
                        </c:forEach>
                    </table>     
                </div>
                <div class="container-reporte">
                    <H3 class="title"  >Reporte de los 5 usuarios con mas pedidos</H3>
                    <form action="ServletReportes" method="POST">
                        <input type="date" name="date"  class="input-date" required>
                    <button class="input" name="button" value="reporteUsuario"> buscar</button>
                    </form>
                    <table>
                        <thead>
                          <tr class="tr">
                                <th>Codigo</th>
                                <th>Nombre</th>
                                <th>Nombre de usuario</th>
                                <th>pedidos Generados</th>
                          </tr>
                        </thead>
                        <c:forEach items="${listas}"  var="userTienda">
                            <tbody>
                                <td><c:out  value="${userTienda.codigo}"> </c:out> </td>
                                <td><c:out value="${userTienda.nombre}"></c:out> </td>
                                <td><c:out value="${userTienda.nombreUsuario}"></c:out> </td>
                                <td><c:out value="${userTienda.email}"></c:out> </td>
                            </tbody>
                        </c:forEach>
                    </table>     
                </div>
            <div></div>
            <div></div>
        </div>
        <div class="container-inicio">
             <div class="container-reporte">
                    <H3 class="title"  >Reporte de los productos mas solicitados</H3>
                    <label for="date" class="label">Fecha</label>
                    <form action="ServletReportes" method="POST">
                    <input type="date" name="date"  class="input-date" required>
                    <button class="input" name="button" value="ReporteProducto"> buscar</button>
                    </form>
                    <table>
                        <thead>
                          <tr class="tr">
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>Cantidad Solicitada</th>
                          </tr>
                        </thead>
                        <c:forEach items="${list}"  var="producto">
                            <tbody>
                            <td><c:out  value="${producto.codigo}"> </c:out> </td>
                            <td><c:out value="${producto.nombre}"></c:out> </td>
                            <td><c:out value="${producto.existencia}"></c:out> </td>

                        </c:forEach>
                    </table>     
                </div>
                 <div class="container-reporte">
                    <H3 class="title"  >Reporte de productos dañados</H3>
                    <label for="date" class="label">Fecha</label>
                    <form action="ServletReportes" method="POST">
                    <input type="date" name="date"  class="input-date" required>
                    <button class="input" name="button" value="ReporteProducto"> buscar</button>
                    </form>
                    <table>
                        <thead>
                          <tr class="tr">
                            <th>Codigo</th>
                            <th>D</th>
                            <th>Tipo</th>
                          </tr>
                        </thead>
                        <c:forEach items="${listaTienda}"  var="tienda">
                            <tbody>
                            <td><c:out  value="${tienda.codigo}"> </c:out> </td>
                            <td><c:out value="${tienda.direccion}"></c:out> </td>
                            <td><c:out value="${tienda.tipo}"></c:out> </td>

                        </c:forEach>
                    </table>     
                </div>
        </div>
    </body>
</html>
