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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import resources.Estado;


/**
 *
 * @author HP
 */
public class Ejecutable{
    
    public static void main(String[] args)  {
        
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

    private static void mostrarInformacion(JSONObject jsonObject) {
        //metodo para entrar a la arrylist
       JSONObject pro= (JSONObject) jsonObject;
       JSONArray list = (JSONArray) pro.get("productos");
       //carga de datos de productos
       for(Object produ:list){
           JSONObject a= (JSONObject) produ;
           System.out.println("-----------------");
           Long codigo = (Long) a.get("codigo");
           String nombre = (String) a.get("nombre");
           Long existencia= (Long) a.get("existencias");
           System.out.println("codigo: "+codigo);
           System.out.println("nombre: "+nombre);
           System.out.println("costo: "+ a.get("costo"));
           System.out.println("precio: "+ a.get("precio"));
           System.out.println("existencia: "+existencia);           
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
           
        //metodo parar cargar admon
        JSONArray listaAdmin = (JSONArray) pro.get("admins");
        for(Object admin:listaAdmin){
            Usuario usuarioadmin=new Usuario();
           JSONObject user= (JSONObject) admin;
           System.out.println("////////////// admin");
           System.out.println("codigo: "+user.get("codigo"));
           System.out.println("nombre: "+user.get("nombre"));
           System.out.println("username: "+user.get("username"));
           System.out.println("contraseña: "+user.get("password"));
           
           long codigo= (long) user.get("codigo");
           int at = (int) codigo;
           String nombre=(String) user.get("nombre");
           String usuario= (String)user.get("username");
           String contraseña= (String)user.get("password");
           usuarioadmin.setCodigo(at);
           usuarioadmin.setNombre(nombre);
           usuarioadmin.setNombreUsuario(usuario);
           usuarioadmin.setContraseña(contraseña);
          
           GuardarDB DB=new GuardarDB();
           GuardarDatosEntrada base=new GuardarDatosEntrada();
           base.verificacionUsuario(at,usuario, contraseña, Estado.ADMINISTRADOR.name());
           //int code=DB.buscarCodigo( (String) user.get("userName"));
           DB.crearAdmin(usuarioadmin ,at);
        }
       }
       
    }

   

  
}
