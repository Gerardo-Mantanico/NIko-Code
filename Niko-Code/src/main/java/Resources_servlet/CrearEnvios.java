/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Resources_servlet;

import BaseDatos.CrearEnvioDB;
import BaseDatos.EditarDB;
import BaseDatos.GuardarDatosEntrada;
import clases.Envios;
import clases.Producto;
import clases.ProductoEnvio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "CrearEnvios", urlPatterns = {"/CrearEnvios"})
public class CrearEnvios extends HttpServlet {
    EditarDB db=new EditarDB();
    CrearEnvioDB envio =new CrearEnvioDB();
    ArrayList<Producto> lista;
    ArrayList<Producto> listCatalogo;
    String tienda;
    String idPedido;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
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
            case "elegirTienda":
                tienda = request.getParameter("tienda");
                request.setAttribute("listpedio",  envio.listadoPedidos(tienda,Estado.SOLICITADO.name()));
                request.getRequestDispatcher("Vendana_Bodega/CrearEnvio.jsp").forward(request, response);
            break;
            case "busrcarPedido":
                idPedido = request.getParameter("idPedido");
                request.setAttribute("listProductos", lista("catalogue"));
                lista=envio.listadoProducto(idPedido);
                listCatalogo= db.listUsuarioTienda("catalogue");
                for(Producto producto: lista){
                   for(Producto catalogo: listCatalogo){
                     if(producto.getCodigo()==catalogo.getCodigo()){
                         catalogo.setExistencia(catalogo.getExistencia()-producto.getExistencia());
                     }
                   }
                       
                }
                request.setAttribute("estado", "Guardar");
                request.setAttribute("listaProducto", lista);
                request.getRequestDispatcher("Vendana_Bodega/CrearEnvio.jsp").forward(request, response);
                break;
            case "Guardar":
                String cantidad=request.getParameter("cantidad");
                String NameProducto= request.getParameter("producto");
                Producto producto = new Producto();
                producto= (Producto)db.ObtenerObjetos("catalogue where _name ='"+NameProducto+"'", "producto");
                producto.setExistencia(Integer.valueOf(cantidad));
                for(Producto catalogo: listCatalogo){
                    if(catalogo.getNombre().equals(producto.getNombre())){
                        if( producto.getExistencia()<=catalogo.getExistencia() ){
                        producto.setPrecio(producto.getCosto()* producto.getExistencia());
                        lista.add(producto);
                        lista=productoRepedios(lista);
                        catalogo.setExistencia(catalogo.getExistencia()-producto.getExistencia());
                    }
                    else{
                        request.setAttribute("msj", "La cantida de existencia del producto:   " +producto.getNombre()+" es de  "+catalogo.getExistencia());
                    }
                    }
                    
                }
                request.setAttribute("estado", "Guardar");
                request.setAttribute("listProductos", lista("catalogue"));
                request.setAttribute("listaProducto", lista);
                request.getRequestDispatcher("Vendana_Bodega/CrearEnvio.jsp").forward(request, response);
                break;
            case "Eliminar":
                String idProducto = request.getParameter("idProducto");
                try {
                    for (Producto p : lista) {
                        if (p.getCodigo() == Integer.valueOf(idProducto)) {
                            for (Producto catalogo : listCatalogo) {
                                catalogo.setExistencia(catalogo.getExistencia()+p.getExistencia());
                            }
                            lista.remove(p);
                        }
                    }
                } catch (Exception ex) {
                }
                request.setAttribute("estado", "Guardar");
                request.setAttribute("listProductos", lista("catalogue"));
                request.setAttribute("listaProducto", lista);
                request.getRequestDispatcher("Vendana_Bodega/CrearEnvio.jsp").forward(request, response);
                break;
            case "Editar":
                String  idProductos = request.getParameter("idProducto");
                try{
                    for(Producto p: lista){
                         if(p.getCodigo()== Integer.valueOf(idProductos)){
                           ArrayList<Producto> listaEditar = new ArrayList ();
                                   listaEditar.add(p);
                         request.setAttribute("listProductos", listaEditar);
                     }
                 }
                }catch (Exception ex) {
                }
                request.setAttribute("estado", "Modificar");
                request.setAttribute("listaProducto", lista);
                request.getRequestDispatcher("Vendana_Bodega/CrearEnvio.jsp").forward(request, response);
             break;
            case "Modificar":
                String cantidads=request.getParameter("cantidad");
                String NameProductos= request.getParameter("producto");
                Producto productoss = new Producto();
                for (Producto catalogo : listCatalogo) {
                    if (catalogo.getNombre().equals(NameProductos)) {
                        if (Integer.valueOf(cantidads) <= catalogo.getExistencia()) {
                            for (Producto p : lista) {
                                if (p.getNombre().equals(NameProductos)) {
                                    p.setExistencia(Integer.valueOf(cantidads));
                                    p.setPrecio(p.getCosto() * p.getExistencia());
                                }
                            }

                        } else {
                            request.setAttribute("msj", "La cantida de existencia del producto:   " + catalogo.getNombre() + " es de  " + catalogo.getExistencia());
                        }

                    }
                }
                request.setAttribute("estado", "Guardar");
                request.setAttribute("listProductos", lista("catalogue"));
                request.setAttribute("listaProducto", lista);
                request.getRequestDispatcher("Vendana_Bodega/CrearEnvio.jsp").forward(request, response);
            break;
            case "CrearEnvio":
                if (lista.isEmpty() == false) {
                    HttpSession session = request.getSession();
                    int IdUsuario = (int) session.getAttribute("codigoUsuario");
                    GuardarDatosEntrada DB = new GuardarDatosEntrada();
                    Envios crearEnvio = new Envios();
                    crearEnvio.setCodigoUsuario(IdUsuario);
                    crearEnvio.setTienda(Integer.valueOf(tienda));
                    crearEnvio.setEstado(Estado.DESPACHADO.name());
                    crearEnvio.setId(Integer.valueOf(idPedido));
                    // Obtener la fecha actual
                    LocalDate fechaActual = LocalDate.now();
                    crearEnvio.setFechaSalida(Date.valueOf(fechaActual));
                     for(Producto pro: lista){
                       crearEnvio.setTotal(crearEnvio.getTotal()+pro.getPrecio());
                     }
                    DB.Crearenvios(crearEnvio);
                    for(Producto produ: lista){
                         ProductoEnvio envioProducto=new ProductoEnvio();
                         envioProducto.setIdEnvio(Integer.valueOf(idPedido));
                         envioProducto.setCodigo(produ.getCodigo());
                         envioProducto.setCosto(produ.getCosto());
                         envioProducto.setCantidad(produ.getExistencia());
                         envioProducto.setCostoTotal(produ.getPrecio());
                                 
                         DB.listaenvio(envioProducto);
                    }
                    String query=" UPDATE pedido SET estado='"+Estado.ENVIADO.name()+"' WHERE id ="+idPedido;
                     db.editar(query);
                    lista.clear();
                }
                request.getRequestDispatcher("Ventanas?accion=crearEncvio").forward(request, response);
                break;
                
            default:
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    public ArrayList  lista(String query){
            ArrayList list = new ArrayList();
            list=db.listUsuarioTienda(query);
        return list;
         
    }
    public ArrayList productoRepedios(List<Producto> list){
        // Crear un Map para almacenar los objetos y sus sumas
        Map<Integer, Producto> mapProductos = new HashMap<>();
        for (Producto producto : list) {
            Integer id = producto.getCodigo();
            if (mapProductos.containsKey(id)) {
                // Si ya existe el objeto, sumar su valor
                Producto p = mapProductos.get(id);
                p.setExistencia(p.getExistencia()+producto.getExistencia());
                p.setPrecio(p.getPrecio()+producto.getPrecio());
            } else {
                // Si no existe el objeto, añadirlo al Map
                mapProductos.put(id, producto);
            }
        }
        // Crear un nuevo ArrayList a partir de los valores del Map
        List<Producto> productosSumados = new ArrayList<>(mapProductos.values());
        return (ArrayList) productosSumados;
    }
    
}
