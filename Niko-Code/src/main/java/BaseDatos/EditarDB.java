/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import clases.Usuario;
import clases.UsuarioSupervisor;
import clases.UsuarioTienda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.ConexionBase;

/**
 *
 * @author HP
 */
public class EditarDB {
    
    ConexionBase con=new ConexionBase();
    Statement stamente;
    ResultSet r;
    //metodo para obtener una lista  de los diferentes tipos de usuarios
    public ArrayList  listUsuarioTienda(String tipo){
        ArrayList<Object> list = new ArrayList();
        String query="SELECT* FROM "+tipo;
        try {
            stamente = con.conexion().createStatement();
            r = stamente.executeQuery(query);
            switch (tipo) {
               case "user_store":
                    while(r.next()){
                        UsuarioTienda usuario=new UsuarioTienda();
                        usuario.setCodigo(r.getInt("_code"));
                        usuario.setNombre(r.getString("_name"));
                        usuario.setTienda(r.getInt("store"));
                        usuario.setNombreUsuario(r.getString("user_name"));
                        usuario.setContraseña(r.getString("_password"));
                        usuario.setEmail(r.getString("email"));
                        list.add(usuario);}
                break;
                case "supervisory":
                    while(r.next()){
                        UsuarioSupervisor usuario=new UsuarioSupervisor();
                        usuario.setCodigo(r.getInt("_code"));
                        usuario.setNombre(r.getString("_name"));
                        usuario.setNombreUsuario(r.getString("user_name"));
                        usuario.setContraseña(r.getString("_password"));
                        usuario.setEmail(r.getString("email"));
                        list.add(usuario);}    
               break;
               case"user_admin":
                   while(r.next()){
                        Usuario usuario=new Usuario();
                        usuario.setCodigo(r.getInt("_code"));
                        usuario.setNombre(r.getString("_name"));
                        usuario.setNombreUsuario(r.getString("user_name"));
                        usuario.setContraseña(r.getString("_password"));
                        list.add(usuario);} 
               break;
               default:
            }r.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
}
