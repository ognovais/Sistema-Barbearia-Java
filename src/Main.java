import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        //Criando Barbeiro e adicionando a lista
        Barbeiro b1 = new Barbeiro("Lucas", "27993254187", "Cortes modernos");
        UsuarioBarbeiro ub1 = new UsuarioBarbeiro("lucas123", "1234", b1);

        //Criando lista de Barbeiros
        List<UsuarioBarbeiro> usuariosBarbeiros = new ArrayList<>();
        usuariosBarbeiros.add(ub1);

        //Criando serviços
        Servico s1 = new Servico("Corte Máquina", 30, 35.0);
        Servico s2 = new Servico("Barba", 20, 20.0);
        b1.adicionarServico(s1);
        b1.adicionarServico(s2);

        //Criando cliente
        Cliente c1 = new Cliente("Antony", "2799835648");

        //Criando a lista de agendamentos
        LocalDate data = LocalDate.of(2025, 6, 20);
        List<Agendamento> agendamentos = new ArrayList<>();
        agendamentos.add(new Agendamento(data, "10:00", s1, "Antony", "2799984751",b1));

        MenuCliente menuCliente = new MenuCliente(b1, agendamentos);

        boolean sair = false;

        while (!sair) {
            System.out.println("\n===== BarberNow - Menu Principal =====");
            System.out.println("1 - Menu Cliente");
            System.out.println("2 - Menu Barbeiro");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = 0;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1:
                    menuCliente.iniciar();
                    break;
                case 2:
                    System.out.print("Usuário: ");
                    String usuarioEntrada = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senhaEntrada = scanner.nextLine();

                    // Busca o usuário barbeiro que bate com os dados
                    UsuarioBarbeiro usuarioLogado = null;
                    for (UsuarioBarbeiro u : usuariosBarbeiros) {
                        if (u.autenticar(usuarioEntrada, senhaEntrada)) {
                            usuarioLogado = u;
                            break;
                        }
                    }

                    if (usuarioLogado != null) {
                        MenuBarbeiro menuBarbeiro = new MenuBarbeiro(usuarioLogado.getBarbeiro(), agendamentos);
                        System.out.println("Bem vindo, " + usuarioLogado.getBarbeiro().getNome() + " !");
                        menuBarbeiro.iniciar();
                    } else {
                        System.out.println("Usuário ou senha incorretos!");
                    }
                    break;
                case 3:
                    System.out.println("Encerrando o programa!");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida, escolha 1, 2 ou 3.");
            }
        }

    }
}
