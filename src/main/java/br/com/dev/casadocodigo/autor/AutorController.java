package br.com.dev.casadocodigo.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    public ResponseEntity<?> cria(@RequestBody @Valid NovoAutorRequest novoAutorRequest) {

        if(autorRepository.existsByEmail(novoAutorRequest.getEmail())) {
            return ResponseEntity.status(422).body("{ \"message\": \"Email já existente\"}");
        }
        autorRepository.save(novoAutorRequest.toEntity());

        return ResponseEntity.ok().build();
    }

}
