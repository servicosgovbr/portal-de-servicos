# Monitoramento

A monitoração da saúde dos hosts e instâncias da aplicação e [ElasticSearch] pode ser feita através das seguintes URLs:

## Aplicação

A URL `/health` do Portal de Serviços indica o estado de saúde da aplicação em si e de suas conexões ao cluster do [ElasticSearch]. Caso o valor da variável de ambiente `ENDPOINTS_HEALTH_SENSITIVE` seja `false`, mais informação é exibida:

```json
{
  "status": "UP",
  "elasticSearch": {
    "status": "GREEN",
    "nodes": 3,
    "node-0": "Freakmaster",
    "node-1": "Seth",
    "node-2": "Right-Winger"
  },
  "diskSpace": {
    "status": "UP",
    "free": 96787795968,
    "threshold": 10485760
  }
}
```

Caso contrário, apenas o estado geral da aplicação é retornado:

```json
{
  "status": "UP"
}
```

## [ElasticSearch]

A URL `/_cluster/health` do [ElasticSearch] disponibiliza diversas informações sobre o estado de saúde e configuração do cluster. Por exemplo:

```json
{
  "cluster_name": "elasticsearch",
  "status": "red",
  "timed_out": false,
  "number_of_nodes": 1,
  "number_of_data_nodes": 1,
  "active_primary_shards": 13,
  "active_shards": 13,
  "relocating_shards": 0,
  "initializing_shards": 0,
  "unassigned_shards": 29,
  "number_of_pending_tasks": 0
}
```

No exemplo acima, pode-se ver que o número de nodos insuficiente (apenas 1) é motivo de preocupação e torna o status *red*.

Quando a conexão entre o nodo e seus pares no cluster se estabelece com sucesso, o estado muda em alguns instantes:

```json
{
   "cluster_name": "elasticsearch",
   "status": "green",
   "timed_out": false,
   "number_of_nodes": 2,
   "number_of_data_nodes": 2,
   "active_primary_shards": 7,
   "active_shards": 14,
   "relocating_shards": 0,
   "initializing_shards": 0,
   "unassigned_shards": 0,
   "number_of_pending_tasks": 0
}
```

O estado também pode ficar marcado como "red" caso o número de partições (_shards_) não seja suficiente para distribuir todos os dados. Neste caso, reiniciar a aplicação deve trazer todas as partições necessárias para o cluster.

## Java Management Extensions (JMX)

Diversas informações estão disponíveis através do serviço de gerenciamento da JVM. Existem diversos [clientes para o protocolo JMX disponíveis](http://java-source.net/open-source/jmx), e a JDK já vem com o [JConsole](http://docs.oracle.com/javase/7/docs/technotes/guides/management/jconsole.html) embutido. Ao abrir o processo do Portal de Serviços no JConsole, temos:

![JConsole: Overview](/desenvolvimento/jconsole-overview.png)

E ao listar os MBeans disponíveis em `org.springframework.boot/Endpoint/environmentEndpoint/Attributes`:

![JConsole: Environments](/desenvolvimento/jconsole-environment.png)

As informações e operações oferecidas através do JMX são maneiras poderosas de manter e diagnosticar a saúde da aplicação e do [ElasticSearch], e devem ser manuseadas com o cuidado necessário. Para mais informações sobre segurança do JMX, por favor utilize a [documentação de referência oficial](http://docs.oracle.com/javase/8/docs/technotes/guides/management/agent.html).

[ElasticSearch]:/desenvolvimento/elasticsearch.md