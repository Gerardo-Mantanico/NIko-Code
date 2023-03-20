/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Resources_servlet;

import BaseDatos.CrearEnvioDB;
import BaseDatos.EditarDB;
import clases.ListaId;
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
import javax.servlet.http.HttpSession;
import resources.Estado;

/**
 *
 * @author HP
 */
@WebServlet(name = "Ventanas", urlPatterns = {"/Ventanas"})
public class Ventanas extends HttpServlet {
   String query;
   String tienda;
   EditarDB db=new EditarDB();
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
                tienda = request.getParameter("valor");
                query="envios where tienda="+tienda+" and estado='"+Estado.DESPACHADO.name()+"'";
                request.setAttribute("listEnvio", listas(query, "envios"));
                request.getRequestDispatcher("Ventana_Tienda/RecibirEnvio.jsp").forward(request, response);
            break;
            case"incidencias":
                 tienda = request.getParameter("valor");
                 query="select e.id, e.estado, e.tienda from envios e left  join incidencias i on e.id=i.id where i.id is null";
                request.setAttribute("listEnvio", this.idEnvio(query));
                request.getRequestDispatcher("Ventana_Tienda/IncidenciaDevolucion.jsp").forward(request, response);
                break;
            case"inicio":
                super.destroy();
                response.sendRedirect("index.jsp");
            break;
            case"devolucion":
                tienda = request.getParameter("valor");
                query="select e.id, e.estado, e.tienda from envios e left  join devoluciones d on e.id=d.id where d.id is null";
                request.setAttribute("listEnvio", this.idEnvio(query));
                request.getRequestDispatcher("Ventana_Tienda/Devoluciones.jsp").forward(request, response);
            break;
            case"crearEncvio":
                HttpSession session = request.getSession();
                int c= (int) session.getAttribute("valor");
                CrearEnvioDB envio =new CrearEnvioDB();
                request.setAttribute("listTiendas", envio.listadoTiendas( String.valueOf(c)));
                request.getRequestDispatcher("Vendana_Bodega/CrearEnvio.jsp").forward(request, response);
                break;
            case"ReporteBodega":
                 request.getRequestDispatcher("Vendana_Bodega/Reporte.jsp").forward(request, response);
                
                
                break;
                
                 case"s":
                break;
                
                 case"a":
                break;
                 case"e":
                break;
                 case"f":
                break;
                
            default:
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
     
    public ArrayList idEnvio(String query) {
        ArrayList<ListaId> li;
        ArrayList<String> nueva = new ArrayList();
        li = db.IdDevoluciones(query);
        for (ListaId listaid : li) {
            if (listaid.getTienda() == Integer.valueOf(tienda)) {
                if (listaid.getEstad().equals(Estado.RECIBIDO.name())) {
                    nueva.add(String.valueOf(listaid.getId()));
                }
            }
        }
        return nueva;

    }
   

}
