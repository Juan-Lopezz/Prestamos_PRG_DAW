package ProyectoPrestamos;

import java.time.LocalDate;

public class PruebasClases {
    public static void main(String[] args) {
        try {
            Usuario u1 = new Usuario("pepe", "pa@b.lo", "SOC123455", LocalDate.now(), true, LocalDate.now());
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (UsuarioRepetidoException e) {
            System.out.println(e.getMessage());
        }
    }
}
