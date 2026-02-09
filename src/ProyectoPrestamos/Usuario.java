package ProyectoPrestamos;

import java.time.LocalDate;

public class Usuario {
    private String nombre;
    private String email;
    private String numeroSocio;
    private LocalDate fechaRegistro;
    private boolean sancionado;
    private LocalDate fechaScancion;
    public Usuario (String nombre, String email, String numeroSocio, LocalDate fechaRegistro, boolean sancionado, LocalDate fechaScancion) throws UsuarioRepetidoException, UsuarioInvalidoException{
        this.nombre = nombre;
        if (email.matches(".+@.+\\..+"))this.email = email;
            else throw new UsuarioInvalidoException("Email incorrecto, debe contener '@' y '.'");
        if (numeroSocio.matches("SOC[0-9]{5}"))this.numeroSocio = numeroSocio;
            else throw new UsuarioInvalidoException("Número de Socio incorrecto, debe contener 'SOC' seguido de 5 dígitos");
        this.fechaRegistro = fechaRegistro;
        this.sancionado = sancionado;
        if (sancionado == false) this.fechaScancion = null;
            else if(sancionado == true && fechaScancion == null) throw new  UsuarioRepetidoException("Si el usuario está sancionado debe tener una fecha de sanción") ;
            else this.fechaScancion = fechaScancion;

    }
}
