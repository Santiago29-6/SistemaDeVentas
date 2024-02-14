/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Santiago
 */
public class VentaDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    public String GenerarSerie(){
        String numeroserie = "";
        String sql = "SELECT max(numeroSerie) FROM ventas";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                numeroserie = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("No se pudo generar numero de serie"+e.getMessage());
        }
        return numeroserie;
    }
    public String IdVentas(){
        String idventas = "";
        String sql = "SELECT MAX(IdVentas) FROM ventas";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                idventas = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("No se pudo traer el id "+e.getMessage());
        }
        return idventas;
    }
    public int guardarVenta(Venta ve){
        String sql ="INSERT INTO ventas(IdCliente,IdEmpleado,NumeroSerie,FechaVentas,Monto,Estado)VALUES(?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ve.getIdcliente());
            ps.setInt(2, ve.getIdempleado());
            ps.setString(3, ve.getNumserie());
            ps.setString(4, ve.getFecha());
            ps.setDouble(5, ve.getMonto());
            ps.setString(6, ve.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("No se pudo guardar la venta "+e.getMessage());
        }
        return r;
    }
    public int guardaDetalleVentas(Venta ve){
        String sql = "INSERT INTO detalle_ventas(IdVentas,IdProducto,Cantidad,PrecioVenta)VALUES(?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ve.getId());
            ps.setInt(2, ve.getIdproducto());
            ps.setInt(3, ve.getCantidad());
            ps.setDouble(4, ve.getPrecio());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("No se pudo guardar el detalle de venta: "+e.getMessage());
        }
        return r;
    }

}
