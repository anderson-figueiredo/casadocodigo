package br.com.dev.casadocodigo.livro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {
    boolean existsByTitulo(String titulo);

    boolean existsByIsbn(String isbn);
}
