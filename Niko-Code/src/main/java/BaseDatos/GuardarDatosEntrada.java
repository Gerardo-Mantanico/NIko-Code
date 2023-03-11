/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import Resources_servlet.ServletCreate;
import Resources_servlet.ServletLogin;
import clases.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.ConexionBase;
import resources.Estado;

/**
 *
 * @author HP
 */
public class GuardarDatosEntrada {
       private  ConexionBase con= new ConexionBase();
       private PreparedStatement preparedStatement; 
    
       //metodo para verificar usuarios
     public void  verificacionUsuario(int codigo,String usuario, String contraseña,String tipo){
            String query = "SELECT * FROM LOGIN WHERE user_name = '"+usuario+"'";
            try{
                PreparedStatement p = con.conexion().prepareStatement(query);
                ResultSet r = p.executeQuery();
                if(r.next()){System.out.println("Este usuario ya existe");}
                else{
                     crearUsuario(codigo,usuario,contraseña,tipo);}
            }catch(SQLException ex){
                System.out.println("Error en "+ ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     // metodo para crear usuario
     public void crearUsuario(int codigo,String usuario, String contraseña, String tipo) {
       String query = "INSERT INTO LOGIN (_code,user_name, _password, _type, state) VALUES (?,?, ?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, codigo);
            preparedStatement.setString(2, usuario);
            preparedStatement.setString(3, contraseña);
            preparedStatement.setString(4, tipo);
            preparedStatement.setString(5,   Estado.ACTIVADO.name());
            preparedStatement.executeUpdate();
            System.out.println("Usuario creado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     //metodo para crear prodcuto 
     public void crearProductos(Producto prodcuto){
         String query = "INSERT INTO CATALOGUE (_code, _name, cost,price,existence) VALUES (?,?, ?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, prodcuto.getCodigo());
            preparedStatement.setString(2, prodcuto.getNombre());
            preparedStatement.setDouble(3, prodcuto.getCosto());
            preparedStatement.setDouble(4, prodcuto.getPrecio());
            preparedStatement.setInt(5,prodcuto.getExistencia());
            preparedStatement.executeUpdate();
            System.out.println("producto creado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
