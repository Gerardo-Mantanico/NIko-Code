/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Resources_servlet;

import BaseDatos.EditarDB;
import BaseDatos.GuardarDatosEntrada;
import clases.Devoluciones;
import clases.Incidencia;
import clases.ListaId;
import clases.Producto;
import clases.ProductoDevolucion;
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
    ArrayList<ProductoDevolucion> lista = new ArrayList<ProductoDevolucion>();
    EditarDB base =new EditarDB();
    GuardarDatosEntrada DB= new GuardarDatosEntrada();
    Devoluciones devolucion= new Devoluciones();
    String tienda;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {           
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu= request.getParameter("button");
        switch (menu) {
            case  "buscar":
               lista.clear();
               devolucion.setTotal(0);
                String fecha= request.getParameter("fecha");
                String IdEnvio=request.getParameter("idEnvio");
                tienda=request.getParameter("tienda");
                devolucion.setId(Integer.valueOf(IdEnvio));
                devolucion.setTienda(Integer.valueOf(tienda));
                devolucion.setFecha(   Date.valueOf(fecha));
                devolucion.setEstado(Estado.ACTIVA.name());
                devolucion.setCodigoUsuario(Integer.valueOf(request.getParameter("codigoUsuario")));
                String query="envios_productos where id_pedido="+IdEnvio;
                list=base.listas(query, "productosEnvio");
                request.setAttribute("listaProductos", list);
               //request.setAttribute("listEnvio", this.listas("envios where tienda="+tienda, "envios"));
                request.setAttribute("estado", this.estados());
                request.setAttribute("fecha", fecha);
                request.getRequestDispatcher("Ventana_Tienda/Devoluciones.jsp").forward(request, response);
            break;
            case "Agregar":
                String cantidad= request.getParameter("cantidad");
                String codigoProducto= request.getParameter("productoCodigo");
                String estado=request.getParameter("estado");
                String q="envios_productos where codigo="+codigoProducto+" and id_pedido="+devolucion.getId();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCodigo() == Integer.valueOf(codigoProducto)) {
                        list.remove(i);}
                }
                ProductoDevolucion proDevolucion = (ProductoDevolucion) base.ObtenerObjetos(q, "ProductosDevoluciones");
                proDevolucion.setMotivo(estado);
                if(proDevolucion.getCantidad()>Integer.valueOf(cantidad)){
                    proDevolucion.setCantidad(Integer.valueOf(cantidad));
                }
                else if(proDevolucion.getCantidad()<Integer.valueOf(cantidad)){
                   request.setAttribute("mensaje", "La maxima cantidad que se puede agregar es "+proDevolucion.getCantidad());
                }
                proDevolucion.setCostoTotal(proDevolucion.getCantidad()*proDevolucion.getCosto());
                proDevolucion.setIdDevolucion(devolucion.getId());
                lista.add(proDevolucion);
                devolucion.setTotal(devolucion.getTotal()+proDevolucion.getCostoTotal());
                request.setAttribute("listaProductos", list);
                request.setAttribute("fecha",  devolucion.getFecha().toString());
                request.setAttribute("estado", this.estados());
                request.setAttribute("productosEnvio",lista);
                request.setAttribute("total", (String.valueOf(devolucion.getTotal())));
                request.getRequestDispatcher("Ventana_Tienda/Devoluciones.jsp").forward(request, response);
            break;
            default:
            case "Crear":
           
                if (lista.size() != 0) {
                    DB.crearDevoluciones(devolucion);
                }
                for (ProductoDevolucion producto : lista) {
                    DB.listaDevolucion(producto);
                }
                lista.clear();
                query = "envios where tienda=" + tienda + " and estado='" + Estado.RECIBIDO + "'";
                ArrayList<ListaId> li;
                ArrayList<String> nueva = new ArrayList();
                li = base.IdDevoluciones("select e.id, e.estado, e.tienda from envios e left  join devoluciones d on e.id=d.id where d.id is null");
                System.out.println(tienda + "porque es nulo esata miarda");
                for (ListaId listaid : li) {
                    if (listaid.getTienda() == Integer.valueOf(tienda)) {
                        if (listaid.getEstad().equals(Estado.RECIBIDO.name())) {
                            nueva.add(String.valueOf(listaid.getId()));
                        }
                    }
                }
                request.setAttribute("listEnvio", nueva);
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
