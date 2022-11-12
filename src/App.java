import entities.Categoria;
import entities.Producto;
import entities.Tienda;
import entities.Vendedor;
import exception.ExcepcionProducto;
import exception.ExcepcionVendedor;

public class App {
    public static void main(String[] args) throws Exception {

        // Creo la tienda
        Tienda t = new Tienda();
        // Creo productos
        Producto p1 = t.crearProducto("Prod01", "Remera", 1250, Categoria.INFANTIL);
        Producto p2 = t.crearProducto("Prod02", "Pantalon", 2100, Categoria.INFANTIL);
        Producto p3 = t.crearProducto("Prod03", "Vestido Largo", 2000, Categoria.MUJER);
        Producto p4 = t.crearProducto("Prod04", "Zapatos", 15000, Categoria.MUJER);
        Producto p5 = t.crearProducto("Prod05", "Chomba", 12450, Categoria.HOMBRE);
        Producto p6 = t.crearProducto("Prod06", "Buso", 19300, Categoria.HOMBRE);
        Producto p7 = t.crearProducto("Prod07", "Zapatillas", 22000, Categoria.HOMBRE);
        // Agrego productos
        try {
            t.almacenarProductos(p1);
            t.almacenarProductos(p2);
            t.almacenarProductos(p3);
            t.almacenarProductos(p4);
            t.almacenarProductos(p5);
            t.almacenarProductos(p6);
            t.almacenarProductos(p7);
        } catch (ExcepcionProducto e) {
            System.out.println("Mensaje: " + e.getMessage());
        }
        // Creo vendedores
        Vendedor v1 = t.crearVendedor("Vend01", "Fernando", 75000);
        Vendedor v2 = t.crearVendedor("Vend02", "Marcelo", 80000);
        Vendedor v3 = t.crearVendedor("Vend03", "Alejandro", 60000);
        Vendedor v4 = t.crearVendedor("Vend04", "Jorge", 60000);
        Vendedor v5 = t.crearVendedor("Vend05", "Guillermo", 60000);
        Vendedor v6 = t.crearVendedor("Vend06", "Roberto", 70000);
        Vendedor v7 = t.crearVendedor("Vend07", "Pablo", 77000);
        // Agrego vendedores
        try {
            t.agregarVendedor(v1);
            t.agregarVendedor(v2);
            t.agregarVendedor(v3);
            t.agregarVendedor(v4);
            t.agregarVendedor(v5);
            t.agregarVendedor(v6);
            t.agregarVendedor(v7);
        } catch (ExcepcionVendedor e) {
            System.out.println("Mensaje: "+e.getMessage());
        }
        // Registro ventas, las mismas incrementan las ventas de los vendedores
        t.registrarVenta(p1, v1);
        t.registrarVenta(p2, v1);
        t.registrarVenta(p3, v1);
        t.registrarVenta(p4, v2);
        t.registrarVenta(p5, v3);
        t.registrarVenta(p6, v3);
        t.registrarVenta(p7, v4);
        // Muestro Filtros
        System.out.println("********FILTROS**********");
        System.out.println("Categoria:");
        try {
            t.mostrarProductosPorCategoria(null);
            t.mostrarProductosPorCategoria(Categoria.HOMBRE);
        } catch (Exception e) {
            System.out.println("Mensaje: "+e.getMessage());
        }
        try {
            t.mostrarProductosPorCodigo("Prod07");
            t.mostrarProductosPorCodigo("");

        } catch (ExcepcionProducto e) {
            System.out.println("Mensaje: " + e.getMessage());
        }
        System.out.println("Precio mayor a:");
        t.mostrarProducosConPrecioMayoresA(10000);
        System.out.println("********CALCULAR COMISION POR CADA VENDEDOR**********");
        t.comisionDeVentasPorVendedor();
    }

}
