package br.com.dev.casadocodigo.autor;

import br.com.dev.casadocodigo.SpringIntegrationTest;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;

import static br.com.dev.casadocodigo.autor.AutorBuilder.umAutor;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static net.javacrumbs.jsonunit.spring.JsonUnitResultMatchers.json;

public class AutorTest extends SpringIntegrationTest {
    private AutorBuilder autorBuilder = umAutor();

    private ResultActions resultActions;

    @Dado("um autor com nome {string}")
    public void um_autor_com_nome(String nome) {
        autorBuilder.chamado(nome);
    }

    @E("de email {string}")
    public void deEmail(String email) {
        autorBuilder.comEmail(email);
    }

    @E("com descricao {string}")
    public void comDescricao(String descricao) {
        autorBuilder.comDescricao(descricao);
    }

    @Quando("tenta salva o autor")
    public void salvaOAutor() throws Exception {
        String autorJson = autorBuilder.comoJson();
        resultActions = fazPostParaComJson("/autor", autorJson);
    }

    @Entao("deve ter sucesso")
    public void deveTerSucesso() throws Exception {
        resultActions.andExpect(status().isOk());
    }

    @Dado("um autor com tudo preenchido")
    public void umAutorComTudoPreenchido() {
        autorBuilder = umAutor()
                .chamado("Carlos")
                .comEmail("carkis@email.com")
                .comDescricao("autor da apostila de java");
    }

    @Quando("tenta salva o autor novamente")
    public void tentaSalvaOAutorNovamente() throws Exception {
        resultActions = fazPostParaComJson("/autor", autorBuilder.comoJson());
    }

    @Entao("deve dar mensagem de erro")
    public void deveDarMensagemDeErro() throws Exception {
        resultActions.andExpect(status().is(422))
                .andExpect(json().node("message").isEqualTo("Email já existente"));
    }
}
