import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuBarbeiro {
    private Barbeiro barbeiro;
    private List<Agendamento> agendamentos;

    public MenuBarbeiro(Barbeiro barbeiro, List<Agendamento> agendamentos){
        this.barbeiro = barbeiro;
        this.agendamentos = agendamentos;
    }

    public void iniciar(){
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("\n=======BarberNow=======");
            System.out.println("1 - Ver Agendamentos");
            System.out.println("2 - Gerenciar Serviços");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro(scanner);

            switch (opcao){
                case 1:
                    mostrarAgendamentos();
                    break;
                case 2:
                    gerenciarServicos(scanner);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente");
            }
        }
    }

    private int lerInteiro(Scanner scanner) {
        while(true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine(); // limpar buffer
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Por favor digite um número: ");
                scanner.nextLine(); // limpar buffer inválido
            }
        }
    }

    public void mostrarAgendamentos() {
        System.out.println("\nAgendamentos para " + barbeiro.getNome() + ": ");
        boolean temAgendamento = false;
        for (Agendamento a : agendamentos){
            if (a.getBarbeiro().equals(barbeiro)){
                System.out.println(a);
                temAgendamento = true;
            }
        }
        if (!temAgendamento) {
            System.out.println("Nenhum agendamento encontrado.");
        }
    }

    public void gerenciarServicos(Scanner scanner){
        boolean voltar = false;

        while (!voltar){
            System.out.println("\n=====Gerenciar Serviços=====");
            System.out.println("1 - Adicionar novo serviço");
            System.out.println("2 - Excluir serviço");
            System.out.println("3 - Listar serviços");
            System.out.println("4 - Editar serviços");
            System.out.println("5 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    System.out.print("Nome do serviço: ");
                    String nome = scanner.nextLine();

                    System.out.print("Duração (em minutos): ");
                    int duracao = lerInteiro(scanner);

                    System.out.print("Preço: ");
                    double preco;
                    while(true) {
                        try {
                            preco = Double.parseDouble(scanner.nextLine().replace(",", "."));
                            break;
                        } catch (NumberFormatException e) {
                            System.out.print("Preço inválido. Digite um número válido: ");
                        }
                    }

                    Servico novo = new Servico(nome, duracao, preco);
                    barbeiro.adicionarServico(novo);
                    System.out.println("Serviço adicionado com sucesso!");
                    break;
                case 2:
                    List<Servico> servicos = barbeiro.getServicos();
                    if (servicos.isEmpty()) {
                        System.out.println("Nenhum serviço para excluir.");
                    } else {
                        listarServicosFormatado(servicos);
                        System.out.print("Digite o número do serviço que deseja excluir: ");
                        int indice = lerInteiro(scanner);
                        if (indice > 0 && indice <= servicos.size()) {
                            Servico removido = servicos.remove(indice - 1);
                            System.out.println("Serviço '" + removido.getNome() + "' removido com sucesso!");
                        } else {
                            System.out.println("Índice inválido!");
                        }
                    }
                    break;
                case 3:
                    if (barbeiro.getServicos().isEmpty()) {
                        System.out.println("Nenhum serviço cadastrado.");
                    } else {
                        listarServicosFormatado(barbeiro.getServicos());
                    }
                    break;

                case 4:
                    if (barbeiro.getServicos().isEmpty()) {
                        System.out.println("Nenhum serviço para editar.");
                    } else {
                        listarServicosFormatado(barbeiro.getServicos());

                        System.out.print("Digite o número do serviço que deseja editar ou 0 para voltar: ");
                        int indice = lerInteiro(scanner);

                        if (indice == 0) {
                            System.out.println("Edição cancelada.");
                            break;
                        }

                        if (indice > 0 && indice <= barbeiro.getServicos().size()) {
                            Servico servico = barbeiro.getServicos().get(indice - 1);

                            System.out.print("Novo nome (atual: " + servico.getNome() + "): ");
                            String novoNome = scanner.nextLine();

                            System.out.print("Nova duração (min) (atual: " + servico.getDuracao() + "): ");
                            int novaDuracao = lerInteiro(scanner);

                            System.out.print("Novo preço (atual: R$" + servico.getPreco() + "): ");
                            double novoPreco;
                            while(true) {
                                try {
                                    novoPreco = Double.parseDouble(scanner.nextLine().replace(",", "."));
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.print("Preço inválido. Digite um número válido: ");
                                }
                            }

                            servico.setNome(novoNome);
                            servico.setDuracao(novaDuracao);
                            servico.setPreco(novoPreco);

                            System.out.println("Serviço atualizado com sucesso!");
                        } else {
                            System.out.println("Índice inválido!");
                        }
                    }
                    break;

                case 5:
                    voltar = true;
                    break;

                default:
                    System.out.println("Opção inválida. Tente um número de 1-5");
                    break;
            }
        }
    }

    private void listarServicosFormatado(List<Servico> servicos){
        System.out.println("\nServiços cadastrados:");
        for (int i = 0; i < servicos.size(); i++) {
            Servico s = servicos.get(i);
            System.out.printf("%d - %s, Duração: %d min, Preço: R$%.2f%n", (i + 1), s.getNome(), s.getDuracao(), s.getPreco());
        }
    }
}
