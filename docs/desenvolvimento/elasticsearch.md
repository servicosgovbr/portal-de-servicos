Um mecanismo de busca eficiente e fácil de customizar e manter é uma parte importante do nosso objetivo de fazer com que
os usuários possam encontrar serviços do Governo de forma rápida e fácil.

Estudamos algumas ferramentas existentes, e decidimos basear nossa implementação no [ElasticSearch][ES].

Prós:
----

* Usa Apache Lucene por trás para a parte de armazenamento
* Utiliza nodes, shards, para redundância, replicação, para escalar
* É um DB sem esquema de dados, não é necessário definir os tipos de documentos em configurações, ou metadados
* Por não ter esquema de dados, os documentos e os tipos de dados dos campos são inferidos, e se algum documento for adicionado posteriormente com campos novos, o Elasticsearch se ajusta
* Ao mesmo tempo oferece suporte, mediante configuração, para forçar uso de tipos definidos
* Vários documentos podem ir em um único índice
* Suporta subdocumentos/subtipos
* API REST para se comunicar
* Queries podem ser feitas no corpo do request em formato JSON, para queries mais complexas
* Suporta versionamento de documentos, isso também evita document locking ao fazer queries/inserções
* É possível utilizar parametrização para fazer roteamento de requests manualmente, assim é possível, mesmo sendo de mesmo tipo de documentos, dados de certas entidades estarem em nodes/shards específicas, ou seja divisão pode ser feita por regras de domínio, não ficando preso ao esquema de dados
* Suporta "percolation", que, ao invés de armazenar documentos e pegá-los de volta com queries, é possível armazenar queries, e ver as queries que dão um match dado um documento. É uma operação reversa para descobrir queries para um documento. É muito utilizado para alertas e monitoramento.
* Várias configurações podem ser alteradas em tempo de execução, inclusive algumas para adicionar novos nodes
* O projeto do Elasticsearch está utilizando também os testes [Jepsen][JEPSEN], que são uma bateria de testes para bases distribuidas, desenvolvidos pela comunidade Opensource bem completa
* Opensource
* É possível gerenciar os nodes através da API REST

Cons
----

* Ao mudar algumas configurações, principalmente relativos a mudanças de infraestrutura (Shards/Replicação), é necessário resetar o Elasticsearch
* Apesar de não ter esquema de dados, algumas mudanças no formato do documento, ou se estiver com a configuração para forçar o esquema de dados, é necessário reindexação dos documentos
* Não é muito seguro por si só, o ElasticSearch deve estar em uma rede interna, ou por trás de algum proxy para uso
* Para melhorar performance é necessário algumas boas configurações de infraestrutura (nodes/shards/índices), apesar de já ter uma performance muito boa por meio de um sem esquema de dados node

Referências
----

* [Testes Jepsen][JEPSEN]
* [Comparativo Solr vs ElasticSearch][SOLR-ES1]
* [StackOverflow, comparação Solr vs Elasticsearch][SOLR-ES1]


[ES]:http://www.elasticsearch.org/
[JEPSEN]:https://aphyr.com/posts/317-call-me-maybe-elasticsearch
[SOLR-ES1]:http://solr-vs-elasticsearch.com/
[SOLR-ES2]:http://stackoverflow.com/questions/10213009/solr-vs-elasticsearch
