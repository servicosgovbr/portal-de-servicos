# Testes

## Unitários

Para executar apenas os testes unitários isoladamente, utilize a tarefa `test` do [Gradle]:

```
./gradlew test
```

## Integração


E para executar apenas os testes de integração, que são um pouco mais demorados, execute a tarefa `integrationTest` do [Gradle]:

```
./gradlew integrationTest
```

## Cobertura

Após executar as tarefas `test` ou `integrationTest`, descritas acima, um relatório de cobertura de código pode ser gerado através da tarefa `jacocoTestReport`:

```
./gradlew jacocoTestReport
```

Os relatórios ficam disponíveis no diretório `build/reports/jacoco/`, em formatos XML e HTML, após a execução.

## Interface

<!-- TODO -->

## Checagens estáticas

Antes de publicar uma funcionalidade, com `git push`, é necessário rodar todas as tarefas de testes e verificação do [Gradle]:

```
./gradlew check
```


[Gradle]:http://www.gradle.org