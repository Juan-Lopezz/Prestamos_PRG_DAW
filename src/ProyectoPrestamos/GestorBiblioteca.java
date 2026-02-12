package ProyectoPrestamos;

import java.time.LocalDate;

public class GestorBiblioteca {
    private final static int MAX_USUARIOS =50;
    private final static int MAX_PRESTAMOS =200;
    private Usuario[] usuarios;
    private Prestamo[] prestamos;
    private int numeroUsuarios;
    private int numeroPrestamos;

    public GestorBiblioteca() {
        usuarios = new Usuario[MAX_USUARIOS];
        prestamos = new Prestamo[MAX_PRESTAMOS];
        numeroUsuarios=0;
        numeroPrestamos=0;
    }

    public void registrarUsuario(Usuario usuario) throws UsuarioRepetidoException{
        for (int i=0; i<numeroUsuarios; i++) {
            if (usuarios[i]==usuario) {
                throw new UsuarioRepetidoException("Usuario ya registrado");
            }
            if (usuarios[i].getNumeroSocio().equals(usuario.getNumeroSocio())) {
                throw new UsuarioRepetidoException("Usuario con el mismo número de socio ya registrado");
            }
        }
        usuarios[numeroUsuarios] = usuario;
        numeroUsuarios++;

    }


    public void realizarPrestamo(String codigoLibro, String tituloLibro, LocalDate fechaPrestamo, Usuario usuario)throws UsuarioSancionadoException, LibroNoDisponibleException, PrestamoInvalidoException {
        if (usuario.estaSancionado()){
            throw  new UsuarioSancionadoException("Usuario esta sancionado");
        }
        else {
            for  (int i=0; i<numeroPrestamos; i++) {
                if (prestamos[i].getCodigoLibro().equals(codigoLibro)) {
                    throw  new LibroNoDisponibleException("Libro ya prestado");
                }
            }
            prestamos[numeroPrestamos]= new Prestamo(codigoLibro, usuario, tituloLibro, fechaPrestamo);
            numeroPrestamos++;
        }
    }
}


