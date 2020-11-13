package br.com.dev.casadocodigo.categoria;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nome) {
        Assert.hasText(nome, "Nome da categoria deve ser preenchido");
        this.nome = nome;
    }
}
