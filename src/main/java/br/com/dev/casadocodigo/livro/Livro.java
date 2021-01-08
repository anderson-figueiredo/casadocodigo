package br.com.dev.casadocodigo.livro;

import br.com.dev.casadocodigo.autor.Autor;
import br.com.dev.casadocodigo.categoria.Categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.GenerationType.*;

@Entity
public class Livro {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private int numeroDePaginas;
    private String isbn;
    private LocalDate dataDePublicacao;
    private Categoria categoria;
    private Autor autor;
}
