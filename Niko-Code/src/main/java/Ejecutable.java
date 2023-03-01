


import Resources_servlet.ServletCreate;
import Resources_servlet.ServletLogin;
import clases.Usuario;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.ConexionBase;






/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dell
 */
public class Ejecutable {
    
    
    public static void main(String args[]) {
        //String password = "myPassword123";
        //String en = encryptPassword(password);
       // System.out.println("Password: " + en);
        
        Usuario usuario=new Usuario();
        
         usuario.setNombre("pablo");
         usuario.setNombreUsuario("pablo1");
         usuario.setContraseña("pablo23");
         
         ConexionBase con=new ConexionBase();
        String query = "INSERT INTO user_admin (_code, _name, user_name, _password) VALUES (?,?, ?, ?)";
          int codigo=1004;
           try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, codigo);
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getNombreUsuario());
            preparedStatement.setString(4,   usuario.getContraseña());
            preparedStatement.executeUpdate();
            System.out.println("Usuario Administrador registrado");
            
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
          
    
        
            
     
        
        
        
         // String nombreUsuario=request.getParameter("User_name");
           // String contraseña=request.getParameter("password");  
            /*try{
                ConexionBase con=new ConexionBase();
                String query="SELECT* FROM LOGIN WHERE user_name='luis' ";
                Statement St;
                ResultSet rs;
                St=con.conexion().createStatement();
                rs=St.executeQuery(query);
                if(rs.next()){

                                System.out.println(rs.getString("user_name"));
                                System.out.println(rs.getString("_password"));
                    /*if( contraseña .equals(rs.getString("_password"))){
                        request.getRequestDispatcher("Venta_Administrativa/Venta_Principal.jsp").forward(request, response);
                    }*/
                    // request.getRequestDispatcher("index.jsp").forward(request, response);
               /* }
                else {
                  //  request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                rs.close();
                   
            
            }catch (SQLException ex) {
            
        }*/
           
    
  
    
        
}
