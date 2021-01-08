package br.com.dev.casadocodigo.livro;

import br.com.dev.casadocodigo.autor.Autor;
import br.com.dev.casadocodigo.categoria.Categoria;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.GenerationType.*;

@Entity
public class Livro {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank @Column(unique = true)
    private String titulo;

    @NotBlank @Size(max = 500)
    private String resumo;
    private String sumario;

    @NotNull @DecimalMin("20")
    private BigDecimal preco;
    @Min(100)
    private int numeroDePaginas;

    @NotBlank @Column(unique = true)
    private String isbn;

    private LocalDate dataDePublicacao;

    @ManyToOne
    @NotNull @Valid
    private Categoria categoria;

    @ManyToOne
    @NotNull @Valid
    private Autor autor;

    @Deprecated
    public Livro() {
    }

    public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
                 @NotNull @DecimalMin("20") BigDecimal preco, @Min(100) int numeroDePaginas,
                 @NotBlank String isbn, LocalDate dataDePublicacao, @NotNull @Valid Categoria categoria,
                 @NotNull @Valid Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataDePublicacao = dataDePublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }
}
