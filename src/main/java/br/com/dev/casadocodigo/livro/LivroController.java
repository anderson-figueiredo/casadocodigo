package br.com.dev.casadocodigo.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro")
public class LivroController {


    @PostMapping
    public ResponseEntity cria(NovoLivroRequest novoLivroRequest){
        return ResponseEntity.ok().build();
    }
}
