Desenvolvimento
====

Ambiente local
----

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
git clone "https://github.com/thoughtworks/guia-de-servicos-frontend.git"
```

A seguir, gere o arquivo de projeto do [IntelliJ IDEA][IDEA14CE]:

```
./gradlew clean idea
```

Abra o arquivo `guia-de-servicos-frontend.ipr` no IntelliJ IDEA e você
está pronto para desenvolver.

Construção e execução de testes
----

Para construir o projeto, basta utilizar:

```
./gradlew
```

As tarefas padrão devem ser suficientes para garantir que a compilação,
testes automatizados e empacotamento estão executando corretamente.

Documentação
----

A documentação deste projeto (inclusive este documento que você está lendo) é escrita utilizando o [mkdocs][MKDOCS], no 
formato [Markdown][MARKDOWN].

Para atualizar um documento, basta editá-lo e seguir as convenções do formato [Markdown][MARKDOWN]. Para uma
pré-visualização do conteúdo finalizado antes de gerar um _commit_, instale a ferramenta:

```
pip install mkdocs
```

E, no diretório raiz do repositório, rode:

```
mkdocs serve
```

A pré-visualização deve estar disponível no endereço [http://localhost:8000](http://localhost:8000).


Execução da aplicação
----

Para rodar a aplicação localmente, utilizamos um servidor web [Tomcat][TOMCAT], embutido no [Spring-Boot][SPRINGBOOT]:

```
./gradlew run
```

Após alguns segundos, o servidor estará disponível em [localhost:8080](http://localhost:8080/).

[GIT]:http://git-scm.org
[GITCRYPT]:https://www.agwa.name/projects/git-crypt/
[GPG]:https://www.gnupg.org/
[JDK8]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[IDEA14CE]:https://www.jetbrains.com/idea/download/
[TOMCAT]:http://tomcat.apache.org/
[SPRINGBOOT]:http://projects.spring.io/spring-boot/
[MKDOCS]:http://www.mkdocs.org
[MARKDOWN]:http://daringfireball.net/projects/markdown/
[RTFD]:http://guia-de-servicos-frontend.readthedocs.org