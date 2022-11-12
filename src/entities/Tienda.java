package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import exception.ExcepcionProducto;
import exception.ExcepcionVendedor;

public class Tienda {

    private static final Double PORC_COMISION_MINIMO = 0.05;
    private static final Double PORC_COMISION_MAXIMO = 0.10;
    private static final int CANTIDAD_MINIMA_COMISION = 2;

    private List<Producto> listaProductos;
    private List<Vendedor> listaVendedores;

    public Tienda() {
        this.listaProductos = new ArrayList<>();
        this.listaVendedores = new ArrayList<>();
    }

    public void registrarVenta(Producto p, Vendedor vendedor) {
        try {
            vendedor.setCantVentas();
            vendedor.agregarProductosVendidos(p);
        } catch (Exception e) {
            System.out.println("Vendedor o producto, no puede ser Null!");
        }
    }

    public void almacenarProductos(Producto p) throws ExcepcionProducto {
        if (p == null) {
            throw new ExcepcionProducto("Producto no puede ser Null");
        }
        listaProductos.add(p);

    }

    public void agregarVendedor(Vendedor v) throws ExcepcionVendedor{
        if (v == null) {
            throw new ExcepcionVendedor("Vendedor no puede ser Null");
        }
        listaVendedores.add(v);
    }

    // Filtros
    public List<Producto> productosPorCategoria(String a) {
        List<Producto> result = listaProductos.stream()
                .filter(p -> p.getCategoria().toString().equals(a))
                .collect(Collectors.toList());
        return result;
    }

    public void productoPorCodigo(String codigo) {
        try {
            Optional<Producto> result = listaProductos.stream()
                    .filter(c -> c.getCodigo().equals(codigo))
                    .findAny();
            System.out.println(result.orElseThrow());
        } catch (Exception e) {
            System.out.println("Revisar, algun producto quedo null: "+e.getMessage());
        }
    }

    public List<Producto> producosConPrecioMayoresA(double precio) {
        List<Producto> result = listaProductos.stream()
                .filter(p -> p.getPrecio() > precio)
                .collect(Collectors.toList());
        return result;
    }

    public void comisionDeVentasPorVendedor() {
        int cantTotal = 0;
        int i = 0;
        while (i < this.listaVendedores.size()) {
            cantTotal = this.listaVendedores.get(i).getCantVentas();
            if (cantTotal > 0 && cantTotal <= CANTIDAD_MINIMA_COMISION) {
                System.out.println("Comision del vendedor " + this.listaVendedores.get(i).getNombre() + " es: $"
                        + this.listaVendedores.get(i).totalVendido() * PORC_COMISION_MINIMO);
            } else if (cantTotal > CANTIDAD_MINIMA_COMISION) {
                System.out.println("Comision del vendedor " + this.listaVendedores.get(i).getNombre() + " es: $"
                        + this.listaVendedores.get(i).totalVendido() * PORC_COMISION_MAXIMO);
            } else {
                System.out.println("Comision del vendedor " + this.listaVendedores.get(i).getNombre() + " es: $0.00");
            }
            i++;
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public Producto crearProducto(String codigo, String nombre, double precio, Categoria categoria) {
        return new Producto(codigo, nombre, precio, categoria);
    }

    public Vendedor crearVendedor(String codigo, String nombre, double suedo) {
        return new Vendedor(codigo, nombre, suedo);
    }

    public void mostrarProductosPorCategoria(Categoria c) {
        if (c == null) {
            throw new NullPointerException("Categoria no pueden ser null");
        }
        for (Producto p : this.productosPorCategoria(c.toString())) {
            System.out.println(p.toString());
        }
    }

    public void mostrarProductosPorCodigo(String c) throws ExcepcionProducto {
        if (!c.isEmpty()) {
            this.productoPorCodigo(c);
        } else {
            throw new ExcepcionProducto("Para poder filtrar por código debe ingresar un valor");
        }
    }

    public void mostrarProducosConPrecioMayoresA(double precio) {
        for (Producto p : this.producosConPrecioMayoresA(precio)) {
            System.out.println(p.toString());
        }
    }

}
