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
public class ProductoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Productos buscar(int id){
        Productos p = new Productos();
        String sql = "SELECT * FROM producto WHERE IdProducto = "+id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                p.setIdProducto(rs.getInt(1));
                p.setNomProducto(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("No se pudo buscar el producto"+e.getMessage());
        }
        return p;
    }
    
    public int ActualizarStock(int id, int stock){
        String sql = "UPDATE producto SET Stock=? WHERE IdProducto=?";
        try {
            con =cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("No se pudo actulizar el stock: "+e.getMessage());
        }
        return r;
    }
    
    //Operaciones CRUD
    public List listar(){
        String sql = "SELECT * FROM producto";
        List<Productos> lista1 = new ArrayList<>(); 
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Productos pr = new Productos();
                pr.setIdProducto(rs.getInt(1));
                pr.setNomProducto(rs.getString(2));
                pr.setPrecio(rs.getDouble(3));
                pr.setStock(rs.getInt(4));
                pr.setEstado(rs.getString(5));
                lista1.add(pr);
            }
        } catch(Exception ex){
            System.out.println("Error al traer datos "+ex.getMessage());
        }
        return lista1;
    }
    public int agregar(Productos pr){
        String sql = "INSERT INTO producto(Nombres,Precio,Stock,Estado)VALUES(?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getNomProducto());
            ps.setDouble(2, pr.getPrecio());
            ps.setInt(3, pr.getStock());
            ps.setString(4, pr.getEstado());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("No se pudo agregar nuevo producto"+ex.getMessage());
        }
        return r;
    }
    public Productos listarId(int id){
        Productos pr = new Productos();
        String sql = "SELECT * FROM producto WHERE IdProducto="+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                pr.setIdProducto(id);
                pr.setNomProducto(rs.getString(2));
                pr.setPrecio(rs.getDouble(3));
                pr.setStock(rs.getInt(4));
                pr.setEstado(rs.getString(5));
            }
        }catch(Exception ex){
            System.out.println("No se pudo listar id "+ex.getMessage());
        } 
        return pr;
    }
    public int actualizar(Productos pr){
        String sql = "UPDATE producto SET Nombres=?,Precio=?,Stock=?,Estado=? WHERE IdProducto=?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getNomProducto());
            ps.setDouble(2, pr.getPrecio());
            ps.setInt(3, pr.getStock());
            ps.setString(4, pr.getEstado());
            ps.setInt(5, pr.getIdProducto());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("No se pudo actualizar "+ex.getMessage());
        }
        return r;
    }
    public void delete(int id){
        String sql = "DELETE FROM producto WHERE IdProducto="+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception ex){
            
        }
    }
}
