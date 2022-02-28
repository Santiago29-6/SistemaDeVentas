/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.ProductoDAO;
import Modelo.Productos;
import Modelo.Venta;
import Modelo.VentaDAO;
import config.GenerarSerie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Santiago
 */
public class Controlador extends HttpServlet {
    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    Cliente clientes = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();
    Productos prod = new Productos();
    ProductoDAO pdao = new ProductoDAO();
    Venta v = new Venta();
    List <Venta> listaVenta = new ArrayList<>();
    Cliente cl = new Cliente();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    
    String numeroserie;
    VentaDAO vdao = new VentaDAO();
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
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        int ide = 0;
        
        
        if(menu.equals("Principal")){
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if(menu.equals("Empleado")){
            switch(accion){
                case "Listar":{
                    List lista = edao.listar();
                    request.setAttribute("empleados", lista);
                    request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                    break;
                }
                case "Agregar":{
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUsuario");
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                }
                case "Editar":{
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edao.listarId(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                }
                case "Actualizar":{
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUsuario");
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    em.setId(ide);
                    edao.actualizar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                }
                case "Delete":{
                    ide = Integer.parseInt(request.getParameter("id"));
                    edao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                }
                default:{
                    throw new AssertionError();
                }
            }
            
        }
        if(menu.equals("Cliente")){
            switch(accion){
                case "Listar":{
                    List lista1 = clienteDAO.listar();
                    request.setAttribute("clientes", lista1);
                    request.getRequestDispatcher("Clientes.jsp").forward(request, response);
                    break;
                }
                case "Agregar":{
                    String dni = request.getParameter("txtDni1");
                    String nom = request.getParameter("txtNombres1");
                    String est = request.getParameter("txtEstado1");
                    String direc = request.getParameter("txtDireccion1");
                    clientes.setDniCliente(dni);
                    clientes.setNomCliente(nom);
                    clientes.setDireccionCliente(direc);
                    clientes.setEstado(est);
                    clienteDAO.agregar(clientes);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                }
                case "Editar":{
                    ide = Integer.parseInt(request.getParameter("id"));
                    Cliente c = new Cliente(ide);
                    request.setAttribute("cliente", c);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                }
                case "Actualizar":{
                    String dni = request.getParameter("txtDni1");
                    String nom = request.getParameter("txtNombres1");
                    String est = request.getParameter("txtEstado1");
                    String direc = request.getParameter("txtDireccion1");
                    clientes.setDniCliente(dni);
                    clientes.setNomCliente(nom);
                    clientes.setEstado(est);
                    clientes.setDireccionCliente(direc);
                    clientes.setIdCliente(ide);
                    clienteDAO.actualizar(clientes);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                }
                case "Delete":{
                    ide = Integer.parseInt(request.getParameter("id"));
                    clienteDAO.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                }
                default:{
                    throw new AssertionError();
                }
            }
            
        }
        if(menu.equals("Producto")){
            switch(accion){
                case "Listar":{
                    List lista2 = pdao.listar();
                    request.setAttribute("productos", lista2);
                    request.getRequestDispatcher("Producto.jsp").forward(request, response);
                    break;
                }
                case "Agregar":{
                    String nom = request.getParameter("txtNombres2");
                    double pre = Double.parseDouble(request.getParameter("txtPrecio"));
                    String est = request.getParameter("txtEstado2");
                    int sto = Integer.parseInt(request.getParameter("txtStock"));
                    prod.setNomProducto(nom);
                    prod.setPrecio(pre);
                    prod.setStock(sto);
                    prod.setEstado(est);
                    pdao.agregar(prod);
                    System.out.println("Si llega");
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                }
                case "Editar":{
                    ide = Integer.parseInt(request.getParameter("id"));
                    Productos p = pdao.listarId(ide);
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                }
                case "Actualizar":{
                    String nom = request.getParameter("txtNombres2");
                    double pre = Double.parseDouble(request.getParameter("txtPrecio"));
                    String est = request.getParameter("txtEstado2");
                    int sto = Integer.parseInt(request.getParameter("txtStock"));
                    prod.setNomProducto(nom);
                    prod.setPrecio(pre);
                    prod.setEstado(est);
                    prod.setStock(sto);
                    prod.setIdProducto(ide);
                    pdao.actualizar(prod);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                }
                case "Delete":{
                    ide = Integer.parseInt(request.getParameter("id"));
                    pdao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                }
                default:{
                    throw new AssertionError();
                }
            }
        }
        if(menu.equals("NuevaVenta")){
            switch(accion){
                case "BuscarCliente":{
                    String dni = request.getParameter("codigocliente");
                    clientes.setDniCliente(dni);
                    cl = clienteDAO.buscar(dni);
                    request.setAttribute("c", cl);
                    request.setAttribute("nserie", numeroserie);
                    break;
                }
                case "BuscarProducto":{
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));
                    prod = pdao.listarId(id);
                    request.setAttribute("c", cl);
                    request.setAttribute("producto",prod);
                    request.setAttribute("lista", listaVenta);
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("nserie", numeroserie);
                    break;
                }
                case "Agregar":{
                    request.setAttribute("c", cl);
                    totalPagar = 0.0;
                    item = item+1;
                    cod = prod.getIdProducto();
                    descripcion = request.getParameter("nomproducto");
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cant = Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio*cant;
                    v = new Venta();
                    v.setItem(item);
                    v.setIdproducto(cod);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cant);
                    v.setSubtotal(subtotal);
                    listaVenta.add(v);
                    for(int i = 0; i < listaVenta.size();i++){
                        totalPagar+=listaVenta.get(i).getSubtotal();
                    }
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", listaVenta);
                    request.setAttribute("nserie", numeroserie); 
                    break;
                }
                case "GenerarVenta":{
                    //Actualización del Stock
                    for (int i = 0; i < listaVenta.size(); i++) {
                        Productos pr = new Productos();
                        int cantidad = listaVenta.get(i).getCantidad();
                        int idproducto = listaVenta.get(i).getIdproducto();
                        ProductoDAO aO = new ProductoDAO();
                        pr = aO.buscar(idproducto);
                        int sac = pr.getStock() - cantidad;
                        aO.ActualizarStock(idproducto, sac);
                    }
                    //Guardar Venta
                    v.setIdcliente(cl.getIdCliente());
                    v.setIdempleado(2);
                    v.setNumserie(numeroserie);
                    v.setFecha("2022-02-27");
                    v.setMonto(totalPagar);
                    v.setEstado("1");
                    vdao.guardarVenta(v);
                    //Guardar Detalle ventas
                    int idv = Integer.parseInt(vdao.IdVentas());
                    System.out.println("idv= "+idv);
                    for(int i = 0; i<listaVenta.size();i++){
                        v = new Venta();
                        v.setId(idv);
                        v.setIdproducto(listaVenta.get(i).getIdempleado());
                        v.setCantidad(listaVenta.get(i).getCantidad());
                        v.setPrecio(listaVenta.get(i).getPrecio());
                        vdao.guardaDetalleVentas(v);
                        
                    }
                    request.setAttribute("nserie", numeroserie);
                    break;
                }
                default:{
                    numeroserie = vdao.GenerarSerie();
                    if(numeroserie == null){
                        numeroserie = "00000001";
                        request.setAttribute("nserie", numeroserie);
                    }else{
                        int incrementar = Integer.parseInt(numeroserie);
                        GenerarSerie gs = new GenerarSerie();
                        numeroserie = gs.NumeroSerie(incrementar);
                        request.setAttribute("nserie", numeroserie);
                    }
                    request.getRequestDispatcher("RegistarVenta.jsp");
                }
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
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
}
