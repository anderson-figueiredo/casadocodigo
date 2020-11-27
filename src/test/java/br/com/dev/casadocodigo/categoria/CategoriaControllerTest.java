package br.com.dev.casadocodigo.categoria;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.dev.casadocodigo.categoria.NovaCategoriaRequestBuilder.umaCategoria;
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

        String json = umaCategoria().comNome("existente").criaCategoriaComoJson();

        mockMvc.perform(post("/categorias").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void erro_nome_nulo_ou_vazio(String nome) throws Exception {
        String json = umaCategoria().comNome(nome).criaCategoriaComoJson();

        mockMvc.perform(post("/categorias").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());
    }

    @Test
    void criando_categoria_com_sucesso() throws Exception {
        String json = umaCategoria().comNome("Programação").criaCategoriaComoJson();

        mockMvc.perform(post("/categorias").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }
}