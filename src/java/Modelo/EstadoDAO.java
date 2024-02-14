/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Santiago
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.Conexion;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public List getEstadosEmp() {
        ArrayList<Estado> lista = new ArrayList<>();
        String sql = "Select * FROM estado_emp";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Estado estado = new Estado();
                estado.setId(rs.getInt(1));
                estado.setEstado(rs.getString(2));
                lista.add(estado);
            }
        } catch (Exception ex) {
            System.out.println("Error al traer los datos" + ex.getMessage());
        }
        return lista;
    }
}
