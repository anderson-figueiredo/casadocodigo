package br.com.dev.casadocodigo.autor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.*;

@SpringBootTest
@AutoConfigureMockMvc
class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void erro() {
        AutorForm emptyForm = new AutorForm();
//        mockMvc.perform()
    }

    @Test
    void erro_email_invalido() { }

    @Test
    void erro_qtd_caracteres_na_descricao() { }

    @Test
    void sucesso() { }



}