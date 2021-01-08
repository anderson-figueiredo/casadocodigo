package br.com.dev.casadocodigo.livro;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TituloLivroUnicoValidator implements Validator {
    private LivroRepositorio livroRepositorio;

    public TituloLivroUnicoValidator(LivroRepositorio livroRepositorio) {
        this.livroRepositorio = livroRepositorio;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NovoLivroRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NovoLivroRequest novoLivroRequest = (NovoLivroRequest) o;

        if(livroRepositorio.existsByTitulo(novoLivroRequest.getTitulo())) {
            errors.rejectValue("titulo", "titulo.ja.existe");
        }
    }
}
