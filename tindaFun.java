import java.util.Scanner;

public class SistemaDeVentas {

    private static Scanner scanner = new Scanner(System.in);

    private static String[] invProductos = new String[100];
    private static double[] invPrecios = new double[100];
    private static int[] invCantidades = new int[100];
    private static int cantProdEnInv = 0;

    private static String[] carritoProd = new String[100];
    private static double[] carritoPrecios = new double[100];
    private static int[] carritoCant = new int[100];
    private static int cantProdEnCarrito = 0;

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

        invProductos[cantProdEnInv] = nomProd;
        invPrecios[cantProdEnInv] = precioProd;
        invCantidades[cantProdEnInv] = cantProd;
        cantProdEnInv++;

        System.out.println("Producto agregado al inventario correctamente.");
    }

    private static void agregarProdAlCarrito() {
        mostrarProdDelInv();
        System.out.println("Ingrese el número del producto que desea agregar al carrito (0 para salir): ");
        int seleccionProd = scanner.nextInt();

        if (seleccionProd > 0 && seleccionProd <= cantProdEnInv) {
            System.out.println("Ingrese la cantidad deseada: ");
            int cantDeseada = scanner.nextInt();

            if (cantDeseada > 0 && cantDeseada <= invCantidades[seleccionProd - 1]) {
                carritoProd[cantProdEnCarrito] = invProductos[seleccionProd - 1];
                carritoPrecios[cantProdEnCarrito] = invPrecios[seleccionProd - 1];
                carritoCant[cantProdEnCarrito] = cantDeseada;
                cantProdEnCarrito++;

                invCantidades[seleccionProd - 1] -= cantDeseada;
                totalVentas += invPrecios[seleccionProd - 1] * cantDeseada;

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
        for (int i = 0; i < cantProdEnInv; i++) {
            System.out.println((i + 1) + "\t" + invProductos[i] + "\t\t$" +
                    invPrecios[i] + "\t\t" + invCantidades[i]);
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
        for (int i = 0; i < cantProdEnCarrito; i++) {
            System.out.println(carritoProd[i] + "\t\t$" + carritoPrecios[i] + "\t\t" + carritoCant[i]);
        }
        System.out.println("----------------------");
    }

    private static void limpiarCarrito() {
        cantProdEnCarrito = 0;
        totalVentas = 0;
    }
}
