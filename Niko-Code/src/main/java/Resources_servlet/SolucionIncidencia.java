/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Resources_servlet;

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
@WebServlet(name = "SolucionIncidencia", urlPatterns = {"/SolucionIncidencia"})
public class SolucionIncidencia extends HttpServlet {
    
    String tienda;
    EditarDB db = new EditarDB();
    String idIncidencia;
    ArrayList<Producto> lista;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SolucionIncidencia</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SolucionIncidencia at " + request.getContextPath() + "</h1>");
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
                String query = "select tienda, id from incidencias where tienda=" + tienda + " and estado='" + Estado.ACTIVA.name() + "'";
                request.setAttribute("listDevolucion", db.listaTida(query));
                request.getRequestDispatcher("Vendana_Bodega/SolucionarIncidencias.jsp").forward(request, response);
                break;
            case "busrcarDevoluicon":
                idIncidencia = request.getParameter("idIncidencia");
                request.setAttribute("listproductos", db.listas(" incidencias_producto where id_pedido="+idIncidencia, "ProductoIncidencias"));
                request.getRequestDispatcher("Vendana_Bodega/SolucionarIncidencias.jsp").forward(request, response);
                break;
            case"AcpetarIncidencia":
                String text = request.getParameter("text");
                query = " UPDATE incidencias SET estado='" + Estado.SOLUCIONADA.name() + "' WHERE id =" + idIncidencia;
                String query2 = "UPDATE incidencias SET solucion='"+text+"'  WHERE id =" + idIncidencia;
                db.editar(query);
                db.editar(query2);
                request.getRequestDispatcher("Vendana_Bodega/SolucionarIncidencias.jsp").forward(request, response);
                break;
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
