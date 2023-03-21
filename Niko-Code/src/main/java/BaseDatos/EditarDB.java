/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import clases.Envios;
import clases.ListaId;
import clases.Producto;
import clases.ProductoDevolucion;
import clases.ProductoIncidencias;
import clases.Usuario;
import clases.UsuarioSupervisor;
import clases.UsuarioTienda;
import java.sql.Date;
import java.sql.PreparedStatement;
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
           stamente  = con.conexion().createStatement();
            r = stamente.executeQuery(query);
            switch (tipo) {
                case "user_store":
                    while (r.next()) {
                        UsuarioTienda usuario = new UsuarioTienda();
                        usuario.setCodigo(r.getInt("_code"));
                        usuario.setNombre(r.getString("_name"));
                        usuario.setTienda(r.getInt("store"));
                        usuario.setNombreUsuario(r.getString("user_name"));
                        usuario.setContraseña(r.getString("_password"));
                        usuario.setEmail(r.getString("email"));
                        list.add(usuario);
                    }
                    break;
                case "supervisory":
                    while (r.next()) {
                        UsuarioSupervisor usuario = new UsuarioSupervisor();
                        usuario.setCodigo(r.getInt("_code"));
                        usuario.setNombre(r.getString("_name"));
                        usuario.setNombreUsuario(r.getString("user_name"));
                        usuario.setContraseña(r.getString("_password"));
                        usuario.setEmail(r.getString("email"));
                        list.add(usuario);
                    }
                    break;
                case "user_admin":
                    while (r.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setCodigo(r.getInt("_code"));
                        usuario.setNombre(r.getString("_name"));
                        usuario.setNombreUsuario(r.getString("user_name"));
                        usuario.setContraseña(r.getString("_password"));
                        list.add(usuario);
                    }
                    break;
                case "catalogue":
                    while (r.next()) {
                        Producto producto = new Producto();
                        producto.setCodigo(r.getInt(1));
                        producto.setNombre(r.getString(2));
                        producto.setCosto(r.getDouble(3));
                        producto.setPrecio(r.getDouble(4));
                        producto.setExistencia(r.getInt(5));
                        list.add(producto);
                    }
                    break;
                case "envios":
                    while(r.next()){
                        Envios envioss = new Envios();
                        envioss.setId(r.getInt(1));
                        envioss.setTienda(r.getInt(2));
                        envioss.setCodigoUsuario(r.getInt(3));
                        envioss.setFechaSalida( r.getDate(4));
                        //envioss.setFechaRecibido(Date.valueOf((String) envio.get("fechaRecibido")));
                        list.add(envioss);
                    }
                    break;
                     case "warehouse":
                    while (r.next()) {
                        UsuarioTienda usuario = new UsuarioTienda();
                        usuario.setCodigo(r.getInt("_code"));
                        usuario.setNombre(r.getString("_name"));
                        usuario.setNombreUsuario(r.getString("user_name"));
                        usuario.setContraseña(r.getString("_password"));
                        usuario.setEmail(r.getString("email"));
                        list.add(usuario);
                    }
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

     public Usuario buscarAdmin(int codigo){
         Usuario admin=new Usuario();
           String query="SELECT* FROM  user_admin where _code="+codigo;
        try {
            stamente = con.conexion().createStatement();
        
            r = stamente.executeQuery(query);
            while(r.next()){
                        Usuario usuario=new Usuario();
                        usuario.setCodigo(r.getInt("_code"));
                        usuario.setNombre(r.getString("_name"));
                        usuario.setNombreUsuario(r.getString("user_name"));
                        usuario.setContraseña(r.getString("_password"));
            }
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (SQLException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
     }
     
     
    public ArrayList listas(String tipo, String menu){
        ArrayList<Object> list = new ArrayList();
        String query="SELECT* FROM "+tipo;
        try {
            stamente = con.conexion().createStatement();
             r = stamente.executeQuery(query);
             switch (menu) {
                case "productosEnvio":
                    while(r.next()){
                         Producto producto= new Producto();
                         producto.setCodigo(r.getInt(2));
                         producto.setCosto(r.getDouble(3));
                         producto.setExistencia(r.getInt(4));
                         producto.setPrecio(r.getDouble(5));
                         list.add(producto);}
                    break;
                case "envios":
                    while(r.next()){
                        Envios envioss = new Envios();
                        envioss.setId(r.getInt(2));
                        envioss.setTienda(r.getInt(3));
                        envioss.setCodigoUsuario(r.getInt(4));
                        envioss.setFechaSalida( r.getDate(5));
                        //envioss.setFechaRecibido(Date.valueOf((String) envio.get("fechaRecibido")));
                        list.add(envioss);
                    }
                    break;
                 case "ProductoDevolucion":
                     while (r.next()) {
                         ProductoDevolucion producto = new ProductoDevolucion();
                         producto.setCodigo(r.getInt(2));
                         producto.setCosto(r.getDouble(3));
                         producto.setCantidad(r.getInt(4));
                         producto.setCostoTotal(r.getDouble(5));
                         producto.setMotivo(r.getString(6));
                         list.add(producto);
                     }
                     break;
                 case "ProductoIncidencias":
                     while (r.next()) {
                         ProductoIncidencias producto = new ProductoIncidencias();
                         producto.setCodigo(r.getInt(2));
                         producto.setCantidad(r.getInt(3));
                         producto.setMotivo(r.getString(4));
                         
                         list.add(producto);
                     }
                     break;  
                default:
                    throw new AssertionError();
            }
             r.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Object ObtenerObjetos(String tipo, String menu){
        String query="SELECT* FROM "+tipo;
         Object objeto=null;
        try {
            stamente = con.conexion().createStatement();
            r = stamente.executeQuery(query);
            switch (menu) {
                case "productosEnvio":
                    while (r.next()) {
                        Producto producto = new Producto();
                        producto.setCodigo(r.getInt(2));
                        producto.setCosto(r.getDouble(3));
                        producto.setExistencia(r.getInt(4));
                        producto.setPrecio(r.getDouble(5));
                        objeto=producto;
                    }
                    break;               
                case "ProductosDevoluciones":
                    while (r.next()) {
                        ProductoDevolucion producto = new ProductoDevolucion();
                        producto.setCodigo(r.getInt(2));
                        producto.setCosto(r.getDouble(3));
                        producto.setCantidad(r.getInt(4));
                        producto.setCostoTotal(r.getDouble(5));
                        objeto = producto;
                    }
                    break; 
                case "envios":
                    while(r.next()){
                        Envios envioss = new Envios();
                        envioss.setId(r.getInt(1));
                        envioss.setTienda(r.getInt(2));
                        envioss.setCodigoUsuario(r.getInt(3));
                        envioss.setFechaSalida( r.getDate(4));
                        envioss.setEstado(r.getString(7));
                        //envioss.setFechaRecibido(Date.valueOf((String) envio.get("fechaRecibido")));
                        objeto=envioss;
                    }
                    break;   
                case"producto":
                    while (r.next()) {
                        Producto producto = new Producto();
                        producto.setCodigo(r.getInt(1));
                        producto.setNombre(r.getString(2));
                        producto.setCosto(r.getDouble(3));
                        producto.setPrecio(r.getDouble(4));
                        producto.setExistencia(r.getInt(5));
                        System.out.println(producto.getExistencia());
                        objeto=producto;
                    }
                break;
                default:
            }
            con.conexion().close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objeto;
    }
    
    public void editar(String query){
         try {
            stamente.execute(query);
            stamente = con.conexion().createStatement();
            con.conexion().close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public ArrayList IdDevoluciones(String query){
        ArrayList<Object> list = new ArrayList();
        try {
            //String query="select e.id, e.estado, e.tienda from envios e left  join devoluciones d on e.id=d.id where d.id is null";
            PreparedStatement pstmt =  con.conexion().prepareStatement(query);
            r = pstmt.executeQuery();
            while(r.next()){
               ListaId listaId = new ListaId();
               listaId.setId(r.getInt("id"));
               listaId.setEstad(r.getString("estado"));
               listaId.setTienda(r.getInt("tienda"));
               System.out.println(listaId.getId());
               list.add(listaId);
            }
            con.conexion().close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return list;
    }
     
    public  ArrayList listaTida(String query){
        ArrayList lista= new ArrayList();
         try {
          stamente = con.conexion().createStatement();
            r = stamente.executeQuery(query);
            while(r.next()){            
                lista.add(r.getInt(2));
            }
            r.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditarDB.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return lista;
    }
}
