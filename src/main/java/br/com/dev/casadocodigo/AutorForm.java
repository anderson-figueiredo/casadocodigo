package br.com.dev.casadocodigo;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

public class AutorForm {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @Max(value=400)
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
}
