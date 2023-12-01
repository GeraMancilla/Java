import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Producto {
    String nombre;
    double precio;
    int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
}

public class SistemaDeVentas {

    private static Scanner scanner = new Scanner(System.in);
    private static final List<Producto> inventario = new ArrayList<>();
    private static final List<Producto> carrito = new ArrayList<>();

    private static double totalVentas = 0;

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            procesarOpcion(opcion);
        } while (opcion != 5);

        // Cerrar el scanner
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Agregar productos al inventario");
        System.out.println("2. Agregar productos del inventario a tu carrito");
        System.out.println("3. Mostrar productos del inventario");
        System.out.println("4. Finalizar compra");
        System.out.println("5. Salir");
        System.out.println("Ingrese la opción deseada (1-5): ");
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                agregarProdAlInv();
                break;
            case 2:
                agregarProdAlCarrito();
                break;
            case 3:
                mostrarProdDelInv();
                break;
            case 4:
                finalizarCompra();
                break;
            case 5:
                System.out.println("Saliendo del programa.");
                break;
            default:
                System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 5.");
        }
    }

    private static void agregarProdAlInv() {
        scanner.nextLine(); // Limpiar el buffer
        System.out.println("Ingrese el nombre del producto: ");
        String nomProd = scanner.nextLine();
        System.out.println("Ingrese el precio del producto: ");
        double precioProd = scanner.nextDouble();
        System.out.println("Ingrese la cantidad del producto: ");
        int cantProd = scanner.nextInt();

        Producto producto = new Producto(nomProd, precioProd, cantProd);
        inventario.add(producto);

        System.out.println("Producto agregado al inventario correctamente.");
    }

    private static void agregarProdAlCarrito() {
        mostrarProdDelInv();
        System.out.println("Ingrese el número del producto que desea agregar al carrito (0 para salir): ");
        int seleccionProd = scanner.nextInt();

        if (seleccionProd > 0 && seleccionProd <= inventario.size()) {
            System.out.println("Ingrese la cantidad deseada: ");
            int cantDeseada = scanner.nextInt();

            Producto productoSeleccionado = inventario.get(seleccionProd - 1);

            if (cantDeseada > 0 && cantDeseada <= productoSeleccionado.cantidad) {
                Producto productoEnCarrito = new Producto(productoSeleccionado.nombre,
                        productoSeleccionado.precio, cantDeseada);

                carrito.add(productoEnCarrito);
                totalVentas += productoSeleccionado.precio * cantDeseada;

                // Actualizar la cantidad en el inventario
                productoSeleccionado.cantidad -= cantDeseada;

                System.out.println("Producto agregado al carrito correctamente.");
            } else {
                System.out.println("Cantidad no válida o insuficiente en el inventario.");
            }
        } else if (seleccionProd != 0) {
            System.out.println("Número de producto no válido.");
        }
    }

    private static void mostrarProdDelInv() {
        System.out.println("\n--- Inventario ---");
        System.out.println("Num\tProducto\t\tPrecio\t\tCantidad");
        for (int i = 0; i < inventario.size(); i++) {
            Producto producto = inventario.get(i);
            System.out.println((i + 1) + "\t" + producto.nombre + "\t\t$" +
                    producto.precio + "\t\t" + producto.cantidad);
        }
    }

    private static void finalizarCompra() {
        System.out.println("\n--- Factura Final ---");
        mostrarCarrito();
        System.out.println("\nTotal a pagar: $" + totalVentas);

        System.out.println("Ingrese la cantidad de efectivo con la que cuenta: $");
        double efectivoIngresado = scanner.nextDouble();

        double cambio = efectivoIngresado - totalVentas;
        if (cambio >= 0) {
            System.out.println("Cambio: $" + cambio);
        } else {
            System.out.println("Cantidad insuficiente. Por favor, ingrese una cantidad válida.");
        }

        System.out.println("Gracias por su compra.");

        limpiarCarrito();
    }

    private static void mostrarCarrito() {
        System.out.println("--- Carrito ---");
        System.out.println("Producto\t\tPrecio\t\tCantidad");
        for (Producto producto : carrito) {
            System.out.println(producto.nombre + "\t\t$" + producto.precio + "\t\t" + producto.cantidad);
        }
        System.out.println("----------------------");
    }

    private static void limpiarCarrito() {
        carrito.clear();
        totalVentas = 0;
    }
}
