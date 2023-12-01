import java.util.Scanner;

public class SistemaDeVentas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Arreglos para almacenar información de productos en el inventario
        String[] inventarioProductos = new String[100];
        double[] inventarioPrecios = new double[100];
        int[] inventarioCantidades = new int[100];
        int cantidadProductosEnInventario = 0;

        // Arreglos para el carrito del usuario
        String[] carritoProductos = new String[100];
        double[] carritoPrecios = new double[100];
        int[] carritoCantidades = new int[100];
        int cantidadProductosEnCarrito = 0;

        // Variables para la factura actual
        double totalVentas = 0;

        // Menú principal
        int opcion;
        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Agregar productos al inventario");
            System.out.println("2. Agregar productos del inventario a tu carrito");
            System.out.println("3. Mostrar productos del inventario");
            System.out.println("4. Finalizar compra");
            System.out.println("5. Salir");
            System.out.println("Ingrese la opción deseada (1-5): ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Agregar productos al inventario
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.println("Ingrese el nombre del producto: ");
                    String nombreProducto = scanner.nextLine();
                    System.out.println("Ingrese el precio del producto: ");
                    double precioProducto = scanner.nextDouble();
                    System.out.println("Ingrese la cantidad del producto: ");
                    int cantidadProducto = scanner.nextInt();

                    // Almacenar el producto en el inventario
                    inventarioProductos[cantidadProductosEnInventario] = nombreProducto;
                    inventarioPrecios[cantidadProductosEnInventario] = precioProducto;
                    inventarioCantidades[cantidadProductosEnInventario] = cantidadProducto;
                    cantidadProductosEnInventario++;

                    System.out.println("Producto agregado al inventario correctamente.");
                    break;

                case 2:
                    // Agregar productos del inventario al carrito
                    mostrarInventario(inventarioProductos, inventarioPrecios, inventarioCantidades, cantidadProductosEnInventario);
                    System.out.println("Ingrese el número del producto que desea agregar al carrito (0 para salir): ");
                    int seleccionProducto = scanner.nextInt();

                    if (seleccionProducto > 0 && seleccionProducto <= cantidadProductosEnInventario) {
                        System.out.println("Ingrese la cantidad deseada: ");
                        int cantidadDeseada = scanner.nextInt();

                        // Verificar la disponibilidad en el inventario
                        if (cantidadDeseada > 0 && cantidadDeseada <= inventarioCantidades[seleccionProducto - 1]) {
                            // Agregar producto al carrito
                            carritoProductos[cantidadProductosEnCarrito] = inventarioProductos[seleccionProducto - 1];
                            carritoPrecios[cantidadProductosEnCarrito] = inventarioPrecios[seleccionProducto - 1];
                            carritoCantidades[cantidadProductosEnCarrito] = cantidadDeseada;
                            cantidadProductosEnCarrito++;

                            // Actualizar la cantidad en el inventario
                            inventarioCantidades[seleccionProducto - 1] -= cantidadDeseada;

                            // Actualizar el total de ventas
                            totalVentas += inventarioPrecios[seleccionProducto - 1] * cantidadDeseada;

                            System.out.println("Producto agregado al carrito correctamente.");
                        } else {
                            System.out.println("Cantidad no válida o insuficiente en el inventario.");
                        }
                    } else if (seleccionProducto != 0) {
                        System.out.println("Número de producto no válido.");
                    }
                    break;

                case 3:
                    // Mostrar productos del inventario
                    mostrarInventario(inventarioProductos, inventarioPrecios, inventarioCantidades, cantidadProductosEnInventario);
                    break;

                case 4:
                    // Finalizar compra
                    System.out.println("\n--- Factura Final ---");
                    mostrarFactura(carritoProductos, carritoPrecios, carritoCantidades, cantidadProductosEnCarrito);
                    System.out.println("\nTotal a pagar: $" + totalVentas);

                    // Pedir al usuario ingresar la cantidad de efectivo
                    System.out.println("Ingrese la cantidad de efectivo con la que cuenta: $");
                    double efectivoIngresado = scanner.nextDouble();

                    // Calcular y mostrar el cambio
                    double cambio = efectivoIngresado - totalVentas;
                    if (cambio >= 0) {
                        System.out.println("Cambio: $" + cambio);
                    } else {
                        System.out.println("Cantidad insuficiente. Por favor, ingrese una cantidad válida.");
                    }

                    System.out.println("Gracias por su compra.");

                    // Limpiar el carrito y reiniciar el total de ventas
                    cantidadProductosEnCarrito = 0;
                    totalVentas = 0;
                    break;

                case 5:
                    // Salir
                    System.out.println("Saliendo del programa.");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 5.");
            }

        } while (opcion != 5);

        // Cerrar el scanner
        scanner.close();
    }

    // Función para mostrar el inventario de productos
    public static void mostrarInventario(String[] productos, double[] precios, int[] cantidades, int cantidadProductos) {
        System.out.println("\n--- Inventario ---");
        System.out.println("Num\tProducto\t\tPrecio\t\tCantidad");
        for (int i = 0; i < cantidadProductos; i++) {
            System.out.println((i + 1) + "\t" + productos[i] + "\t\t$" + precios[i] + "\t\t" + cantidades[i]);
        }
    }

    // Función para mostrar la factura actual
    public static void mostrarFactura(String[] productos, double[] precios, int[] cantidades, int cantidadProductos) {
        System.out.println("--- Carrito ---");
        System.out.println("Producto\t\tPrecio\t\tCantidad");
        for (int i = 0; i < cantidadProductos; i++) {
            System.out.println(productos[i] + "\t\t$" + precios[i] + "\t\t" + cantidades[i]);
        }
        System.out.println("----------------------");
    }
}
