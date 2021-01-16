# language: pt

@txn
Funcionalidade: Criacao de autores
  Cenario: deve criar um autor com sucesso
    Dado um autor com nome "Alexandre"
    E de email "alexandre@email.com"
    E com descricao "autor da apostila de SOLID"
    Quando tenta salva o autor
    Entao deve ter sucesso

  Cenario: Não deve deixar salvar autores com mesmo email
    Dado um autor com nome "Alexandre"
    E de email "alexandre@email.com"
    E com descricao "autor da apostila de SOLID"
    Quando tenta salva o autor
    Entao deve ter sucesso
    Quando tenta salva o autor novamente
    Entao deve dar mensagem de erro

  Cenario: Dado um email invalido não deve cadastrar autor
    Dado  um autor com nome "João"
    E de email "email invalido"
    E com descricao "não deveria ter colocado esse email"
    Quando tenta salva o autor
    Entao deve dar erro em "email" com mensagem "must be a well-formed email address"
    
  Cenario: Não deve cadastrar autor com nome vazio
    Dado um autor com nome ""
    E de email "nao-quero-dizer-meu-nome@gmail.com"
    E com descricao "quero ser mais privado"
    Quando tenta salva o autor
    Entao deve dar erro em "nome" com mensagem "must not be blank"

  Cenario: Não deve cadastrar autor sem nome
    Dado um autor sem nome
    E de email "nao-quero-dizer-meu-nome@gmail.com"
    E com descricao "quero ser mais privado"
    Quando tenta salva o autor
    Entao deve dar erro em "nome" com mensagem "must not be blank"


  Cenario: Não deve cadastrar autor com email vazio
    Dado um autor com nome "Anon"
    E de email ""
    E com descricao "quero ser mais privado"
    Quando tenta salva o autor
    Entao deve dar erro em "email" com mensagem "must not be blank"

  Cenario: Não deve cadastrar autor sem email
    Dado um autor com nome "Anon"
    E sem email
    E com descricao "quero ser mais privado"
    Quando tenta salva o autor
    Entao deve dar erro em "email" com mensagem "must not be blank"


  Cenario: Não deve cadastrar autor com descricao vazia
    Dado um autor com nome "Esquecido"
    E de email "dori@gmail.com"
    E com descricao ""
    Quando tenta salva o autor
    Entao deve dar erro em "descricao" com mensagem "must not be blank"

  Cenario: Não deve cadastrar autor sem descricao
    Dado um autor com nome "Anon"
    E de email "dori@gmail.com"
    E sem descricao
    Quando tenta salva o autor
    Entao deve dar erro em "descricao" com mensagem "must not be blank"

  Cenario: Não deve cadastrar autor com descricao gigante
    Dado um autor com nome "Experiente"
    E de email "cobol@gmail.com"
    E com descricao muito grande
    Quando tenta salva o autor
    Entao deve dar erro em "descricao" com mensagem "size must be between 0 and 400"