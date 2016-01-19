# Monitoramento

A monitoração da saúde do host é feita de maneira externa, utilizando Nagios. Para o monitoramento das instâncias da aplicação e [ElasticSearch], com informações mais detalhadas, as seguintes opções estão disponíveis:

## Balanceador de Carga

As estatísticas do HAProxy ficam disponíveis através da porta de administração (8080/tcp), no endereço `/lb-stats`. Por exemplo, em produção, estas estão disponíveis em `http://servicos.gov.br:8080/lb-stats`.
 
Nesta página estão contidas algumas estatísticas de acesso, em números de I/O brutos, bem como de disponibilidade dos serviços gerenciados.

## Portal de Serviços

O Portal de Serviços disponibiliza informações de saúde através do endereço `/health`, que retorna JSON similar ao seguinte:

```json
{
  "status": "UP",
  "portalDeServicosIndex": {
    "status": "UP",
    "pds-importador": "ok (622 docs)",
    "pds-persistente": "ok (0 docs)"
  },
  "elasticSearch": {
    "status": "GREEN",
    "nodes": 3,
    "node-0": "es1",
    "node-1": "logstash-d9ccaa4d20c4-1-11624",
    "node-2": "es2"
  },
  "cache": {
    "status": "UNKNOWN"
  },
  "diskSpace": {
    "status": "UP",
    "free": 17378258944,
    "threshold": 10485760
  }
}
```

Nele, é possível ver o estado geral da aplicação (`status`), do índice no ElasticSearch (`portalDeServicosIndex`), informações de conexão ao ElasticSearch (`elasticsearch`), do estado do cache interno de páginas (`cache`) e espaço em disco (`diskSpace`).


## Editor de Serviços

O Editor de Serviços disponibiliza informações de saúde através do endereço `/editar/health`, que retorna JSON similar ao seguinte:

```json
{
  "status": "UP",
  "diskSpace": {
    "status": "UP",
    "free": 17378254848,
    "threshold": 10485760
  }
}
```

Nele, é possível ver o estado geral da aplicação (`status`) e do espaço em disco (`diskSpace`).

## Kibana

Todos os logs gerados pelas aplicações são redirecionados para o [ElasticSearch] através do [Logstash]. Eles são indexados e podem ser auditados, buscados e visualizados através do [Kibana], que fica disponível na porta 5601/tcp. Por exemplo, em produção, a ferramenta está disponível em `http://servicos.gov.br:5601/`.

[ElasticSearch]:../desenvolvimento/elasticsearch.md
[Logstash]:https://www.elastic.co/products/logstash
[Kibana]:https://www.elastic.co/products/kibana
