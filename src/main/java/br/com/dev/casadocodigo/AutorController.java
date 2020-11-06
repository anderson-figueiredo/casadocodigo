package br.com.dev.casadocodigo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid AutorForm autorForm, BindingResult result) {

        if(result.hasErrors()){
            // TODO: :)
        }

        autorRepository.save(autorForm.toEntity());

        return ResponseEntity.ok().build();

    }

}
