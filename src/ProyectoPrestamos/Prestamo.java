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

    /**
     * Constructor de la clase Prestamo
     * @param codigoLibro String que recoge el código del libro, debe tener un formato de 3 letras mayúsculas seguidas de 4 números
     * @param socio Objeto Usuario que realiza el préstamo
     * @param tituloLibro String que recoge el título del libro, no puede estar vacío
     * @param fechaPrestamo LocalDate indicando la fecha en la que se realiza el préstamo, debe ser hoy o una fecha anterior
     * @throws PrestamoInvalidoException lanza una excepción si el código de libro no sigue el formato, el título está vacío o la fecha es posterior a la actual
     */
    public Prestamo(String  codigoLibro,  Usuario socio, String tituloLibro, LocalDate fechaPrestamo) throws PrestamoInvalidoException {
        this.socio = socio;
        if (codigoLibro.matches("[A-Z]{3}[0-9]{4}"))this.codigoLibro = codigoLibro;
            else throw new PrestamoInvalidoException("Código de libro erróneo");
        if (!(tituloLibro.isEmpty()) && tituloLibro.matches(".+"))this.tituloLibro = tituloLibro;
            else throw new PrestamoInvalidoException("El libro debe contener un título");
        if (fechaPrestamo!=null && (fechaPrestamo.isBefore(LocalDate.now()) || fechaPrestamo.isEqual(LocalDate.now())))this.fechaPrestamo = fechaPrestamo;
            else throw new PrestamoInvalidoException("Fecha de préstamo de inválida, no puede ser posterior al día actual");
        this.fechaDevolucionPrevista = fechaPrestamo.plusDays(14);
    }

    /**
     * Metodo para registrar la fecha real de devolución del libro
     * @param fechaDevolucion LocalDate con la fecha en la que se entrega el libro, debe ser igual o posterior a la fecha del préstamo
     * @throws PrestamoInvalidoException lanza una excepción si la fecha de devolución es nula o anterior a la fecha en que se prestó el libro
     */
    public void registrarDevolucion(LocalDate fechaDevolucion) throws PrestamoInvalidoException {
        if  (fechaDevolucion!=null && (fechaDevolucion.isAfter(this.fechaPrestamo) || fechaDevolucion.isEqual(this.fechaPrestamo)))this.fechaDevolucionReal = fechaDevolucion;
            else throw new PrestamoInvalidoException("Fecha de devolución inválida");
    }

    /**
     * Metodo para calcular cuántos días de retraso lleva el préstamo respecto a la fecha prevista
     * @return int con la cantidad de días de retraso; devuelve 0 si se devolvió a tiempo o si aún no ha vencido el plazo
     */
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

    /**
     * Metodo para comprobar si el préstamo se encuentra actualmente fuera de plazo
     * @return boolean que indica true si el libro ya ha sido devuelto y hubo retraso, o false en caso contrario
     */
    public boolean estaRetrasado(){
           if (this.fechaDevolucionReal!=null) {
               return this.calcularDiasRetraso() > 0;
           }
           else return false;
    }


    public String  getCodigoLibro() {
        return codigoLibro;
    }

    public Usuario getSocio() {
        return socio;
    }

    public  LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public  LocalDate getFechaDevolucionPrevista() {
        return fechaDevolucionPrevista;
    }


    @Override
    public String toString(){
        return "Código libro: " + this.codigoLibro +
                "\n\tTítulo libro: " + this.tituloLibro +
                "\n\tCodigo socio: " + this.socio.getNumeroSocio() +
                "\n\tFecha de préstamo: " + this.fechaPrestamo +
                "\n\tFecha devolución prevista: " + this.fechaDevolucionPrevista +
                "\n\tFecha devolución real: " + (this.fechaDevolucionReal == null ? "No devuelto" : this.fechaDevolucionReal);   }
}
