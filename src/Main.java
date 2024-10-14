import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorContactos gestor = new GestorContactos();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Listar contactos");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    gestor.agregarContacto(new Contacto(nombre, telefono, email));
                    break;
                case 2:
                    System.out.println("\nLista de contactos:");
                    for (Contacto c : gestor.listarContactos()) {
                        System.out.println(c.getNombre() + " - " + c.getTelefono() + " - " + c.getEmail());
                    }
                    break;
                case 3:
                    System.out.print("Nombre del contacto a buscar: ");
                    nombre = scanner.nextLine();
                    Contacto encontrado = gestor.buscarContacto(nombre);
                    if (encontrado != null) {
                        System.out.println("Contacto encontrado: " + encontrado.getNombre() +
                                " - " + encontrado.getTelefono() + " - " + encontrado.getEmail());
                    }
                    break;
                case 4:
                    System.out.print("Nombre del contacto a eliminar: ");
                    nombre = scanner.nextLine();
                    gestor.eliminarContacto(nombre);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}

