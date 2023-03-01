/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Resources_servlet;

import clases.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author HP
 */
@WebServlet(name = "ServletCreate", urlPatterns = {"/ServletCreate"})
public class ServletCreate extends HttpServlet {
    ConexionBase con=new ConexionBase();
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletCreate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletCreate at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          //response.sendRedirect("Error.jsp");
            Usuario usuario=new Usuario();
            usuario.setNombre(request.getParameter("name"));
            usuario.setNombreUsuario(request.getParameter("user_name"));
            usuario.setContraseña(request.getParameter("password"));
            verificacionUsuario( usuario);
            int code=buscarCodigo(usuario);
            crearAdmin(usuario ,code);
           
            

            
            
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
     public void  verificacionUsuario(Usuario usuario){
            String query = "SELECT * FROM LOGIN WHERE user_name = '"+usuario.getNombreUsuario()+"'";
            try{
                PreparedStatement p = con.conexion().prepareStatement(query);
                ResultSet r = p.executeQuery();
                if(r.next()){}
                else{
                     crearUsuario(usuario);

                    }
                
            }catch(SQLException ex){
                
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
    public void crearAdmin(Usuario usuario ,int codigo) {
        String query = "INSERT INTO user_admin (_code, _name, user_name, _password) VALUES (?,?, ?, ?)";
        
           try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setInt(1, codigo);
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getNombreUsuario());
            preparedStatement.setString(4,   usuario.getContraseña());
            preparedStatement.executeUpdate();
            System.out.println("Usuario Administrador registrado");
            
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void crearUsuario(Usuario usuario) {
        String query = "INSERT INTO LOGIN (user_name, _password) VALUES (?, ?)";
           try{
            PreparedStatement preparedStatement; 
            preparedStatement = con.conexion().prepareStatement(query);
            preparedStatement.setString(1, usuario.getNombreUsuario());
            preparedStatement.setString(2,   usuario.getContraseña());
            preparedStatement.executeUpdate();
            System.out.println("Usuario creado");
            
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public int  buscarCodigo(Usuario usuario){
         String query = "SELECT * FROM LOGIN WHERE user_name = '"+usuario.getNombreUsuario()+"'";
            try{
                PreparedStatement p = con.conexion().prepareStatement(query);
                ResultSet r = p.executeQuery();
                if(r.next()){
                    int codigo=r.getInt("_code");
                 return codigo; 
                }
                
            }catch(SQLException ex){
                
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
