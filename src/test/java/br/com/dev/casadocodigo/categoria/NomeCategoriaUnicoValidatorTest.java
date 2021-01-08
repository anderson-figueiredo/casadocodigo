package br.com.dev.casadocodigo.categoria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class NomeCategoriaUnicoValidatorTest {

    private CategoriaRepository categoriaRepository;
    private Errors errors;
    private NovaCategoriaRequest novaCategoriaRequest;
    private NomeCategoriaUnicoValidator nomeCategoriaUnicoValidator;

    @BeforeEach
    public void setup(){
        this.categoriaRepository = mock(CategoriaRepository.class);
        this.errors = mock(Errors.class);
        this.novaCategoriaRequest = mock(NovaCategoriaRequest.class);
        this.nomeCategoriaUnicoValidator = new NomeCategoriaUnicoValidator(categoriaRepository);
    }

    @Test
    public void validate__deveria_registrar_erro_se_nome_ja_existente(){
        when(novaCategoriaRequest.getNome()).thenReturn("Mobile");
        when(categoriaRepository.existsByNome("Mobile")).thenReturn(true);
        nomeCategoriaUnicoValidator.validate(novaCategoriaRequest, errors);
        verify(errors, times(1)).rejectValue("nome", "categoria.nome.unico");
    }

    @Test
    public void validate__nao_deveria_registrar_erro_se_nome_nao_existente(){
        when(novaCategoriaRequest.getNome()).thenReturn("Design");
        when(categoriaRepository.existsByNome("Design")).thenReturn(false);
        nomeCategoriaUnicoValidator.validate(novaCategoriaRequest, errors);
        verify(errors, never()).rejectValue("nome", "categoria.nome.unico");
    }

}