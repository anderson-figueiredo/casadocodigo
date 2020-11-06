package br.com.dev.casadocodigo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class AutorController {

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid AutorForm autorForm) {

    }

}
