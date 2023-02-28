/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class ConexionBase {
    private final  String USER="root";
    private final  String PASSWORD="123456789";
    private final  String URL_MYSQL="jdbc:mysql://localhost:3306/data_tienda";
    private Connection con;
    
    
    public Connection conexion() throws ClassNotFoundException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(URL_MYSQL,USER,PASSWORD);
            System.out.println("La conexion con la base de datos fue satiscaftorio");
            
        }catch (SQLException ex) {
            System.out.println("Error en la coxeion"+ ex);
            
        }
        return con;
    }
}

