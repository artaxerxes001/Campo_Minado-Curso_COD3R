package ao.com.artaxerxes001.cm.modelo;

import ao.com.artaxerxes001.cm.exception.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampoTest {
    private Campo campo;

    @BeforeEach
// serve para cria um metodo de instancia que vai poder ser usado para testar todos os metodos da class
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }

    @Test
    void testeVizinhoDistancia1Esquerda() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1Direita() {
        Campo vizinho = new Campo(3, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1Cima() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1Baixo() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia2() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeNaoVizinho() {
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlterarMarcado() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlterarMarcadoDuasChamadas() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado() {
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMarcado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado() {

        campo.minar();
        assertThrows(ExplosaoException.class, () -> campo.abrir());
    }

    @Test
    void testAbrirComVizinho() {

        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testAbrirComVizinho2() {

        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 1);
        campo12.minar();


        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }


    @Test
    void testeVizinhancaSegura() {
        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        campo.adicionarVizinho(campo22);

        assertTrue(campo.vizinhancaSegura());
    }

    @Test
    void testeVizinhancaNaoSegura() {
        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        campo22.minar();
        campo.adicionarVizinho(campo22);

        assertFalse(campo.vizinhancaSegura());
    }

    @Test
    void testeobjetivoAlcancadoMinadoEMarcado() {
        Campo campo11 = new Campo(1, 1);
        campo11.minar();
        campo11.alternarMarcacao();
        assertTrue(campo11.objetivoAlcancado());

    }

    @Test
    void testeobjetivoAlcancadoDesminadoEAberto() {
        Campo campo11 = new Campo(1, 1);
        campo11.abrir();
        assertTrue(campo11.objetivoAlcancado());

    }

    @Test
    void testeobjetivoNaoAlcancado() {
        Campo campo11 = new Campo(1, 1);
        campo11.minar();
        assertFalse(campo11.objetivoAlcancado());

    }


    @Test
    void minasNaVisinhanca() {

        Campo campo11 = new Campo(3, 4);
        Campo campo22 = new Campo(2, 2);
        campo11.minar();
        campo22.minar();

        campo.adicionarVizinho(campo22);
        campo.adicionarVizinho(campo11);

        assertEquals(2, campo.minasNaVisinhanca());
    }

    @Test
    void testToString() {
        campo.alternarMarcacao();
        assertEquals("x", campo.toString());
    }

    @Test
    void testToStringMinas() {
        Campo campo11 = new Campo(3, 4);
        Campo campo22 = new Campo(2, 2);
        campo11.minar();
        campo22.minar();

        campo.abrir();
        campo.adicionarVizinho(campo22);
        campo.adicionarVizinho(campo11);

        assertEquals("2", campo.toString());
    }

    @Test
    void testToStringAberto() {
        campo.abrir();
        assertEquals(" ", campo.toString());
    }

    @Test
    void testToStringValorInterrogacao() {
        Campo campo11 = new Campo(3, 4);
        Campo campo22 = new Campo(2, 2);
        campo11.minar();
        campo22.minar();


        campo.adicionarVizinho(campo22);
        campo.adicionarVizinho(campo11);

        assertEquals("?", campo.toString());
    }

    @Test
    void testToStringAbertoEMinado() {
        campo.abrir();
        campo.minar();

        assertEquals("*", campo.toString());
    }
}