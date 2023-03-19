/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import Resources_servlet.ServletCreate;
import Resources_servlet.ServletLogin;
import clases.TiendasBodega;
import clases.Usuario;
import clases.UsuarioSupervisor;
import clases.UsuarioTienda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.ConexionBase;
import resources.Estado;

/**
 *
 * @author HP
 */
public class GuardarDB {
    private  ConexionBase con= new ConexionBase();
    private PreparedStatement preparedStatement; 
    //metodo de verificacion de usuairo 
    
    public boolean verificacionUsuario(String usuario, String contraseña,String tipo){
         boolean estado=true;
            String query = "SELECT * FROM LOGIN WHERE user_name = '"+usuario+"'";
            try{
                PreparedStatement p = con.conexion().prepareStatement(query);
                ResultSet r = p.executeQuery();
                if(r.next()){
                        System.out.println("Este usuario ya existe");
                        estado=true;
                    }
                else{
                     crearUsuario(usuario,contraseña,tipo);
                     estado=false;
                }
            }catch(SQLException ex){
                System.out.println("Error en "+ ex);
                 estado=true;
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
     }
    //metodo para crear usuario  admin
    public void crearAdmin(Usuario usuario) {
        String query = "INSERT INTO user_admin (_code, _name, user_name, _password) VALUES (?,?, ?, ?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, usuario.getCodigo());
            preparedStatement.setString(2,usuario.getNombre());
            preparedStatement.setString(3,usuario.getNombreUsuario());
            preparedStatement.setString(4,usuario.getContraseña());
            preparedStatement.executeUpdate();
            System.out.println("Usuario Administrador registrado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // metodo para crear usuario para login
    public void crearUsuario(String usuario, String contraseña, String tipo) {
       String query = "INSERT INTO LOGIN (user_name, _password, _type, state) VALUES (?, ?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, contraseña);
            preparedStatement.setString(3, tipo);
            preparedStatement.setString(4,   Estado.ACTIVADO.name());
            preparedStatement.executeUpdate();
            System.out.println("Usuario creado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo para obtener codigo 
    public int  buscarCodigo(String usuario){
        String query = "SELECT * FROM LOGIN WHERE user_name = '"+usuario+"'";
        try{
           PreparedStatement p = con.conexion().prepareStatement(query);
           ResultSet r = p.executeQuery();
           if(r.next()){
               int codigo=r.getInt("_code");
                 return codigo; }
           }catch(SQLException ex){
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    //metodo para crear usuarios de tienda
    public void crearUsuarioTienda(UsuarioTienda usuario, int codigo){
       String query = "INSERT INTO user_store (_code, _name, store, user_name, _password, email) VALUES (?,?, ?, ?,?, ?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, codigo);
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setInt(3, usuario.getTienda());
            preparedStatement.setString(4,   usuario.getNombreUsuario());
            preparedStatement.setString(5,   usuario.getContraseña());
            preparedStatement.setString(6,   usuario.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Usuario tienda registrado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario tienda: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo para crear supervisores
    public void crearSupervisor(UsuarioSupervisor supervisor, int codigo) {
        String query = "INSERT INTO supervisory (_code, _name, user_name, _password, email) VALUES (?, ?, ?,?, ?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, codigo);
            preparedStatement.setString( 2,supervisor.getNombre());
            preparedStatement.setString( 3,supervisor.getNombreUsuario());
            preparedStatement.setString( 4,supervisor.getContraseña());
            preparedStatement.setString( 5,supervisor.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Usuario Administrador registrado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario supervisor------: " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }   
    
    //metodo para crear usuario bodega
    public void crearbodega(UsuarioSupervisor supervisor, int codigo) {
        String query = "INSERT INTO warehouse (_code, _name, user_name, _password, email) VALUES (?, ?, ?,?, ?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, codigo);
            preparedStatement.setString( 2,supervisor.getNombre());
            preparedStatement.setString( 3,supervisor.getNombreUsuario());
            preparedStatement.setString( 4,supervisor.getContraseña());
            preparedStatement.setString( 5,supervisor.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Usuario Administrador registrado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario de bodega----: " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }   
     
    //metodo de lista de tiendas de usuario de bodega
    public void listaTiendaBodega( TiendasBodega tienda) {
        String query = "INSERT INTO listatiendabodega (_code, tienda) VALUES (?, ?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, tienda.getUsuarioBodega());
            preparedStatement.setInt( 2,tienda.getTienda());
            preparedStatement.executeUpdate();
            System.out.println("usario de bodegas con tiendas guardado");
        } catch (SQLException e) {
            System.out.println("Error lista bodega " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }   
    
 
}
