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
    public ResponseEntity<?> create(@RequestBody @Valid AutorForm autorForm) {

        if(autorRepository.existsByEmail(autorForm.getEmail())) {
            return ResponseEntity.badRequest().body("Email já existe");
        }
        autorRepository.save(autorForm.toEntity());

        return ResponseEntity.ok().build();
    }

}
