<%-- 
    Document   : Venta_Principal
    Created on : 22/02/2023, 22:56:48
    Author     : dell
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="MenuBodega.jsp" %>

<!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="css/VentanaBodega.css">
            <style>
                .container-list {
                margin-left: 290px;}
            </style>
            <title>Bodega</title>
        </head>
        <body>
            <div class="container-createDelete">
                <div class="container-list">
                    <h1 class="title"> Aceptar o Rechar Devoluciones</h1>
                    <div class="container-buscar">
                        <form action="AODevolucion"  method="POST" class="form-buscar">
                            <label for="" class="label">Tienda:</label>
                            <select name="tienda"  class="input-search" required>
                                <c:forEach items="${listTiendas}" var="tienda">
                                    <option value="<c:out value="${tienda}" ></c:out>">
                                        <c:out value="${tienda}" ></c:out>
                                        </option>                           
                                </c:forEach>
                            </select>
                            <button class="button-secundary" name="button" value="elegirTienda">Buscar</button>
                            <label for="" class="label">Codigo Devolucion:</label>
                        </form>
                        
                         <form action="AODevolucion"  method="POST" class="form-buscar">
                             <select name="idDevolucion"  class="input-search" required>
                                <c:forEach items="${listDevolucion}" var="devolucion">
                                    <option value="<c:out value="${devolucion}" ></c:out>">
                                        <c:out value="${devolucion}" ></c:out>
                                        </option>                           
                                </c:forEach>
                            </select>
                            <button class="button-secundary" name="button" value="busrcarDevoluicon">Buscar Devolucion</button>
                        </form>
                    </div>
                    <div class="container-detalles">
                    <table>
                        <h1 class="title">Detalle de productos</h1>
                        <thead>
                          <tr>
                            <th>Codigo</th>
                            <th>Costo</th>
                            <th>Cantidad</th>
                            <th>SubTotal</th> 
                            <th>Motivo</th>
                        <form action="AODevolucion" method="POST">
                            <th><button class="button-secundary" name="button" value="Aceptar">Aceptar</button></th>
                            <th><button class="button-secundary" name="button" value="Rechazar">Rechazar</button></th>
                            </form>
                          </tr>
                        </thead>
                        <c:forEach items="${listproductos}" var="producto"> 
                         <tr>
                             <td><c:out value="${producto.codigo}"></c:out></td>
                             <td>Q <c:out value="${producto.costo}"></c:out></td>
                             <td><c:out value="${producto.cantidad}"></c:out></td>
                             <td>Q  <c:out value="${producto.costoTotal}"></c:out></td>
                             <td>  <c:out value="${producto.motivo}"></c:out></td>
                         </tr>
                        </c:forEach> 
                    </table>
                </div>     
                </div>
            </div>
        </body>
    </html>
