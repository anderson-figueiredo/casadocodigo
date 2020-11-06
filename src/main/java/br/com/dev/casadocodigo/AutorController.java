package br.com.dev.casadocodigo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid AutorForm autorForm) {

//        if(result.hasErrors()){
//            // TODO: :)
//            return ResponseEntity.badRequest().build();
//        }

        autorRepository.save(autorForm.toEntity());

        return ResponseEntity.ok().build();

    }

}
