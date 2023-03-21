<%-- 
    Document   : ReporteBodega
    Created on : 21/03/2023, 06:01:15
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="MenuBodega.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <link rel="stylesheet" href="css/EstiloReporteBodega.css">
        </style>
        <title>JSP Page</title>
    </head>
    <body>
               <div class="container-inicio">
                <div class="container-reporte">
                    <h1 class="title"> Reportes de bodega</h1>
                    <div class="container-form">
                    <form action="ServletReportes" method="POST" >
                    <label for="date" class="label">Tienda</label>
                    <select class="input" name="tienda">
                        <c:forEach items="${listTiendas}" var="tienda">
                        <option value="<c:out value="${tienda}"></c:out>"><c:out value="${tienda}"></c:out></option>
                        </c:forEach>
                    </select>
                    <label for="date" class="label">Fecha</label> 
                    <input type="date" name="date"  class="input-date" required>
                    <button class="input" name="button" value="reporteBodegaEnvios"> buscar</button>
                    </form>
                        </div>
                        <H3 class="title"  >Reporte de envios de Tienda</H3>
                    <table>
                        <thead>
                          <tr class="tr">
                            <th>Pedido</th>
                            <th>Tienda</th>
                            <th>Codigo de usuario</th>
                            <th>fecha de salida</th>
                            <th>Total</th>
                          </tr>
                        </thead>
                        <c:forEach items="${listEnvio}"  var="envio">
                            <tbody>
                            <td><c:out  value="${envio.id}"> </c:out> </td>
                            <td><c:out value="${envio.tienda}"></c:out> </td>
                            <td><c:out value="${envio.codigoUsuario}"></c:out> </td>
                            <td><c:out value="${envio.fechaSalida}"></c:out> </td>
                            <td>Q <c:out value="${envio.total}"></c:out> </td>
                        </c:forEach>
                    </table>
                </div>
                <div class="container-reporte">
                    <H3 class="title"  >Reporte de incidencias solucionadas</H3>
                    <table>
                        <thead>
                          <tr class="tr">
                            <th>id Envio</th>
                            <th>Tienda</th>
                            <th>Codigo de usuario</th>
                            <th>Fecha</th>
                            <th>Solucion</th>
                            <th>Estado</th>
                          </tr>
                        </thead>
                        <c:forEach items="${listIncidecia}"  var="incidencia">
                            <tbody>
                                <td><c:out  value="${incidencia.envio}"> </c:out> </td>
                                <td><c:out value="${incidencia.tienda}"></c:out> </td>
                                <td><c:out value="${incidencia.codigoUsuario}"></c:out> </td>
                                <td><c:out value="${incidencia.fecha}"></c:out> </td>
                                 <td><c:out value="${incidencia.solucion}"></c:out> </td>
                                  <td><c:out value="${incidencia.estado}"></c:out> </td>
                            </tbody>
                        </c:forEach>
                    </table>     
                </div>
                <div class="container-reporte">
                    <H3 class="title"  >Reporte de devoluciones</H3>
                    <table>
                        <thead>
                          <tr class="tr">
                              <th>id Envio</th>
                              <th>Tienda</th>
                              <th>Codigo de usuario</th>
                              <th>Fecha</th>
                              <th>total</th>
                              <th>Estado</th>
                          </tr>
                        </thead>
                        <c:forEach items="${listDevolucion}"  var="devolucione">
                            <tbody>
                            <td><c:out  value="${devolucione.envio}"> </c:out> </td>
                            <td><c:out value="${devolucione.tienda}"></c:out> </td>
                            <td><c:out value="${devolucione.codigoUsuario}"></c:out> </td>
                            <td><c:out value="${devolucione.fecha}"></c:out> </td>
                            <td><c:out value="${devolucione.total}"></c:out> </td>
                            <td><c:out value="${devolucione.estado}"></c:out> </td>
                            </tbody>
                    </c:forEach>
                    </table>     
                </div>
            <div></div>
            <div></div>
        </div>
    </body>
</html>
