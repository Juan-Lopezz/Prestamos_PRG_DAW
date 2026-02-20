package ProyectoPrestamos;

import java.time.LocalDate;

public class GestorBiblioteca {
    private final static int MAX_USUARIOS =50;
    private final static int MAX_PRESTAMOS =200;
    private Usuario[] usuarios;
    private Prestamo[] prestamos;
    private int numeroUsuarios;
    private int numeroPrestamos;

    /**
     * Constructor de la clase GestorBiblioteca
     * Inicializa los arrays de usuarios y préstamos con sus capacidades máximas y pone los contadores a cero
     */
    public GestorBiblioteca() {
        usuarios = new Usuario[MAX_USUARIOS];
        prestamos = new Prestamo[MAX_PRESTAMOS];
        numeroUsuarios=0;
        numeroPrestamos=0;
    }

    /**
     * Metodo para registrar un nuevo usuario en la biblioteca
     * @param usuario Objeto Usuario que se desea dar de alta en el sistema
     * @throws UsuarioRepetidoException lanza una excepción si el objeto usuario ya existe o si ya hay un usuario con el mismo número de socio registrado
     */
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

    /**
     * Metodo para gestionar la realización de un préstamo de un libro a un usuario
     * @param codigoLibro String que identifica el código único del libro
     * @param tituloLibro String que recoge el título del libro prestado
     * @param fechaPrestamo LocalDate indicando la fecha en la que se realiza el préstamo
     * @param usuario Objeto Usuario que solicita el libro
     * @return Prestamo objeto que contiene la información del préstamo realizado
     * @throws UsuarioSancionadoException lanza una excepción cuando el usuario tiene una sanción activa
     * @throws LibroNoDisponibleException lanza una excepción cuando el libro ya se encuentra en posesión de otro usuario
     * @throws PrestamoInvalidoException lanza una excepción si el usuario que intenta coger el libro no está registrado
     */
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
                if (prestamos[i].getCodigoLibro().equals(codigoLibro) && prestamos[i].getFechaDevolucionReal()==null) {
                    throw  new LibroNoDisponibleException("Libro ya prestado");
                }
            }
            prestamos[numeroPrestamos]= new Prestamo(codigoLibro, usuario, tituloLibro, fechaPrestamo);
            numeroPrestamos++;
            return  prestamos[numeroPrestamos-1];// restamos uno porque en la línea de arriba ya pasamos a la siguiente posición del array porque después del return no se puede poner
        }
    }

    /**
     * Metodo para registrar la devolución de un libro y gestionar las posibles sanciones por retraso
     * @param codigoLibro String con el identificador del libro a devolver
     * @param fechaDevolucion LocalDate con la fecha real en la que se entrega el libro
     * @return boolean que indica true si el libro se ha devuelto correctamente o false si no se encuentra el préstamo
     * @throws PrestamoInvalidoException lanza una excepción si ocurre un error en la validación de los datos del préstamo
     */
    public boolean devolverLibro(String codigoLibro, LocalDate fechaDevolucion)throws PrestamoInvalidoException{
        for  (int i=0; i<numeroPrestamos; i++) {
            if (prestamos[i].getCodigoLibro().equals(codigoLibro) && prestamos[i].getFechaDevolucionReal()==null) {
                prestamos[i].registrarDevolucion(fechaDevolucion);
                if (prestamos[i].estaRetrasado()){
                    prestamos[i].getSocio().sancionar(prestamos[i].calcularDiasRetraso(), fechaDevolucion);//Lo sancionamos con el número de días de retraso y desde la fecha de devolución
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para localizar un usuario dentro del sistema mediante su código de socio
     * @param codigoSocio String que representa el identificador único del socio a buscar
     * @return Usuario el objeto buscado si se encuentra en el array, o null si no existe
     */
    public Usuario buscarUsuario(String codigoSocio){
        for   (int i=0; i<numeroUsuarios; i++) {
            if (usuarios[i].getNumeroSocio().equals(codigoSocio)) {
                return usuarios[i];
            }else return null;
        }
        return null;
    }

    public Prestamo[] getPrestamos() {
        return prestamos;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    @Override
    public String toString() {
        String usuariosString = "";
        String prestamosString = "";
        for (int i=0; i<numeroUsuarios; i++) {
            usuariosString += (usuarios[i].toString() + "\n");
        }
        for (int i=0; i<numeroPrestamos; i++) {
            prestamosString += (prestamos[i].toString() + "\n");
        }
        return "Número de usuarios: " + numeroUsuarios + "\n\n" + usuariosString + "\n" +"Número de préstamos: "+ this.numeroPrestamos + "\n\n" +  prestamosString;
    }
}


