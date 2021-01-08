package br.com.dev.casadocodigo.autor;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Autor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @Size(max = 400)
    @NotBlank
    private String descricao;

    @NotNull
    private LocalDateTime criadoEm = LocalDateTime.now();

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
