/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Resources_servlet;

import BaseDatos.CrearEnvioDB;
import BaseDatos.EditarDB;
import clases.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "AODevolucion", urlPatterns = {"/AODevolucion"})
public class AODevolucion extends HttpServlet {

    String tienda;
    EditarDB db = new EditarDB();
    String idDevolucion;
    ArrayList<Producto> lista;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AODevolucion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AODevolucion at " + request.getContextPath() + "</h1>");
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
           String menu = request.getParameter("button");
           switch (menu) {
               case "elegirTienda":
                   tienda = request.getParameter("tienda");
                   String query="select tienda, id from devoluciones where tienda="+tienda+" and estado='"+Estado.ACTIVA.name()+"'";
                   System.out.println(tienda);
                   request.setAttribute("listDevolucion", db.listaTida(query));
                   request.getRequestDispatcher("Vendana_Bodega/AceptarRecharDevolucion.jsp").forward(request, response);
                break;
                case"busrcarDevoluicon":
                     idDevolucion= request.getParameter("idDevolucion");
                    System.out.println(idDevolucion);
                    request.setAttribute("listproductos",db.listas("devoluciones_producto where id_pedido="+idDevolucion, "ProductoDevolucion"));
                    request.getRequestDispatcher("Vendana_Bodega/AceptarRecharDevolucion.jsp").forward(request, response);
                    break;
                case"Aceptar":
                    db.editar("UPDATE devoluciones SET estado='"+Estado.ACEPTADA.name()+"' WHERE id ="+idDevolucion);
                    request.getRequestDispatcher("Vendana_Bodega/AceptarRecharDevolucion.jsp").forward(request, response);
                    break;
                case"Rechazar":
                    db.editar("UPDATE devoluciones SET estado='"+Estado.RECHAZADA+"' WHERE id ="+idDevolucion);
                    request.getRequestDispatcher("Vendana_Bodega/AceptarRecharDevolucion.jsp").forward(request, response);
                    break;    
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
