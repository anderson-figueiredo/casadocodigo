# language: pt
Funcionalidade: Criacao de autores
  Cenario: deve criar um autor com sucesso
    Dado um autor com nome "Alexandre"
    E de email "alexandre@email.com"
    E com descricao "autor da apostila de SOLID"
    Quando tenta salva o autor
    Entao deve ter sucesso

  Cenario: Não deve deixar salvar autores com mesmo email
    Dado um autor com tudo preenchido
    Quando tenta salva o autor
    Entao deve ter sucesso
    Quando tenta salva o autor novamente
    Entao deve dar mensagem de erro