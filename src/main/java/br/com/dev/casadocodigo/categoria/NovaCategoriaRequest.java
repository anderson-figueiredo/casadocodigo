package br.com.dev.casadocodigo.categoria;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria toEntity() {
        return new Categoria(this.nome);
    }

    public String getNome() {
        return this.nome;
    }
}
