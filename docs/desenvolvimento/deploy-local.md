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
git clone "https://github.com/servicosgovbr/portal-de-servicos.git"
```

A seguir, gere o arquivo de projeto do [IntelliJ IDEA][IDEA14CE]:

```
./gradlew clean idea
```

Abra o arquivo `portal-de-servicos.ipr` no IntelliJ IDEA e você está pronto para navegar pelo código. 

Caso você não tenha desenvolvido utilizando o [Lombok] antes, a IDE marcará diversos arquivos com problemas de compilação. Será necessário instalar o plugin para que as coisas funcionem normalmente. Este plugin precisa ser manualmente instalado.

Para o IntelliJ IDEA, procure nos repositórios de plugins pelo [Lombok IntelliJ Plugin][PLUGIN].

[Lombok]:http://projectlombok.org/
[GIT]:http://git-scm.org
[GITCRYPT]:https://www.agwa.name/projects/git-crypt/
[GPG]:https://www.gnupg.org/
[JDK8]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[IDEA14CE]:https://www.jetbrains.com/idea/download/
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

Após alguns segundos, o servidor estará disponível em [localhost:8080](http://localhost:8080/).

### Alterações em arquivos estáticos


Alterações locais em arquivos estáticos (html, js, css) só  estarão integralmente disponíveis após execução da ferramenta `Brunch`. O seguinte comando deve ser utilizado para executar o processamento dos arquivos estáticos:

```
./brunch build
```

Para forçar a execução da ferramenta `Brunch` toda vez que uma alteração é realizada em um arquivo estático, o seguinte comando deve ser utilizado: 

```
./brunch watch
```

### Visibilidade dos logs da aplicação

Para obter mais detalhes das operações que o Portal de Serviços está executando, é necessário alterar as configurações de log da aplicação. Os seguintes passos são necessários:

1. No arquivo `build/resources/main/logback.xml`, o paramêtro `encoder` deve ser alterado com o seguinte valor: 
```
<encoder>
<pattern>%d %-5level [%thread] %logger{0}: %msg%n</pattern>
</encoder>
```
2. O servidor da aplicação deve ser reiniciado com 
```
./gradlew bootRun
```

### Visualizar e depurar execução da aplicação

Para obter mais detalhes da execução do código do Portal de Serviços e poder depurar o comportamento da aplicação, os seguintes passos são necessários:

1. No IntelliJ IDEA, configurar ferramenta para **debug** remoto da JVM. A configuração padrão do IntelliJ IDEA deve ser utilizada (`localhost:5005`)
2. Executar aplicação localmente, utilizando o paramêtro de **debug** `./gradlew clean BootRun --debug-jvm`
3. No console, a seguinte mensagem aparecerá `Listening for transport dt_socket at address: 5005`
4. Escolher ponto desejado do código para interromper execução
5. Iniciar funcionalidade de **debug** no IntelliJ IDEA

## Atualizar conteúdo do site

Caso tenha alterado algum contéudo estático do site, para que sua instancia local reflita as mudanças realizadas, você terá reiniciar a aplicação com o comando mostrado na sessão acima. Caso não queira reiniciar a aplicação, também é possível obter o mesmo resultando simplesmente acessando o endereço [http://localhost:8080/jolokia/exec/ServicosGovBr:type=Importador/importar](http://localhost:8080/jolokia/exec/ServicosGovBr:type=Importador/importar)

## ElasticSearch externo

Para utilizar um servidor [ElasticSearch](./elasticsearch.md) configurado em `localhost:9300`, basta configurar a variável `SPRING_DATA_ELASTICSEARCH_CLUSTERNODES`:

```
SPRING_DATA_ELASTICSEARCH_CLUSTERNODES=127.0.0.1:9300 ./gradlew run
```

Para a inicialização funcionar desta forma, é necessário que o ElasticSearch já esteja executando antes de começar.
