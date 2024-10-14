import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorContactos {
    private final String ARCHIVO = "contactos.txt";

    public void agregarContacto(Contacto contacto) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(contacto.toString());
            bw.newLine();
            System.out.println("Contacto agregado: " + contacto.getNombre());
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public List<Contacto> listarContactos() {
        List<Contacto> contactos = new ArrayList<>();
        try (FileReader fr = new FileReader(ARCHIVO);
             BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    contactos.add(new Contacto(datos[0], datos[1], datos[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return contactos;
    }

    public Contacto buscarContacto(String nombre) {
        for (Contacto contacto : listarContactos()) {
            if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                return contacto;
            }
        }
        System.out.println("Contacto no encontrado.");
        return null;
    }

    public void eliminarContacto(String nombre) {
        List<Contacto> contactos = listarContactos();
        boolean encontrado = contactos.removeIf(c -> c.getNombre().equalsIgnoreCase(nombre));
        if (encontrado) {
            try (FileWriter fw = new FileWriter(ARCHIVO, false);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                for (Contacto contacto : contactos) {
                    bw.write(contacto.toString());
                    bw.newLine();
                }
                System.out.println("Contacto eliminado: " + nombre);
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }
}
