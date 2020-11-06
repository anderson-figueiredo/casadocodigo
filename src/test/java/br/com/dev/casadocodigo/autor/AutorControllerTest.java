package br.com.dev.casadocodigo.autor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.dev.casadocodigo.autor.AutorBuilder.umAutor;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    void erro_email_unico() throws Exception {
        autorRepository.save(umAutor().chamado("Gabriel").comEmail("email@jaexiste.com").comDescricao("autor de ficcao cientifica").cria());

        String jsonAutorComEmailQueJaExiste = umAutor()
                .chamado("Alexandre")
                .comEmail("email@jaexiste.com")
                .comDescricao("autor da apostila de SOLID")
                .comoJson();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/autor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAutorComEmailQueJaExiste))
                .andExpect(status().isBadRequest());
    }
}