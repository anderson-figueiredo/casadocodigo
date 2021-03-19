package br.com.dev.casadocodigo.livro;

import br.com.dev.casadocodigo.autor.Autor;
import br.com.dev.casadocodigo.categoria.Categoria;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class IsbnUnicoValidatorTest {

    private LivroRepositorio livroRepositorio;
    private Errors errors;
    private NovoLivroRequest novoLivroRequest;
    private IsbnUnicoValidator isbnUnicoValidator;

    @BeforeEach
    void setUp() {
        this.livroRepositorio = mock(LivroRepositorio.class);
        this.errors = mock(Errors.class);
        this.novoLivroRequest = mock(NovoLivroRequest.class);
        when(novoLivroRequest.getIsbn()).thenReturn("123456789");
        this.isbnUnicoValidator = new IsbnUnicoValidator(livroRepositorio);
    }

    @Test
    void validate__nao_deve_conter_erro_caso_isbn_nao_exista() {
        when(livroRepositorio.existsByIsbn(novoLivroRequest.getIsbn())).thenReturn(false);
        isbnUnicoValidator.validate(novoLivroRequest, errors);

        verify(errors, never()).rejectValue("isbn", "isbn.ja.existe");
    }

    @Test
    void validate__deve_conter_erro_caso_isbn_exista() {
        when(livroRepositorio.existsByIsbn(novoLivroRequest.getIsbn())).thenReturn(true);
        isbnUnicoValidator.validate(novoLivroRequest, errors);

        verify(errors, times(1)).rejectValue("isbn", "isbn.ja.existe");
    }
}
