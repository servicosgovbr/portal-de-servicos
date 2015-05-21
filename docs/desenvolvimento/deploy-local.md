# Desenvolvimento local

## Configuração inicial

Tentamos facilitar o desenvolvimento de novas funcionalidades por membros da comunidade, e todas as contribuições são bem-vindas. Para começar, é necessário um ambiente de desenvolvimento Java atualizado, com:

* [git][GIT]
* [git-crypt][GITCRYPT]
* [GPG][GPG]
* [JDK 8][JDK8]
* Sua IDE favorita (nós preferimos [IntelliJ IDEA 14 CE][IDEA14CE])

Para começar, clone o repositório:

```
cd ~/projetos # ou o diretório de sua preferência
git clone "https://github.com/servicosgovbr/guia-de-servicos.git"
```

A seguir, gere o arquivo de projeto do [IntelliJ IDEA][IDEA14CE]:

```
./gradlew clean idea
```

Abra o arquivo `guia-de-servicos.ipr` no IntelliJ IDEA e você está pronto para navegar pelo código. 

Caso você não tenha desenvolvido utilizando o [Lombok] antes, a IDE marcará diversos arquivos com problemas de compilação. Será necessário instalar o plugin para que as coisas funcionem normalmente. Este plugin precisa ser manualmente instalado.

Para o IntelliJ IDEA, procure nos repositórios de plugins pelo [Lombok IntelliJ Plugin][PLUGIN].

[Lombok]:http://projectlombok.org/
[GIT]:http://git-scm.org
[GITCRYPT]:https://www.agwa.name/projects/git-crypt/
[GPG]:https://www.gnupg.org/
[JDK8]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[IDEA14CE]:https://www.jetbrains.com/idea/download/
[VAGRANT]:http://vagrantup.com
[PLUGIN]:https://github.com/mplushnikov/lombok-intellij-plugin
[TOMCAT]:http://tomcat.apache.org/
[SPRINGBOOT]:http://projects.spring.io/spring-boot/

## Execução da aplicação

Para rodar a aplicação localmente, utilizamos um servidor web [Tomcat][TOMCAT], embutido pelo [Spring-Boot][SPRINGBOOT]:

```
./gradlew bootRun
```

Caso queira que o conteúdo do site seja gerado automaticamente, basta configurar a variável `FLAGS_IMPORTAR_AUTOMATICAMENTE`:

```
FLAGS_IMPORTAR_AUTOMATICAMENTE=true ./gradlew bootRun
```

Após alguns segundos, o servidor estará disponível em [localhost:8080](http://localhost:8080/). Arquivos estáticos (html, js, css) alterados estarão disponíveis alguns segundos após alterações.

## ElasticSearch externo

Para utilizar um servidor [ElasticSearch](./elasticsearch.md) configurado em `localhost:9300`, basta configurar a variável `SPRING_DATA_ELASTICSEARCH_CLUSTERNODES`:

```
SPRING_DATA_ELASTICSEARCH_CLUSTERNODES=127.0.0.1:9300 ./gradlew run
```

Para a inicialização funcionar desta forma, é necessário que o ElasticSearch já esteja executando antes de começar.
