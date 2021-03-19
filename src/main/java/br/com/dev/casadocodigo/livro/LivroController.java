package br.com.dev.casadocodigo.livro;

import br.com.dev.casadocodigo.autor.Autor;
import br.com.dev.casadocodigo.autor.AutorRepository;
import br.com.dev.casadocodigo.categoria.Categoria;
import br.com.dev.casadocodigo.categoria.CategoriaRepository;
import br.com.dev.casadocodigo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepositorio livroRepositorio;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @InitBinder("novoLivroRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new TituloLivroUnicoValidator(livroRepositorio), new IsbnUnicoValidator(livroRepositorio));
    }

    @PostMapping
    public ResponseEntity cria(@Valid @RequestBody NovoLivroRequest novoLivroRequest) {
        Categoria categoria = categoriaRepository.findById(novoLivroRequest.getCategoryId()).orElseThrow(NotFoundException::new);
        Autor autor = autorRepository.findById(novoLivroRequest.getAutorId()).orElseThrow(NotFoundException::new);

        Livro livro = novoLivroRequest.toEntity(categoria, autor);
        livroRepositorio.save(livro);

        return ResponseEntity.ok().build();
    }
}
