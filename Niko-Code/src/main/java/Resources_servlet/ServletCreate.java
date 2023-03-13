
package Resources_servlet;

import BaseDatos.EditarDB;
import BaseDatos.GuardarDB;
import clases.Usuario;
import clases.UsuarioSupervisor;
import clases.UsuarioTienda;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import resources.CargaDatosEntrada;
import resources.ConexionBase;
import resources.Encriptar;
import resources.Estado;

/**
 *
 * @author HP
 */
@WebServlet(name = "ServletCreate", urlPatterns = {"/ServletCreate"})
@MultipartConfig()
public class ServletCreate extends HttpServlet {
    ConexionBase con=new ConexionBase();
    int code;
    Encriptar encriptar=new Encriptar();
    GuardarDB Db =new GuardarDB();
    boolean estado;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    

            
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String menu=request.getParameter("button");
        try {    
           switch (menu) {
            case "cargarArchivo":
                Part filePart = request.getPart("fileInput");
                String nombreArch= filePart.getSubmittedFileName();
                String path=this.getServletConfig().getServletContext().getRealPath("/fileInput");
                File directorio= new  File(path);
                 if(!directorio.exists()){
                    directorio.mkdir();
                    }
                filePart.write(path+"/"+nombreArch);
                File archivo= new File(path+"/"+nombreArch);
                CargaDatosEntrada carga=new CargaDatosEntrada();
                carga.leerJson(archivo);
                carga.leerJson(archivo);
                request.setAttribute("lista", lista("user_admin"));
                request.getRequestDispatcher("Venta_Administrativa/Venta_Principal.jsp").forward(request, response); 
                
            break;
            case "admin":
                Usuario usuario=new Usuario();
                usuario.setNombre(request.getParameter("name"));
                usuario.setNombreUsuario(request.getParameter("user_name"));
                usuario.setContraseña(encriptar.hashPassword(request.getParameter("password")));
                estado= Db.verificacionUsuario(usuario.getNombreUsuario(),usuario.getContraseña()  ,Estado.ADMINISTRADOR.name());
               if(estado==false){
                code=Db.buscarCodigo(usuario.getNombreUsuario());
                Db.crearAdmin(usuario ,code);
                request.setAttribute("msj","Usuario administador creado");
                request.setAttribute("lista", lista("user_admin"));
                request.getRequestDispatcher("Venta_Administrativa/Venta_Principal.jsp").forward(request, response); 
               }
               if(estado==true)
               {
                   request.setAttribute("lista", lista("user_admin"));
                   request.setAttribute("msj","Este usuario ya existe");
                   request.getRequestDispatcher("Venta_Administrativa/Venta_Principal.jsp").forward(request, response); 
               }
                break;
                
            case "Tienda":
                UsuarioTienda userTienda=new UsuarioTienda();
                userTienda.setNombre(request.getParameter("name"));
                int store=Integer.parseInt(request.getParameter("store"));
                userTienda.setTienda(store);
                userTienda.setNombreUsuario(request.getParameter("user_name"));
                userTienda.setContraseña(encriptar.hashPassword(request.getParameter("password")));
                userTienda.setEmail(request.getParameter("email"));  
                estado=Db.verificacionUsuario(userTienda.getNombreUsuario(),userTienda.getContraseña()   ,Estado.TIENDA.name());
                if(estado==false){
                    code=Db.buscarCodigo(userTienda.getNombreUsuario());
                    Db.crearUsuarioTienda(userTienda,  code);
                    request.setAttribute("msj","Usuario Tienda creado creado");
                    request.setAttribute("lista", lista("user_store"));
                    request.getRequestDispatcher("Venta_Administrativa/UsuariosTienda.jsp").forward(request, response); 
                }
                else{
                    request.setAttribute("lista", lista("user_store"));
                    request.setAttribute("msj","Este usuario ya existe");
                    request.getRequestDispatcher("Venta_Administrativa/UsuariosTienda.jsp").forward(request, response); 
                }
                
                break;
            case "Supervisor":
                    UsuarioSupervisor supervisor=new UsuarioSupervisor();
                    supervisor.setNombre(request.getParameter("name"));
                    supervisor.setNombreUsuario(request.getParameter("user_name"));
                    supervisor.setEmail(request.getParameter("email"));
                    supervisor.setContraseña(encriptar.hashPassword(request.getParameter("password")));
                   estado= Db.verificacionUsuario(supervisor.getNombreUsuario(),supervisor.getContraseña()   ,Estado.SUPERVISOR.name());
                   if(estado==false){
                       code=Db.buscarCodigo(supervisor.getNombreUsuario());
                       Db.crearSupervisor(supervisor, code);
                       request.setAttribute("msj","Usuario supervisor creado creado");
                       request.setAttribute("lista", lista("supervisory"));
                       request.getRequestDispatcher("Venta_Administrativa/SupervisorTienda.jsp").forward(request, response); 
                   }
                   else{
                       request.setAttribute("msj","Este usuario ya existe intente con uno nuevo");
                       request.setAttribute("lista", lista("supervisory"));
                       request.getRequestDispatcher("Venta_Administrativa/SupervisorTienda.jsp").forward(request, response); 
                   }
                    
                break;
            default:}  
            } catch (NoSuchAlgorithmException ex) {
                 Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);}
    }   

    @Override
    public String getServletInfo() {
        return "Short description";
    }
   
    
     public ArrayList  lista(String query){
            EditarDB db=new EditarDB();
            ArrayList list = new ArrayList();
            list=db.listUsuarioTienda(query);
        return list;
         
    }
    
}
