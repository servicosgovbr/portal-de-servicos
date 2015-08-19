# Estratégia e Execução de Testes

## Ferramentas utilizadas

{% include './_ferramentas-testes.md' %}

## Testes unitários e de integração

Testes unitários e de integração são utilizados para garantir que a implementação de métodos e classes agem de acordo com o comportamento esperado, e também como forma de documentação técnica mais detalhada do comportamento das partes relevantes do sistema.

Para executar apenas os testes unitários e de integração isoladamente, utilize a tarefa `test` do [Gradle]:

```
./gradlew test
```

Esta tarefa executa os testes unitários e de integração Java utilizando [JUnit]. Exemplo de saída:

```
mpog:portal-de-servicos $ ./gradlew test
Starting a new Gradle Daemon for this build (subsequent builds will be faster).
:compileJava UP-TO-DATE
:assetCompile
Compiling assets in directory /Users/cvillela/Sync/src/servicos.gov.br/portal-de-servicos/src/main/assets
Processing File 1 of 2 - main.scss
Working Path: 
Compiling file main.scss
    write /main.css
Found Output File /main.css
Minifying File 1 of 2 - main
Compressing File 1 of 2 - main
Processing File 2 of 2 - print.scss
Working Path: 
Compiling file print.scss
    write /print.css
Found Output File /print.css
Minifying File 2 of 2 - print
Compressing File 2 of 2 - print
Finished Precompiling Assets
:processResources
:classes
:compileTestJava
:processTestResources
:testClasses
:test
00000000-0000-0000-0000-000000000000 INFO  [Thread-5] o.s.w.c.s.GenericWebApplicationContext - Closing org.springframework.web.context.support.GenericWebApplicationContext@60388907: startup date [Wed Aug 19 15:26:53 BRT 2015]; root of context hierarchy

... (mais linhas de diagnóstico) ...

BUILD SUCCESSFUL

Total time: 1 mins 30.468 secs
```

## Cobertura de testes

Após executar a tarefa `test`, descrita acima, um relatório de cobertura de código pode ser gerado através da tarefa `jacocoTestReport`:

```
./gradlew jacocoTestReport
```

Os relatórios ficam disponíveis no diretório `build/reports/jacoco/`, em formatos XML e HTML, após a execução.

### Coveralls

Resultados dos relatórios de cobertura de testes são automaticamente disponibilizados através da integração contínua, utilizando o [Coveralls], para todas as revisões do projeto.

## Verificações estáticas

Antes de publicar alterações a uma funcionalidade, com `git push`, é necessário rodar todas as tarefas de testes e verificação do [Gradle]:

```
./gradlew check
```

Esta tarefa compila e executa os testes, gera relatórios de execução e cobertura, bem como busca por possíveis bugs no código através da ferramenta [FindBugs]. Quando são encontradas violações ou possíveis bugs cobertos pelo FindBugs, estes são descritos na saída da execução, e descritos em um arquivo HTML gerado pela ferramenta. 

[Gradle]:http://www.gradle.org
[JUnit]:http://junit.org
[FindBugs]:http://findbugs.sourceforge.net
[Coveralls]:https://coveralls.io/github/servicosgovbr