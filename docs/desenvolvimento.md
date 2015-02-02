h1. Como desenvolver

Tentamos facilitar o desenvolvimento de novas funcionalidades por
membros da comunidade, e todas as contribuições são bem-vindas.
Para começar, você vai precisar de um ambiente de desenvolvimento
Java atualizado, com:

* [git][GIT]
* [git-crypt][GITCRYPT]
* [GPG][GPG]
* [JDK 8][JDK8]
* Sua IDE favorita (nós preferimos [IntelliJ IDEA 14 CE][IDEA14CE])

Para começar, clone o repositório:

```
cd ~/projetos # ou o diretório de sua preferência
git clone https://github.com/thoughtworks/guia-de-servicos-frontend.git
```

A seguir, gere o arquivo de projeto do [IntelliJ IDEA][IDEA14CE]:

```
./gradlew clean idea
```

Abra o arquivo `guia-de-servicos-frontend.ipr` no IntelliJ IDEA e você
está pronto para desenvolver.

Para construir o projeto:

```./gradlew```

As tarefas padrão devem ser suficientes para garantir que a compilação,
testes automatizados e empacotamento estão executando corretamente.

Para rodar a aplicação:

```./gradlew run```

Após alguns segundos, o servidor estará disponível em [localhost:8080](http://localhost:8080/).

[GIT]:http://git-scm.org
[GITCRYPT]:https://www.agwa.name/projects/git-crypt/
[GPG]:https://www.gnupg.org/
[JDK8]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[IDEA14CE]:https://www.jetbrains.com/idea/download/
