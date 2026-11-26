package pt.ul.fc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SimulacaoTest {

    private static final double EPS = 1e-9;

    private Simulacao novaSimulacao() {
        return new Simulacao(new Bola(5.1, 6.3, 2.2, 2.6));
    }

    @Test
    void posicaoInicial() {
        Simulacao simulacao = novaSimulacao();
        Bola bola = simulacao.getBola();
        // Antes de qualquer passo, a bola está na posição inicial
        assertEquals(5.1, bola.getX(), EPS);
        assertEquals(6.3, bola.getY(), EPS);
    }

    @Test
    void bolaMexe() {
        Simulacao simulacao = novaSimulacao();
        double dt = 0.5;
        // Bola mexe-se ao passar tempo na simulacao
        for (int i = 0; i < 2; i++) {
            Bola bola = simulacao.getBola();
            // Confirma velocidade da Bola está posta.
            assertEquals(2.2, bola.getVx());
            assertEquals(2.6, bola.getVy());

            // Confirma posição esperada
            double expectedPositionX = 5.1 + (bola.getVx() * i * dt);
            double expectedPositionY = 6.3 + (bola.getVy() * i * dt);
            assertEquals(Math.round(expectedPositionX), Math.round(bola.getX())); // Confirma posicao x
            assertEquals(Math.round(expectedPositionY), Math.round(bola.getY())); // Confirma posicao y
            simulacao.passo(dt);
        }
    }

    @Test
    void ficaDentroDosLimites() {
        Simulacao simulacao = novaSimulacao();
        for (int i = 0; i < 10; i++) {
            simulacao.passo(0.05);
        }
        Bola bola = simulacao.getBola();
        assertTrue(bola.getX() >= Simulacao.RAIO, "x abaixo do limite: " + bola.getX());
        assertTrue(bola.getX() <= Simulacao.LARGURA - Simulacao.RAIO, "x acima do limite: " + bola.getX());
        assertTrue(bola.getY() >= Simulacao.RAIO, "y abaixo do limite: " + bola.getY());
        assertTrue(bola.getY() <= Simulacao.ALTURA - Simulacao.RAIO, "y acima do limite: " + bola.getY());
    }
}
