import java.util.ArrayList;
import java.util.List;

public class Barbeiro {
    private String nome;
    private String telefone;
    private String especialidade;
    private List<Servico> servicos;

    public Barbeiro(String nome, String telefone, String especialidade) {
        this.nome = nome;
        this.telefone = telefone;
        this.especialidade = especialidade;
        this.servicos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void adicionarServico(Servico servico){
        servicos.add(servico);
        System.out.println("Serviço adicionado com sucesso!");
    }

    @Override
    public String toString(){
        return "Nome: " + nome + ", Telefone: " + telefone + ", Especialidade: " + especialidade;
    }
}
