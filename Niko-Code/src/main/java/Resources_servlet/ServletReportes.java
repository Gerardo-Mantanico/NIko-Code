/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Resources_servlet;

import BaseDatos.EditarDB;
import clases.Envios;
import clases.Producto;
import clases.Reporte;
import clases.Tienda;
import clases.UsuarioSupervisor;
import clases.UsuarioTienda;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import resources.ConexionBase;
import resources.Estado;

/**
 *
 * @author HP
 */
@WebServlet(name = "ServletReportes", urlPatterns = {"/ServletReportes"})
public class ServletReportes extends HttpServlet {
    ConexionBase con= new ConexionBase();
    Statement stamente;
    ResultSet r;
    ArrayList<Reporte> list= new ArrayList();
    ArrayList listObject= new ArrayList();
    String fecha;
    String query;
    EditarDB DB = new EditarDB();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletReportes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletReportes at " + request.getContextPath() + "</h1>");
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
        list.clear();
        listObject.clear();
        String menu = request.getParameter("button");
        try {
            
            switch (menu) {
                case "reporteTienda":
                    fecha = request.getParameter("date");
                    query = "SELECT tienda, COUNT(*) as Total  FROM pedido where fecha<='" + Date.valueOf(fecha) + "' GROUP BY tienda order by  Total desc";
                    stamente = con.conexion().createStatement();
                    r = stamente.executeQuery(query);
                    while (r.next()) {
                        Reporte reporte = new Reporte();
                        reporte.setCampo(r.getInt("tienda"));
                        reporte.setCantidad(r.getInt(2));
                        list.add(reporte);
                    }
                    for (Reporte tienda : list) {
                        String queryTienda = "select* from store where _code=" + tienda.getCampo();
                        r = stamente.executeQuery(queryTienda);
                        while (r.next()) {
                            Tienda nuevaTienda = new Tienda();
                            nuevaTienda.setCodigo(r.getInt(1));
                            nuevaTienda.setDireccion(r.getString(2));
                            nuevaTienda.setTipo(r.getString(3));
                            listObject.add(nuevaTienda);
                        }
                    }
                    r.close();
                    request.setAttribute("listaTienda", listObject);
                    request.getRequestDispatcher("Venta_Administrativa/Reportes.jsp").forward(request, response);
                    break;
                case "reporteBodega":
                    fecha = request.getParameter("date");
                    query = "SELECT codigo_usuario,  COUNT(*) as Total  FROM envios  where fechaSalida<='" + fecha + "'  GROUP BY codigo_usuario  order by  Total desc";
                    stamente = con.conexion().createStatement();
                    r = stamente.executeQuery(query);
                    while (r.next()) {
                        Reporte reporte = new Reporte();
                        reporte.setCampo(r.getInt(1));
                        reporte.setCantidad(r.getInt(2));
                        list.add(reporte);
                    }
                    for (Reporte usuario : list) {
                        String queryTienda = "select* from warehouse where _code=" + usuario.getCampo();
                        r = stamente.executeQuery(queryTienda);
                        while (r.next()) {
                            UsuarioSupervisor userBodega = new UsuarioSupervisor();
                            userBodega.setCodigo(r.getInt(1));
                            userBodega.setNombre(r.getString(2));
                            userBodega.setNombreUsuario(r.getString(3));
                            String valor = String.valueOf(usuario.getCantidad());
                            userBodega.setEmail(valor);
                            listObject.add(userBodega);
                        }
                    }
                    r.close();
                    request.setAttribute("lista", listObject);
                    request.getRequestDispatcher("Venta_Administrativa/Reportes.jsp").forward(request, response);
                    break;

                case "reporteUsuario":
                    fecha = request.getParameter("date");
                    query = "SELECT codigo_usuario,  COUNT(*) as Total  FROM pedido  where fecha<='" + fecha + "'  GROUP BY codigo_usuario  order by  Total desc";
                    stamente = con.conexion().createStatement();
                    r = stamente.executeQuery(query);
                    while (r.next()) {
                        Reporte reporte = new Reporte();
                        reporte.setCampo(r.getInt(1));
                        reporte.setCantidad(r.getInt(2));
                        list.add(reporte);
                    }
                    for (int i = 0; i < 4; i++) {
                        String queryTienda = "select* from user_store where _code=" + list.get(i).getCampo();
                        r = stamente.executeQuery(queryTienda);
                        while (r.next()) {
                            UsuarioTienda userTienda = new UsuarioTienda();
                            userTienda.setCodigo(r.getInt(1));
                            userTienda.setNombre(r.getString(2));
                            userTienda.setNombreUsuario(r.getString(4));
                            String valor = String.valueOf(list.get(i).getCantidad());
                            userTienda.setEmail(valor);
                            listObject.add(userTienda);
                        }
                    }
                    r.close();
                    request.setAttribute("listas", listObject);
                    request.getRequestDispatcher("Venta_Administrativa/Reportes.jsp").forward(request, response);
                    break;
                case "ReporteProducto":
                    fecha = request.getParameter("date");
                    query = "select codigo,   COUNT(*) as Total  FROM pedidos_productos  join pedido   on pedido.id=pedidos_productos.id_pedido  where pedido.fecha<='" + fecha + "'  GROUP BY codigo order by  Total desc";
                    stamente = con.conexion().createStatement();
                    r = stamente.executeQuery(query);
                    while (r.next()) {
                        Reporte reporte = new Reporte();
                        reporte.setCampo(r.getInt(1));
                        reporte.setCantidad(r.getInt(2));
                        list.add(reporte);
                    }
                    for (Reporte repo : list) {
                        String queryTienda = "select* from catalogue where _code=" + repo.getCampo();
                        r = stamente.executeQuery(queryTienda);
                        while (r.next()) {
                            Producto producto = new Producto();
                            producto.setCodigo(r.getInt(1));
                            producto.setNombre(r.getString(2));
                            producto.setExistencia(repo.getCantidad());
                            listObject.add(producto);
                        }
                    }
                    r.close();
                    request.setAttribute("list", listObject);
                    request.getRequestDispatcher("Venta_Administrativa/Reportes.jsp").forward(request, response);
                    break;

                case "reporteBodegaEnvios":
                    String tienda=request.getParameter("tienda");
                    fecha = request.getParameter("date");
                    query = "select* from envios where tienda="+tienda+" and  fechaSalida<='"+fecha+"'";
                    String query2 ="incidencias where tienda="+tienda+" and  fecha<='"+fecha+"' and estado='"+Estado.SOLUCIONADA.name()+"'";
                    String query3 ="devoluciones where tienda="+tienda+" and  fecha<='"+fecha+"'";
                    stamente = con.conexion().createStatement();
                    r = stamente.executeQuery(query);
                    while (r.next()) {
                       Envios envio = new Envios();
                        envio.setId(r.getInt(2));
                        envio.setTienda(r.getInt(3));
                        envio.setCodigoUsuario(r.getInt(4));
                        envio.setFechaSalida(r.getDate(5));
                        envio.setTotal(r.getDouble(7));
                        listObject.add(envio);
                    }
                    r.close();
                    request.setAttribute("listDevolucion", DB.listas(query3,"devolucion"));
                    request.setAttribute("listIncidecia", DB.listas(query2,"Incidencia"));
                    request.setAttribute("listEnvio", listObject);
                    request.getRequestDispatcher("Ventanas?accion=reporteBodega").forward(request, response);
                    break;

                default:

            }
            
            } catch (SQLException ex) {
                Logger.getLogger(ServletReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
              catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletReportes.class.getName()).log(Level.SEVERE, null, ex);
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

}
