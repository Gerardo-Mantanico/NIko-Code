

import com.mycompany.niko.code.resources.ConexionBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;





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
    public static void main(String args[]){
        try {
            ConexionBase con=new ConexionBase();
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
        
    }
}
