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

@DataJpaTest
public class IsbnUnicoValidatorTest {

    private LivroRepositorio livroRepositorio;
    private Errors errors;

    @BeforeEach
    void setUp() {
        this.livroRepositorio = mock(LivroRepositorio.class);
        this.errors = mock(Errors.class);
    }

    @Test
    void validate__nao_deve_conter_erro_caso_isbn_nao_exista() {
        IsbnUnicoValidator isbnUnicoValidator = new IsbnUnicoValidator(livroRepositorio);

        when(livroRepositorio.existsByIsbn("123456789")).thenReturn(true);

        NovoLivroRequest novoLivroRequest = new NovoLivroRequest();


        isbnUnicoValidator.validate(novoLivroRequest, errors);

        verify(errors, never()).rejectValue("isbn", "isbn.ja.existe");
    }
}
