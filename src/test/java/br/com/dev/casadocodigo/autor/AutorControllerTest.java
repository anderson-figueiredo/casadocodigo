package br.com.dev.casadocodigo.autor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
                .andExpect(status().is(422));
    }

    @Test
    void grava_autor_sucesso() throws Exception {
        String formJson = umAutor()
                .chamado("Alexandre")
                .comEmail("alexandre@email.com")
                .comDescricao("autor da apostila de SOLID")
                .comoJson();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/autor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson))
                .andExpect(status().isOk());
    }

    @Test
    void email_invalido() throws Exception {
        String formJson = umAutor()
                .chamado("João")
                .comEmail("joao")
                .comDescricao("autor da apostila de Kubernetes")
                .comoJson();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/autor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void erro_quantidade_de_caracteres_na_descricao() throws Exception {
        String formJson = umAutor()
                .chamado("João")
                .comEmail("joao@email.com")
                .comDescricao("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec aliquam vehicula odio, id posuere urna. Nunc ultricies ornare sodales. Mauris congue eros quam, et egestas magna malesuada vitae. Sed fringilla vestibulum congue. Etiam dignissim, lectus non venenatis volutpat, tellus lectus malesuada sapien, nec maximus ipsum nunc sollicitudin massa. Maecenas ut purus erat. Curabitur eu faucibus dui. Nam hendrerit efficitur elit, ut varius dui gravida et. Donec mi tellus, ultrices vel tristique nec, finibus vitae tortor. Nunc convallis consectetur est sit amet pretium. Nam ut bibendum nibh. Pellentesque gravida libero ullamcorper lorem fringilla sagittis.\n" +
                        "\n" +
                        "Morbi rhoncus feugiat erat, eu interdum arcu sagittis eleifend. Aliquam volutpat molestie molestie. Quisque eleifend erat sed leo tincidunt, vel scelerisque tellus convallis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Phasellus quis nulla dignissim, faucibus tellus a, congue tellus. Donec bibendum ut mi ac molestie. Maecenas lorem tortor, viverra at ligula pharetra, feugiat mollis magna. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.\n" +
                        "\n" +
                        "Ut blandit pharetra risus. Nam eget nulla velit. Quisque suscipit eget massa vitae vestibulum. Quisque eget ligula sed metus sagittis cursus sed et nisi. Curabitur vulputate, ante vel aliquet blandit, nunc eros finibus eros, in venenatis nulla ipsum et orci. Curabitur in nulla ut dui scelerisque ultrices eget vel urna. Maecenas ornare vestibulum lectus, in lobortis tortor maximus nec. Donec et purus in neque ultricies tincidunt. Nunc nisi felis, posuere ac tincidunt non, fringilla nec arcu. In ut lobortis massa. Maecenas vitae metus vitae velit lobortis blandit eu eu risus.\n" +
                        "\n" +
                        "Nam id ultricies ante. Morbi arcu erat, blandit eu dolor eu, posuere luctus eros. Quisque quis dui metus. Donec leo erat, mattis in leo ac, tincidunt tempus nulla. Curabitur eu hendrerit dolor. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer quis diam vel erat porttitor sollicitudin id eu dui. Maecenas dapibus, mi vitae luctus sollicitudin, augue tellus interdum dui, ut bibendum elit lectus consectetur nibh. Sed quis enim turpis. Pellentesque pulvinar enim id mauris volutpat pharetra. Morbi ut lorem mi. Nulla ac purus at turpis hendrerit rutrum.\n" +
                        "\n" +
                        "Vestibulum sit amet est congue, tincidunt ante a, pretium nibh. Duis condimentum nulla accumsan blandit maximus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nam ut purus eget tellus lacinia interdum ut at massa. Proin tempus, nulla vel pretium luctus, eros nisl pretium velit, at tempor ipsum odio a augue. Interdum et malesuada fames ac ante ipsum primis.")
                .comoJson();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/autor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void erro_descricao_nulo_ou_vazia(String descricao) throws Exception {
        String formJson = umAutor()
                .chamado("João")
                .comEmail("joao@email.com")
                .comDescricao(descricao)
                .comoJson();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/autor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void erro_email_nulo_ou_vazio(String email) throws Exception {
        String formJson = umAutor()
                .chamado("João")
                .comEmail(email)
                .comDescricao("qualquer coisa bem pequena")
                .comoJson();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/autor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void erro_nome_nulo_ou_vazio(String nome) throws Exception {
        String formJson = umAutor()
                .chamado(nome)
                .comEmail("geovani@email.com")
                .comDescricao("qualquer coisa bem pequena")
                .comoJson();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/autor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formJson))
                .andExpect(status().isBadRequest());
    }
}