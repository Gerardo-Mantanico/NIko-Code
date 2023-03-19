<%-- 
    Document   : Tienda
    Created on : 4/03/2023, 00:36:16
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="MenuTienda.jsp" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/EstiloTienda.css"/>
    <title>Tienda</title>
</head>
<body>
    <div class="container-store">
        <div class="container-left">
            <div class="container-order">
                <div></div>
                <div class="container-header">
                    <h1 class="title">Crear Pedido</h1>
                    <form method="POST" action="PedidosListProducto" class="form">
                    <label for="text"  class="label"> id </label>
                    <input type="text" class="input-number" readonly  name="id" value="<%=IdUsuario%>">
                    <c:forEach items="${fecha}" var="fecha">
                    <label for="datetime" class="label">Fecha:</label>
                    <input type="date"  value="<c:out value="${fecha}" ></c:out>"   name="fecha" id="" class="input"  required>
                    </c:forEach>
                    <label for="text" class="label">Tienda</label>
                    <c:forEach items="${tienda}" var="tiendas">
                    <input type="text" readonly name="tienda" class="input" value="<%= tienda %>">
                    </c:forEach>
                    <div class="container-products">
                        <label for="text" class="label">cantidad:</label>
                        <input type="text" name="cantidad"  class="input-number" required="">
                        <label for="text" class= "label">Productos:</label>
                        <select name="producto"  class="input-search">
                        <c:forEach items="${lista}" var="producto">
                            <option value="<c:out value="${producto.codigo}" ></c:out>">
                                <c:out value="${producto.nombre}" ></c:out>
                            </option>                           
                        </c:forEach>
                        </select>
                        <button class="input-button" value="agregar" name="button" >Agregar</button>
                        </form>
                    </div>
                    <div class="container-list">
                        <table class="default">
                            <tr>
                              <th>Codigo</th>
                              <th>costo unitario</th>
                              <th>cantidad</th>
                              <th>costo total</th>
                            <form method="POST" action="PedidosListProducto" class="form">
                              <th><button class="input-button" value="pedido" name="button" >Pedido</button></th> 
                            </form>
                            </tr>
                            <c:forEach items="${produ}" var="product">
                                <tr>                    
                                    <td><c:out value="${product.codigo}" ></c:out></td>
                                    <td><c:out value="Q ${product.precio}" ></c:out></td>
                                    <td><c:out value="${product.existencia}" ></c:out></td>
                                    <td><c:out value="Q ${product.costo}" ></c:out></td>
                                </tr>
                            </c:forEach>
                            <c:forEach items="${total}" var="total">
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td value="<c:out value="${total}" ></c:out>" name="total">total: Q <c:out value="${total}" ></c:out></td>
                              </tr>
                            </c:forEach>
                          </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>