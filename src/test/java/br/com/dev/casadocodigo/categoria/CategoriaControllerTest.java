package br.com.dev.casadocodigo.categoria;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void erro_nome_unico() throws Exception {

        categoriaRepository.save(new Categoria("existente"));
        mockMvc.perform(post("/categorias").contentType(MediaType.APPLICATION_JSON).content("{\"nome\":\"existente\"}")).andExpect(status().isBadRequest());
    }
}