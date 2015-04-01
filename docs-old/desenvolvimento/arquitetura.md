
##### Há dados com alta taxa de acesso para leitura/gravação que podem ser mantidos em memória nas diferentes camadas da arquitetura?

Sim, e os caches estão configurados para o ambiente de produção para que o [Thymeleaf] e o [ElasticSearch] façam bom uso da memória disponível.

##### Quais são os gargalos de desempenho esperados?

Excesso de usuários concorrentes pode causar contenção nos acessos ao [ElasticSearch].

##### Quais são os requisitos de hardware?

CPU: 

* 2 a 4 cores

Memória: 

* 2 a 4 GB

Espaço em disco: 

* 300 MB (mínimo) a 50+ GB (confortável para logs, etc)

Links de comunicação: 

* VLAN (100mbit+ recomendado)

Banco de dados:

- [ElasticSearch], 500 a 3000 documentos indexados

Interação com outros sistemas internos:

- N/A

Interação com sistemas externos:

- Google Analytics (planejado)
- Piwik (planejado)
- Buscador GovBr (apenas exportação XML)

### Escalabilidade

##### Qual o máximo de usuários esperado realizando que tipos de operações?

Pico de 270 a 300 mil acessos ao dia, com média de 100 mil acessos ao dia.

##### Como a confiabilidade pode ser avaliada antes da implantação?

Testes de carga (load) e imersão (soak) antes da implantação inicial em produção, e no ambiente de homologação a cada publicação subsequente.

##### Há algum comportamento do sistema que precisa ser alterado regularmente sem necessidade de alteração na aplicação?

Páginas estáticas (localizadas em `src/main/resources/conteudo`), templates (em `src/main/resources/templates`) e estilos [SASS] (`src/main/assets`) podem requerer alterações.

Estas devem seguir o mesmo fluxo de publicação das alterações de código.

##### Isso pode estar contido em uma base de dados?

Não é recomendável, por incetivar a criação de processos de publicação de alterações externos à [integração contínua][CI].

[CI]:http://en.wikipedia.org/wiki/Continuous_integration

##### Portabilidade de dados entre a aplicação e outras soluções?

A aplicação disponibiliza URLs para a exportação de dados em formatos XML, JSON e é possível replicar os índices do [ElasticSearch] utilizando outras instâncias do mesmo com facilidade.

##### Padrões de linguagem e de codificação

Utilizamos o [Google Java Style](https://google-styleguide.googlecode.com/svn/trunk/javaguide.html), com exceção da obrigação das informações de direito autoral e _javadoc_ no prólogo de cada arquivo, e da permissão ao uso de _wildcard imports_.

