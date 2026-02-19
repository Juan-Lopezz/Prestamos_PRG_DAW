package ProyectoPrestamos;

import java.time.LocalDate;

import static java.lang.Integer.parseInt;

public class Funciones {
    public static int getDia(String fecha) {
        String[] fechas;
        fechas = fecha.split("/");
        return parseInt(fechas[0]);
    }
    public static int getMes(String fecha) {
        String[] fechas;
        fechas = fecha.split("/");
        return parseInt(fechas[1]);
    }
    public static int getAnio(String fecha) {
        String[] fechas;
        fechas = fecha.split("/");
        return parseInt(fechas[2]);
    }
    
}
