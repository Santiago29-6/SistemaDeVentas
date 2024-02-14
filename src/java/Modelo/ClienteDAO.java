
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santiago
 */
public class ClienteDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Cliente buscar(String dni){
        Cliente c = new Cliente();
        String sql = "SELECT * FROM cliente WHERE Dni="+dni;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                c.setIdCliente(rs.getInt(1));
                c.setDniCliente(rs.getString(2));
                c.setNomCliente(rs.getString(3));
                c.setDireccionCliente(rs.getString(4));
                c.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("No se busco de manera correcta");
        }
        return c;
    }
    //Operaciones CRUD
    
    public List listar(){
        String sql = "SELECT * FROM cliente";
        List<Cliente> lista1 = new ArrayList<>(); 
        int i=0;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cl = new Cliente();
                cl.setIdCliente(rs.getInt(1));
                cl.setDniCliente(rs.getString(2));
                cl.setNomCliente(rs.getString(3));
                cl.setDireccionCliente(rs.getString(4));
                cl.setEstado(rs.getString(5));
                lista1.add(cl);
            }
        } catch(Exception ex){
            System.out.println("Error al traer datos "+ex.getMessage());
        }
        return lista1;
    }
    public int agregar(Cliente cl){
        String sql = "INSERT INTO cliente(Dni,Nombres,Direccion,Estado)VALUES(?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDniCliente());
            ps.setString(2, cl.getNomCliente());
            ps.setString(3, cl.getDireccionCliente());
            ps.setString(4, cl.getEstado());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("No se pudo agregar nuevo cliente"+ex.getMessage());
        }
        return r;
    }
    public Cliente listarId(int id){
        Cliente cli = new Cliente();
        String sql = "SELECT * FROM cliente WHERE IdCliente="+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                cli.setDniCliente(rs.getString(2));
                cli.setNomCliente(rs.getString(3));
                cli.setDireccionCliente(rs.getString(4));
                cli.setEstado(rs.getString(5));
            }
        }catch(Exception ex){
            System.out.println("No se pudo listar id "+ex.getMessage());
        } 
        return cli;
    }
    public int actualizar(Cliente cl){
        String sql = "UPDATE cliente SET Dni=?,Nombres=?,Direccion=?,Estado=? WHERE IdCliente=?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDniCliente());
            ps.setString(2, cl.getNomCliente());
            ps.setString(3, cl.getDireccionCliente());
            ps.setString(4, cl.getEstado());    
            ps.setInt(5, cl.getIdCliente());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("No se pudo actualizar "+ex.getMessage());
        }
        return r;
    }
    public void delete(int id){
        String sql = "DELETE FROM cliente WHERE IdCliente="+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception ex){
            
        }
    }
}
