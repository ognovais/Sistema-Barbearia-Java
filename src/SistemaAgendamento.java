import java.util.ArrayList;
import java.util.List;

public class SistemaAgendamento {
    private List<Agendamento> agendamentos;

    public SistemaAgendamento(){
        this.agendamentos = new ArrayList<>();
    }

    public boolean adicionarAgendamento(Agendamento novoAgendamento) {
        for (Agendamento a : agendamentos){
            if (a.getBarbeiro().getNome().equals(novoAgendamento.getBarbeiro().getNome())
            && a.getData().equals(novoAgendamento.getData())
            && a.getHora() == novoAgendamento.getHora()) {
                System.out.println("Este Horário já está marcado!");
                return false;
            }
        }
        agendamentos.add(novoAgendamento);
        System.out.println("Agendamento adicionado com sucesso!");
        return true;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }
}
