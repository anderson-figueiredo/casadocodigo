package br.com.dev.casadocodigo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Autor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String descricao;

    public Autor(){}

    public Autor(String nome, String email, String descricao){
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

}
