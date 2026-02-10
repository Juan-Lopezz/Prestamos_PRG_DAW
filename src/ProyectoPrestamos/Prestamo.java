package ProyectoPrestamos;

import java.time.LocalDate;

public class Prestamo {
    private String codigoLibro;
    private String tituloLibro;
    private Usuario socio;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolucionReal;


    public Prestamo(String  codigoLibro,  Usuario socio, String tituloLibro, LocalDate fechaPrestamo) throws PrestamoInvalidoException {
        if (codigoLibro.matches("[A-Z]{3}[0-9]{4}"))this.codigoLibro = codigoLibro;
            else throw new PrestamoInvalidoException("Código de libro erróneo");
        if (!(tituloLibro.isEmpty()) || tituloLibro.matches(".+"))this.tituloLibro = tituloLibro;
            else throw new PrestamoInvalidoException("El libro debe contener un título");
        if (fechaPrestamo!=null && (fechaPrestamo.isBefore(LocalDate.now())|| fechaPrestamo.isEqual(LocalDate.now())))this.fechaPrestamo = fechaPrestamo;
            else throw new PrestamoInvalidoException("Fecha de préstamo inválida");
    }
}
