<%-- 
    Document   : IncidenciaDevolucion
    Created on : 4/03/2023, 00:43:52
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="MenuTienda.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/EstiloTienda.css"/>
    <title>Incidencias y Devoluciones</title>
</head>
<body>
    <div class="container-store">
        <div class="container-left">
            <div class="container-order">
                <div class="container-header">
                    <h1 class="title">Crear Devoluciones</h1>
                    <form action="ServletDevolucion" method="POST">
                    <label for="text" class="label"> Id envio: </label>
                   <select name="idEnvio"  class="input-search" required>
                    <c:forEach items="${listEnvio}" var="envio">
                        <option value="<c:out value="${envio}" ></c:out>"><c:out value="${envio}" ></c:out></option>
                    </c:forEach> 
                    </select>
                    <label for="text" class="label"> Tienda: </label>
                    <input type="text" class="input-number" readonly  name="tienda" value="<%=tienda%>" >
                    <label for="text" class="label"> Usuario: </label>
                    <input type="text" class="input-number"  readonly  name="codigoUsuario" value="<%=IdUsuario%>">
                    <label for="datetime" class="label">Fecha:</label>
                    <c:forEach items="${fecha}" var="fecha">
                        <input type="date" name="fecha"  value="<c:out value="${fecha}"></c:out>" class="input" required>
                    </c:forEach>
                    <button class="input-button" name="button" value="buscar">Buscar</button>
                    </form>
                    <div class="container-products">
                        <form action="ServletDevolucion" method="Post">
                        <label for="number" class="label">Motivo:</label>
                        <select name="estado"   class="input-search">
                            <c:forEach items="${estado}" var="estado">
                                <option value="<c:out value="${estado.name()}"></c:out> "><c:out value="${estado.name()}"></c:out> </option>
                            </c:forEach>
                        </select>
                        <label for="text" class= "label">Productos:</label>
                        <select name="productoCodigo" class="input-search" required>
                            <c:forEach items="${listaProductos}" var="producto">
                                <option value="<c:out value="${producto.codigo}" ></c:out>"><c:out value="${producto.codigo}" ></c:out></option>
                            </c:forEach>
                        </select>
                        <label for="number"class="label" >Cantidad:</label>
                        <input  class="input-number" type="number"  name="cantidad" id="pennies" min="1"   step="1" required>
                        <button class="input-button" name="button" value="Agregar">Agregar</button>
                        </form>
                        <c:forEach items="${mensaje}" var="mensaje">
                            <h4 class="title"><c:out value="${mensaje}"></c:out></h4>
                        </c:forEach>
                    </div>
                    <div class="container-list">
                        <table class="default">
                            <tr>
                              <th>Codigo</th>
                              <th>Cantidad</th>
                              <th>Motivo</th>
                              <th>CostoU</th>
                              <th>Sub total</th>
                            <form action="ServletDevolucion" method="POST">
                              <th>  <button class="input-button" name="button" value="Crear">Crear</button></th>
                              </form>
                            </tr>
                            <c:forEach items="${productosEnvio}" var="producto">
                            <tr>
                               <td><c:out value="${producto.codigo}"></c:out></td>
                               <td><c:out value="${producto.cantidad}"></c:out></td>
                               <td> <c:out value="${producto.motivo}"></c:out></td>
                               <td>Q  <c:out value="${producto.costo}"></c:out></td>
                               <td>Q  <c:out value="${producto.costoTotal}"></c:out></td>
                            </tr>
                            </c:forEach>
                            
                             <tr>
                                 <td></td>
                                 <td></td>
                                 <td></td>
                                 <td></td>
                                 <td></td>
                                 <c:forEach items="${total}" var="total">
                                     <td class="subtotal">Q <c:out value="${total}"></c:out> </td>
                                 </c:forEach>
                            </tr>
                          </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>