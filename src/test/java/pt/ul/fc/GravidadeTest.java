package pt.ul.fc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class GravidadeTest {
    private Simulacao novaSimulacao() {
        return new Simulacao(new Bola(5.4, 4.7, -1.9, -2.5));
    }

    @Test
    void primeiroPassoNoSentidoDaVelocidade() {
        Simulacao simulacao = novaSimulacao();
        simulacao.passo(0.05);
        Bola bola = simulacao.getBola();
        assertEquals(5.305, bola.getX(), 1e-9);  // 5.4 + (-1.9)(0.05)
        assertEquals(4.575, bola.getY(), 1e-9);  // 4.7 + (-2.5)(0.05)
    }
}
