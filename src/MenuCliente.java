import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuCliente {
    private Barbeiro barbeiro;
    private List<Agendamento> agendamentos;

    public MenuCliente(Barbeiro barbeiro, List<Agendamento> agendamentos){
        this.barbeiro = barbeiro;
        this.agendamentos = agendamentos;
    }

    Scanner scanner = new Scanner(System.in);

    public void iniciar(){

        boolean sair = false;

        while (!sair){
            System.out.println("\n=======BarberNow=======");
            System.out.println("1 - Ver serviços");
            System.out.println("2 - Agendar serviços");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1:
                    mostrarServicos();
                    break;
                case 2:
                    agendarServico();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Escolha uma opção de 1-3");
                    break;
            }
        }
    }

    public void mostrarServicos() {
        System.out.println("\nServiços prestados pela barbearia: ");
        for (int i = 0; i < barbeiro.getServicos().size(); i++){
            System.out.println((i + 1) + " - " + barbeiro.getServicos().get(i));
        }
    }

    public void agendarServico() {
        List<Servico> servicos = barbeiro.getServicos();

        if (servicos.isEmpty()){
            System.out.println("Nenhum serviço disponível para agendamento");
            return;
        }

        System.out.println("\nQual serviço gostaria de agendar? ");
        for (int i = 0; i < servicos.size(); i++){
            System.out.println((i + 1) + " - " + servicos.get(i));
        }

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao < 1 || opcao > servicos.size()){
            System.out.println("Opção inválida.");
            return;
        }

        Servico servicoEscolhido = servicos.get(opcao - 1);

        System.out.print("Qual Data de sua preferência (dd-MM-yyyy): ");
        String data = scanner.nextLine().trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate dataDigitada = LocalDate.parse(data, formatter);
            LocalDate hoje = LocalDate.now();

            if (dataDigitada.isBefore(hoje)) {
                System.out.println("Não é possivel agendar para uma data anterior a hoje.");
                return;
            } else {
                System.out.println("Data válida: " + dataDigitada);
                continuarAgendamento(dataDigitada, servicoEscolhido);
            }

        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. use dd-MM-yyyy");
        }
    }

    public List<String> gerarHorariosDoDia() {
        List<String> horariosDoDia = new ArrayList<>();
        int hora = 9;
        int minuto = 0;

        while (hora < 17 || (hora == 17 && minuto == 0)) {
            String horario = String.format("%02d:%02d", hora, minuto);
            horariosDoDia.add(horario);

            minuto += 30;
            if (minuto == 60) {
                minuto = 0;
                hora++;
            }
        }

        return horariosDoDia;
    }



    public void continuarAgendamento(LocalDate dataDigitada, Servico servicoEscolhido) {
        List<String> horariosDisponiveis = gerarHorariosDoDia();

        for (Agendamento a : agendamentos) {
            if (a.getData().equals(dataDigitada)) {
                horariosDisponiveis.remove(a.getHora());
            }
        }

        if (dataDigitada.isEqual(LocalDate.now())) {
            LocalTime agora = LocalTime.now().withSecond(0).withNano(0);

            horariosDisponiveis.removeIf(horarioNow -> {
                LocalTime horaAgendada = LocalTime.parse(horarioNow);
                boolean remover = horaAgendada.isBefore(agora);
                return remover;
            });
        }

        if (horariosDisponiveis.isEmpty()) {
            System.out.println("Não temos horários disponíveis nessa data.");
            return;
        }

        System.out.println("Horários disponíveis:");
        for (int i = 0; i < horariosDisponiveis.size(); i++) {
            System.out.println((i + 1) + " - " + horariosDisponiveis.get(i));
        }

        System.out.print("Escolha um horário: ");
        int horario = scanner.nextInt();
        scanner.nextLine();

        if (horario < 1 || horario > horariosDisponiveis.size()) {
            System.out.println("Horário Inválido.");
            return;
        }

        String horaEscolhida = horariosDisponiveis.get(horario - 1);

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu número de telefone: ");
        String telefone = scanner.nextLine();

        Agendamento novoAgendamento = new Agendamento(dataDigitada, horaEscolhida, servicoEscolhido, nome, telefone, barbeiro);
        agendamentos.add(novoAgendamento);

        System.out.println("Agendamento Realizado com sucesso!");
    }


}
