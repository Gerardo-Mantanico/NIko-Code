<%-- 
    Document   : Tienda
    Created on : 4/03/2023, 00:36:16
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/EstiloTienda.css"/>
    <title>Tienda</title>
</head>
<body>
    <header class="header">
        <H1>Tienda</H1>
        <a class="a-header" href="IncidenciaDevolucion.jsp"> Incidencias y devoluciones</a>
    </header>
    <div class="container-store">
        <div class="container-left">
            <div class="container-order">
                <div></div>
                <div class="container-header">
                    <h1 class="title">create order</h1>
                    <label for="text" class="label"> id </label>
                    <input type="text" class="input-number" disabled>
                    <label for="datetime" class="label">Fecha:</label>
                    <input type="datetime" name="" id="" class="input">
                    <label for="text" class="label">Tienda</label>
                    <input type="text" disabled class="input">
                    <div class="container-products">
                        <label for="number" class="label">cantidad:</label>
                        <input type="number" name="" id="" class="input-number">
                        <label for="text" class= "label">Productos:</label>
                        <input type="search" name="" id="" class="input-search">
                        <input type="button" value="buscar" class="input-button">
                    </div>
                    <div class="container-list">
                        <table class="default">
                            <tr>
                              <th>Codigo</th>
                              <th>costo unitario</th>
                              <th>cantidad</th>
                              <th>costo total</th>
                              <th><input type="button" value="Crear" class="input-button"></th> 
                            </tr>
                            <tr>
                              <td>12345678</td>
                              <td>100.2</td>
                              <td>100</td>
                              <td>50000</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>total:</td>
                              </tr>              
                          </table>
                    </div>
                </div>
            </div>
            
        </div>
        <div class="container-right">
            <div class="container-receiver">
                <div class="container-header">
                    <h1 class="title">Recibir Envios</h1>
                    <div class="container-list">
                        <table class="default">
                            <tr>
                              <th>Codigo</th>
                              <th>costo</th>
                              <th>cantidad</th>
                              <th>cantidad</th>
                            </tr>
                            <tr>
                              <td>Soleado</td>
                              <td>Mayormente soleado</td>
                              <td>Parcialmente nublado</td>
                            </tr>     
                          </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>