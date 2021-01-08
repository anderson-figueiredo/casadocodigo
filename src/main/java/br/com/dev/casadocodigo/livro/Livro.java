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
    private int numeroDePaginas;

    @NotBlank @ISBN @Column(unique = true)
    private String isbn;
    private LocalDate dataDePublicacao;

    @NotNull @Valid
    private Categoria categoria;

    @NotNull
    private Autor autor;
}
