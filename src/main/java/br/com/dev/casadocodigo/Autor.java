package br.com.dev.casadocodigo;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Autor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @Max(value = 400)
    @NotBlank
    private String descricao;

    @Deprecated
    public Autor(){}

    public Autor(String nome, String email, String descricao){
        Assert.hasText(nome, "Nome do autor deve ser preenchido");
        Assert.hasText(email, "Email do autor deve ser preenchido");
        Assert.hasText(descricao, "Descrição do autor deve ser preenchido");
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

}
