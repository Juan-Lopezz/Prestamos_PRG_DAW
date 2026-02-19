package ProyectoPrestamos;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class MainPrestamos {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String respuesta = "";
        GestorBiblioteca biblioteca = new GestorBiblioteca();

            do {
                try {
                System.out.println(
                        "===== MENÚ BIBLIOTECA =====\n" +
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
                        String nombre, email, numsoc, fecha;
                        LocalDate fechaRegistro;
                        Usuario usuario;
                        System.out.println("Nombre: ");
                        nombre = in.nextLine();
                        System.out.println("Email: ");
                        email = in.nextLine();
                        System.out.println("Número de socio: ");
                        numsoc = in.nextLine();
                        try {
                            System.out.println("Fecha registro (dd/mm/aaaa): ");
                            fecha = in.nextLine();
                        fechaRegistro = LocalDate.of(Funciones.getAnio(fecha), Funciones.getMes(fecha), Funciones.getDia(fecha));
                        usuario = new Usuario(nombre, email, numsoc, fechaRegistro);
                        biblioteca.registrarUsuario(usuario);
                        }catch(IndexOutOfBoundsException | DateTimeException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Fecha de registro inválida");
                            System.out.println("into para continuar");
                            in.nextLine();
                        }
                        System.out.println("Usuario correctamente registrado");
                        break;
                    case "2":
                        Usuario usuarioPrestamo = null;
                        String codigoLibro;
                        String titulo;
                        String numeroSocio;
                        String fecha1;
                        LocalDate fechaPrestamo;
                        System.out.println("Código libro: ");
                        codigoLibro = in.nextLine();
                        System.out.println("Título: ");
                        titulo = in.nextLine();
                        System.out.println("Número de socio: ");
                        numeroSocio = in.nextLine();
                        try {
                            System.out.println("Fecha prestamo (dd/mm/aaaa): ");
                            fecha1 = in.nextLine();
                            fechaPrestamo = LocalDate.of(Funciones.getAnio(fecha1), Funciones.getMes(fecha1), Funciones.getDia(fecha1));
                            for (int i =0; i<biblioteca.getUsuarios().length; i++){
                               if(biblioteca.getUsuarios()[i] != null){
                                   if(numeroSocio.equals(biblioteca.getUsuarios()[i].getNumeroSocio())){
                                       usuarioPrestamo = biblioteca.getUsuarios()[i];
                                   }
                               }else break;
                            }
                            if (usuarioPrestamo == null){
                                throw new UsuarioInvalidoException("Usuario no encontrado");
                            }
                            biblioteca.realizarPrestamo(codigoLibro, titulo, fechaPrestamo, usuarioPrestamo);
                            System.out.println("Prestamo realizado");
                            System.out.println("Devolución prevista: " + fechaPrestamo.plusDays(14));
                        }catch(IndexOutOfBoundsException | DateTimeException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Fecha de préstamo inválida");
                            System.out.println("into para continuar");
                            in.nextLine();
                        }catch (UsuarioInvalidoException e) {
                            System.out.println(e.getMessage());
                            System.out.println("into para continuar");
                            in.nextLine();
                        }
                        break;
                    case "3":
                        String codigoLibro1;
                        String fecha2;
                        LocalDate fechaDevolucion;
                        System.out.println("Código de libro: ");
                        codigoLibro1 = in.nextLine();
                        try {
                            System.out.println("Fecha de devolución: ");
                            fecha2 = in.nextLine();
                            fechaDevolucion = LocalDate.of(Funciones.getAnio(fecha2), Funciones.getMes(fecha2), Funciones.getDia(fecha2));
                            biblioteca.devolverLibro(codigoLibro1, fechaDevolucion);
                            System.out.println("Devolución realizada");
                            for (int i =0; i<biblioteca.getPrestamos().length; i++){}
                        }catch(IndexOutOfBoundsException | DateTimeException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Fecha de registro inválida");
                            System.out.println("into para continuar");
                            in.nextLine();
                        }
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
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("into para continuar");
                    in.nextLine();
                }
            } while (!respuesta.equals("8"));
    }
}
