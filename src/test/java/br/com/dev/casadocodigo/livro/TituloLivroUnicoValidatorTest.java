package br.com.dev.casadocodigo.livro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TituloLivroUnicoValidatorTest {

    private LivroRepositorio livroRepositorio;
    private TituloLivroUnicoValidator tituloLivroUnicoValidator;
    private Errors errors;
    private NovoLivroRequest novoLivroRequest;

    @BeforeEach
    public void setUp() {
        livroRepositorio = mock(LivroRepositorio.class);
        tituloLivroUnicoValidator = new TituloLivroUnicoValidator(livroRepositorio);
        errors = mock(Errors.class);
        novoLivroRequest = mock(NovoLivroRequest.class);
    }

    @Test
    public void validate__deve_lancar_erro_se_titulo_ja_existe() {
        when(novoLivroRequest.getTitulo()).thenReturn("Spring Boot");
        when(livroRepositorio.existsByTitulo("Spring Boot")).thenReturn(true);
        tituloLivroUnicoValidator.validate(novoLivroRequest, errors);
        verify(errors, times(1)).rejectValue("titulo", "titulo.ja.existe");
    }

}