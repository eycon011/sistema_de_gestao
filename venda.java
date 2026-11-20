package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/* Registro imutável de uma transação comercial concluída */
public class Venda {
    private static int contadorId = 1;

    private final int idVenda;
    private final Veiculo veiculo;
    private final Cliente cliente;
    private final double valorFinal;
    private final LocalDateTime dataVenda;

    public Venda(Veiculo veiculo, Cliente cliente) {
        this.idVenda = contadorId++;
        this.veiculo = veiculo;
        this.cliente = cliente;
        // Captura o valor calculado polimorficamente no momento da venda
        this.valorFinal = veiculo.calcularPrecoFinal();
        this.dataVenda = LocalDateTime.now();
    }

    public void finalizarStatusVeiculo() {
        // Método auxiliar para marcar o veículo como vendido após registrar a venda
        veiculo.setStatus(StatusVeiculo.VENDIDO);
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public String gerarComprovante() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format(
                "Venda #%d | Data: %s | Cliente: %s (CPF: %s) | Veículo: %s %s (Chassi: %s) | Total Pago: R$ %.2f",
                idVenda,
                dataVenda.format(formatter),
                cliente.getNome(),
                cliente.getCpf(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getChassi(),
                valorFinal
        );
    }
}