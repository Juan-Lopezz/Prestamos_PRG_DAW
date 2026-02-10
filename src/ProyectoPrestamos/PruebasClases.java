package ProyectoPrestamos;

import java.time.LocalDate;

public class PruebasClases {
    public static void main(String[] args) {
        try {
            Usuario u1 = new Usuario("pepe", "pa@b.lo", "SOC12345", LocalDate.now(), true, null);
            u1.sancionar(13,LocalDate.now());
            System.out.println();
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }
}
