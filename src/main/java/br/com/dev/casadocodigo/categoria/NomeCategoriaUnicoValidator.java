package br.com.dev.casadocodigo.categoria;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class NomeCategoriaUnicoValidator implements Validator {

    private CategoriaRepository categoriaRepository;

    public NomeCategoriaUnicoValidator(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCategoriaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NovaCategoriaRequest novaCategoriaRequest = (NovaCategoriaRequest) o;

    }
}
