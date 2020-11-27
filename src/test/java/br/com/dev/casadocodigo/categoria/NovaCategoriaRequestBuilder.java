package br.com.dev.casadocodigo.categoria;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NovaCategoriaRequestBuilder {

    @JsonProperty
    private String nome;

    public static NovaCategoriaRequestBuilder umaCategoria(){
        return new NovaCategoriaRequestBuilder();
    }

    public NovaCategoriaRequestBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String criaCategoriaComoJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
