package pt.ul.fc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SimulacaoTest {

    private static final double EPS = 1e-9;

    private Simulacao novaSimulacao() {
        return new Simulacao(new Bola(6.9, 3.8, 1.5, 1.6));
    }

    @Test
    void posicaoInicial() {
        Simulacao simulacao = novaSimulacao();
        Bola bola = simulacao.getBola();
        // Antes de qualquer passo, a bola está na posição inicial
        assertEquals(6.9, bola.getX(), EPS);
        assertEquals(3.8, bola.getY(), EPS);
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
    void MoveNaDirecaoCorreta(){
        Simulacao simulacao = novaSimulacao();
        simulacao.passo(0.1);
        Bola bola = simulacao.getBola();
        if (bola.getVx() > 0){
        assertTrue(bola.getX() > 6.9, "Bola move no sentido positivo de x");}
        if (bola.getVy() > 0){
        assertTrue(bola.getY() > 3.8, "Bola move no sentido positivo de y");}
        if (bola.getVx() < 0){
        assertTrue(bola.getX() < 6.9, "Bola move no sentido negativo de x");}
        if (bola.getVy() < 0){
        assertTrue(bola.getY() < 3.8, "Bola move no sentido negativo de y");}

    }
}
