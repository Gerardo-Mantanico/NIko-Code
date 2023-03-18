/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import clases.Envios;
import clases.Producto;
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
public class CrearEnvioDB {
    ConexionBase con =new ConexionBase();
    Statement stamente;
    ResultSet r;
    public ArrayList listadoTiendas(String usuario){
        ArrayList list= new ArrayList();
        String query="select tienda from listatiendabodega where _code="+usuario;
        try {
            stamente = con.conexion().createStatement();
            r = stamente.executeQuery(query);
            while(r.next()){
                list.add(r.getInt("tienda"));
            }
         } catch (SQLException ex) {
             Logger.getLogger(CrearEnvioDB.class.getName()).log(Level.SEVERE, null, ex);
         }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(CrearEnvioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList listadoPedidos(String tienda, String estado){
        ArrayList list= new ArrayList();
        String query=" select* from pedido where tienda=  "+tienda+"  AND estado='"+estado+"'";
        try {
            stamente = con.conexion().createStatement();
            r = stamente.executeQuery(query);
            while(r.next()){
                list.add(r.getInt("id"));
            }
         } catch (SQLException ex) {
             Logger.getLogger(CrearEnvioDB.class.getName()).log(Level.SEVERE, null, ex);
         }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(CrearEnvioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public ArrayList listadoProducto(String idPedido){
        ArrayList list= new ArrayList();
        //String query="select* from pedidos_productos  where id_pedido="+idPedido;
          String query = "select c._name, e.codigo, e.costoU, e.cantidad, e.costoTotal   from pedidos_productos e "
                  + "  left  join catalogue c on e.codigo =c._code where e.id_pedido="+idPedido;
        try {
            stamente = con.conexion().createStatement();
            r = stamente.executeQuery(query);
            while(r.next()){
                        Producto producto= new Producto();
                         producto.setNombre(r.getString(1));
                         producto.setCodigo(r.getInt(2));
                         producto.setCosto(r.getDouble(3));
                         producto.setExistencia(r.getInt(4));
                         producto.setPrecio(r.getDouble(5));
                         list.add(producto);
            }
         } catch (SQLException ex) {
             Logger.getLogger(CrearEnvioDB.class.getName()).log(Level.SEVERE, null, ex);
         }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(CrearEnvioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
