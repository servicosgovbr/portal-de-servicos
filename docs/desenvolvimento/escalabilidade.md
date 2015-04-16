# Escalabilidade

É possível aumentar a capacidade de carga do Guia de Serviços de duas maneiras:

## Aumentando a capacidade do servidor (vertical)

Servidores estão atualmente provisionados de acordo com a seção "[Implantação e execução](../desenvolvimento/implantacao-e-execucao.md)". Para adicionar mais capacidade, é necessário revisar as configurações pertinentes e analisar a performance total novamente (ver: "[Performance](./performance.md)").

## Aumentando a quantidade de servidores (horizontal)

Para adicionar um servidor de aplicação, é necessário adicioná-lo ao [balanceador de carga](./infraestrutura.md#balanceador-de-carga). Para um nodo no [ElasticSearch](./elasticsearch.md), é necessário atualizar a configuração do ambiente a modificar para que o IP do novo nodo seja utilizado.

Por exemplo, para adicionar uma nova máquina com ElasticSearch ao [cluster no Vagrant](./deploy-vagrant.md), com o IP `10.16.0.14`, basta modificar o arquivo `src/main/resources/application-vagrant.yaml` para:

```
spring:
  data:
    elasticsearch:
      clusterNodes: 10.16.0.11:9300,10.16.0.9:9300,10.16.0.14:9300
```

…e [executar a implantação](./deploy-vagrant.md) para o ambiente Vagrant novamente.
