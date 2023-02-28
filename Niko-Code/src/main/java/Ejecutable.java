


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public static void main(String args[]) throws ClassNotFoundException{
        String password = "myPassword123";
        String en = encryptPassword(password);
        System.out.println("Password: " + en);
        
        
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
         
        
       /* try {
            ConexionBase con=new ConexionBase();
           // String query="SELECT* FROM LOGIN WHERE user_name='luis' ";
             String query="SELECT* FROM LOGIN WHERE user_name='wicho' ";
            Statement ps;
            ResultSet rs;
            
            ps=con.conexion().createStatement();
            rs=ps.executeQuery(query);
            
            if(rs.next()){
              //  String conta="hola";
               // String a="AES_ENCRYPT('123456','clave')";

            System.out.println(rs.getString("user_name"));
            System.out.println(rs.getString("_password"));
            
            }
            else {
            System.out.println("EL Usuario no tiene cuenta");
            }
            con.conexion().close();
        } catch (SQLException ex) {
            Logger.getLogger(Ejecutable.class.getName()).log(Level.SEVERE, null, ex);
        }
     */   
    }
    
       public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedPassword) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    
        
}
