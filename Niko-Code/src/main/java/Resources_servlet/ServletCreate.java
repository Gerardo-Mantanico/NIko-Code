
package Resources_servlet;

import clases.Usuario;
import clases.UsuarioSupervisor;
import clases.UsuarioTienda;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import resources.ConexionBase;
import resources.Encriptar;
import resources.Estado;

/**
 *
 * @author HP
 */
@WebServlet(name = "ServletCreate", urlPatterns = {"/ServletCreate"})
public class ServletCreate extends HttpServlet {
    ConexionBase con=new ConexionBase();
    int code;
    Encriptar encriptar=new Encriptar();
   

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
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String menu=request.getParameter("button");
        try {    
           switch (menu) {
            case "admin":
                Usuario usuario=new Usuario();
                usuario.setNombre(request.getParameter("name"));
                usuario.setNombreUsuario(request.getParameter("user_name"));
                usuario.setContraseña(encriptar.hashPassword(request.getParameter("password")));
                verificacionUsuario(usuario.getNombreUsuario(),usuario.getContraseña()  ,Estado.ADMINISTRADOR.name());
                code=buscarCodigo(usuario.getNombreUsuario());
                crearAdmin(usuario ,code);
                response.sendRedirect("Venta_Administrativa/Venta_Principal.jsp"); 
                break;
            case "Tienda":
                UsuarioTienda userTienda=new UsuarioTienda();
                userTienda.setNombre(request.getParameter("name"));
                int store=Integer.parseInt(request.getParameter("store"));
                userTienda.setTienda(store);
                userTienda.setNombreUsuario(request.getParameter("user_name"));
                userTienda.setContraseña(encriptar.hashPassword(request.getParameter("password")));
                userTienda.setEmail(request.getParameter("email"));  
                verificacionUsuario(userTienda.getNombreUsuario(),userTienda.getContraseña()   ,Estado.TIENDA.name());
                code=buscarCodigo(userTienda.getNombreUsuario());
                crearUsuarioTienda(userTienda,  code);
                response.sendRedirect("Venta_Administrativa/UsuariosTienda.jsp"); 
                break;
            case "Supervisor":
                    UsuarioSupervisor supervisor=new UsuarioSupervisor();
                    supervisor.setNombre(request.getParameter("name"));
                    supervisor.setNombreUsuario(request.getParameter("user_name"));
                    supervisor.setEmail(request.getParameter("email"));
                    supervisor.setContraseña(encriptar.hashPassword(request.getParameter("password")));
                    verificacionUsuario(supervisor.getNombreUsuario(),supervisor.getContraseña()   ,Estado.SUPERVISOR.name());
                    code=buscarCodigo(supervisor.getNombreUsuario());
                    crearSupervisor(supervisor, code);
                    response.sendRedirect("Venta_Administrativa/SupervisorTienda.jsp"); 
                break;
            default:}  
            } catch (NoSuchAlgorithmException ex) {
                 Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);}
    }   

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    //metodo de verificacion de usuairo 
    public void  verificacionUsuario(String usuario, String contraseña,String tipo){
            String query = "SELECT * FROM LOGIN WHERE user_name = '"+usuario+"'";
            try{
                PreparedStatement p = con.conexion().prepareStatement(query);
                ResultSet r = p.executeQuery();
                if(r.next()){}
                else{
                     crearUsuario(usuario,contraseña,tipo);}
            }catch(SQLException ex){
                System.out.println("Error en "+ ex);
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    //metodo para crear usuario 
    public void crearAdmin(Usuario usuario ,int codigo) {
        String query = "INSERT INTO user_admin (_code, _name, user_name, _password) VALUES (?,?, ?, ?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, codigo);
            preparedStatement.setString(2,usuario.getNombre());
            preparedStatement.setString(3,usuario.getNombreUsuario());
            preparedStatement.setString(4,usuario.getContraseña());
            preparedStatement.executeUpdate();
            System.out.println("Usuario Administrador registrado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // metodo para crear usuario para login
    public void crearUsuario(String usuario, String contraseña, String tipo) {
       String query = "INSERT INTO LOGIN (user_name, _password, _type, state) VALUES (?, ?,?,?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, contraseña);
            preparedStatement.setString(3, tipo);
            preparedStatement.setString(4,   Estado.ACTIVADO.name());
            preparedStatement.executeUpdate();
            System.out.println("Usuario creado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo para obtener codigo 
    public int  buscarCodigo(String usuario){
        String query = "SELECT * FROM LOGIN WHERE user_name = '"+usuario+"'";
        try{
           PreparedStatement p = con.conexion().prepareStatement(query);
           ResultSet r = p.executeQuery();
           if(r.next()){
               int codigo=r.getInt("_code");
                 return codigo; }
           }catch(SQLException ex){
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    //metodo para crear usuarios de tienda
    public void crearUsuarioTienda(UsuarioTienda usuario, int codigo){
       String query = "INSERT INTO user_store (_code, _name, store, user_name, _password, email) VALUES (?,?, ?, ?,?, ?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, codigo);
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setInt(3, usuario.getTienda());
            preparedStatement.setString(4,   usuario.getNombreUsuario());
            preparedStatement.setString(5,   usuario.getContraseña());
            preparedStatement.setString(6,   usuario.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Usuario Administrador registrado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo para crear supervisores
    public void crearSupervisor(UsuarioSupervisor supervisor, int codigo) {
        String query = "INSERT INTO supervisory (_code, _name, user_name, _password, email) VALUES (?, ?, ?,?, ?)";
        try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, codigo);
            preparedStatement.setString( 2,supervisor.getNombre());
            preparedStatement.setString( 3,supervisor.getNombreUsuario());
            preparedStatement.setString( 4,supervisor.getContraseña());
            preparedStatement.setString( 5,supervisor.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Usuario Administrador registrado");
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }   
}
