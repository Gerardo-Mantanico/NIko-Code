


import BaseDatos.EditarDB;
import Resources_servlet.ServletCreate;
import Resources_servlet.ServletLogin;
import clases.Usuario;
import clases.UsuarioTienda;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

        EditarDB db=new EditarDB();
        ArrayList<UsuarioTienda> modelList;
        modelList=db.listUsuarioTienda();
           for(int i = 0; i < modelList.size(); i++) {
            System.out.println(modelList.get(i).getNombreUsuario());
        }    }
    
          
    
        
        public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // Convertir el hash en una cadena de caracteres hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


    
        
}
