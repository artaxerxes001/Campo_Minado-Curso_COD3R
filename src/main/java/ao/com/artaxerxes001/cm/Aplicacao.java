package ao.com.artaxerxes001.cm;

import ao.com.artaxerxes001.cm.modelo.Tabuleiro;
import ao.com.artaxerxes001.cm.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6,6,6);
        new TabuleiroConsole(tabuleiro);

    }
}
