package service;

import model.Cliente;
import model.StatusVeiculo;
import model.Veiculo;
import model.Venda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* Camada de regras de negócio para gerenciamento de estoque, linha de montagem e vendas */
public class ConcessionariaService {
    private final List<Veiculo> estoque;
    private final List<Venda> historicoVendas;

    public ConcessionariaService() {
        this.estoque = new ArrayList<>();
        this.historicoVendas = new ArrayList<>();
    }

    // Cadastra um novo veículo verificando duplicidade de chassi
    public boolean cadastrarVeiculo(Veiculo novoVeiculo) {
        if (buscarPorChassi(novoVeiculo.getChassi()).isPresent()) {
            System.out.println("[ERRO] Já existe um veículo cadastrado com o chassi " + novoVeiculo.getChassi());
            return false;
        }
        estoque.add(novoVeiculo);
        System.out.println("[SUCESSO] Veículo cadastrado no estoque: " + novoVeiculo.getMarca() + " " + novoVeiculo.getModelo());
        return true;
    }

    public Optional<Veiculo> buscarPorChassi(String chassi) {
        return estoque.stream()
                .filter(v -> v.getChassi().equalsIgnoreCase(chassi.trim()))
                .findFirst();
    }

    // Executa o próximo passo do fluxo de montagem usando polimorfismo
    public void avancarFluxoMontagem(String chassi) {
        Optional<Veiculo> veiculoOpt = buscarPorChassi(chassi);
        if (veiculoOpt.isEmpty()) {
            System.out.println("[ERRO] Veículo com chassi " + chassi + " não encontrado.");
            return;
        }
        veiculoOpt.get().executarEtapaMontagem();
    }

    // Valida se o veículo passou pela montagem e efetua a venda
    public boolean realizarVenda(String chassi, Cliente cliente) {
        Optional<Veiculo> veiculoOpt = buscarPorChassi(chassi);
        if (veiculoOpt.isEmpty()) {
            System.out.println("[ERRO] Veículo não encontrado no sistema.");
            return false;
        }

        Veiculo veiculo = veiculoOpt.get();

        // Regra de negócio: veículos não montados ou já vendidos não podem ser comercializados
        if (veiculo.getStatus() != StatusVeiculo.DISPONIVEL) {
            System.out.println("[BLOQUEADO] Venda não permitida. Status atual do veículo: "
                    + veiculo.getStatus().getDescricao());
            return false;
        }

        Venda novaVenda = new Venda(veiculo, cliente);
        novaVenda.finalizarStatusVeiculo();
        historicoVendas.add(novaVenda);

        System.out.println("[VENDA CONCLUÍDA] " + novaVenda.gerarComprovante());
        return true;
    }

    public void listarEstoqueCompleto() {
        System.out.println("\n--- ESTOQUE ATUAL DA CONCESSIONÁRIA ---");
        if (estoque.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }
        for (Veiculo v : estoque) {
            System.out.println(v.exibirResumo());
        }
    }

    public void listarHistoricoVendas() {
        System.out.println("\n--- RELATÓRIO DE VENDAS ---");
        if (historicoVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada até o momento.");
            return;
        }
        double faturamentoTotal = 0.0;
        for (Venda venda : historicoVendas) {
            System.out.println(venda.gerarComprovante());
            faturamentoTotal += venda.getValorFinal();
        }
        System.out.printf("Faturamento Total Acumulado: R$ %.2f%n", faturamentoTotal);
    }
}