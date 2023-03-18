<%-- 
    Document   : Reporte
    Created on : 16/03/2023, 20:49:03
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="MenuBodega.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
             <link rel="stylesheet" href="css/EstiloTienda.css"/>
             <link rel="stylesheet" href="../css/Reportebodega.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-store">
            <div class="container-left">
                <div class="container-order">
                    <div class="container-header">
                        <h1 class="title">Crear Pedido</h1>
                        <div id="detail_modal" class="modal">
                            <form action="">
                             <label class="label">Tienda:</label>
                             <select name="idPedido"  class="input-search" required>
                                <c:forEach items="${listpedio}" var="pedido">
                                    <option value="<c:out value="${pedido}" ></c:out>">
                                        <c:out value="${pedido}" ></c:out>
                                        </option>                           
                                </c:forEach>
                            </select>
                            <label for="" class="label"> Fecha:</label>
                            <input type="date" name="fecha"   class="input">
                            <button class="input-button">Buscar</button>
                        </form>
                        <div class="container-detalles">
                            <table>
                                <h1 class="title">Detalle de Envios</h1>
                                <thead>
                                    <tr>
                                        <th>Clave de artículo</th>
                                        <th>Nombre</th>
                                        <th>Descripción</th>
                                        <th>Almacén</th>
                                        <th>Precio por unidad</th>
                                        <th>Precio total</th>
                                        <th>Subtotal</th>
                                        <th>Impuesto</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        </div>
                    </div> 
                </div>
            </div>
        </div>
    </body>
</html>
