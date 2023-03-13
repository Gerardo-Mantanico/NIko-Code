/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources;

import BaseDatos.GuardarDB;
import BaseDatos.GuardarDatosEntrada;
import clases.Ejecutable;
import clases.Envios;
import clases.Incidencia;
import clases.Pedido;
import clases.Producto;
import clases.Tienda;
import clases.Usuario;
import clases.UsuarioSupervisor;
import clases.UsuarioTienda;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author HP
 */
public class CargaDatosEntrada {
    
    GuardarDB DB=new GuardarDB();
    GuardarDatosEntrada base=new GuardarDatosEntrada();
    boolean estado;
    
    public void leerJson(File file){
        JSONParser jsonParser = new JSONParser();      
        try (FileReader reader = new FileReader(file)){
            //Read JSON file
            Object obj = jsonParser.parse(reader);     
            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);
            for(Object productos :employeeList ){
                 mostrarInformacion( (JSONObject) productos );   
            }
            } catch (FileNotFoundException e) 
            {
                e.printStackTrace();
            } catch (IOException e) 
            {
                e.printStackTrace();
            } catch (ParseException e) 
            {
                e.printStackTrace();
            }        
    
    }
    
    private  void mostrarInformacion(JSONObject jsonObject)  {
        //metodo para entrar a la arrylist
       JSONObject pro= (JSONObject) jsonObject;
       JSONArray list = (JSONArray) pro.get("productos");
       //carga de datos de productos
       for(Object produ:list){
           JSONObject a= (JSONObject) produ;
           System.out.println("-----------------");
           Producto producto=new Producto();
           GuardarDatosEntrada base=new GuardarDatosEntrada();
           producto.setCodigo( this.convertirLongInt(a.get("codigo")) );
           producto.setNombre((String) a.get("nombre"));    
           producto.setCosto(convertirLongDouble(a.get("costo")));
           producto.setPrecio(convertirLongDouble(a.get("precio")));
           producto.setExistencia(convertirLongInt(a.get("existencias")));
           base.crearProductos(producto);
       }
       //para cargar los datos de tienda
       JSONArray lista = (JSONArray) pro.get("tiendas");
        for(Object tienda:lista){
           JSONObject a= (JSONObject) tienda;
           Tienda tiendas= new Tienda();
            tiendas.setCodigo(this.convertirLongInt(a.get("codigo")));
            tiendas.setDireccion((String) a.get("direccion"));
            tiendas.setTipo((String) a.get("tipo"));
            base.CrearTiendas(tiendas);
            JSONArray listaprodcutos = (JSONArray) a.get("productos");
                for( Object productos: listaprodcutos){
                    JSONObject e= (JSONObject) productos;
                    base.ProductoTiendas(tiendas.getCodigo(), convertirLongInt(e.get("codigo")), convertirLongInt(e.get("existencias")) );
                }
       }
        //metodo parar cargar admin
        JSONArray listaAdmin = (JSONArray) pro.get("admins");
        for(Object admin:listaAdmin){
            Usuario usuarioadmin=new Usuario();
            JSONObject user= (JSONObject) admin;
            usuarioadmin.setCodigo(convertirLongInt(user.get("codigo")));
            usuarioadmin.setNombre( (String) user.get("nombre"));
            usuarioadmin.setNombreUsuario((String) user.get("username"));
            usuarioadmin.setContraseña(contraseñaIncriptada((String) user.get("password")));
            base.verificacionUsuario(convertirLongInt(user.get("codigo")),(String) user.get("username"), contraseñaIncriptada((String) user.get("password")), Estado.ADMINISTRADOR.name());
            DB.crearAdmin(usuarioadmin ,convertirLongInt(user.get("codigo")));
        }
        
        //metodo para cargar usuariostienda
        System.out.println("////////////// usuarios de tienda");
        JSONArray listUsuariostienda =(JSONArray) pro.get("usuariostienda");
        for(Object usuarioTienda: listUsuariostienda){
            JSONObject userTienda= (JSONObject) usuarioTienda;
            UsuarioTienda user =new UsuarioTienda();
            user.setCodigo(convertirLongInt(userTienda.get("codigo")));
            user.setNombre((String) userTienda.get("nombre"));
            user.setTienda(convertirLongInt(userTienda.get("tienda")));
            user.setNombreUsuario((String) userTienda.get("username"));
            user.setContraseña(contraseñaIncriptada((String) userTienda.get("password")));
            user.setEmail((String) userTienda.get("email"));
            base.verificacionUsuario(user.getCodigo(),user.getNombreUsuario(),user.getContraseña(), Estado.TIENDA.name());
            DB.crearUsuarioTienda(user, user.getCodigo());
        }
        
        // metodo para cargar supervisores
        System.out.println("////////////// supervisor");
        JSONArray listsupervisor =(JSONArray) pro.get("supervisores");
        for(Object usuariosupervisor: listsupervisor){
            JSONObject userSupervisor= (JSONObject) usuariosupervisor;
            UsuarioSupervisor user= new UsuarioSupervisor();
            user.setCodigo(convertirLongInt(userSupervisor.get("codigo")));
            user.setNombre((String) userSupervisor.get("nombre"));
            user.setNombreUsuario((String) userSupervisor.get("username"));
            user.setContraseña(contraseñaIncriptada((String) userSupervisor.get("password")));
            user.setEmail((String) userSupervisor.get("email"));
            base.verificacionUsuario(user.getCodigo(),user.getNombreUsuario(),user.getContraseña(), Estado.SUPERVISOR.name());
            DB.crearSupervisor(user, user.getCodigo());
        }
         
        // metodo para cargar encargados de bodega 
        System.out.println("////////////// Bodega");
        JSONArray listBodega =(JSONArray) pro.get("encargadosBodega");
        for(Object usuarioBodega: listBodega){
            JSONObject userBodega= (JSONObject) usuarioBodega;
            UsuarioSupervisor user= new UsuarioSupervisor();
            user.setCodigo(convertirLongInt(userBodega.get("codigo")));
            user.setNombre((String) userBodega.get("nombre"));
            user.setNombreUsuario((String) userBodega.get("username"));
            user.setContraseña(contraseñaIncriptada((String) userBodega.get("password")));
            user.setEmail((String) userBodega.get("email"));
            estado=base.verificacionUsuario(user.getCodigo(), user.getNombreUsuario(), user.getContraseña(),Estado.BODEGA.name());
            DB.crearbodega(user, user.getCodigo());
            JSONArray listatiendass = (JSONArray) userBodega.get("tiendas");
                for( Object tienda: listatiendass){
                    int valor=Integer.valueOf(tienda.toString());
                    System.out.println(user.getCodigo()+"   "+ valor);
                   DB.listaTiendaBodega(user.getCodigo(), valor);
                }
        }
        //metodo para cargar pedidos
        System.out.println("------------------ pedidos");
         JSONArray listPedidos =(JSONArray) pro.get("pedidos");
        for(Object pedidos: listPedidos){
            JSONObject pedido= (JSONObject) pedidos;
            Pedido pedidoss= new Pedido();
            pedidoss.setId(convertirLongInt(pedido.get("id")));
            pedidoss.setTienda(convertirLongInt(pedido.get("tienda")));
            pedidoss.setCodigoUsuario(convertirLongInt(pedido.get("usuario")));
            pedidoss.setFecha(Date.valueOf( (String) pedido.get("fecha")));
            JSONArray listPedidoProducto =(JSONArray) pedido.get("productos");
            for( Object productos: listPedidoProducto ){
                 JSONObject producto= (JSONObject) productos;
                 Producto productoss=new Producto();
                 productoss.setCodigo(convertirLongInt(producto.get("codigo")));
                 productoss.setPrecio(convertirLongDouble(producto.get("costoU")));
                 productoss.setExistencia(convertirLongInt(producto.get("cantidad")));
                 productoss.setCosto(convertirLongDouble(producto.get("costoTotal")));
                 base.listapedido(productoss, pedidoss.getId());
            }
            
            pedidoss.setTotal(convertirLongDouble(pedido.get("total")));
            pedidoss.setEstado((String) pedido.get("estado"));
            base.pedido(pedidoss);
        }
         //metodo para enviar

        JSONArray listenvio =(JSONArray) pro.get("envios");
        for(Object envios: listenvio){
            JSONObject envio= (JSONObject) envios;
            Envios envioss =new Envios();
             envioss.setId(convertirLongInt(envio.get("id")));
             envioss.setTienda(convertirLongInt(envio.get("tienda")));
             envioss.setCodigoUsuario(this.convertirLongInt(envio.get("usuario")));
             envioss.setFechaSalida(Date.valueOf((String) envio.get("fechaSalida")));
             //envioss.setFechaRecibido(Date.valueOf((String) envio.get("fechaRecibido")));
            
            JSONArray listEnvioProducto =(JSONArray) envio.get("productos");
            for( Object productos: listEnvioProducto ){
                 JSONObject producto= (JSONObject) productos;
                 Producto lisproduc=new Producto();
                 lisproduc.setCodigo(convertirLongInt(producto.get("codigo")));
                 lisproduc.setPrecio(convertirLongDouble(producto.get("costoU")));
                 lisproduc.setExistencia(convertirLongInt(producto.get("cantidad")));
                 lisproduc.setCosto(convertirLongDouble(producto.get("costoTotal")));
                 base.listaenvio(lisproduc, envioss.getId());
            }
            envioss.setTotal(this.convertirLongDouble(envio.get("total")));
            envioss.setEstado((String) envio.get("estado"));
            base.Crearenvios(envioss);
        }
         
        //metodo para crear incidencias
        System.out.println("------------------ incidencias");
        JSONArray listIncidencias =(JSONArray) pro.get("incidencias");
        for(Object incidencias: listIncidencias){
            JSONObject incidencia= (JSONObject) incidencias;
            Incidencia inci=new Incidencia();
            inci.setId(convertirLongInt(incidencia.get("id")));
            inci.setTienda(convertirLongInt(incidencia.get("tienda")));
            inci.setCodigoUsuario(convertirLongInt(incidencia.get("usuario")));
            inci.setFecha(Date.valueOf((String) incidencia.get("fecha")));
            JSONArray listPedidoProducto =(JSONArray) incidencia.get("productos");
            for( Object productos: listPedidoProducto ){
                 JSONObject producto= (JSONObject) productos;
                 base.listIncidencias(inci.getId() , convertirLongInt(producto.get("codigo")), convertirLongInt(producto.get("cantidad")), (String) producto.get("motivo"));      
            }
            inci.setSolucion((String) incidencia.get("solucion"));
            inci.setEstado((String) incidencia.get("estado"));
            base.Incidencias(inci);
        }
        
        //metodo para crear devoluciones
        System.out.println("****** devoluciones");
        JSONArray listDevoluciones =(JSONArray) pro.get("devoluciones");
            for( Object devolucion: listDevoluciones ){
                 JSONObject devo= (JSONObject) devolucion;
                 Pedido devoluciones =new Pedido();
                 devoluciones.setId(this.convertirLongInt(devo.get("id")));
                 devoluciones.setTienda(this.convertirLongInt(devo.get("tienda")));
                 devoluciones.setCodigoUsuario(this.convertirLongInt(devo.get("usuario")));
                 devoluciones.setFecha( Date.valueOf((String) devo.get("fecha")));
                 JSONArray listDevolucionesProducto =(JSONArray) devo.get("productos");
                 for( Object productos: listDevolucionesProducto ){
                    JSONObject producto= (JSONObject) productos;
                    Producto productoss=new Producto();
                    productoss.setCodigo(this.convertirLongInt(producto.get("codigo")));
                    productoss.setPrecio(this.convertirLongDouble(producto.get("costo")));
                    productoss.setExistencia(this.convertirLongInt(producto.get("cantidad")));
                    productoss.setCosto(this.convertirLongDouble(producto.get("costoTotal")));
                    base.listaDevolucion(productoss, devoluciones.getId(), (String) producto.get("motivo"));
                 }
                 devoluciones.setTotal(this.convertirLongDouble(devo.get("total")));
                 devoluciones.setEstado((String) devo.get("estado"));
                 base.crearDevoluciones(devoluciones);

            }
        
        
  
       }
    
 
    
    public String   contraseñaIncriptada(String contraseña){
        String password="";
        try {
            Encriptar encriptar= new Encriptar();
           password= encriptar.hashPassword(contraseña);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CargaDatosEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return password;
    }
    
    public int convertirLongInt(Object objeto){
         long valor= (long) objeto;
         int numero= (int) valor;
        return numero;
    }
    
    public double convertirLongDouble(Object objeto){
        double decimal=0;
        try {
              decimal= (double) objeto;     
           }catch (Exception e){
               long costoU= (long) objeto; 
               decimal=  (double) costoU;
           }
        return decimal;
    }
    
    
     
}
