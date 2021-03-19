package br.com.dev.casadocodigo.livro;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class IsbnUnicoValidator implements Validator {

    private final LivroRepositorio livroRepositorio;

    public IsbnUnicoValidator(LivroRepositorio livroRepositorio) {
        this.livroRepositorio = livroRepositorio;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NovoLivroRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NovoLivroRequest request = (NovoLivroRequest) o;


    }
}
