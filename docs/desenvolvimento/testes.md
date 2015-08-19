# Estratégia e Execução de Testes

## Unitários e Integração

Testes unitários e de integração são utilizados para garantir que a implementação de métodos e classes agem de acordo com o comportamento esperado, e também como forma de documentação técnica mais detalhada do comportamento das partes relevantes do sistema.

Para executar apenas os testes unitários e de integração isoladamente, utilize a tarefa `test` do [Gradle]:

```
./gradlew test
```

Esta tarefa executa os testes unitários e de integração Java utilizando [JUnit].

## Cobertura

Após executar a tarefa `test`, descrita acima, um relatório de cobertura de código pode ser gerado através da tarefa `jacocoTestReport`:

```
./gradlew jacocoTestReport
```

Os relatórios ficam disponíveis no diretório `build/reports/jacoco/`, em formatos XML e HTML, após a execução.

### Coveralls

Resultados dos relatórios de cobertura de testes são automaticamente disponibilizados através da integração contínua, utilizando o [Coveralls], para todas as revisões do projeto.

## Checagens estáticas

Antes de publicar alterações a uma funcionalidade, com `git push`, é necessário rodar todas as tarefas de testes e verificação do [Gradle]:

```
./gradlew check
```

Esta tarefa compila e executa os testes, gera relatórios de execução e cobertura, bem como busca por possíveis bugs no código através da ferramenta [FindBugs].

[Gradle]:http://www.gradle.org
[JUnit]:http://junit.org
[FindBugs]:http://findbugs.sourceforge.net
[Coveralls]:https://coveralls.io/github/servicosgovbr