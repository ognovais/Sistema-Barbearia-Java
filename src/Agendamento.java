import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Agendamento {
    private LocalDate data;
    private String hora;
    private Servico servico;
    private Cliente cliente;
    private Barbeiro barbeiro;

    public Agendamento(LocalDate data, String hora, Servico servico, String nomeCliente, String telefone, Barbeiro barbeiro) {
        this.data = data;
        this.hora = hora;
        this.servico = servico;
        this.cliente = new Cliente(nomeCliente, telefone);
        this.barbeiro = barbeiro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    @Override
    public String toString() {
        return "Data: " + data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                ", Hora: " + hora +
                ", Serviço: " + servico.getNome() +
                ", Cliente: " + cliente.getNome() +
                ", Barbeiro: " + barbeiro.getNome();
    }


}
