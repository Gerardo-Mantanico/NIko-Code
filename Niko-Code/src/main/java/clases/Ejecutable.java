/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import BaseDatos.GuardarDB;
import BaseDatos.GuardarDatosEntrada;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import resources.Encriptar;
import resources.Estado;


/**
 *
 * @author HP
 */
public class Ejecutable{
    
    private String  pedidos="pedidos";
    
    public static void main(String[] args)   {
        
      JSONParser jsonParser = new JSONParser();
              
            try (FileReader reader = new FileReader("C:/Users/HP/Desktop/ingles Luis/IPC2/entrada.json"))
            {
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

    private static void mostrarInformacion(JSONObject jsonObject)  {
        //metodo para entrar a la arrylist
       JSONObject pro= (JSONObject) jsonObject;
       JSONArray list = (JSONArray) pro.get("productos");
       //carga de datos de productos
       for(Object produ:list){
           JSONObject a= (JSONObject) produ;
           System.out.println("-----------------");
           Producto producto=new Producto();
           long codigo = (long) a.get("codigo");
           String nombre = (String) a.get("nombre");
           long existencia= (long) a.get("existencias");
           System.out.println("codigo: "+codigo);
           System.out.println("nombre: "+nombre);
           System.out.println("costo: "+ a.get("costo"));
           System.out.println("precio: "+ a.get("precio"));
           System.out.println("existencia: "+existencia);
           GuardarDatosEntrada base=new GuardarDatosEntrada();
           producto.setCodigo( (int) codigo );
           producto.setNombre((String) a.get("nombre"));
           try {
                  producto.setCosto((double) a.get("costo"));
           }catch (Exception e){
               long costoU= (long) a.get("costo");
               producto.setCosto( (double) costoU);}
            try {
                producto.setPrecio((long) a.get("precio"));
             }catch (Exception e){
                producto.setPrecio((double) a.get("precio"));
             }
           producto.setExistencia((int) existencia);
          // base.crearProductos(producto);
       }
       
       //para cargar los datos de tienda
       JSONArray lista = (JSONArray) pro.get("tiendas");
        for(Object tienda:lista){
           JSONObject a= (JSONObject) tienda;
           System.out.println("***********");
           System.out.println("codigo: "+a.get("codigo"));
           System.out.println("nombre: "+a.get("direccion"));
           System.out.println("costo: "+ a.get("tipo"));
           JSONArray listaprodcutos = (JSONArray) a.get("productos");
                for( Object productos: listaprodcutos){
                    JSONObject e= (JSONObject) productos;
                    System.out.println("         codigo: "+e.get("codigo"));
                    System.out.println("         existencia: "+e.get("existencias")); 
                }
       }
           
        //metodo parar cargar admin
        JSONArray listaAdmin = (JSONArray) pro.get("admins");
        for(Object admin:listaAdmin){
            Usuario usuarioadmin=new Usuario();
           JSONObject user= (JSONObject) admin;
           System.out.println("////////////// admin");
           System.out.println("codigo: "+user.get("codigo"));
           System.out.println("nombre: "+user.get("nombre"));
           System.out.println("username: "+user.get("username"));
           System.out.println("contraseña: "+user.get("password"));
           Encriptar encriptar=new Encriptar();
           long codigo= (long) user.get("codigo");
           int at = (int) codigo;
           String nombre=(String) user.get("nombre");
           String usuario= (String)user.get("username");
           String contraseña= (String)user.get("password");
           usuarioadmin.setCodigo(at);
           usuarioadmin.setNombre(nombre);
           usuarioadmin.setNombreUsuario(usuario);
           try {
               usuarioadmin.setContraseña(encriptar.hashPassword(contraseña));
               GuardarDB DB=new GuardarDB();
               GuardarDatosEntrada base=new GuardarDatosEntrada();
               base.verificacionUsuario(at,usuario, encriptar.hashPassword(contraseña), Estado.ADMINISTRADOR.name());
              // DB.crearAdmin(usuarioadmin ,at);
           } catch (NoSuchAlgorithmException ex) {
               Logger.getLogger(Ejecutable.class.getName()).log(Level.SEVERE, null, ex);
           }
          
           
        }
        
        //metodo para cargar usuariostienda
         System.out.println("////////////// usuarios de tienda");
        JSONArray listUsuariostienda =(JSONArray) pro.get("usuariostienda");
        for(Object usuarioTienda: listUsuariostienda){
            JSONObject userTienda= (JSONObject) usuarioTienda;
            System.out.println("codigo:  "+ userTienda.get("codigo"));
            System.out.println("nombre:  "+ userTienda.get("nombre"));
        }
        
        // metodo para cargar supervisores
         System.out.println("////////////// supervisor");
         JSONArray listsupervisor =(JSONArray) pro.get("supervisores");
        for(Object usuariosupervisor: listsupervisor){
            JSONObject userSupervisor= (JSONObject) usuariosupervisor;
            System.out.println("codigo:  "+ userSupervisor.get("codigo"));
            System.out.println("nombre:  "+ userSupervisor.get("nombre"));
        }
        
        
         
        // metodo para cargar encargados de bodega 
         System.out.println("////////////// Bodega");
         JSONArray listBodega =(JSONArray) pro.get("encargadosBodega");
        for(Object usuarioBodega: listBodega){
            JSONObject userBodega= (JSONObject) usuarioBodega;
            System.out.println("codigo:  "+ userBodega.get("codigo"));
            System.out.println("nombre:  "+ userBodega.get("nombre"));
            JSONArray listatiendas = (JSONArray) userBodega.get("tiendas");
            System.out.println("tiendas");
                for( Object tienda: listatiendas){
                     int valor=Integer.valueOf(tienda.toString());
                    System.out.println("            "+ userBodega.get("codigo")+"  "+valor);
                }
        }
        
        
        //metodo para cargar pedidos
        System.out.println("------------------ pedidos");
         JSONArray listPedidos =(JSONArray) pro.get("pedidos");
        for(Object pedidos: listPedidos){
            JSONObject pedido= (JSONObject) pedidos;
            System.out.println("id:  "+ pedido  .get("id"));
            System.out.println("tienda:  "+ pedido.get("tienda"));
            System.out.println("****** productos");
            JSONArray listPedidoProducto =(JSONArray) pedido.get("productos");
            for( Object productos: listPedidoProducto ){
                 JSONObject producto= (JSONObject) productos;
                 System.out.println("       codigo:  "+ producto.get("codigo"));
                 System.out.println("       costoU:  "+ producto.get("costoU"));
            }
            System.out.println("Estado:  "+ pedido.get("estado"));
        }
         //metodo para enviar
         
         System.out.println("------------------ envios");
         JSONArray listenvio =(JSONArray) pro.get("envios");
        for(Object envios: listenvio){
            JSONObject envio= (JSONObject) envios;
            System.out.println("id:  "+ envio  .get("id"));
            System.out.println("tienda:  "+ envio.get("tienda"));
            System.out.println("****** productos");
            JSONArray listPedidoProducto =(JSONArray) envio.get("productos");
            for( Object productos: listPedidoProducto ){
                 JSONObject producto= (JSONObject) productos;
                 System.out.println("       codigo:  "+ producto.get("codigo"));
                 System.out.println("       costoU:  "+ producto.get("costoU"));
            }
            System.out.println("Estado:  "+ envio.get("estado"));
        }
         
        //metodo parar incidencias
       System.out.println("------------------ incidencias");
         JSONArray listIncidencias =(JSONArray) pro.get("incidencias");
        for(Object incidencias: listIncidencias){
            JSONObject incidencia= (JSONObject) incidencias;
            System.out.println("id:  "+ incidencia  .get("id"));
            System.out.println("tienda:  "+ incidencia.get("tienda"));
            System.out.println("****** productos");
            JSONArray listPedidoProducto =(JSONArray) incidencia.get("productos");
            for( Object productos: listPedidoProducto ){
                 JSONObject producto= (JSONObject) productos;
                 System.out.println("       codigo:  "+ producto.get("codigo"));
                 System.out.println("       cantidad:  "+ producto.get("cantidad"));
            }
            System.out.println("Estado:  "+ incidencia.get("estado"));
        }
     
        cargarArchivo("devoluciones",pro);
       }
    
    public static void  cargarArchivo(String tipo, JSONObject objetoentra){
           System.out.println("****** "+tipo);
            JSONArray list =(JSONArray) objetoentra.get(tipo);
            for( Object productos: list ){
                 JSONObject producto= (JSONObject) productos;
                 System.out.println("id:  "+ producto.get("id"));
                 System.out.println("tienda:  "+ producto.get("tienda"));
            }
    }
}
