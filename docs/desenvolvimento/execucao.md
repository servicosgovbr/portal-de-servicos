Execução da aplicação
----

Para rodar a aplicação localmente, utilizamos um servidor web [Tomcat][TOMCAT], embutido pelo [Spring-Boot][SPRINGBOOT]:

```
./gradlew run
```

Após alguns segundos, o servidor estará disponível em [localhost:8080](http://localhost:8080/).

ElasticSearch externo
----

Para utilizar um servidor ElasticSearch configurado em `localhost:9300`, basta adicionar o perfil `cluster` aos perfis
ativos do Spring:

```
SPRING_PROFILES_ACTIVE=cluster ./gradlew run
```

Para a inicialização funcionar desta forma, é necessário que o ElasticSearch já esteja funcionando antes de começar.

[TOMCAT]:http://tomcat.apache.org/
[SPRINGBOOT]:http://projects.spring.io/spring-boot/
