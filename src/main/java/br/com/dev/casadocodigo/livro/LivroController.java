package br.com.dev.casadocodigo.livro;

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

    @InitBinder("novoLivroRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new TituloLivroUnicoValidator(livroRepositorio));
    }

    @PostMapping
    public ResponseEntity cria(@Valid @RequestBody NovoLivroRequest novoLivroRequest) {
        return ResponseEntity.ok().build();
    }
}
