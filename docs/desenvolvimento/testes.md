# Testes

## Unitários e Integração

Para executar apenas os testes unitários e de integração isoladamente, utilize a tarefa `test` do [Gradle]:

```
./gradlew test
```

## Cobertura

Após executar a tarefa `test`, descrita acima, um relatório de cobertura de código pode ser gerado através da tarefa `jacocoTestReport`:

```
./gradlew jacocoTestReport
```

Os relatórios ficam disponíveis no diretório `build/reports/jacoco/`, em formatos XML e HTML, após a execução.

## Checagens estáticas

Antes de publicar uma funcionalidade, com `git push`, é necessário rodar todas as tarefas de testes e verificação do [Gradle]:

```
./gradlew check
```

[Gradle]:http://www.gradle.org