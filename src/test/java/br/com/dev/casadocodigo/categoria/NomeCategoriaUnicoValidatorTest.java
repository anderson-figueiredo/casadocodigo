package br.com.dev.casadocodigo.categoria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.when;

class NomeCategoriaUnicoValidatorTest {

    private CategoriaRepository categoriaRepository;
    private Errors errors;
    private NovaCategoriaRequest novaCategoriaRequest;
    private NomeCategoriaUnicoValidator nomeCategoriaUnicoValidator;

    @BeforeEach
    public void setup(){
        this.categoriaRepository = Mockito.mock(CategoriaRepository.class);
        this.errors = Mockito.mock(Errors.class);
        this.novaCategoriaRequest = Mockito.mock(NovaCategoriaRequest.class);
        this.nomeCategoriaUnicoValidator = new NomeCategoriaUnicoValidator(categoriaRepository);
    }

    @Test
    public void validate__deveria_registrar_erro_se_nome_ja_existente(){
        when();
    }

}