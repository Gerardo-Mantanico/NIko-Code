/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import Resources_servlet.ServletCreate;
import Resources_servlet.ServletLogin;
import clases.Envios;
import clases.Incidencia;
import clases.Pedido;
import clases.Producto;
import clases.ProductoDevolucion;
import clases.Tienda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
     public boolean  verificacionUsuario(int codigo,String usuario, String contraseña,String tipo){
         boolean estado=true;   
         String query = "SELECT * FROM LOGIN WHERE user_name = '"+usuario+"'";
            try{
                PreparedStatement p = con.conexion().prepareStatement(query);
                ResultSet r = p.executeQuery();
                if(r.next()){
                    estado=true;                   
                }
                else{
                     crearUsuario(codigo,usuario,contraseña,tipo);
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
     
     //metodo para  crear pedidos
     public void  pedido(Pedido pedido) {
       String query = "INSERT INTO  pedido (id, tienda, codigo_usuario, fecha, total, estado) VALUES (?,?, ?,?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, pedido.getId());
            preparedStatement.setInt(2, pedido.getTienda());
            preparedStatement.setInt(3, pedido.getCodigoUsuario());
            preparedStatement.setDate(4, pedido.getFecha());
            preparedStatement.setDouble(5, pedido.getTotal());
            preparedStatement.setString(6,   pedido.getEstado());
            preparedStatement.executeUpdate();
            System.out.println("pedido creado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo lista de producto de pedidos 
     public void  listapedido(Producto producto, int idPedido) {
       String query = "INSERT INTO pedidos_productos (id_pedido, codigo, costoU, cantidad, costoTotal) VALUES (?,?, ?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, idPedido);
            preparedStatement.setInt(2, producto.getCodigo());
            preparedStatement.setDouble(3, producto.getPrecio());
            preparedStatement.setInt(4, producto.getExistencia());
            preparedStatement.setDouble(5, producto.getCosto());
            preparedStatement.executeUpdate();
            System.out.println("guardado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    //metodo para crear envios
     public void  Crearenvios(Envios envios) {
       String query = "INSERT INTO  envios (id, tienda, codigo_usuario, fechaSalida, fechaRecibido, total, estado) VALUES (?,?, ?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, envios.getId());
            preparedStatement.setInt(2, envios.getTienda());
            preparedStatement.setInt(3, envios.getCodigoUsuario());
            preparedStatement.setDate(4, envios.getFechaSalida());
            preparedStatement.setDate(5, envios.getFechaRecibido());
            preparedStatement.setDouble(6, envios.getTotal());
            preparedStatement.setString(7,   envios.getEstado());
            preparedStatement.executeUpdate();
            System.out.println("pedido creado");
        } catch (SQLException e) {
            System.out.println("Error al crear envio " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo para crear tiendas
    public void  CrearTiendas(Tienda tienda) {
        String query = "INSERT INTO  store (_code, address, _type) VALUES (?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, tienda.getCodigo());
            preparedStatement.setString(2, tienda.getDireccion());
            preparedStatement.setString(3, tienda.getTipo());
            preparedStatement.executeUpdate();
            System.out.println("Tienda creada");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo de lista de productos de tienda
    public void  ProductoTiendas(int codigoTienda,int codigoProducto, int existencia) {
        String query = "INSERT INTO  tienda_producto (codigo_tienda, codigo_producto, existencia) VALUES (?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, codigoTienda);
            preparedStatement.setInt(2, codigoProducto);
            preparedStatement.setInt(3, existencia);
            preparedStatement.executeUpdate();
            System.out.println("Tienda creada");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    //metodo lista de producto de envio
    public void  listaenvio(Producto producto, int idPedido) {
       String query = "INSERT INTO envios_productos (id_pedido, codigo, costoU, cantidad, costoTotal) VALUES (?,?, ?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, idPedido);
            preparedStatement.setInt(2, producto.getCodigo());
            preparedStatement.setDouble(3, producto.getPrecio());
            preparedStatement.setInt(4, producto.getExistencia());
            preparedStatement.setDouble(5, producto.getCosto());
            preparedStatement.executeUpdate();
            System.out.println("guardado");
        } catch (SQLException e) {
            System.out.println("Error al crear lista productos enviados: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    //metodo para crear incidencias 
    public void  Incidencias(Incidencia incidencia) {
        String query = "INSERT INTO incidencias (id, tienda, codigo_usuario, fecha, solucion,estado) VALUES (?,?, ?,?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, incidencia.getId());
            preparedStatement.setInt(2, incidencia.getTienda());
            preparedStatement.setInt(3, incidencia.getCodigoUsuario());
            preparedStatement.setDate(4, incidencia.getFecha());
            preparedStatement.setString(5, incidencia.getSolucion());
            preparedStatement.setString(6, incidencia.getEstado());
            preparedStatement.executeUpdate();
            System.out.println(" Incidencia guardado");
        } catch (SQLException e) {
            System.out.println("Error al crear incidenia" + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    //metodo de lista de productos de incidencia
    public void  listIncidencias(int idpedio, int codigo, int cantida, String motivo) {
       String query = "INSERT INTO incidencias_producto (id_pedido, codigo, cantidad, motivo) VALUES (?,?, ?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, idpedio);
            preparedStatement.setInt(2, codigo);
            preparedStatement.setInt(3, cantida);
            preparedStatement.setString(4, motivo);
            preparedStatement.executeUpdate();
            System.out.println(" productos de Incidencia guardado ");
        } catch (SQLException e) {
            System.out.println("Error al crear lista de incidencia enviados: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo para crear devoluciones
     public void  crearDevoluciones(Pedido devolucion) {
        String query = "INSERT INTO DEVOLUCIONES (id, tienda, codigo_usuario, fecha, total, estado) VALUES (?,?, ?,?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, devolucion.getId());
            preparedStatement.setInt(2, devolucion.getTienda());
            preparedStatement.setInt(3, devolucion.getCodigoUsuario());
            preparedStatement.setDate(4, devolucion.getFecha());
            preparedStatement.setDouble(5, devolucion.getTotal());
            preparedStatement.setString(6, devolucion.getEstado());
            preparedStatement.executeUpdate();
            System.out.println(" devolucion guardado ");
        } catch (SQLException e) {
            System.out.println("Error devolucion: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
         //metodo lista de producto de devolucion
    public void  listaDevolucion(ProductoDevolucion producto, int idPedido) {
        String query = "INSERT INTO devoluciones_producto (id_pedido, codigo, costo, cantida, costoTotal, motivo) VALUES (?,?, ?,?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, idPedido);
            preparedStatement.setInt(2, producto.getCodigo());
            preparedStatement.setDouble(3, producto.getCosto());
            preparedStatement.setInt(4, producto.getCantidad());
            preparedStatement.setDouble(5, producto.getCostoTotal());
            preparedStatement.setString(6, producto.getMotivo());
            preparedStatement.executeUpdate();
            System.out.println("guardado");
        } catch (SQLException e) {
            System.out.println("Error lista  de productos devoluciones------>: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int IdMax(String tipo){
        int id=0;
        String query="SELECT MAX(id) AS id FROM "+tipo;
        PreparedStatement preparedStatement; 
           try {
               Statement stamente;
               ResultSet r;
               stamente = con.conexion().createStatement();
               r = stamente.executeQuery(query);
            if (r.next()) {
                id=r.getInt(1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuardarDatosEntrada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GuardarDatosEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
           return id;
    }
}
