package pt.ul.fc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * MRE para o issue "A bola fica colada ao chão".
 */
class RessaltoChaoTest {

    private static final double EPS = 1e-9;

    @Test
    void bolaRessaltaParaCimaAoTocarNoChao() {
        // Bola quase no chão, a descer
        Simulacao simulacao = new Simulacao(new Bola(3.0, 0.3, 0.0, -5.0));
        simulacao.passo(0.05);
        Bola bola = simulacao.getBola();
        // vy antes do choque: -5 + (-9.8 * 0.05) = -5.49
        // depois do ressalto deve apontar para cima, com 85% da velocidade
        assertTrue(bola.getVy() > 0, "a bola devia subir depois de tocar no chão, vy = " + bola.getVy());
        assertEquals(5.49 * Simulacao.COEFICIENTE_RESTITUICAO, bola.getVy(), EPS);
    }

    @Test
    void bolaLargadaA7_7NaoFicaColadaAoChao() {
        // Cenário do relatório de erro: bola parada a 7,7 m
        Simulacao simulacao = new Simulacao(new Bola(3.0, 7.7, 0.0, 0.0));
        boolean tocouNoChao = false;
        double alturaMaxDepoisDoChao = 0;
        for (int i = 0; i < 100; i++) {
            simulacao.passo(0.05);
            double y = simulacao.getBola().getY();
            if (y <= Simulacao.RAIO + EPS) {
                tocouNoChao = true;
            } else if (tocouNoChao) {
                alturaMaxDepoisDoChao = Math.max(alturaMaxDepoisDoChao, y);
            }
        }
        assertTrue(tocouNoChao, "a bola devia ter chegado ao chão");
        assertTrue(alturaMaxDepoisDoChao > 1.0,
            "a bola devia ressaltar, mas ficou colada ao chão (altura máx. após o choque = "
                + alturaMaxDepoisDoChao + ")");
    }
}
