package br.com.dev.casadocodigo.autor;

import javax.validation.constraints.*;

class AutorForm {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @Size(max = 400)
    @NotBlank
    private String descricao;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Autor toEntity() {
        return new Autor(this.nome, this.email, this.descricao);
    }

    public String getEmail() {
        return this.email;
    }
}
