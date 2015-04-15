# Dados criptografados

Todo o código-fonte do projeto é aberto e segue a [licença MIT][MIT], mas algumas informações pertinentes a ambientes de execução em homologação e produção são exclusivos a certas pessoas. Caso você seja uma delas, e precise ver o conteúdo destes arquivos (residentes no diretório `secrets`), é necessário que sua chave [GPG][GPG] esteja adicionada no banco [git-crypt][GITCRYPT] do repositório.

Para tal, peça a um dos mantenedores do projeto para adicionar sua chave, e quando isto for feito, basta executar:

```
git crypt unlock
```

O `git-crypt` pedirá para destravar sua chave [GPG] e vai imediatamente decriptar todos os arquivos secretos de forma
transparente ao uso do [Git][GIT]. A partir daí, a utilização do repositório pode continuar normalmente.

Para travar o repositório novamente, de forma que os arquivos criptografados não sejam mais visíveis, execute:

```
git crypt lock
```

É recomendado não manter o repositório destravado desnecessariamente.

## Aviso importante

Entre em contato com os mantenedores imediatamente caso haja uma suspeita de que sua chave tenha sido comprometida.

[GIT]:http://git-scm.org
[GITCRYPT]:https://www.agwa.name/projects/git-crypt/
[GPG]:https://www.gnupg.org/
[MIT]:../sobre-o-projeto/licenca.md
