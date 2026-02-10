package ProyectoPrestamos;

import java.time.LocalDate;

public class Usuario {
    private String nombre;
    private String email;
    private String numeroSocio;
    private LocalDate fechaRegistro;
    private boolean sancionado;
    private LocalDate fechaFinScancion;



    /*constructor para pruebas*/
    public Usuario (String nombre, String email, String numeroSocio, LocalDate fechaRegistro, boolean sancionado, LocalDate fechaFinScancion) throws  UsuarioInvalidoException{
        this.nombre = nombre;
        if (email.matches(".+@.+\\..+"))this.email = email;
            else throw new UsuarioInvalidoException("Email incorrecto, debe contener '@' y '.'");
        if (numeroSocio.matches("SOC[0-9]{5}"))this.numeroSocio = numeroSocio;
            else throw new UsuarioInvalidoException("Número de Socio incorrecto, debe contener 'SOC' seguido de 5 dígitos");
        this.fechaRegistro = fechaRegistro;
        this.sancionado = sancionado;
        if (sancionado == false) this.fechaFinScancion = null;
            else if(sancionado == true && fechaFinScancion == null) throw new  UsuarioInvalidoException("Si el usuario está sancionado debe tener una fecha de sanción") ;
            else this.fechaFinScancion = fechaFinScancion;

    }
    /**
     * Constructor de la clase Usuario
     *  @param nombre String que recoge el nombre del usuario
     * @param email String que recoge el email del usuario, debe contener'@' y '.'
     * @param numeroSocio String que indica un codigo de socio en formato 'SOC+5 digitos'
     * @param fechaRegistro LocalDate indicando la fecha en que se registró el usuario
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

    public void sancionar(int diasSancion, LocalDate fechaInicioSancion){
        LocalDate fechaFinSanc =  LocalDate.now().plusDays(diasSancion);
        this.sancionado = true;
        this.fechaFinScancion = fechaFinSanc;
    }
}
