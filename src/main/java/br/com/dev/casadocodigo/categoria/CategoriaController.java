package br.com.dev.casadocodigo.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> cria(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {
        if(categoriaRepository.existsByNome(novaCategoriaRequest.getNome())) {
            return ResponseEntity.badRequest().body("Categoria com este nome já existe");
        }

        Categoria categoria = novaCategoriaRequest.toEntity();
        categoriaRepository.save(categoria);
        return ResponseEntity.ok().build();
    }
}
