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


    public Prestamo realizarPrestamo(String codigoLibro, String tituloLibro, LocalDate fechaPrestamo, Usuario usuario)throws UsuarioSancionadoException, LibroNoDisponibleException, PrestamoInvalidoException {
        int validadorDeEstado=0;
        for  (int i=0; i<numeroUsuarios; i++) {
            if(usuarios[i]==usuario) {
                validadorDeEstado=1;
                break; // break funcional para que si encuentra el usuario antes de realizar la búsqueda entera en el array ya no busque más
            }
        }
        if (validadorDeEstado==0){
            throw  new PrestamoInvalidoException("Usuario no registrado");
        }
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
            return  prestamos[numeroPrestamos-1];// restamos uno porque en la línea de arriba ya pasamos a la siguiente posición del array porque después del return no se puede poner
        }
    }


    public boolean devolverLibro(String codigoLibro, LocalDate fechaDevolucion)throws PrestamoInvalidoException{
        for  (int i=0; i<numeroPrestamos; i++) {
            if (prestamos[i].getCodigoLibro().equals(codigoLibro)) {
                prestamos[i].registrarDevolucion(fechaDevolucion);
                if (prestamos[i].estaRetrasado()){
                    prestamos[i].getSocio().sancionar(prestamos[i].calcularDiasRetraso(), fechaDevolucion);//Lo sancionamos con el número de días de retraso y desde la fecha de devolución
                }
                return true;
            }else return false;
        }
        return false;
    }
}


