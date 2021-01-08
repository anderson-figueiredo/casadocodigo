package br.com.dev.casadocodigo.livro;

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



}
