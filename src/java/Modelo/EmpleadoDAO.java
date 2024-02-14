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
public class EmpleadoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    public Empleado validar(String user, String contra){
        Empleado em = new Empleado();
        String sql = "SELECT * FROM empleado WHERE User = ? AND contra = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, contra);
            rs = ps.executeQuery();
            while(rs.next()){
                em.setId(rs.getInt("IdEmpleado"));
                em.setUser(rs.getString("User"));
                em.setDni(rs.getString("Dni"));
                em.setNom(rs.getString("Nombres"));
                em.setContra(rs.getString("contra"));
            }
        } catch (Exception ex) {
            System.out.println("No se hizo la validación");
        }
        return em;
    }
    //Operaciones CRUD
    public List listar(){
        String sql = "SELECT em.IdEmpleado,em.Dni,em.Nombres,em.Telefono,ese.estado,em.User,em.contra "
                + "FROM empleado em JOIN estado_emp ese on (em.Estado = ese.id_est)";
        List<Empleado> lista = new ArrayList<>(); 
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Empleado em = new Empleado();
                em.setId(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setNom(rs.getString(3));
                em.setTel(rs.getString(4));
                em.setEstado(rs.getString(5));
                em.setUser(rs.getString(6));
                em.setContra(rs.getString(7));
                lista.add(em);
            }
        } catch(Exception ex){
            System.out.println("Error al traer datos"+ex.getMessage());
        }
        return lista;
    }
    public int agregar(Empleado em){
        String sql = "INSERT INTO empleado(Dni,Nombres,Telefono,Estado,User,contra)VALUES(?,?,?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel());
            ps.setInt(4, em.getEstado1());
            ps.setString(5, em.getUser());
            ps.setString(6, em.getContra());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("No se pudo agregar nuevo empleado");
        }
        return r;
    }
    public Empleado listarId(int id){
        Empleado emp = new Empleado();
        String sql = "SELECT em.IdEmpleado,em.Dni,em.Nombres,em.Telefono,em.User,em.contra "
                + "FROM empleado em JOIN estado_emp ese on (em.Estado = ese.id_est) "
                + "WHERE IdEmpleado="+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                emp.setDni(rs.getString(2));
                emp.setNom(rs.getString(3));
                emp.setTel(rs.getString(4));
                emp.setUser(rs.getString(5));
                emp.setContra(rs.getString(6));
            }
        }catch(Exception ex){
            
        } 
        return emp;
    }
    public int actualizar(Empleado em){
        String sql = "UPDATE empleado SET Dni=?,Nombres=?,Telefono=?,Estado=?,User=?,contra=? WHERE IdEmpleado=?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel());
            System.out.println("num: "+em.getEstado1());
            ps.setInt(4, em.getEstado1());
            ps.setString(5, em.getUser());
            ps.setString(6,em.getContra());
            System.out.println(em.getId());
            ps.setInt(7, em.getId());
            ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("No se pudo actualizar "+ex.getMessage());
        }
        return r;
    }
    public void delete(int id){
        String sql = "DELETE FROM empleado WHERE IdEmpleado="+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception ex){
            
        }
    }
}
