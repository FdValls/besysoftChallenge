package entities;

public class Producto {

    private String codigo;
    private String nombre;
    private double precio;
    private Categoria categoria;

    public Producto(String codigo, String nombre, double precio, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Codigo: " + getCodigo() + " " + "Nombre: " + getNombre() + " " + "Precio: " + getPrecio() + " "
                + "Categoria: " + getCategoria();
    }

}
