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
             <link rel="stylesheet" href="../css/VentanaBodega.css">
            <title>Bodega</title>
        </head>
        <body>
            <div class="container-createDelete">
                <div class="container">
                    <div class="logo">
                        <img src="https://images.unsplash.com/photo-1601599963565-b7ba29c8e3ff?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464&q=80"> </div>
                    <div class="container-form">
                        <h1 class="title"> Agregar Productos </h1>
                        <form method="POST" action="CrearEnvios" class="form">
                            <label for="" class="label">Producto</label>
                                <select name="producto"  class="input-search">
                                    <c:forEach items="${listProductos}"   var="producto">
                                    <option value="<c:out value="${producto.nombre}"></c:out>"> 
                                        <c:out value="${producto.nombre}"></c:out>
                                    </c:forEach>
                                    </option>
                                </select>
                            <label for="tex" class="label">Cantidad:</label>
                            <input type="text" name="cantidad" class="input"  required>
                            <c:forEach items="${msj}" var="mensaje">
                                <h5> <c:out value="${mensaje}" ></c:out></h5>
                            </c:forEach>
                            <c:forEach  items="${estado}" var="tipo">
                                <button class="button" value="<c:out value="${tipo}"></c:out>" name="button" >Guardar</button>
                            </c:forEach>
                        </form>
                    </div>
                </div>
                <div class="container-list">
                    <div class="container-buscar">
                        <form action="CrearEnvios"  method="POST" class="form-buscar">
                            <label for="" class="label">Tienda:</label>
                            <select name="tienda"  class="input-search" required>
                                <c:forEach items="${listTiendas}" var="tienda">
                                    <option value="<c:out value="${tienda}" ></c:out>">
                                        <c:out value="${tienda}" ></c:out>
                                        </option>                           
                                </c:forEach>
                            </select>
                            <button class="button-secundary" name="button" value="elegirTienda">Buscar</button>
                            <label for="" class="label">Codigo dePedido:</label>
                        </form>
                        
                         <form action="CrearEnvios"  method="POST" class="form-buscar">
 
                             <select name="idPedido"  class="input-search" required>
                                <c:forEach items="${listpedio}" var="pedido">
                                    <option value="<c:out value="${pedido}" ></c:out>">
                                        <c:out value="${pedido}" ></c:out>
                                        </option>                           
                                </c:forEach>
                            </select>
                            <button class="button-secundary" name="button" value="busrcarPedido">Buscar Pedido</button>
                        </form>
                    </div>
                    <div class="container-detalles">
                    <table>
                        <h1 class="title">Detalle de productos</h1>
                        <thead>
                          <tr>
                            <th>Codigo</th>
                            <th>nombre</th>
                            <th>Costo</th>
                            <th>Cantidad</th>
                            <th>SubTotal</th>
                        <form action="CrearEnvios" method="POST">
                            <th><button class="button-secundary" name="button" value="CrearEnvio">Crear Envio</button></th>
                            </form>
                          </tr>
                        </thead>
                        <c:forEach items="${listaProducto}" var="producto"> 
                         <tr>
                         <form action="CrearEnvios" method="POST">
                            <td>  
                                 <input type="text" name="idProducto" value="<c:out value="${producto.codigo}"></c:out>" class="input-idProducto" readonly=>
                            </td>
                                 <td><c:out value="${producto.nombre}"></c:out></td>
                             <td>Q <c:out value="${producto.costo}"></c:out></td>
                             <td><c:out value="${producto.existencia}"></c:out></td>
                             <td>Q  <c:out value="${producto.precio}"></c:out></td>
                             <th>
                                 <button class="input-producto"  name="button" value="Editar">Editar</button>
                                 <button class="input-producto" name="button" value="Eliminar">Eliminar</button>
                             </th>
                         </form>
                         </tr>
                        </c:forEach> 
                    </table>
                </div>     
                </div>
            </div>
        </body>
    </html>
