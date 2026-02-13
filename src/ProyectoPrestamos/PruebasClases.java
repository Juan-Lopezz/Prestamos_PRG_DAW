package ProyectoPrestamos;

import java.time.LocalDate;

public class PruebasClases {
    public static void main(String[] args) {
        try {
            GestorBiblioteca g = new GestorBiblioteca();
            Usuario u1 = new Usuario("pepe", "pa@b.lo", "SOC12345", LocalDate.now());
            Usuario u2 =new Usuario("pepe", "pa@b.lo", "SOC12335", LocalDate.now());
            Prestamo p1 = new Prestamo("ABC1234",u1,"wer",LocalDate.now().plusDays(-15)); // solucionar el null pointer exception cuando pasan null en un string para un constructor(catch en el main)
            p1.registrarDevolucion(LocalDate.now().plusDays(12));
            //u1.sancionar(15,LocalDate.now());
            g.registrarUsuario(u1);
            p1= g.realizarPrestamo("ABC1234","qw",LocalDate.now().plusDays(-14), u1);
            System.out.println(g.devolverLibro("ABC1234", LocalDate.now().plusDays(1)));
            System.out.println(g.devolverLibro("ABC1234", LocalDate.now().plusDays(1))); // solucionar
            System.out.println(u1.estaSancionado());
            System.out.println("---------------");
            g.registrarUsuario(u2);
            System.out.println(p1.toString());
            System.out.println(p1.calcularDiasRetraso());
            System.out.println(p1.estaRetrasado());
            u1.sancionar(1234455,LocalDate.now());
            System.out.println(u1.toString());
            u1.levantarSancion();
            System.out.println(u1.toString());
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
        catch (PrestamoInvalidoException e) {
            System.out.println(e.getMessage());
        }
        catch (NullPointerException e){
            System.out.println("Error inesperado");
        }
        catch (UsuarioRepetidoException e){
            System.out.println(e.getMessage());
        }
        catch (UsuarioSancionadoException e){
            System.out.println(e.getMessage());
        }
        catch (LibroNoDisponibleException e){
            System.out.println(e.getMessage());
        }
    }
}
