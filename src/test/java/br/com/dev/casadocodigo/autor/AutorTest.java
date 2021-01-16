package br.com.dev.casadocodigo.autor;

import br.com.dev.casadocodigo.SpringIntegrationTest;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static br.com.dev.casadocodigo.autor.AutorBuilder.umAutor;
import static net.javacrumbs.jsonunit.spring.JsonUnitResultMatchers.json;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AutorTest extends SpringIntegrationTest {
    private AutorBuilder autorBuilder = umAutor();
    private ResultActions resultActions;

    @After
    public void tearDown(){
        autorBuilder = umAutor();
    }

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

    @Dado("um autor sem nome")
    public void umAutorSemNome() {
        autorBuilder.chamado(null);
    }

    @E("sem email")
    public void semEmail() {
        autorBuilder.comEmail(null);
    }

    @E("sem descricao")
    public void semDescricao() {
        autorBuilder.comDescricao(null);
    }

    @E("com descricao muito grande")
    public void comDescricaoMuitoGrande() {
        autorBuilder.comDescricao("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec aliquam vehicula odio, id posuere urna. Nunc ultricies ornare sodales. Mauris congue eros quam, et egestas magna malesuada vitae. Sed fringilla vestibulum congue. Etiam dignissim, lectus non venenatis volutpat, tellus lectus malesuada sapien, nec maximus ipsum nunc sollicitudin massa. Maecenas ut purus erat. Curabitur eu faucibus dui. Nam hendrerit efficitur elit, ut varius dui gravida et. Donec mi tellus, ultrices vel tristique nec, finibus vitae tortor. Nunc convallis consectetur est sit amet pretium. Nam ut bibendum nibh. Pellentesque gravida libero ullamcorper lorem fringilla sagittis.\n" +
                "\n" +
                "Morbi rhoncus feugiat erat, eu interdum arcu sagittis eleifend. Aliquam volutpat molestie molestie. Quisque eleifend erat sed leo tincidunt, vel scelerisque tellus convallis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Phasellus quis nulla dignissim, faucibus tellus a, congue tellus. Donec bibendum ut mi ac molestie. Maecenas lorem tortor, viverra at ligula pharetra, feugiat mollis magna. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.\n" +
                "\n" +
                "Ut blandit pharetra risus. Nam eget nulla velit. Quisque suscipit eget massa vitae vestibulum. Quisque eget ligula sed metus sagittis cursus sed et nisi. Curabitur vulputate, ante vel aliquet blandit, nunc eros finibus eros, in venenatis nulla ipsum et orci. Curabitur in nulla ut dui scelerisque ultrices eget vel urna. Maecenas ornare vestibulum lectus, in lobortis tortor maximus nec. Donec et purus in neque ultricies tincidunt. Nunc nisi felis, posuere ac tincidunt non, fringilla nec arcu. In ut lobortis massa. Maecenas vitae metus vitae velit lobortis blandit eu eu risus.\n" +
                "\n" +
                "Nam id ultricies ante. Morbi arcu erat, blandit eu dolor eu, posuere luctus eros. Quisque quis dui metus. Donec leo erat, mattis in leo ac, tincidunt tempus nulla. Curabitur eu hendrerit dolor. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer quis diam vel erat porttitor sollicitudin id eu dui. Maecenas dapibus, mi vitae luctus sollicitudin, augue tellus interdum dui, ut bibendum elit lectus consectetur nibh. Sed quis enim turpis. Pellentesque pulvinar enim id mauris volutpat pharetra. Morbi ut lorem mi. Nulla ac purus at turpis hendrerit rutrum.\n" +
                "\n" +
                "Vestibulum sit amet est congue, tincidunt ante a, pretium nibh. Duis condimentum nulla accumsan blandit maximus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nam ut purus eget tellus lacinia interdum ut at massa. Proin tempus, nulla vel pretium luctus, eros nisl pretium velit, at tempor ipsum odio a augue. Interdum et malesuada fames ac ante ipsum primis.");
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

    @Entao("deve dar erro em {string} com mensagem {string}")
    public void deveDarErroComMensagem(String campo, String mensagemDeErro) throws Exception {
        resultActions.andExpect(status().isBadRequest())
                .andExpect(json().node(campo).isEqualTo(List.of(mensagemDeErro)));
    }


}
