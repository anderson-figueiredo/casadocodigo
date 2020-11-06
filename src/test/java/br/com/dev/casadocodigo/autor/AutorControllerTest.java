package br.com.dev.casadocodigo.autor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
    void erro_email_unico() {
        autorRepository.save(new Autor("Gabriel", "email@jaexiste.com", "autor de ficcao cientifica"));

        AutorForm emptyForm = new AutorForm();
        mockMvc.perform(MockMvcRequestBuilders.post("/autor").content("{\"nome\": \"alexandre\", \"email\": \"email@jaexiste.com\", " +
                "\"descricao\": \"lkjasddashdakhkdas\"}")).andExpect(status());
    }
}