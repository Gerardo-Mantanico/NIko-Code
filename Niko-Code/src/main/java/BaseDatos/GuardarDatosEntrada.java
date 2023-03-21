/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import Resources_servlet.ServletCreate;
import Resources_servlet.ServletLogin;
import clases.Devoluciones;
import clases.Envios;
import clases.Incidencia;
import clases.Pedido;
import clases.Producto;
import clases.ProductoDevolucion;
import clases.ProductoEnvio;
import clases.ProductoIncidencias;
import clases.ProductoTienda;
import clases.Tienda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
       ArrayList<String> listaErrores= new ArrayList();
    
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
            // listaErrores.add("No se pudo guardar el usuario");
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     //metodo para crear prodcuto 
     public void crearProductos(Producto prodcuto) {
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
            listaErrores.add("No se pudo guardar  el producto "+prodcuto.getNombre());
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
            listaErrores.add("No se pudo guardar el pedido "+pedido.getId()+" puede que no exista la tienda o el usuario");
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo lista de producto de pedidos 
     public void  listapedido(ProductoEnvio producto) {
       String query = "INSERT INTO pedidos_productos (id_pedido, codigo, costoU, cantidad, costoTotal) VALUES (?,?, ?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, producto.getIdEnvio());
            preparedStatement.setInt(2, producto.getCodigo());
            preparedStatement.setDouble(3, producto.getCosto());
            preparedStatement.setInt(4, producto.getCantidad());
            preparedStatement.setDouble(5, producto.getCostoTotal());
            preparedStatement.executeUpdate();
            System.out.println("guardado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
            listaErrores.add("No se pudo guardar los productos del pedido "+producto.getIdEnvio()+"  puede que no exista");
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    //metodo para crear envios
     public void  Crearenvios(Envios envios) {
       String query = "INSERT INTO  envios (id, tienda, codigo_usuario, fechaSalida, fechaRecibido, total, estado,id_envio) VALUES (?,?, ?,?,?,?,?,?)";
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
            preparedStatement.setInt(8,   envios.getIdEnvio());
            preparedStatement.executeUpdate();
            System.out.println("pedido creado");
        } catch (SQLException e) {
            System.out.println("Error al crear envio " + e);
            listaErrores.add("No se pudo guardar el envio "+envios.getId()+ " puede que no exista la tienda ");
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
            listaErrores.add("No se pudo guardar la tienda "+tienda.getCodigo() +" porque ya existe");
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo de lista de productos de tienda
    public void  ProductoTiendas(ProductoTienda producto) {
        String query = "INSERT INTO  tienda_producto (codigo_tienda, codigo_producto, existencia) VALUES (?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, producto.getTienda());
            preparedStatement.setInt(2, producto.getCodigo());
            preparedStatement.setInt(3, producto.getExistencia());
            preparedStatement.executeUpdate();
            System.out.println("Tienda creada");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
            listaErrores.add("No se pudo guardar los productos de la tienda porque la tienda no exista");
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    //metodo lista de producto de envio
    public void  listaenvio(ProductoEnvio producto) {
       String query = "INSERT INTO envios_productos (id_pedido, codigo, costoU, cantidad, costoTotal) VALUES (?,?, ?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, producto.getIdEnvio() );
            preparedStatement.setInt(2, producto.getCodigo());
            preparedStatement.setDouble(3, producto.getCosto());
            preparedStatement.setInt(4, producto.getCantidad());
            preparedStatement.setDouble(5, producto.getCostoTotal());
            preparedStatement.executeUpdate();
            System.out.println("guardado");
        } catch (SQLException e) {
            System.out.println("Error al crear lista productos enviados: " + e);
            listaErrores.add("No se pudo guardar los productos de envio porque pueda ser que la tienda no exista");
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    //metodo para crear incidencias 
    public void  Incidencias(Incidencia incidencia) {
        String query = "INSERT INTO incidencias (id, tienda, codigo_usuario, fecha, solucion,estado, id_incidencia) VALUES (?,?, ?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, incidencia.getId());
            preparedStatement.setInt(2, incidencia.getTienda());
            preparedStatement.setInt(3, incidencia.getCodigoUsuario());
            preparedStatement.setDate(4, incidencia.getFecha());
            preparedStatement.setString(5, incidencia.getSolucion());
            preparedStatement.setString(6, incidencia.getEstado());
            preparedStatement.setInt(7, incidencia.getEnvio());
            preparedStatement.executeUpdate();
            System.out.println(" Incidencia guardado");
        } catch (SQLException e) {
            System.out.println("Error al crear incidenia" + e);
            listaErrores.add("No se pudo guardar la incidencia porque no exista la tienda o el usuario");
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    //metodo de lista de productos de incidencia
    public void  listIncidencias(ProductoIncidencias producto) {
       String query = "INSERT INTO incidencias_producto (id_pedido, codigo, cantidad, motivo) VALUES (?,?, ?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, producto.getCodigoIncidencia());
            preparedStatement.setInt(2, producto.getCodigo());
            preparedStatement.setInt(3, producto.getCantidad());
            preparedStatement.setString(4, producto.getMotivo());
            preparedStatement.executeUpdate();
            System.out.println(" productos de Incidencia guardado ");
        } catch (SQLException e) {
            System.out.println("Error al crear lista de incidencia enviados: " + e);
            listaErrores.add("No se pudo guardar los productos de incidencia porque la incidencia no existe");
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo para crear devoluciones
     public void  crearDevoluciones(Devoluciones devolucion) {
        String query = "INSERT INTO DEVOLUCIONES (id, tienda, codigo_usuario, fecha, total, estado,id_devoluciones) VALUES (?,?, ?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, devolucion.getId());
            preparedStatement.setInt(2, devolucion.getTienda());
            preparedStatement.setInt(3, devolucion.getCodigoUsuario());
            preparedStatement.setDate(4, devolucion.getFecha());
            preparedStatement.setDouble(5, devolucion.getTotal());
            preparedStatement.setString(6, devolucion.getEstado());
            preparedStatement.setInt(7, devolucion.getEnvio());
            preparedStatement.executeUpdate();
            System.out.println(" devolucion guardado ");
        } catch (SQLException e) {
            System.out.println("Error devolucion: " + e);
            listaErrores.add("No se pudo guardar  la devolucion porque  puede que no exista la tienda ");
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
         //metodo lista de producto de devolucion
    public void  listaDevolucion(ProductoDevolucion producto) {
        String query = "INSERT INTO devoluciones_producto (id_pedido, codigo, costo, cantida, costoTotal, motivo) VALUES (?,?, ?,?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, producto.getIdDevolucion());
            preparedStatement.setInt(2, producto.getCodigo());
            preparedStatement.setDouble(3, producto.getCosto());
            preparedStatement.setInt(4, producto.getCantidad());
            preparedStatement.setDouble(5, producto.getCostoTotal());
            preparedStatement.setString(6, producto.getMotivo());
            preparedStatement.executeUpdate();
            System.out.println("guardado");
        } catch (SQLException e) {
            System.out.println("Error lista  de productos devoluciones------>: " + e);
            listaErrores.add("No se pudo guardar los productos de devolucion porque pueda que la devolucion no exista");
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
    
    public ArrayList error(){
           return this.listaErrores;
    }
}
