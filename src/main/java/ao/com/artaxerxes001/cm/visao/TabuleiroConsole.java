package ao.com.artaxerxes001.cm.visao;

import ao.com.artaxerxes001.cm.exception.ExplosaoException;
import ao.com.artaxerxes001.cm.exception.SairException;
import ao.com.artaxerxes001.cm.modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {
    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;
            while (continuar) {

                cicloDoJogo();
                System.out.print("Outra partida? [S/n]: ");

                String resposta = entrada.nextLine();
                if ("n".equalsIgnoreCase(resposta)) {
                    continuar = false;
                } else {
                    tabuleiro.reiniciar();
                }
            }
        } catch (SairException e) {
            System.out.println("Tchau!!!");
        } finally {
            entrada.close();
        }
    }

    private void cicloDoJogo() {
        try {
            while (!tabuleiro.objetivoAlcancado()) {
                System.out.println(tabuleiro);

                String digitado = capturarValorDigitado("Digite (x, y): ");
                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e.trim()))
                        .iterator();
                digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
                if ("1".equalsIgnoreCase(digitado)) {
                    tabuleiro.abrir(xy.next(), xy.next());
                }else if ("2".equalsIgnoreCase(digitado)) {
                    tabuleiro.alternarMarcacao(xy.next(), xy.next());
                }
            }
            System.out.println(tabuleiro);
            System.out.println("Voce Ganhou!!! \uD83C\uDFC6 ");
        } catch (ExplosaoException e) {
            System.out.println(tabuleiro);
            System.out.println("Voce Perdeu!!!");
        }
    }

    private String capturarValorDigitado(String texto) {
        System.out.print(texto);
        String digitado = entrada.nextLine();
        if ("sair".equalsIgnoreCase(digitado)) {
            throw new SairException();
        }
        return digitado;
    }
}
