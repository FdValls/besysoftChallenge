import java.util.Map;
import java.util.Scanner;

import entities.Categoria;
import entities.Producto;
import entities.Tienda;
import entities.Vendedor;
import exception.ExcepcionProducto;

public class App {
    public static Scanner txtEntrada = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        String txtSalirProducto = "";
        String txtCodigoProducto = "";
        String txtNombreProducto = "";
        double doublePrecioProducto = 0;
        Tienda tienda = new Tienda();
        Categoria categoria = null;

        System.out.println("Agregar productos: ");
        while (!txtSalirProducto.toLowerCase().equals("no")) {

            // Valido los input STRING
            System.out.print("Introduzca código del producto: ");
            txtCodigoProducto = validarStringInput(txtEntrada.nextLine());
            System.out.print("Introduzca el nombre del producto: ");
            txtNombreProducto = validarStringInput(txtEntrada.nextLine());
            System.out.print("Introduzca precio del producto: ");
            // Valida el precio
            doublePrecioProducto = validarDoubleEntrada(txtEntrada.nextLine());
            System.out.print("Introduzca la categoria del producto MUJER - INFANTL - HOMBRE : ");
            categoria = validarCategoria(txtEntrada.nextLine().toLowerCase());

            tienda.almacenarProductos(
                    new Producto(txtCodigoProducto, txtNombreProducto, doublePrecioProducto, categoria));

            System.out.print("Desea continuar cargando productos - SI - NO: ");
            txtSalirProducto = txtEntrada.nextLine().toLowerCase();

        }

        String txtSalirVendedor = "";
        String txtCodigoVendedor = "";
        String txtNombreVendedor = "";
        String txtSueldoVendedor = "";
        double doubleSueldoVendedor = 0;

        System.out.println("Agregar vendedores: ");
        while (!txtSalirVendedor.toLowerCase().equals("no")) {
            System.out.print("Introduzca código del vendedor: ");
            txtCodigoVendedor = validarStringInput(txtEntrada.nextLine());
            System.out.print("Introduzca el nombre del vendedor: ");
            txtNombreVendedor = validarStringInput(txtEntrada.nextLine());
            System.out.print("Introduzca sueldo del vendedor: ");
            txtSueldoVendedor = validarStringInput(txtEntrada.nextLine());
            doubleSueldoVendedor = validarDoubleEntrada(txtSueldoVendedor);
            tienda.agregarVendedor(new Vendedor(txtCodigoVendedor, txtNombreVendedor, doubleSueldoVendedor));

            System.out.print("Desea continuar cargando vendedores - SI - NO: ");
            txtSalirVendedor = txtEntrada.nextLine().toLowerCase();
        }

        String txtRegistrarVentas = "";
        String codigoProducto = "";
        String codigoPVendedor = "";

        System.out.println("Registrar Ventas: ");
        while (!txtRegistrarVentas.toLowerCase().equals("no")) {
            System.out.println("Introduzca código del producto y codigo del vendedor: ");
            codigoProducto = txtEntrada.nextLine();
            codigoPVendedor = txtEntrada.nextLine();
            try {

                if (tienda.productoPorCodigo(codigoProducto) == null) {
                    throw new ExcepcionProducto("Codigo producto inexsitente");
                }
                if (tienda.buscarVendedorPorCodigo(codigoPVendedor) == null) {
                    throw new ExcepcionProducto("Codigo vendedor inexsitente");
                }
            } catch (ExcepcionProducto e) {
                System.out.println(e.getMessage());
            }
            try {
                tienda.registrarVenta(tienda.productoPorCodigo(codigoProducto),
                        tienda.buscarVendedorPorCodigo(codigoPVendedor));
            } catch (ExcepcionProducto e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Desea seguir registrando ventas? - SI - NO");
            txtRegistrarVentas = txtEntrada.nextLine().toLowerCase();
        }

        String txtFiltros = "";
        String txtMostrarProductosPorCategoria = "";
        String txtMostrarProductosPorCodigo = "";
        double txtMostrarProductosConPrecioMayoresA = 0;

        // Filtros
        System.out.println("Mostrar filtros - SI - NO");
        txtFiltros = txtEntrada.nextLine();
        while (!txtFiltros.toLowerCase().equals("no")) {
            System.out.println("PRUEBA DE FILTRO POR CATEGORIA MUJER - INFANTL - HOMBRE: ");
            txtMostrarProductosPorCategoria = validarStringInput(txtEntrada.nextLine().toLowerCase());
            tienda.productosPorCategoria(txtMostrarProductosPorCategoria)
                    .forEach(producto -> System.out.println(producto));

            System.out.println("PRUEBA DE FILTRO POR CODIGO: ");
            txtMostrarProductosPorCodigo = validarStringInput(txtEntrada.nextLine().toLowerCase());
            try {
                System.out.println(tienda.productoPorCodigo(txtMostrarProductosPorCodigo).toString());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("PRUEBA DE FILTRO POR PRECIO MAYO A: ");
            txtMostrarProductosConPrecioMayoresA = validarDoubleEntrada(txtEntrada.nextLine().toLowerCase());
            tienda.productosConPrecioMayoresA(txtMostrarProductosConPrecioMayoresA)
                    .forEach(producto -> System.out.println(producto));

            System.out.println("Desea seguir filtrando productos ? - SI - NO");
            txtFiltros = txtEntrada.nextLine().toLowerCase();

        }

        String txtMostrarComisionesPorVendedor = "";

        System.out.println("Mostrar comision por vendedores? - SI - NO");
        txtMostrarComisionesPorVendedor = txtEntrada.nextLine();
        while (!txtMostrarComisionesPorVendedor.equals("no")) {

            tienda.comisionDeVentasPorVendedor();
            for (Map.Entry<String, Double> e : tienda.comisionDeVentasPorVendedor().entrySet())
                System.out.println(e.getKey() + " "
                        + e.getValue());
            txtMostrarComisionesPorVendedor = txtEntrada.nextLine();

            System.out.println("Desea volver al menu principal ? - SI - NO");
            txtFiltros = txtEntrada.nextLine().toLowerCase();
        }

    }

    private static Categoria validarCategoria(String txtCategoriaProducto) {
        Categoria categoria = null;
        boolean noEsValido = false;
        if (txtCategoriaProducto.equals(Categoria.MUJER.getDescripcion())) {
            noEsValido = true;
            categoria = Categoria.MUJER;
        }
        if (txtCategoriaProducto.equals(Categoria.HOMBRE.getDescripcion())) {
            noEsValido = true;
            categoria = Categoria.HOMBRE;
        }
        if (txtCategoriaProducto.equals(Categoria.INFANTIL.getDescripcion())) {
            noEsValido = true;
            categoria = Categoria.INFANTIL;
        }
        while (!noEsValido || txtCategoriaProducto.isBlank()) {

            System.out.print("Por favor introduzca una categoria del producto correcta, MUJER - INFANTL - HOMBRE : ");
            return validarCategoria(txtEntrada.nextLine().toLowerCase());
        }

        return categoria;

    }

    private static double validarDoubleEntrada(String precio) {
        double precioInicial = 1;
        boolean esPrecioValido = false;
        while (!esPrecioValido) {
            try {
                precioInicial = Double.parseDouble(precio);
                if ((precio.matches("^-?\\d+(?:.\\d+)?$") && precioInicial >= 0)) {
                    esPrecioValido = true;
                } else {
                    System.out.print("Introducí un valor numerico distinto de 0: ");
                    precio = txtEntrada.nextLine();
                }
            } catch (Exception e) {
                System.out.print(
                        "Introducí un valor numerico distinto de 0 o verifica que este colocando '.' en lugar de ',': ");
                precio = txtEntrada.nextLine();
            }
        }
        return precioInicial;

    }

    private static String validarStringInput(String input) {
        while (input.isEmpty() || input.isBlank()) {
            System.out.print("Debe ingresar un valor no vacío: ");
            input = txtEntrada.nextLine().toLowerCase();
        }
        return input;
    }
}
