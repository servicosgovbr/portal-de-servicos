Construção e execução de testes
----

Para construir o projeto, basta utilizar:

```
./gradlew
```

As tarefas padrão devem ser suficientes para garantir que a compilação, testes automatizados e empacotamento estão
executando corretamente.

Testes
----

Para executar apenas os testes unitários isoladamente, a tarefa `test` do [Gradle][GRADLE] deve ser suficiente:

```
./gradlew test
```

E para executar apenas os testes de integração, que são um pouco mais demorados, basta executar a tarefa `integrationTest` do [Gradle][GRADLE]:

```
./gradlew integrationTest
```

Antes de publicar uma funcionalidade, com `git push`, recomendamos rodar todas as tarefas de testes e verificação do [Gradle][GRADLE]:

```
./gradlew check
```

Integração Contínua
----

Utilizamos o [Snap][SNAP] para gerência de [integração contínua][CI]. Eventualmente, repensaremos seu uso conforme
ficarem mais claros os requisitos para [entrega contínua][CD].

[GRADLE]:https://gradle.org/
[SNAP]:http://snap-ci.com
[CI]:http://en.wikipedia.org/wiki/Continuous_integration
[CD]:http://en.wikipedia.org/wiki/Continuous_delivery
