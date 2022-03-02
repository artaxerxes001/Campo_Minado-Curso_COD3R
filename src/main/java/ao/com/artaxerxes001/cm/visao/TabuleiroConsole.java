package ao.com.artaxerxes001.cm.visao;

import ao.com.artaxerxes001.cm.exception.SairException;
import ao.com.artaxerxes001.cm.modelo.Tabuleiro;

import java.util.Scanner;

public class TabuleiroConsole {
    private Tabuleiro tabuleiro;
    private Scanner scanner = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        executarJogo();
    }

    private void executarJogo() {
        try {

        } catch (SairException e) {
            System.out.println("Tchau!!!");
        }
    }
}
