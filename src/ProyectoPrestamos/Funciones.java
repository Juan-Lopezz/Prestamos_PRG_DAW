package ProyectoPrestamos;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Funciones {
    public static int getDia(String fecha) throws DateTimeException {
        if (fecha.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            String[] fechas;
            fechas = fecha.split("/");
            return parseInt(fechas[0]);
        }else throw new DateTimeException("Fecha incorrecta");
    }
    public static int getMes(String fecha) throws DateTimeException {
        if (fecha.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            String[] fechas;
            fechas = fecha.split("/");
            return parseInt(fechas[1]);
        }else throw new DateTimeException("Fecha incorrecta");
    }
    public static int getAnio(String fecha) throws DateTimeException {
        if (fecha.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            String[] fechas;
            fechas = fecha.split("/");
            return parseInt(fechas[2]);
        }else throw new DateTimeException("Fecha incorrecta");
    }
    public static void parada(){
        Scanner in = new Scanner(System.in);
        System.out.println("into para continuar");
        in.nextLine();
    }
    
}
