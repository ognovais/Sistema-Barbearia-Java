public class Servico {
    private String nome;
    private int duracao;
    private double preco;

    public Servico(String nome, int duracao, double preco) {
        this.nome = nome;
        this.duracao = duracao;
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }


    @Override
    public String toString() {
        return nome + ", Duração: " + duracao + "min, Preço: " + String.format("R$%.2f", preco);
    }

}
