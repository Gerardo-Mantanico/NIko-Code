
package Resources_servlet;

import BaseDatos.EditarDB;
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
@WebServlet(name = "EnvioLisProducto", urlPatterns = {"/EnvioLisProducto"})
public class EnvioLisProducto extends HttpServlet {

    ArrayList listaproducto;
    EditarDB DB =new EditarDB();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EnvioLisProducto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EnvioLisProducto at " + request.getContextPath() + "</h1>");
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
            String id=request.getParameter("envio");
            String tienda=request.getParameter("tienda");
            request.setAttribute("productosEnvio", DB.listas( "envios_productos where id_pedido="+id, "productosEnvio"));
            request.setAttribute("listEnvio", this.listas("envios where tienda=" + tienda, "envios"));
            request.getRequestDispatcher("Ventana_Tienda/RecibirEnvio.jsp").forward(request, response);
            
       
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
