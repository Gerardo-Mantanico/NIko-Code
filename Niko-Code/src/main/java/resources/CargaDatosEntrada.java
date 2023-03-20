/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources;

import BaseDatos.GuardarDB;
import BaseDatos.GuardarDatosEntrada;
import clases.Devoluciones;
import clases.Ejecutable;
import clases.Envios;
import clases.Incidencia;
import clases.Pedido;
import clases.Producto;
import clases.ProductoDevolucion;
import clases.ProductoEnvio;
import clases.ProductoIncidencias;
import clases.ProductoTienda;
import clases.Tienda;
import clases.TiendasBodega;
import clases.Usuario;
import clases.UsuarioSupervisor;
import clases.UsuarioTienda;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    GuardarDB db = new GuardarDB();
    GuardarDatosEntrada base=new GuardarDatosEntrada();
    ArrayList<Producto> listProducto = new ArrayList();
    ArrayList<Tienda> listTienda  = new ArrayList();
    ArrayList<Usuario> listAdmin  = new ArrayList();
    ArrayList<UsuarioTienda> listUsuarioTienda  = new ArrayList();
    ArrayList<UsuarioSupervisor> listSupervisado  = new ArrayList();
    ArrayList<UsuarioSupervisor> listBodega  = new ArrayList();
    ArrayList<Pedido> listPedido  = new ArrayList();
    ArrayList<Envios> listEnvios  = new ArrayList();
    ArrayList<Incidencia> listIncidencia  = new ArrayList();
    ArrayList<Devoluciones> listDevoluciones  = new ArrayList();
    ArrayList<ProductoTienda> listProductoTienda  = new ArrayList();
    ArrayList<ProductoEnvio> listProductoPedido  = new ArrayList();
    ArrayList<ProductoEnvio> listProductoEnvios  = new ArrayList();
    ArrayList<ProductoIncidencias> listProductoIncidencia  = new ArrayList();
    ArrayList<ProductoDevolucion> listProductoDevoluciones  = new ArrayList();
    ArrayList<String> listErrores= new ArrayList();
    ArrayList<TiendasBodega> listaTiendaBodega = new ArrayList();  
     
      
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
          this.listProducto.add(producto);
          
       }
       //para cargar los datos de tienda
       JSONArray lista = (JSONArray) pro.get("tiendas");
        for(Object tienda:lista){
            JSONObject a = (JSONObject) tienda;
            Tienda tiendas = new Tienda();
            tiendas.setCodigo(this.convertirLongInt(a.get("codigo")));
            tiendas.setDireccion((String) a.get("direccion"));
            tiendas.setTipo((String) a.get("tipo"));
            this.listTienda.add(tiendas);
            JSONArray listaprodcutos = (JSONArray) a.get("productos");
            for (Object productos : listaprodcutos) {
                JSONObject e = (JSONObject) productos;
                ProductoTienda producto = new ProductoTienda();
                ProductoTienda TiendaProducto=new ProductoTienda();
                TiendaProducto.setTienda(tiendas.getCodigo());
                TiendaProducto.setCodigo(convertirLongInt(e.get("codigo")));
                TiendaProducto.setExistencia(convertirLongInt(e.get("existencias")));
                this.listProductoTienda.add(TiendaProducto);
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
            this.listAdmin.add(usuarioadmin);
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
            this.listUsuarioTienda.add(user);
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
            this.listSupervisado.add(user);
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
            this.listBodega.add(user);
            JSONArray listatiendass = (JSONArray) userBodega.get("tiendas");
                for( Object tienda: listatiendass){
                    TiendasBodega tiendabodega = new TiendasBodega();
                    int valor=Integer.valueOf(tienda.toString());
                    tiendabodega.setUsuarioBodega(user.getCodigo());
                    tiendabodega.setTienda(valor);
                    this.listaTiendaBodega.add(tiendabodega);
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
                 ProductoEnvio productoss=new ProductoEnvio();
                 productoss.setIdEnvio(pedidoss.getId());
                 productoss.setCodigo(convertirLongInt(producto.get("codigo")));
                 productoss.setCosto(convertirLongDouble(producto.get("costoU")));
                 productoss.setCantidad(convertirLongInt(producto.get("cantidad")));
                 productoss.setCostoTotal(convertirLongDouble(producto.get("costoTotal")));
                 this.listProductoPedido.add(productoss);
            }
            pedidoss.setTotal(convertirLongDouble(pedido.get("total")));
            pedidoss.setEstado((String) pedido.get("estado"));
            this.listPedido.add(pedidoss);
        }
         //metodo para enviar
        JSONArray listenvio =(JSONArray) pro.get("envios");
        for(Object envios: listenvio){
            JSONObject envio= (JSONObject) envios;
            Envios envioss = new Envios();
            //envioss.setIdEnvio(convertirLongInt(envio.get("id")));
            envioss.setId(convertirLongInt(envio.get("pedido")));
            envioss.setTienda(convertirLongInt(envio.get("tienda")));
            envioss.setCodigoUsuario(this.convertirLongInt(envio.get("usuario")));
            envioss.setFechaSalida(Date.valueOf((String) envio.get("fechaSalida")));
            try{
                 envioss.setFechaRecibido(Date.valueOf((String) envio.get("fechaRecibido")));
            }catch (Exception ex) {
                
            }
            JSONArray listEnvioProducto =(JSONArray) envio.get("productos");
            for( Object productos: listEnvioProducto ){
                 JSONObject producto= (JSONObject) productos;
                 ProductoEnvio envioProducto=new ProductoEnvio();
                 envioProducto.setIdEnvio(envioss.getId());
                 envioProducto.setCodigo(convertirLongInt(producto.get("codigo")));
                 envioProducto.setCosto(convertirLongDouble(producto.get("costoU")));
                 envioProducto.setCantidad(convertirLongInt(producto.get("cantidad")));
                 envioProducto.setCostoTotal(convertirLongDouble(producto.get("costoTotal")));
                 this.listProductoEnvios.add(envioProducto);
            }
            envioss.setTotal(this.convertirLongDouble(envio.get("total")));
            envioss.setEstado((String) envio.get("estado"));
            this.listEnvios.add(envioss);
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
                ProductoIncidencias productoInci = new ProductoIncidencias();
                productoInci.setCodigoIncidencia(inci.getId());
                productoInci.setCodigo(convertirLongInt(producto.get("codigo")));
                productoInci.setCantidad(convertirLongInt(producto.get("cantidad")));
                productoInci.setMotivo((String) producto.get("motivo"));
                this.listProductoIncidencia.add(productoInci);
            }
            inci.setSolucion((String) incidencia.get("solucion"));
            inci.setEstado((String) incidencia.get("estado"));
            this.listIncidencia.add(inci);
            
           // base.Incidencias(inci);
        }
        
        //metodo para crear devoluciones
        System.out.println("****** devoluciones");
        JSONArray listDevoluciones =(JSONArray) pro.get("devoluciones");
            for( Object devolucion: listDevoluciones ){
                 JSONObject devo= (JSONObject) devolucion;
                 Devoluciones devoluciones =new Devoluciones();
                 devoluciones.setId(this.convertirLongInt(devo.get("id")));
                 devoluciones.setTienda(this.convertirLongInt(devo.get("tienda")));
                 devoluciones.setCodigoUsuario(this.convertirLongInt(devo.get("usuario")));
                 devoluciones.setFecha( Date.valueOf((String) devo.get("fecha")));
                 JSONArray listDevolucionesProducto =(JSONArray) devo.get("productos");
                 for( Object productos: listDevolucionesProducto ){
                    JSONObject producto= (JSONObject) productos;
                    ProductoDevolucion productoss=new ProductoDevolucion();
                    productoss.setIdDevolucion(devoluciones.getId());
                    productoss.setCodigo(this.convertirLongInt(producto.get("codigo")));
                    productoss.setCosto(this.convertirLongDouble(producto.get("costo")));
                    productoss.setCantidad(this.convertirLongInt(producto.get("cantidad")));
                    productoss.setMotivo((String) producto.get("motivo"));
                    productoss.setCostoTotal(this.convertirLongDouble(producto.get("costoTotal")));
                    this.listProductoDevoluciones.add(productoss);
                 }
                 devoluciones.setTotal(this.convertirLongDouble(devo.get("total")));
                 devoluciones.setEstado((String) devo.get("estado"));
                 this.listDevoluciones.add(devoluciones);
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
   
    public void cargarDatado() {
        // guardar productos  en la bodega
        for (Producto producto : listProducto) {
            base.crearProductos(producto);
        }
        // guardar tienda
        for (Tienda tienda : this.listTienda) {
             base.CrearTiendas(tienda);
        }
        //guardar productos de tienda
        for (ProductoTienda productoTienda : this.listProductoTienda) {
            base.ProductoTiendas(productoTienda);
        }
        
        //guardar admin
        for(Usuario admin: this.listAdmin){
            base.verificacionUsuario(admin.getCodigo(),(String) admin.getNombreUsuario(), contraseñaIncriptada(admin.getContraseña()), Estado.ADMINISTRADOR.name());
            db.crearAdmin(admin);
        }
        
         //guardar usuario tienda
        for(UsuarioTienda tienda: this.listUsuarioTienda){
            base.verificacionUsuario(tienda.getCodigo(),tienda.getNombreUsuario(), tienda.getContraseña(),  Estado.TIENDA.name());
            db.crearUsuarioTienda(tienda, tienda.getCodigo());
        }
     
        //guardar supervisor
        for(UsuarioSupervisor supervisor: this.listSupervisado){           
            base.verificacionUsuario( supervisor.getCodigo(),supervisor.getNombreUsuario(),supervisor.getContraseña(), Estado.SUPERVISOR.name());
            db.crearSupervisor(supervisor, supervisor.getCodigo());
        }
        
        //guardar bodega
        for(UsuarioSupervisor bodega: this.listBodega){           
            estado=base.verificacionUsuario(bodega.getCodigo(), bodega.getNombreUsuario(), bodega.getContraseña(),Estado.BODEGA.name());
            db.crearbodega(bodega, bodega.getCodigo()); 
        }
        //guardar tiendas bodegas
         for(TiendasBodega tienda:this.listaTiendaBodega){                    
                   db.listaTiendaBodega(tienda);
            }
          //guardar pedido
        for (Pedido pedido : this.listPedido) {
            base.pedido(pedido);
       }
        //guardar producto pedido
         for (ProductoEnvio productoPedido : this.listProductoPedido) {
            base.listapedido(productoPedido);
        }
        
        //guardar envios
        for (Envios Envios : this.listEnvios) {
            base.Crearenvios(Envios);
         //guardar productos de envio
        }
         //guardar producto envio
        for (ProductoEnvio productoenvio : this.listProductoEnvios) {
               base.listaenvio(productoenvio);
        }
        
        // guardar Incidencia
        for (Incidencia incidencia : this.listIncidencia) {
            base.Incidencias(incidencia);
        }
        //guardar prodcuto de incidencias
        for (ProductoIncidencias productoincidencia : this.listProductoIncidencia) {
            base.listIncidencias(productoincidencia);
        }
        //guardar devolucion
        for (Devoluciones devolucion : this.listDevoluciones) {
            base.crearDevoluciones(devolucion);
        }
        //guardar  productos de devolucion
        for (ProductoDevolucion productoDevolucion : this.listProductoDevoluciones) {
            base.listaDevolucion(productoDevolucion);
            
        }
       
        
    }
     
    public ArrayList Errores(){
       this.listErrores=base.error();
        return this.listErrores;
    
    }
}
