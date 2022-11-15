import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import entities.Categoria;
import entities.Producto;
import entities.Tienda;
import entities.Vendedor;

public class App {
    public static Scanner txtEntrada = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // Variables producto
        String txtSalirProducto = "";
        String txtCodigoProducto = "";
        String txtNombreProducto = "";
        double doublePrecioProducto = 0;
        // Variables vendedor
        String txtSalirVendedor = "";
        String txtCodigoVendedor = "";
        String txtNombreVendedor = "";
        String txtSueldoVendedor = "";
        double doubleSueldoVendedor = 0;
        // Variables filtro por categoria
        String txtMostrarProductosPorCategoria = "";
        String txtSalirFiltroCaterogia = "";
        /// Variables filtro por código
        String txtMostrarProductosPorCodigo = "";
        // Variables filtro por precio mayor a:
        String txtFiltroMAyorA = "";
        double txtMostrarProductosConPrecioMayoresA = 0;
        // Variables filtro por comision
        String txtMostrarComisionesPorVendedor = "";
        /////////////////////////////
        // Variables registrar ventas
        String txtRegistrarVentas = "";
        String codigoProducto = "";
        String codigoPVendedor = "";
        /////////////////////////////
        Tienda tienda = new Tienda();
        Categoria categoria = null;
        Producto producto;
        Vendedor vendedor;
        boolean salir = false;
        int opcion;

        while (!salir) {

            System.out.println("1. Opcion 1 -> para crear productos: ");
            System.out.println("2. Opcion 2 -> para crear vendedores: ");
            System.out.println("3. Opcion 3 -> para registrar ventas: ");
            System.out.println("4. Opcion 4 -> para filtrar por categoria: ");
            System.out.println("5. Opcion 5 -> para filtrar por codigo: ");
            System.out.println("6. Opcion 6 -> para filtrar por productos mayores a (precio): ");
            System.out.println("7. Opcion 7 -> para mostrar comisiones por vendedor: ");
            System.out.println("8. Salir");

            try {

                System.out.print("Escribe una de las opciones: ");
                opcion = txtEntrada.nextInt();
                txtEntrada.nextLine();

                switch (opcion) {
                    case 1:
                        while (!txtSalirProducto.toLowerCase().equals("no")) {
                            System.out.print("Introduzca código del producto: ");
                            txtCodigoProducto = txtEntrada.nextLine().toLowerCase();
                            validarStringInput(txtCodigoProducto);
                            System.out.print("Introduzca el nombre del producto: ");
                            txtNombreProducto = validarStringInput(txtEntrada.nextLine());
                            System.out.print("Introduzca precio del producto: ");
                            doublePrecioProducto = validarDoubleEntrada(txtEntrada.nextLine());
                            System.out.print("Introduzca la categoria del producto MUJER - INFANTL - HOMBRE : ");
                            categoria = validarCategoria(txtEntrada.nextLine().toLowerCase());
                            tienda.almacenarProductos(
                                    new Producto(txtCodigoProducto, txtNombreProducto, doublePrecioProducto,
                                            categoria));

                            System.out.print("Desea continuar cargando productos - SI - NO: ");
                            txtSalirProducto = txtEntrada.nextLine().toLowerCase();
                        }
                        break;
                    case 2:

                        System.out.println("Agregar vendedores: ");
                        while (!txtSalirVendedor.toLowerCase().equals("no")) {
                            System.out.print("Introduzca código del vendedor: ");
                            txtCodigoVendedor = validarStringInput(txtEntrada.nextLine());
                            System.out.print("Introduzca el nombre del vendedor: ");
                            txtNombreVendedor = validarStringInput(txtEntrada.nextLine());
                            System.out.print("Introduzca sueldo del vendedor: ");
                            txtSueldoVendedor = validarStringInput(txtEntrada.nextLine());
                            doubleSueldoVendedor = validarDoubleEntrada(txtSueldoVendedor);
                            tienda.agregarVendedor(
                                    new Vendedor(txtCodigoVendedor, txtNombreVendedor, doubleSueldoVendedor));

                            System.out.print("Desea continuar cargando vendedores - SI - NO: ");
                            txtSalirVendedor = txtEntrada.nextLine().toLowerCase();
                        }
                        txtSalirVendedor = "";
                        break;
                    case 3:

                        while (!txtRegistrarVentas.toLowerCase().equals("no")) {
                            System.out.println("Introduzca código del producto y codigo del vendedor: ");
                            codigoProducto = txtEntrada.nextLine();
                            codigoPVendedor = txtEntrada.nextLine();
                            producto = tienda.productoPorCodigo(codigoProducto);
                            vendedor = tienda.buscarVendedorPorCodigo(codigoPVendedor);

                            try {
                                tienda.registrarVenta(producto, vendedor);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println("Desea seguir registrando ventas? - SI - NO");
                            txtRegistrarVentas = txtEntrada.nextLine().toLowerCase();
                        }
                        txtRegistrarVentas = "";
                        break;
                    case 4:
                        while (!txtSalirFiltroCaterogia.toLowerCase().equals("no")) {
                            System.out
                                    .print("Para filtrar por categoria escriba una opción: MUJER - INFANTL - HOMBRE: ");
                            txtMostrarProductosPorCategoria = validarStringInput(txtEntrada.nextLine().toLowerCase());
                            if (tienda.productosPorCategoria(txtMostrarProductosPorCategoria).isEmpty()) {
                                System.out.println("No hay elementos para esa categoría");
                            } else {
                                tienda.productosPorCategoria(txtMostrarProductosPorCategoria)
                                        .forEach(p -> System.out.println(p));
                            }
                            System.out
                                    .print("Volver a ver filtro por categoria o volver al menú principal? - SI - NO: ");
                            txtSalirFiltroCaterogia = txtEntrada.nextLine().toLowerCase();
                        }
                        txtSalirFiltroCaterogia = "";
                        break;
                    case 5:
                        while (!txtMostrarProductosPorCodigo.toLowerCase().equals("no")) {
                            System.out.print("Escriba el número de código que desea buscar: ");
                            txtMostrarProductosPorCodigo = validarStringInput(txtEntrada.nextLine().toLowerCase());
                            if (tienda.productoPorCodigo(txtMostrarProductosPorCodigo) == null) {
                                System.out.println("El codigo de producto no existe");
                            } else {
                                System.out.println(tienda.productoPorCodigo(txtMostrarProductosPorCodigo).toString());
                            }
                            System.out
                                    .print("Volver a ver filtro por código o volver al menú principal? - SI - NO: ");
                            txtMostrarProductosPorCodigo = validarStringInput(txtEntrada.nextLine().toLowerCase());
                        }
                        txtMostrarProductosPorCodigo = "";
                        break;
                    case 6:
                        while (!txtFiltroMAyorA.equals("no")) {
                            System.out.print("Escriba un precio de referencia para buscar productos mayores a: ");
                            txtMostrarProductosConPrecioMayoresA = validarDoubleEntrada(
                                    txtEntrada.nextLine().toLowerCase());

                            if (tienda.productosConPrecioMayoresA(txtMostrarProductosConPrecioMayoresA).isEmpty()) {
                                System.out.println("No hay elementos en la lista mayores a ese precio");
                            } else {
                                tienda.productosConPrecioMayoresA(txtMostrarProductosConPrecioMayoresA)
                                        .forEach(p -> System.out.println(p));
                            }
                            System.out.println("Desea seguir filtrando productos ? - SI - NO");
                            txtFiltroMAyorA = txtEntrada.nextLine().toLowerCase();
                        }
                        txtFiltroMAyorA = "";
                        break;
                    case 7:
                        while (!txtMostrarComisionesPorVendedor.equals("no")) {

                            if (tienda.comisionDeVentasPorVendedor().size() == 0) {
                                System.out.println("Ningun vendedor obtuvo comision");
                            } else {
                                tienda.comisionDeVentasPorVendedor();
                                for (Map.Entry<String, Double> e : tienda.comisionDeVentasPorVendedor().entrySet())
                                    System.out.println("Vendedor " + e.getKey() + " "
                                            + "comision de: $" + e.getValue());
                            }

                            System.out.println("Volver a ver las comisiones o volver al menu principal ? - SI - NO");
                            txtMostrarComisionesPorVendedor = txtEntrada.nextLine().toLowerCase();
                        }
                        txtMostrarComisionesPorVendedor = "";
                        break;
                    case 8:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 8");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                txtEntrada.next();
            }
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
                if ((precio.matches("^-?\\d+(?:.\\d+)?$") && precioInicial > 0)) {
                    esPrecioValido = true;
                } else {
                    System.out.print("Introducí un valor numerico distinto y mayor a 0: ");
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
        while ((input.isEmpty() || input.isBlank())) {
            System.out.print("Debe ingresar un valor no vacío: ");
            input = txtEntrada.nextLine().toLowerCase();
        }
        return input;
    }
}
