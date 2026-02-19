package ProyectoPrestamos;

import java.sql.SQLOutput;
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
                            System.out.println("Fecha de registro inválida");
                            Funciones.parada();
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
                               }else break; // parada para que deje de mirar en el resto del array porque a partir de que encuentra un null el resto están a null
                            }
                            if (usuarioPrestamo == null){
                                throw new UsuarioInvalidoException("Usuario no encontrado");
                            }
                            biblioteca.realizarPrestamo(codigoLibro, titulo, fechaPrestamo, usuarioPrestamo);
                            System.out.println("Prestamo realizado");
                            System.out.println("Devolución prevista: " + fechaPrestamo.plusDays(14));
                        }catch(IndexOutOfBoundsException | DateTimeException e) {
                            System.out.println("Fecha de préstamo inválida");
                            Funciones.parada();
                        }catch (UsuarioInvalidoException e) {
                            System.out.println(e.getMessage());
                            Funciones.parada();
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
                            for (int i =0; i<biblioteca.getPrestamos().length; i++){
                                if (biblioteca.getPrestamos()[i] != null){
                                    if (biblioteca.getPrestamos()[i].getCodigoLibro().equals(codigoLibro1)) {
                                        System.out.println("Devolución realizada con " + biblioteca.getPrestamos()[i].calcularDiasRetraso() + " días de retraso");
                                        System.out.println("Usuario sancionado con " + biblioteca.getPrestamos()[i].calcularDiasRetraso() + " días");
                                    }
                                }else break; // parada para que deje de mirar en el resto del array porque a partir de que encuentra un null el resto están a null
                            }
                        }catch(IndexOutOfBoundsException | DateTimeException e) {
                            System.out.println("Fecha de devolución inválida");
                            Funciones.parada();
                        }
                        break;
                    case "4":
                        String numeroSocio1;
                        System.out.println("Número de socio: ");
                        numeroSocio1 = in.nextLine();
                        for (int i =0; i<biblioteca.getUsuarios().length; i++){
                            if  (biblioteca.getUsuarios()[i] != null){
                                if (biblioteca.getUsuarios()[i].getNumeroSocio().equals(numeroSocio1)){
                                    System.out.println(biblioteca.getUsuarios()[i].estaSancionado() ? "Está sancionado" : "No está sancionado");
                                }
                            }else break; // parada para que deje de mirar en el resto del array porque a partir de que encuentra un null el resto están a null
                        }
                        Funciones.parada();
                        break;
                    case "5":
                        Prestamo[] prestamos;
                        prestamos = biblioteca.getPrestamos();
                        for (int i =0; i<prestamos.length; i++){
                            if (prestamos[i] != null){
                                if (prestamos[i].getFechaDevolucionReal()!=null) {
                                    System.out.println(prestamos[i].toString());
                                }
                            }else break; // parada para que deje de mirar en el resto del array porque a partir de que encuentra un null el resto están a null
                        }
                        Funciones.parada();
                        break;
                    case "6":
                        System.out.println("Sancionados: ");
                        for (int i =0; i<biblioteca.getUsuarios().length; i++) {
                            if (biblioteca.getUsuarios()[i] != null) {
                                if (biblioteca.getUsuarios()[i].estaSancionado()){
                                    System.out.println(biblioteca.getUsuarios()[i].toString());
                                }
                            }
                        }
                        Funciones.parada();
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
                    /*System.out.println("Error inesperado \n Código de error:");*/
                    System.out.println(e.getMessage());
                    Funciones.parada();
                }
            } while (!respuesta.equals("8"));
    }
}
