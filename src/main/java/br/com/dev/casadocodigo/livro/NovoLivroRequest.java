package br.com.dev.casadocodigo.livro;

import br.com.dev.casadocodigo.autor.Autor;
import br.com.dev.casadocodigo.autor.AutorRepository;
import br.com.dev.casadocodigo.categoria.Categoria;
import br.com.dev.casadocodigo.categoria.CategoriaRepository;
import br.com.dev.casadocodigo.exception.NotFoundException;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivroRequest {

    @NotBlank
    private String titulo;

    @NotBlank @Size(max = 500)
    private String resumo;
    private String sumario;

    @NotNull
    @DecimalMin("20")
    private BigDecimal preco;
    @Min(100)
    private int numeroDePaginas;

    @NotBlank
    private String isbn;

    @Future
    private LocalDate dataDePublicacao;

    @NotNull
    private Long categoriaId;

    @NotNull
    private Long autorId;

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public Livro toEntity(Categoria categoria, Autor autor) {
        return new Livro(titulo, resumo, sumario, preco, numeroDePaginas, isbn, dataDePublicacao, categoria, autor);
    }

    public Long getCategoryId() {
        return categoriaId;
    }

    public Long getAutorId() {
        return autorId;
    }
}
