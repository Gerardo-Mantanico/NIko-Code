/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Resources_servlet;

import BaseDatos.EditarDB;
import clases.Usuario;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import resources.ConexionBase;
import resources.Encriptar;

/**
 *
 * @author HP
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletLogin at " + request.getContextPath() + "</h1>");
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
            String nombreUsuario=request.getParameter("User_name");
            String contraseña=request.getParameter("password");
            
            ConexionBase con=new ConexionBase();
            String query = "SELECT * FROM LOGIN WHERE user_name = '"+nombreUsuario+"'";
            try{
                Encriptar encriptar=new Encriptar();
                PreparedStatement p = con.conexion().prepareStatement(query);
                ResultSet r = p.executeQuery();
                try {
                   String nuevo= encriptar.hashPassword(contraseña);
                     if(r.next()){
                    String conta=r.getString("_password");
                    if(nuevo.equals(conta)){
                        String tipo=r.getString("_type");
                        switch (tipo) {
                            case "ADMINISTRADOR":
                                request.setAttribute("lista", listaAdmin());
                                request.getRequestDispatcher("Venta_Administrativa/Venta_Principal.jsp").forward(request, response);
                            break;
                            case "TIENDA":
                                String userTienda="SELECT * FROM user_store WHERE user_name = '"+nombreUsuario+"'";
                                PreparedStatement pa = con.conexion().prepareStatement(userTienda);
                                ResultSet rr = pa.executeQuery();
                                if(rr.next()){
                                    HttpSession session = request.getSession();
                                    session.setAttribute("fecha", "2023-02-04");
                                    session.setAttribute("codigoUsuario", String.valueOf(r.getInt("_code")));
                                    session.setAttribute("tienda", String.valueOf(rr.getInt("store")));
                                    request.setAttribute("lista", this.lista("catalogue"));
                                    request.getRequestDispatcher("Ventana_Tienda/Tienda.jsp").forward(request, response);
                                 }
                            break;
                            case "SUPERVISOR":
                                 response.sendRedirect("Venta_Administrativa/Venta_Principal.jsp");
                            break;
                            default:
 
                        }
                    }
                    else{
                      request.setAttribute("msj","contraseña incorrecta");
                     request.getRequestDispatcher("index.jsp").forward(request, response); 
                    }
                }
                else{
                     request.setAttribute("msj","No existe este usuario");
                     request.getRequestDispatcher("index.jsp").forward(request, response); 
                }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
                }catch(SQLException ex){
                } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public ArrayList  listaAdmin(){
          EditarDB db=new EditarDB();
            ArrayList<Usuario> modelList;
            modelList=db.listUsuarioTienda("user_admin");
        return modelList;
         
    }
     public ArrayList  lista(String query){
            EditarDB db=new EditarDB();
            ArrayList list = new ArrayList();
            list=db.listUsuarioTienda(query);
        return list;
     }
    

}


                    