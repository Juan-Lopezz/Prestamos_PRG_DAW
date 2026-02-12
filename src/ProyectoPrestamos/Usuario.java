package ProyectoPrestamos;

import java.time.LocalDate;

public class Usuario {
    private String nombre;
    private String email;
    private String numeroSocio;
    private LocalDate fechaRegistro;
    private boolean sancionado;
    private LocalDate fechaFinScancion;


    /**
     * Constructor de la clase Usuario
     *  @param nombre String que recoge el nombre del Usuario
     * @param email String que recoge el email del usuario, debe contener'@' y '.'
     * @param numeroSocio String que indica un codigo de socio en formato 'SOC+5 digitos'
     * @param fechaRegistro LocalDate indicando la fecha en que se registró el Usuario
     * @throws UsuarioInvalidoException lanza un UsuarioInvalidoException cuando cualquiera de los datos que le metemos no cumplen los parametros indicados
     * */
    public Usuario (String nombre, String email, String numeroSocio, LocalDate fechaRegistro) throws  UsuarioInvalidoException{
        this.nombre = nombre;
        if (email.matches(".+@.+\\..+"))this.email = email;
        else throw new UsuarioInvalidoException("Email incorrecto, debe contener '@' y '.'");
        if (numeroSocio.matches("SOC[0-9]{5}"))this.numeroSocio = numeroSocio;
        else throw new UsuarioInvalidoException("Número de Socio incorrecto, debe contener 'SOC' seguido de 5 dígitos");
        this.fechaRegistro = fechaRegistro;
        this.sancionado = false;
        this.fechaFinScancion = null;
    }


    /**
     *Metodo para sancionar a los usuarios
     * @param diasSancion int con la cantidad de dias de la sancion
     * @param fechaInicioSancion LocalDate con la fecha de cuando ha iniciado la sancion
     */
    public void sancionar(int diasSancion, LocalDate fechaInicioSancion){
        LocalDate fechaFinSanc =  LocalDate.now().plusDays(diasSancion);
        this.sancionado = true;
        this.fechaFinScancion = fechaFinSanc;
    }


    /**
     * Metodo usado para quitar la sancion a los Usuarios
     * Cambia el estado de sancionado a false y quita la fecha de fin de sancion
      */
    public void levantarSancion(){
        this.sancionado = false;
        this.fechaFinScancion = null;
    }


    /**
     * Metodo usado para saber si un Usuario esta sancionado
     * Devuelve true si lo esta y false si no lo esta
     */
    public boolean estaSancionado(){
        return this.sancionado;
    }


    public String getNumeroSocio() {
        return numeroSocio;
    }


    @Override
    public String toString(){
        return "Nombre: " + this.nombre  + "\n\tEmail: " + this.email + "\n\tNúmero de socio: " + this.numeroSocio + "\n\tFecha de registro: " + this.fechaRegistro + "\n\tEstado de sanción: " + (sancionado==true?"Sancionado," + " Fin de sanción: " + this.fechaFinScancion:"No Sancionado");
    }
}
