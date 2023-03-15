/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Resources_servlet;

import BaseDatos.EditarDB;
import BaseDatos.GuardarDatosEntrada;
import clases.Incidencia;
import clases.Producto;
import clases.ProductoDevolucion;
import clases.ProductoIncidencia;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import resources.Estado;
import resources.EstadoVista;

/**
 *
 * @author HP
 */
@WebServlet(name = "ServletDevolucion", urlPatterns = {"/ServletDevolucion"})
public class ServletDevolucion extends HttpServlet {
    ArrayList<Producto> list;
    ArrayList<Producto> lista = new ArrayList<Producto>();
    EditarDB base =new EditarDB();
    GuardarDatosEntrada DB= new GuardarDatosEntrada();
    Incidencia devolucion= new Incidencia();
    ProductoDevolucion proDevolucion =new ProductoDevolucion();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletDevolucion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletDevolucion at " + request.getContextPath() + "</h1>");
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
        String menu= request.getParameter("button");
        switch (menu) {
            case  "buscar":
               lista.clear();
                String fecha= request.getParameter("fecha");
                String IdEnvio=request.getParameter("idEnvio");
                String tienda=request.getParameter("tienda");
                devolucion.setId(Integer.valueOf(IdEnvio));
                devolucion.setTienda(Integer.valueOf(tienda));
                devolucion.setFecha(   Date.valueOf(fecha));
                devolucion.setEstado(Estado.ACTIVA.name());
                devolucion.setCodigoUsuario(Integer.valueOf(request.getParameter("codigoUsuario")));
                String query="envios_productos where id_pedido="+IdEnvio;
                list=base.listas(query, "productosEnvio");
                request.setAttribute("listaProductos", list);
                request.setAttribute("listEnvio", this.listas("envios where tienda="+tienda, "envios"));
                request.setAttribute("estado", this.estados());
                request.setAttribute("fecha", fecha);
                request.getRequestDispatcher("Ventana_Tienda/Devoluciones.jsp").forward(request, response);
            break;
            case "Agregar":
                
                String codigoProducto= request.getParameter("productoCodigo");
                String estado=request.getParameter("estado");
                String q="envios_productos where codigo="+codigoProducto+" and id_pedido="+devolucion.getId();
               for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCodigo() == Integer.valueOf(codigoProducto)) {
                        list.remove(i);
                    }
                }
                Producto pro= (Producto)base.ObtenerObjetos(q, "productosEnvio");
                proDevolucion.setMotivo(estado);
                lista.add(pro);
                request.setAttribute("listaProductos", list);
                request.setAttribute("fecha",  devolucion.getFecha().toString());
                request.setAttribute("estado", this.estados());
                request.setAttribute("productosEnvio",lista);
                request.getRequestDispatcher("Ventana_Tienda/Devoluciones.jsp").forward(request, response);
            break;
            default:
            case "Crear":
                if(lista.size()!=0){
                    devolucion.setId(0);
                    DB.Incidencias(devolucion);
                }
                for(Producto producto: lista){
                    DB.listaDevolucion(producto, DB.IdMax("devoluciones"), proDevolucion.getMotivo());
                }
                lista.clear();
                request.setAttribute("listEnvio", this.listas("envios where tienda="+devolucion.getTienda(), "envios"));
                request.getRequestDispatcher("Ventana_Tienda/Devoluciones.jsp").forward(request, response);
                
                
            break;
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
     public ArrayList  listas(String query, String menu){
            EditarDB db=new EditarDB();
            ArrayList list = new ArrayList();
            list=db.listas(query, menu);
        return list;
        
    }
    public ArrayList estados() {
        ArrayList<EstadoVista> listEstado = new ArrayList<>(Arrays.asList(EstadoVista.values()));
        return listEstado;
    }
}
