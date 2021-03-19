package br.com.dev.casadocodigo.livro;

import br.com.dev.casadocodigo.autor.Autor;
import br.com.dev.casadocodigo.categoria.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IsbnUnicoTest {

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void existeLivroComIsbn__deve_retornar_falso_caso_nao_exista_isbn() {
        Categoria terror = testEntityManager.persist(new Categoria("terror"));
        Autor autor = testEntityManager.persist(new Autor("Escritor", "a@a.com", "autor de livros"));
        Livro livro = testEntityManager.persist(new Livro("titulo", "livro de teste", "sumario",
                BigDecimal.valueOf(42L), 200, "123456789", LocalDate.now(), terror, autor));
    }
}
