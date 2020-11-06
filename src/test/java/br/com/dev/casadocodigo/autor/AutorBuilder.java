package br.com.dev.casadocodigo.autor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AutorBuilder {

    @JsonProperty
    private String nome;

    @JsonProperty
    private String email;

    @JsonProperty
    private String descricao;

    public AutorBuilder chamado(String nome) {
        this.nome = nome;
        return this;
    }

    public AutorBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public AutorBuilder comDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public static AutorBuilder umAutor() {
        return new AutorBuilder();
    }

    public Autor cria() {
        return new Autor(this.nome, this.email, this.descricao);
    }

    public String comoJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
