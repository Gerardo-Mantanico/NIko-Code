/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Resources_servlet;

import BaseDatos.EditarDB;
import clases.Usuario;
import clases.UsuarioSupervisor;
import clases.UsuarioTienda;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name = "Ventanas", urlPatterns = {"/Ventanas"})
public class Ventanas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String menu=request.getParameter("accion");
            switch (menu) {
            case "admin":
                 request.setAttribute("lista", lista("user_admin"));
                 request.getRequestDispatcher("Venta_Administrativa/Venta_Principal.jsp").forward(request, response);
            break;
            case"tienda":
                request.setAttribute("lista", lista("user_store"));
                request.getRequestDispatcher("Venta_Administrativa/UsuariosTienda.jsp").forward(request, response);
            break;
            case"bodega":
                 request.setAttribute("lista", lista("user_store"));
                request.getRequestDispatcher("Venta_Administrativa/UsuarioBodega.jsp").forward(request, response);
            break;
            case"supervisor":
                request.setAttribute("lista", lista("supervisory"));
                request.getRequestDispatcher("Venta_Administrativa/SupervisorTienda.jsp").forward(request, response);
            break;
            case"RecibirEnvios":
                String tienda = request.getParameter("valor");
                System.out.println(tienda);
                String query = "envios where tienda=" + tienda;
                request.setAttribute("listEnvio", this.listas(query, "envios"));
                request.getRequestDispatcher("Ventana_Tienda/RecibirEnvio.jsp").forward(request, response);
            break;
            case"incidencias":
                String tiend = request.getParameter("valor");
                String queryr = "envios where tienda=" + tiend;
                request.setAttribute("listEnvio", this.listas(queryr, "envios"));
                request.getRequestDispatcher("Ventana_Tienda/IncidenciaDevolucion.jsp").forward(request, response);
                break;
            case"inicio":
                super.destroy();
                response.sendRedirect("index.jsp");
            break;
            case"devolucion":
                String tiendr = request.getParameter("valor");
                String queryrr= "envios where tienda=" + tiendr;
                request.setAttribute("listEnvio", this.listas(queryrr, "envios"));
                request.getRequestDispatcher("Ventana_Tienda/Devoluciones.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
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
        processRequest(request, response);
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
    
  

     public ArrayList  lista(String query){
            EditarDB db=new EditarDB();
            ArrayList list = new ArrayList();
            list=db.listUsuarioTienda(query);
        return list;
         
    }
     
       public ArrayList  listas(String query, String menu){
            EditarDB db=new EditarDB();
            ArrayList list = new ArrayList();
            list=db.listas(query, menu);
        return list;
         
    }
         


}
