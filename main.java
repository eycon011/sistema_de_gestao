import model.Carro;
import model.Cliente;
import model.Moto;
import model.Veiculo;
import service.ConcessionariaService;

import java.util.Scanner;

/* Classe principal com menu interativo para demonstrar o fluxo completo do sistema */
public class Main {
    public static void main(String[] args) {
        ConcessionariaService service = new ConcessionariaService();
        Scanner scanner = new Scanner(System.in);

        // Carga inicial de dados para facilitar os testes ao executar o projeto
        inicializarDadosExemplo(service);

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Digite apenas números válidos.");
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarNovoVeiculo(service, scanner);
                    break;
                case 2:
                    service.listarEstoqueCompleto();
                    break;
                case 3:
                    System.out.print("Informe o chassi do veículo para avançar a montagem: ");
                    String chassiMontagem = scanner.nextLine();
                    service.avancarFluxoMontagem(chassiMontagem);
                    break;
                case 4:
                    processarVenda(service, scanner);
                    break;
                case 5:
                    service.listarHistoricoVendas();
                    break;
                case 0:
                    System.out.println("Encerrando o Sistema de Gestão de Concessionária.");
                    break;
                default:
                    System.out.println("[AVISO] Opção inválida.");
            }
        }
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n==========================================");
        System.out.println("   SISTEMA DE GESTÃO DE CONCESSIONÁRIA    ");
        System.out.println("==========================================");
        System.out.println("1. Cadastrar Veículo no Estoque");
        System.out.println("2. Listar Estoque Completo");
        System.out.println("3. Avançar Etapa de Montagem (PDI)");
        System.out.println("4. Realizar Venda");
        System.out.println("5. Relatório de Vendas e Faturamento");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarNovoVeiculo(ConcessionariaService service, Scanner scanner) {
        try {
            System.out.print("Tipo de veículo (1 - Carro | 2 - Moto): ");
            int tipo = Integer.parseInt(scanner.nextLine());

            System.out.print("Chassi: ");
            String chassi = scanner.nextLine();

            System.out.print("Marca: ");
            String marca = scanner.nextLine();

            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();

            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());

            System.out.print("Preço Base (R$): ");
            double precoBase = Double.parseDouble(scanner.nextLine().replace(",", "."));

            if (tipo == 1) {
                System.out.print("Número de portas: ");
                int portas = Integer.parseInt(scanner.nextLine());

                System.out.print("Câmbio automático? (true/false): ");
                boolean automatico = Boolean.parseBoolean(scanner.nextLine());

                Veiculo carro = new Carro(chassi, marca, modelo, ano, precoBase, portas, automatico);
                service.cadastrarVeiculo(carro);
            } else if (tipo == 2) {
                System.out.print("Cilindradas (cc): ");
                int cilindradas = Integer.parseInt(scanner.nextLine());

                System.out.print("Possui freio ABS? (true/false): ");
                boolean abs = Boolean.parseBoolean(scanner.nextLine());

                Veiculo moto = new Moto(chassi, marca, modelo, ano, precoBase, cilindradas, abs);
                service.cadastrarVeiculo(moto);
            } else {
                System.out.println("[ERRO] Tipo de veículo inválido.");
            }
        } catch (Exception e) {
            System.out.println("[ERRO] Falha ao cadastrar veículo: " + e.getMessage());
        }
    }

    private static void processarVenda(ConcessionariaService service, Scanner scanner) {
        try {
            System.out.print("Chassi do veículo a ser vendido: ");
            String chassi = scanner.nextLine();

            System.out.print("Nome do cliente: ");
            String nome = scanner.nextLine();

            System.out.print("CPF do cliente: ");
            String cpf = scanner.nextLine();

            System.out.print("Telefone do cliente: ");
            String telefone = scanner.nextLine();

            Cliente cliente = new Cliente(nome, cpf, telefone);
            service.realizarVenda(chassi, cliente);
        } catch (Exception e) {
            System.out.println("[ERRO] Falha ao registrar venda: " + e.getMessage());
        }
    }

    private static void inicializarDadosExemplo(ConcessionariaService service) {
        Veiculo c1 = new Carro("9BWZZZ377VT001", "Toyota", "Corolla XEi", 2026, 145000.00, 4, true);
        Veiculo m1 = new Moto("9C2KC2500R002", "Honda", "CB 300F Twister", 2026, 23500.00, 293, true);

        service.cadastrarVeiculo(c1);
        service.cadastrarVeiculo(m1);

        // Avança o Corolla por duas etapas para já iniciar disponível para venda
        service.avancarFluxoMontagem("9BWZZZ377VT001");
        service.avancarFluxoMontagem("9BWZZZ377VT001");
    }
}