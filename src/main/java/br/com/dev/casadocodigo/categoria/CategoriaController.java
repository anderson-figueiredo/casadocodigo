package br.com.dev.casadocodigo.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @InitBinder("novaCategoriaRequest")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new NomeCategoriaUnicoValidator(categoriaRepository));
    }

    @PostMapping
    public ResponseEntity<?> cria(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {

        categoriaRepository.save(novaCategoriaRequest.toEntity());
        return ResponseEntity.ok().build();
    }
}
