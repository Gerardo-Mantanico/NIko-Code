/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.niko.code.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class ConexionBase {
    private  String USER="root";
    private  String PASSWORD="123456789";
    private  String URL_MYSQL="jdbc:mysql://localhost:3306/data_tienda";
    private Connection con;
    
    
    public Connection conexion(){
        try{
          
            con=DriverManager.getConnection(URL_MYSQL,USER,PASSWORD);
            System.out.println("La conexion con la base de datos fue satiscaftorio");
            
        }catch (SQLException ex) {
            System.out.println("Error en la coxeion"+ ex);
            
        }
        return con;
    }
}

