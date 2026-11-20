package model;

/* Classe base abstrata que define o contrato e os atributos comuns de qualquer veículo */
public abstract class Veiculo {
    private String chassi;
    private String marca;
    private String modelo;
    private int ano;
    private double precoBase;
    private StatusVeiculo status;

    public Veiculo(String chassi, String marca, String modelo, int ano, double precoBase) {
        if (chassi == null || chassi.trim().isEmpty()) {
            throw new IllegalArgumentException("O número do chassi é obrigatório.");
        }
        if (precoBase <= 0) {
            throw new IllegalArgumentException("O preço base deve ser maior que zero.");
        }

        this.chassi = chassi.toUpperCase().trim();
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.precoBase = precoBase;
        // Todo veículo entra no estoque aguardando o fluxo de montagem/preparação
        this.status = StatusVeiculo.AGUARDANDO_MONTAGEM;
    }

    // Métodos abstratos que obrigam as subclasses a implementarem suas próprias regras (Polimorfismo)
    public abstract double calcularPrecoFinal();

    public abstract void executarEtapaMontagem();

    // Getters e Setters encapsulados
    public String getChassi() {
        return chassi;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        if (precoBase > 0) {
            this.precoBase = precoBase;
        }
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    // Método protegido para permitir alteração controlada de status pelas subclasses e pacote
    protected void setStatus(StatusVeiculo status) {
        this.status = status;
    }

    public String exibirResumo() {
        return String.format("[%s] %s %s (%d) | Chassi: %s | Status: %s | Preço Final: R$ %.2f",
                this.getClass().getSimpleName().toUpperCase(),
                marca,
                modelo,
                ano,
                chassi,
                status.getDescricao(),
                calcularPrecoFinal());
    }
}