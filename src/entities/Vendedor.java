package entities;

import java.util.ArrayList;
import java.util.List;

public class Vendedor {

    private String codigo;
    private String nombre;
    private double sueldo;
    private int cantidadVentas;
    private List<Producto> productosVendidos;

    public Vendedor(String codigo, String nombre, double sueldo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.cantidadVentas = 0;
        this.productosVendidos = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    private void setCantidadVentas() {
        this.cantidadVentas++;
    }

    public void agregarProductosVendidos(Producto p){
        this.setCantidadVentas();
        this.productosVendidos.add(p);
    }

    public double totalVendido(){
        double total = 0;
        for (Producto producto : productosVendidos) {
            total += producto.getPrecio();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Codigo: " + getCodigo() + " " + "Nombre: " + getNombre() + " " + "Sueldo: " + getSueldo() + " "
                + "Cantidad de Ventas: " + getCantidadVentas();
    }

}
