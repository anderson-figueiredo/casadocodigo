package br.com.dev.casadocodigo.categoria;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NovaCategoriaRequestBuilder {

    public NovaCategoriaRequest umaNovaCategoriaRequestBuilder;


    public static NovaCategoriaRequestBuilder umaCategoria(){
        return new NovaCategoriaRequestBuilder();
    }

    public nova

    public String criaCategoriaComoJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
