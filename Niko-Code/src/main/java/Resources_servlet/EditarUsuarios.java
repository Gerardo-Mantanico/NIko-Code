/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Resources_servlet;

import BaseDatos.EditarDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import resources.Estado;

/**
 *
 * @author HP
 */
@WebServlet(name = "EditarUsuarios", urlPatterns = {"/EditarUsuarios"})
public class EditarUsuarios extends HttpServlet {

   EditarDB DB= new EditarDB();
   String id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditarUsuarios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarUsuarios at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              String menu= request.getParameter("button");
              switch (menu) {
            case "Desactivar":
                 id =request.getParameter("IdUsuario");
                String query="UPDATE login set state='"+Estado.DESACTIVADO.name()+"' WHERE  _code="+id;
                DB.editar(query);
                request.getRequestDispatcher("Ventanas?accion=tienda").forward(request, response); 
                break;
                  case "Activar":
                 id =request.getParameter("IdUsuario");
                query="UPDATE login set state='"+Estado.ACTIVADO.name()+"' WHERE  _code="+id;
                DB.editar(query);
                request.getRequestDispatcher("Ventanas?accion=tienda").forward(request, response); 
                break;
            default:
               
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
