package ProyectoPrestamos;

import java.time.LocalDate;

public class PruebasClases {
    public static void main(String[] args) {
        try {
            Usuario u1 = new Usuario("pepe", "pa@b.lo", "SOC12345", LocalDate.now());
            Prestamo p1 = new Prestamo("ABC1234",u1,"sef",LocalDate.now().plusDays(-17)); // solucionar el null pointer exception cuando pasan null en un string para un constructor(catch en el main)
            p1.registrarDevolucion(LocalDate.now().plusDays(12));
            System.out.println(p1.calcularDiasRetraso());
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
    }
}
