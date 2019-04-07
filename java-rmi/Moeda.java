import java.io.*;

public class Moeda implements Serializable {
    private String nome;
    private double cotacao;

    Moeda(String nome, double cotacao) {
        this.nome = nome;
        this.cotacao = cotacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCotacao(double cotacao) {
        this.cotacao = cotacao;
    }

    public String getNome() {
        return this.nome;
    }

    public double getCotacao() {
        return this.cotacao;
    }
}
