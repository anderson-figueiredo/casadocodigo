package br.com.dev.casadocodigo.autor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
        autorRepository.save(new Autor("Gabriel", "email@jaexiste.com", "autor de ficcao cientifica"));

        AutorForm autorComEmailQueJaExiste = new AutorForm();
        autorComEmailQueJaExiste.setNome("Alexandre");
        autorComEmailQueJaExiste.setEmail("email@jaexiste.com");
        autorComEmailQueJaExiste.setDescricao("autor do treinamento");

        String jsonAutorComEmailQueJaExiste = new ObjectMapper().writeValueAsString(autorComEmailQueJaExiste);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/autor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAutorComEmailQueJaExiste))
                .andExpect(status().isBadRequest());
    }
}