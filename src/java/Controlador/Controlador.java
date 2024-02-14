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
import Modelo.EstadoDAO;
import Modelo.ProductoDAO;
import Modelo.Productos;
import Modelo.RegistroVenta;
import Modelo.RegistroVentaDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import config.GenerarSerie;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    List<Venta> listaVenta = new ArrayList<>();
    Cliente cl = new Cliente();
    EstadoDAO esdao = new EstadoDAO();
    List<RegistroVenta> listRV = new ArrayList<>();
    RegistroVentaDAO rvdao = new RegistroVentaDAO();
    int item = 1;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    int ide = 0;
    String numeroserie;
    VentaDAO vdao = new VentaDAO();
    RegistroVenta rgven = new RegistroVenta();
    int idemp;
    double total;

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
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
            idemp = Integer.parseInt(request.getParameter("idemp"));
        }
        if (menu.equals("Empleado")) {
            switch (accion) {
                case "Listar": {
                    List lista = edao.listar();
                    List est = esdao.getEstadosEmp();
                    request.setAttribute("empleados", lista);
                    request.setAttribute("estado", est);
                    request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                    break;
                }
                case "Agregar": {
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    int est = Integer.parseInt(request.getParameter("estado"));
                    String user = request.getParameter("txtUsuario");
                    String contra = request.getParameter("txtContra");
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado1(est);
                    em.setUser(user);
                    em.setContra(contra);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                }
                case "Editar": {
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edao.listarId(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                }
                case "Actualizar": {
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    int est = Integer.parseInt(request.getParameter("estado"));
                    String user = request.getParameter("txtUsuario");
                    String contra = request.getParameter("txtContra");
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado1(est);
                    em.setUser(user);
                    em.setContra(contra);
                    em.setId(ide);
                    edao.actualizar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                }
                case "Delete": {
                    ide = Integer.parseInt(request.getParameter("id"));
                    edao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                }
                default: {
                    throw new AssertionError();
                }
            }
        }
        if (menu.equals("Cliente")) {
            switch (accion) {
                case "Listar": {
                    List lista1 = clienteDAO.listar();
                    request.setAttribute("clientes", lista1);
                    request.getRequestDispatcher("Clientes.jsp").forward(request, response);
                    break;
                }
                case "Agregar": {
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
                case "Editar": {
                    ide = Integer.parseInt(request.getParameter("id"));
                    Cliente c = clienteDAO.listarId(ide);
                    request.setAttribute("cliente", c);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                }
                case "Actualizar": {
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
                case "Delete": {
                    ide = Integer.parseInt(request.getParameter("id"));
                    clienteDAO.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                }
                default: {
                    throw new AssertionError();
                }
            }

        }
        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar": {
                    List lista2 = pdao.listar();
                    request.setAttribute("productos", lista2);
                    request.getRequestDispatcher("Producto.jsp").forward(request, response);
                    break;
                }
                case "Agregar": {
                    String nom = request.getParameter("txtNombres2");
                    double pre = Double.parseDouble(request.getParameter("txtPrecio"));
                    String est = request.getParameter("txtEstado2");
                    int sto = Integer.parseInt(request.getParameter("txtStock"));
                    prod.setNomProducto(nom);
                    prod.setPrecio(pre);
                    prod.setStock(sto);
                    prod.setEstado(est);
                    pdao.agregar(prod);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                }
                case "Editar": {
                    ide = Integer.parseInt(request.getParameter("id"));
                    Productos p = pdao.listarId(ide);
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                }
                case "Actualizar": {
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
                case "Delete": {
                    ide = Integer.parseInt(request.getParameter("id"));
                    pdao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                }
                default: {
                    throw new AssertionError();
                }
            }
        }
        if (menu.equals("NuevaVenta")) {
            switch (accion) {
                case "BuscarCliente": {
                    String dni = request.getParameter("codigocliente");
                    clientes.setDniCliente(dni);
                    cl = clienteDAO.buscar(dni);
                    request.setAttribute("c", cl);
                    request.setAttribute("nserie", numeroserie);
                    break;
                }
                case "BuscarProducto": {
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));
                    prod = pdao.listarId(id);
                    request.setAttribute("c", cl);
                    request.setAttribute("producto", prod);
                    request.setAttribute("lista", listaVenta);
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("nserie", numeroserie);
                    break;
                }
                case "Agregar": {
                    request.setAttribute("c", cl);
                    totalPagar = 0.0;
                    item = (listaVenta.size() > 0) ? listaVenta.size() + 1 : 1;
                    cod = prod.getIdProducto();
                    descripcion = prod.getNomProducto();
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cant = Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio * cant;
                    if (listaVenta.isEmpty()) {
                        v = new Venta();
                        v.setItem(item);
                        v.setIdproducto(cod);
                        v.setDescripcionP(descripcion);
                        v.setPrecio(precio);
                        v.setCantidad(cant);
                        v.setSubtotal(subtotal);
                        listaVenta.add(v);
                    } else {
                        int j = 100000;
                        for (int i = 0; i < listaVenta.size(); i++) {
                            j = (cod == listaVenta.get(i).getIdproducto()) ? i : 100000;
                        }
                        if (j != 100000) {
                            listaVenta.get(j).setCantidad(listaVenta.get(j).getCantidad() + cant);
                            listaVenta.get(j).setSubtotal(listaVenta.get(j).getSubtotal() + subtotal);
                        } else {
                            v = new Venta();
                            v.setItem(item);
                            v.setIdproducto(cod);
                            v.setDescripcionP(descripcion);
                            v.setPrecio(precio);
                            v.setCantidad(cant);
                            v.setSubtotal(subtotal);
                            listaVenta.add(v);
                        }
                    }
                    for (int i = 0; i < listaVenta.size(); i++) {
                        totalPagar += listaVenta.get(i).getSubtotal();
                    }
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", listaVenta);
                    request.setAttribute("nserie", numeroserie);
                    break;
                }
                case "Cancelar": {
                    listaVenta.clear();
                    totalPagar = 0;
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", listaVenta);
                    request.setAttribute("nserie", numeroserie);
                    break;
                }
                case "Editar": {
                    int idp = Integer.parseInt(request.getParameter("id"));
                    System.out.println(idp);
                    prod = pdao.listarId(idp);
                    for (int i = 0; i < listaVenta.size(); i++) {
                        totalPagar += listaVenta.get(i).getSubtotal();
                    }
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("producto", prod);
                    request.setAttribute("lista", listaVenta);
                    request.setAttribute("nserie", numeroserie);
                    break;
                }
                case "Actualizar": {
                    cant = Integer.parseInt(request.getParameter("cant"));
                    precio = Double.parseDouble(request.getParameter("precio"));
                    int j = 100000;
                    for (int i = 0; i < listaVenta.size(); i++) {
                        j = (cod == listaVenta.get(i).getIdproducto()) ? i : 100000;
                    }
                    if (j != 100000) {
                        listaVenta.get(j).setCantidad(cant);
                        listaVenta.get(j).setSubtotal(cant * precio);
                    }
                    totalPagar = 0.0;
                    for (int i = 0; i < listaVenta.size(); i++) {
                        totalPagar += listaVenta.get(i).getSubtotal();
                    }
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", listaVenta);
                    request.setAttribute("nserie", numeroserie);
                    break;
                }
                case "Delete": {
                    int idn = Integer.parseInt(request.getParameter("id"));
                    listaVenta.remove(idn - 1);
                    totalPagar = 0.0;
                    for (int i = 0; i < listaVenta.size(); i++) {
                        totalPagar += listaVenta.get(i).getSubtotal();
                        listaVenta.get(i).setItem(i + 1);
                    }
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", listaVenta);
                    request.setAttribute("nserie", numeroserie);
                    break;
                }
                case "GenerarVenta": {
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
                    System.out.println("hola: " + idemp);
                    v.setIdempleado(idemp);
                    v.setNumserie(numeroserie);
                    LocalDate localDate = LocalDate.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String fechaFormateada = localDate.format(formato);
                    v.setFecha(fechaFormateada);
                    v.setMonto(totalPagar);
                    v.setEstado("1");
                    vdao.guardarVenta(v);
                    //Guardar Detalle ventas
                    int idv = Integer.parseInt(vdao.IdVentas());
                    for (int i = 0; i < listaVenta.size(); i++) {
                        v = new Venta();
                        v.setId(idv);
                        v.setIdproducto(listaVenta.get(i).getIdproducto());
                        v.setCantidad(listaVenta.get(i).getCantidad());
                        v.setPrecio(listaVenta.get(i).getPrecio());
                        vdao.guardaDetalleVentas(v);
                    }
                    numeroserie = vdao.GenerarSerie();
                    request.setAttribute("nserie", numeroserie);
                    break;
                }
                default: {
                    numeroserie = vdao.GenerarSerie();
                    if (numeroserie == null) {
                        numeroserie = "00000001";
                        request.setAttribute("nserie", numeroserie);
                    } else {
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
        if (menu.equals("RegistroVenta")) {
            switch (accion) {
                case "Listar": {
                    listRV = rvdao.traerDatos();
                    total = 0;
                    for (int i = 0; i < listRV.size(); i++) {
                        total += listRV.get(i).getTotal();
                    }
                    request.setAttribute("registros", listRV);
                    request.setAttribute("total", total);
                    request.getRequestDispatcher("RegistroVenta.jsp").forward(request, response);
                }
                case "Descargar": {
                    Document documento = new Document();
                    int idven = Integer.parseInt(request.getParameter("idven"));
                    rgven = rvdao.traerDatosConId(idven);
                    try {
                        String ruta = System.getProperty("user.home");
                        PdfWriter.getInstance(documento, new FileOutputStream(ruta + 
                                "/Downloads/Reporte_venta_" + rgven.getIdVenta() + ".pdf"));
                        documento.open();
                        Font f = new Font();
                        f.setFamily(FontFamily.TIMES_ROMAN.name());
                        f.setStyle(Font.ITALIC);
                        f.setSize(15);
                        f.setColor(BaseColor.RED);
                        Paragraph paragraph = new Paragraph();
                        paragraph.setFont(f);        
                        paragraph.add("REPORTE DE LA VENTA " + rgven.getIdVenta() + "\n\n");

                        paragraph.setAlignment(Element.ALIGN_CENTER);

                        documento.add(paragraph);
                        paragraph.clear();
                        f.setSize(10);
                        f.setColor(BaseColor.BLACK);
                        f.setFamily(FontFamily.HELVETICA.name());
                        paragraph.setFont(f);
                        paragraph.add("En este documento solo se extrae la información de la venta solicitada, "
                                + "si desea todas la ventas en el mismo sistema se encuentra un boton que descagará"
                                + " todos las ventas en un solo documento.\n\n");
                        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                        documento.add(paragraph);
                        PdfPTable table = new PdfPTable(6);
                        table.addCell("Id Venta");
                        table.addCell("Fecha");
                        table.addCell("Número de serie");
                        table.addCell("Cliente");
                        table.addCell("Empleado");
                        table.addCell("Total de venta");
                        table.addCell("" + rgven.getIdVenta());
                        table.addCell("" + rgven.getFecha());
                        table.addCell("" + rgven.getnSerie());
                        table.addCell("" + rgven.getnCliente());
                        table.addCell("" + rgven.getnEmpleado());
                        table.addCell("$ " + rgven.getTotal());
                        documento.add(table);
                        documento.close();
                    } catch (Exception e) {
                        System.out.println("No se pudo descargar el documento");
                    }
                    request.setAttribute("registros", listRV);
                    request.setAttribute("total", total);
                    request.getRequestDispatcher("RegistroVenta.jsp").forward(request, response);
                }
            }
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
