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
public class RegistroVenta {
    int idVenta;
    String fecha;
    String nSerie;
    String nCliente;
    String nEmpleado;
    Double total;

    public RegistroVenta() {
    }

    public RegistroVenta(int idVenta, String fecha, String nSerie, String nCliente, String nEmpleado, Double total) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.nSerie = nSerie;
        this.nCliente = nCliente;
        this.nEmpleado = nEmpleado;
        this.total = total;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getnSerie() {
        return nSerie;
    }

    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public String getnCliente() {
        return nCliente;
    }

    public void setnCliente(String nCliente) {
        this.nCliente = nCliente;
    }

    public String getnEmpleado() {
        return nEmpleado;
    }

    public void setnEmpleado(String nEmpleado) {
        this.nEmpleado = nEmpleado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
}
