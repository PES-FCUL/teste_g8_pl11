package pt.ul.fc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SimulacaoTest {

    private static final double EPS = 1e-9;

    private Simulacao novaSimulacao() {
        return new Simulacao(new Bola(4.2, 5.3, -3.0, 1.4));
    }

    @Test
    void posicaoInicial() {
        Simulacao simulacao = novaSimulacao();
        Bola bola = simulacao.getBola();
        // Antes de qualquer passo, a bola está na posição inicial
        assertEquals(4.2, bola.getX(), EPS);
        assertEquals(5.3, bola.getY(), EPS);
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
    void deslocaSentidoVelocidade(){
        Bola bola = new Bola(4.2, 5.3, -3.0, 1.4);
        Simulacao simulacao = new Simulacao(bola);

        double xInicial = bola.getX();
        double yInicial = bola.getY();

        simulacao.passo(Main.DT);

        assertTrue(bola.getX() < xInicial, 
            "Esperava-se que X diminuísse (x inicial: " + xInicial + ", x atual: " + bola.getX() + ")");
        assertTrue(bola.getY() > yInicial, 
            "Esperava-se que Y aumentasse (y inicial: " + yInicial + ", y atual: " + bola.getY() + ")");
    }
}