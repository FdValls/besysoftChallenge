package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void registrarVenta(Producto p, Vendedor vendedor) throws Exception {
        if (p == null) {
            throw new ExcepcionProducto("Producto no puede ser null!");
        }
        if (vendedor == null) {
            throw new ExcepcionVendedor("Vendedor no puede ser null!");
        }
        vendedor.agregarProductosVendidos(p);
    }

    public void almacenarProductos(Producto p) throws ExcepcionProducto {
        if (p == null) {
            throw new ExcepcionProducto("Producto no puede ser Null");
        }
        listaProductos.add(p);

    }

    public void agregarVendedor(Vendedor v) throws ExcepcionVendedor {
        if (v == null) {
            throw new ExcepcionVendedor("Vendedor no puede ser Null");
        }
        listaVendedores.add(v);
    }

    // Filtros
    public List<Producto> productosPorCategoria(String nombreCategoria) {
        List<Producto> result = listaProductos.stream()
                .filter(producto -> producto.getCategoria().getDescripcion().equals(nombreCategoria))
                .collect(Collectors.toList());
        return result;
    }

    public Producto productoPorCodigo(String codigo) {
        Optional<Producto> producto = listaProductos.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst();

        return producto.isPresent() ? producto.get() : null;
    }

    public List<Producto> productosConPrecioMayoresA(double precio) {
        List<Producto> result = listaProductos.stream()
                .filter(p -> p.getPrecio() > precio)
                .collect(Collectors.toList());
        return result;
    }

    public Map<String, Double> comisionDeVentasPorVendedor() {
        int cantTotal = 0;
        Map<String, Double> mapaComisiones = new HashMap<>();
        int i = 0;
        while (i < this.listaVendedores.size()) {
            Vendedor vendedor = this.listaVendedores.get(i);
            double comision = 0;
            cantTotal = vendedor.getCantidadVentas();
            if (cantTotal > 0 && cantTotal <= CANTIDAD_MINIMA_COMISION) {
                comision = vendedor.totalVendido() * PORC_COMISION_MINIMO;
            } else if (cantTotal > CANTIDAD_MINIMA_COMISION) {
                comision = vendedor.totalVendido() * PORC_COMISION_MAXIMO;
            }
            mapaComisiones.put(vendedor.getNombre(), comision);
            i++;
        }
        return mapaComisiones;

    }

    public Producto crearProducto(String codigo, String nombre, double precio, Categoria categoria) {
        return new Producto(codigo, nombre, precio, categoria);
    }

    public Vendedor crearVendedor(String codigo, String nombre, double suedo) {
        return new Vendedor(codigo, nombre, suedo);
    }

    public Vendedor buscarVendedorPorCodigo(String codigo) {
        Optional<Vendedor> vendedor = listaVendedores.stream()
                .filter(v -> v.getCodigo().equals(codigo))
                .findFirst();
        return vendedor.orElse(null);
    }

}
