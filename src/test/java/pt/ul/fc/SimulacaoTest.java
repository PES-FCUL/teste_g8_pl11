package pt.ul.fc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SimulacaoTest {

    private static final double EPS = 1e-9;

    private Simulacao novaSimulacao() {
        return new Simulacao(new Bola(4.6, 5.1, 2.6, -2.0));
    }

    @Test
    void posicaoInicial() {
        Simulacao simulacao = novaSimulacao();
        Bola bola = simulacao.getBola();
        // Antes de qualquer passo, a bola está na posição inicial
        assertEquals(4.6, bola.getX(), EPS);
        assertEquals(5.1, bola.getY(), EPS);
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

    @Test
    void testeErro() {
        Simulacao simulacao = novaSimulacao();
        Bola bola = simulacao.getBola();
        double posX1 = bola.getX();
        double posY1 = bola.getY();

        // andar 1 passo
        simulacao.passo(0.05);
        double posX2 = bola.getX();
        double posY2 = bola.getY();

        assertEquals(posX1+(2.6/20), posX2);
        assertEquals(posY1-(2.0/20), posY2);


    }
}
