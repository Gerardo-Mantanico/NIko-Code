<%-- 
    Document   : EditarPendientes
    Created on : 4/03/2023, 00:47:27
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
    <title>Editar y pendientes</title>
</head>
<body>
    <header class="header">
        <H1>Tienda</H1>
        <a class="a-header" href="">Pedidos y Envios</a>
    </header>
    <div class="container-store">
        <div class="container-left">
            <div class="container-order">
                <div class="container-header">
                    <h1 class="title">Editar pedido</h1>
                    <h4>mensaje de porque fue rechazado</h4>
                    <label for="text" class="label"> Id envio: </label>
                    <input type="text" class="input-number">
                    <label for="datetime" class="label">Fecha:</label>
                    <input type="datetime" name="" id="" class="input">
                    <label for="text" class="label">Estado:</label>
                    <input type="text" disabled class="input">
                    <div class="container-products">
                        <label for="number" class="label">cantidad:</label>
                        <input type="number" name="" id="" class="input-number">
                        <label for="text" class= "label">Productos:</label>
                        <select name="" id="" class="input-button"></select>
                        <input type="button" value="Agregar" class="input-button">
                    </div>
                    <div class="container-list">
                        <table class="default">
                            <tr>
                              <th>Codigo</th>
                              <th>costo unitario</th>
                              <th>cantidad</th>
                              <th>costo total</th>
                              <th><input type="button" value="Guardar" class="input-button"></th> 
                            </tr>
                            <tr>
                              <td>12345678</td>
                              <td>100.2</td>
                              <td><input type="number" name="" id="" value="200" class="input-number"></td>
                              <td>50000</td>
                              <td> <th><input type="button" value="Eliminar" class="input-button"></th> </td>
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
                    <h1 class="title">Pedidos pendientes</h1>
                    <div class="container-list">
                        <label for="" class="label"> Tienda:</label>
                        <select name="" id="" class="input-button"></select>
                        <input type="button" value="Aceptar" class="input-button">
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
