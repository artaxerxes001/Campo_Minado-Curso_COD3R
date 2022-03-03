package ao.com.artaxerxes001.cm.modelo;

import ao.com.artaxerxes001.cm.exception.ExplosaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private int minas;

    private final List<Campo> campos = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }

    public void abrir(int linha, int coluna) {
        try {
            campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst().ifPresent(c -> c.abrir());

        } catch (ExplosaoException e) {
            campos.forEach(c -> c.setAberto(true));
            throw e;
        }
    }

    public void alternarMarcacao(int linha, int coluna) {
        campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst().ifPresent(c -> c.alternarMarcacao());
    }

    private void gerarCampos() {
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                campos.add(new Campo(linha, coluna));
            }
        }
    }

    private void associarVizinhos() {
        for (Campo c1 : campos) {
            for (Campo c2 : campos) {
                c1.adicionarVizinho(c2);
            }
        }
    }

    private void sortearMinas() {
        long minasArmadas = 0;
        Predicate<Campo> minado = c -> c.isMinado();
        do {
            int aleatorio = (int) (Math.random() * campos.size());
            minasArmadas = campos.stream().filter(minado).count();
            campos.get(aleatorio).minar();
        } while (minasArmadas < minas);
    }

    public boolean objetivoAlcancado() {
        return campos.stream().allMatch(c -> c.objetivoAlcancado());
    }

    public void reiniciar() {
        campos.forEach(c -> c.reiniciar());
        sortearMinas();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
//        espaço inicial para os indeces das colunas
        sb.append("    ");
        for (int coluna = 0; coluna < colunas; coluna++) {
            sb.append(" ");
            sb.append(coluna+" ");
            sb.append(" ");

        }
        sb.append("\n");
        sb.append("    ");
        for (int coluna = 0; coluna < colunas; coluna++) {
            sb.append(" ");
            sb.append("_ ");
            sb.append(" ");

        }
        sb.append("\n");
        int i = 0;
        for (int linha = 0; linha < linhas; linha++) {
            sb.append(linha+" |");
            sb.append(" ");
            for (int coluna = 0; coluna < colunas; coluna++) {
                sb.append(" ");
                sb.append(campos.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
