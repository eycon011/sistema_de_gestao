package model;

/* Subclasse que herda de Veiculo e aplica regras específicas de montagem e precificação de motocicletas */
public class Moto extends Veiculo {
    private int cilindradas;
    private boolean freioAbs;

    public Moto(String chassi, String marca, String modelo, int ano, double precoBase,
                int cilindradas, boolean freioAbs) {
        super(chassi, marca, modelo, ano, precoBase);
        this.cilindradas = cilindradas;
        this.freioAbs = freioAbs;
    }

    @Override
    public double calcularPrecoFinal() {
        // Aplica 5% de encargos/logística sobre o valor base + adicional do sistema ABS
        double imposto = getPrecoBase() * 0.05;
        double adicionalAbs = freioAbs ? 1800.00 : 0.00;
        return getPrecoBase() + imposto + adicionalAbs;
    }

    @Override
    public void executarEtapaMontagem() {
        switch (getStatus()) {
            case AGUARDANDO_MONTAGEM:
                setStatus(StatusVeiculo.EM_MONTAGEM);
                System.out.println("[MONTAGEM MOTO] Iniciando preparação de " + getModelo()
                        + " (" + cilindradas + "cc): instalação da roda dianteira, guidão e calibração de suspensão.");
                break;
            case EM_MONTAGEM:
                setStatus(StatusVeiculo.DISPONIVEL);
                System.out.println("[MONTAGEM MOTO] Teste de partida e freios concluído para " + getModelo()
                        + ". Motocicleta liberada para venda.");
                break;
            case DISPONIVEL:
                System.out.println("[AVISO] A moto " + getModelo() + " já está pronta para venda.");
                break;
            case VENDIDO:
                System.out.println("[AVISO] A moto " + getModelo() + " já foi vendida.");
                break;
        }
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public boolean isFreioAbs() {
        return freioAbs;
    }
}