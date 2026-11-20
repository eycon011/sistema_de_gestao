package model;

/* Entidade encapsulada que representa o comprador */
public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;

    public Cliente(String nome, String cpf, String telefone) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }
        this.nome = nome.trim();
        this.cpf = cpf.trim();
        this.telefone = telefone.trim();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }
}