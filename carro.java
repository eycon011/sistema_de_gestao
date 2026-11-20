package model;

/* Subclasse que herda de Veiculo e aplica regras específicas de montagem e precificação de automóveis */
public class Carro extends Veiculo {
    private int numeroPortas;
    private boolean cambioAutomatico;

    public Carro(String chassi, String marca, String modelo, int ano, double precoBase,
                 int numeroPortas, boolean cambioAutomatico) {
        super(chassi, marca, modelo, ano, precoBase);
        this.numeroPortas = numeroPortas;
        this.cambioAutomatico = cambioAutomatico;
    }

    @Override
    public double calcularPrecoFinal() {
        // Aplica 8% de encargos/logística sobre o valor base + adicional de câmbio automático
        double imposto = getPrecoBase() * 0.08;
        double adicionalCambio = cambioAutomatico ? 4500.00 : 0.00;
        return getPrecoBase() + imposto + adicionalCambio;
    }

    @Override
    public void executarEtapaMontagem() {
        switch (getStatus()) {
            case AGUARDANDO_MONTAGEM:
                setStatus(StatusVeiculo.EM_MONTAGEM);
                System.out.println("[MONTAGEM CARRO] Iniciando linha de montagem de " + getModelo()
                        + ": acoplamento de eixos, alinhamento e revisão de fluidos.");
                break;
            case EM_MONTAGEM:
                setStatus(StatusVeiculo.DISPONIVEL);
                System.out.println("[MONTAGEM CARRO] Inspeção final concluída para " + getModelo()
                        + " (" + numeroPortas + " portas). Veículo liberado para venda.");
                break;
            case DISPONIVEL:
                System.out.println("[AVISO] O carro " + getModelo() + " já está pronto para venda.");
                break;
            case VENDIDO:
                System.out.println("[AVISO] O carro " + getModelo() + " já foi vendido.");
                break;
        }
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public boolean isCambioAutomatico() {
        return cambioAutomatico;
    }
}