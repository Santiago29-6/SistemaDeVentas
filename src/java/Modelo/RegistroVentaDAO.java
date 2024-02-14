/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.Conexion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santiago
 */
public class RegistroVentaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public List traerDatos() {
        List<RegistroVenta> lista = new ArrayList<>();
        String sql = "SELECT ve.IdVentas, ve.FechaVentas, ve.NumeroSerie, cl.Nombres, em.Nombres, ve.Monto "
                + "FROM cliente as cl JOIN ventas as ve ON (cl.IdCliente = ve.IdCliente) "
                + "JOIN empleado as em ON (em.IdEmpleado = ve.IdEmpleado);";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                RegistroVenta r = new RegistroVenta();
                r.setIdVenta(rs.getInt(1));
                r.setFecha(rs.getString(2));
                r.setnSerie(rs.getString(3));
                r.setnCliente(rs.getString(4));
                r.setnEmpleado(rs.getString(5));
                r.setTotal(rs.getDouble(6));
                lista.add(r);
            }
        } catch (Exception ex) {
            System.out.println("No se puedo taer los datos: " + ex.getMessage());
        }
        return lista;
    }

    public RegistroVenta traerDatosConId(int idven) {
        String sql = "SELECT ve.IdVentas, ve.FechaVentas, ve.NumeroSerie, cl.Nombres, em.Nombres, ve.Monto "
                + "FROM cliente as cl JOIN ventas as ve ON (cl.IdCliente = ve.IdCliente) "
                + "JOIN empleado as em ON (em.IdEmpleado = ve.IdEmpleado)"
                + "WHERE ve.IdVentas = " + idven + ";";
        RegistroVenta r = new RegistroVenta();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                r.setIdVenta(rs.getInt(1));
                r.setFecha(rs.getString(2));
                r.setnSerie(rs.getString(3));
                r.setnCliente(rs.getString(4));
                r.setnEmpleado(rs.getString(5));
                r.setTotal(rs.getDouble(6));
            }
        } catch (Exception ex) {
            System.out.println("No se puedo taer los datos: " + ex.getMessage());
        }
        return r;
    }

}
