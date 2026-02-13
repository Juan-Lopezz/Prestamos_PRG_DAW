package ProyectoPrestamos;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class MainPrestamos {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String respuesta;
        GestorBiblioteca biblioteca = new GestorBiblioteca();
        do{
            System.out.println(
                    "===== MENÚ BIBLIOTECA =====" +
                            "1. Registrar nuevo usuario\n" +
                            "2. Realizar préstamo de libro\n" +
                            "3. Devolver libro\n" +
                            "4. Consultar estado de usuario\n" +
                            "5. Mostrar préstamos activos\n" +
                            "6. Mostrar usuarios sancionados\n" +
                            "7. Actualizar sanciones\n" +
                            "8. Salir\n" +
                            "Seleccione una opción: ");
            respuesta = in.nextLine().trim();// trim para quitarlos espacios y uso un String para la respuesta para que si el usuario mete algo que no sean números en la respuesta no salte una excepción y se vaya directamente al caso default del switch que es opción no válida
            switch (respuesta) {
                case "1":
                    System.out.println("Registrar nuevo usuario");
                    break;
                case "2":
                    System.out.println("Realizar préstamo de libro");
                    break;
                case "3":
                    System.out.println("Devolver libro");
                    break;
                case "4":
                    System.out.println("Consultar estado de usuario");
                    break;
                case "5":
                    System.out.println("Mostrar préstamos activos");
                    break;
                case "6":
                    System.out.println("Mostrar usuarios sancionados");
                    break;
                case "7":
                    System.out.println("Actualizar sanciones");
                    break;
                case "8":
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }while (!respuesta.equals("8"));
    }
}
