<%-- 
    Document   : RecibirEnvio
    Created on : 13/03/2023, 12:24:32
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="MenuTienda.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/EstiloTienda.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-store">
            <div class="container-receiver">
                <div class="container-header">
                    <h1 class="title">Recibir Envios</h1>
                    <div class="container-products">
                        <form action="EnvioLisProducto" method="POST">
                            <label for="text" class="label">Tienda</label>
                            <input type="text" readonly name="tienda" class="input" value="<%= tienda%>">
                            <label for="text" class= "label">Id  de envios:</label>
                            <select name="envio"  class="input-search">
                                <c:forEach items="${listEnvio}" var="envio">
                                    <option value="<c:out value="${envio.id}" ></c:out>">
                                        <c:out value="${envio.id}" ></c:out>
                                        </option>                           
                                </c:forEach>
                            </select>
                        <button class="input-button" value="ver" name="button" >Ver</button>
                        </form>
                    </div>
                    <div class="container-list">
                        <table class="default">
                            <tr>
                                <th>Codigo</th>
                                <th>costoU</th>
                                <th>cantidad</th>
                                <th>Costo total</th>
                            <form action="EnvioLisProducto" method="POST">
                                <th><button class="input-button" value="Recibir" name="button" >Recibir</button></th> 
                            </form>
                        </tr>
                        <c:forEach items="${productosEnvio}" var="pro">
                            <tr>
                                <td><c:out value="${pro.codigo}" ></c:out></td>
                                <td><c:out value="${pro.costo}"> </c:out></td>
                               <td><c:out value="${pro.existencia}"> </c:out></td>
                               <td><c:out value="${pro.precio}"> </c:out></td>
                            </tr>
                            </c:forEach>
                          </table>
                         
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
