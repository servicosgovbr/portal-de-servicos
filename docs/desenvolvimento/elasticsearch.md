# ElasticSearch

Um mecanismo de busca eficiente e fácil de customizar e manter é uma parte importante do nosso objetivo de fazer com que os usuários possam encontrar serviços do Governo de forma rápida e fácil.

Estudamos algumas ferramentas existentes, e decidimos basear nossa implementação no [ElasticSearch][ES].

## Características

O [ElasticSearch][ES] é um mecanismo de indexação e busca de código aberto, que utiliza o [Apache Lucene][LUCENE] como meio de armazenamento, e incorpora diversas funcionalidades adicionais, necessárias para volumes de dados maiores, como replicação e particionamento dos índices em vários nodos em _cluster_.

Seu acesso é por meio de APIs [REST][REST], ou programaticamente, em Java.

## Prós

* Também age como um banco de dados sem esquema (_schema-less_), onde não é necessário definir os tipos de documentos em configurações ou metadados.
* Documentos e tipos de dados dos campos são inferidos (e ajustados posteriormente caso haja alterações).
* Vários tipos diferentes de documentos podem ser indexados em um único índice.
* Sub-documentos e sub-tipos são suportados, para pesquisas mais específicas.
* Consultas podem ser feitas de forma flexível (tanto na _query string_ quanto no corpo da requisição, em formato JSON).
* Suporta versionamento de documentos, o que evita _document locking_ ao fazer consultas e inserções em paralelo.
* É possível utilizar parametrização para fazer roteamento de requisições manualmente, garantindo que certos dados estarão em nodos ou partições específicas, para maior performance.
* Suporta operações de _percolation_, onde pode-se consultar as próprias consultas que retornam um determinado documento, o que facilita o desenvolvimento de ferramentas de alerta e monitoramento.
* A maioria das configurações pode ser alterada em tempo de execução, simplificando a operação do índice.
* Estabilidade em situações de carga anormal garantidas por testes automatizados do [Jepsen][JEPSEN].

## Contras

* Ainda é necessário reiniciar o servidor após a mudança de algumas configurações de _cluster_.
* Algumas mudanças no esquema de documentos requerem a reindexação dos mesmos.
* Não oferece mecanismos de controle de acesso e permissões próprios; estes devem ser providos por outras partes da arquitetura.
* Configurações de infraestrutura necessárias para obter redundância e performance ideal.

## Populando

### Em desenvolvimento

Para realizar a importação dos dados iniciais (em `src/main/resources/portaldeservicos.xml`) é necessária a execução de um importador embutido na aplicação. Em desenvolvimento, esta operação está disponível segunindo o link [ServicosGovBr:type=Importador/importar](http://localhost:8080/jolokia/exec/ServicosGovBr:type=Importador/importar).

Esta operação é destrutiva, e reconstrói o índice do [ElasticSearch] a partir dos dados armazenados no XML.

### Em outros ambientes

Em outros ambientes a importação dos dados se dá automaticamente quando o servidor é iniciado.

[REST]:http://pt.wikipedia.org/wiki/REST
[ES]:http://www.elasticsearch.org/
[JEPSEN]:https://aphyr.com/posts/317-call-me-maybe-elasticsearch
[SOLR-ES1]:http://solr-vs-elasticsearch.com/
[SOLR-ES2]:http://stackoverflow.com/questions/10213009/solr-vs-elasticsearch
[LUCENE]:http://lucene.apache.org/
