package model;

/* Enum responsável por controlar os estágios do ciclo de vida do veículo na concessionária */
public enum StatusVeiculo {
    AGUARDANDO_MONTAGEM("Aguardando Montagem"),
    EM_MONTAGEM("Em Linha de Montagem / PDI"),
    DISPONIVEL("Disponível para Venda"),
    VENDIDO("Vendido");

    private final String descricao;

    StatusVeiculo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}