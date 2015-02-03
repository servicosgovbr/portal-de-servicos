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

Para executar apenas os testes isoladamente, a tarefa `test` do [Gradle][GRADLE] deve ser suficiente:

```
./gradlew test
```

Integração Contínua
----

Utilizamos o [Snap][SNAP] para gerência de [integração contínua][CI]. Eventualmente, repensaremos seu uso conforme
ficarem mais claros os requisitos para [entrega contínua][CD].

[GRADLE]:https://gradle.org/
[SNAP]:http://snap-ci.com
[CI]:http://en.wikipedia.org/wiki/Continuous_integration
[CD]:http://en.wikipedia.org/wiki/Continuous_delivery