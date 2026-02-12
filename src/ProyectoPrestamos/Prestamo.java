package ProyectoPrestamos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private String codigoLibro;
    private String tituloLibro;
    private Usuario socio;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolucionReal=null;


    public Prestamo(String  codigoLibro,  Usuario socio, String tituloLibro, LocalDate fechaPrestamo) throws PrestamoInvalidoException {
        this.socio = socio;
        if (codigoLibro.matches("[A-Z]{3}[0-9]{4}"))this.codigoLibro = codigoLibro;
            else throw new PrestamoInvalidoException("Código de libro erróneo");
        if (!(tituloLibro.isEmpty()) && tituloLibro.matches(".+"))this.tituloLibro = tituloLibro;
            else throw new PrestamoInvalidoException("El libro debe contener un título");
        if (fechaPrestamo!=null && (fechaPrestamo.isBefore(LocalDate.now()) || fechaPrestamo.isEqual(LocalDate.now())))this.fechaPrestamo = fechaPrestamo;
            else throw new PrestamoInvalidoException("Fecha de préstamo inválida");
        this.fechaDevolucionPrevista = fechaPrestamo.plusDays(14);
    }


    public void registrarDevolucion(LocalDate fechaDevolucion) throws PrestamoInvalidoException {
        if  (fechaDevolucion!=null && (fechaDevolucion.isAfter(this.fechaPrestamo) || fechaDevolucion.isEqual(this.fechaPrestamo)))this.fechaDevolucionReal = fechaDevolucion;
        else throw new PrestamoInvalidoException("Fecha de devolución inválida");
    }

    public int calcularDiasRetraso(){
        int noRetraso;
        if(this.fechaDevolucionReal==null) {
            noRetraso = (int)ChronoUnit.DAYS.between(fechaDevolucionPrevista, LocalDate.now());
            if (noRetraso<0){
                return 0;
            }
            else return noRetraso;
        }
        else if ((ChronoUnit.DAYS.between(fechaDevolucionPrevista, this.fechaDevolucionReal))<0) return 0;
        else return (int)(ChronoUnit.DAYS.between(fechaDevolucionPrevista, this.fechaDevolucionReal));
    }
}
