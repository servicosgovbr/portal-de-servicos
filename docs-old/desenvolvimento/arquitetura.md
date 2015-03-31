Arquitetura
====

Definição das camadas
----

### Apresentação


#### Tecnologias Utilizadas

- [Thymeleaf]
- [HTML 5][HTML5]
- [SASS]
- [jQuery]
- [Modernizr]
- [Pegdown]

[Thymeleaf]:http://thymeleaf.org/
[HTML5]:http://www.w3.org/TR/html5/
[SASS]:http://sass-lang.com/
[jQuery]:http://jquery.org/
[Modernizr]:http://modernizr.com/
[Pegdown]:https://github.com/sirthias/pegdown

### Componentes e atividades


#### Tecnologias Utilizadas

- [Lombok]
- [Slf4j]
- [Spring-Boot]
- [Spring MVC][MVC]
- [Jolokia]
- [Slugify]
- [ElasticSearch]

### Domínio


#### Tecnologias Utilizadas

- [Lombok]
- [Spring Data]
- [ElasticSearch]

[Lombok]:http://projectlombok.org/
[Slf4j]:http://slf4j.org
[Spring-Boot]:https://github.com/spring-projects/spring-boot/
[Spring Data]:http://projects.spring.io/spring-data/
[MVC]:http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html
[Jolokia]:http://jolokia.org
[Slugify]:https://github.com/slugify/slugify
[ElasticSearch]:https://www.elastic.co/products/elasticsearch

Requisitos Arquiteturais
----

Esta seção é um checklist cujo resultado final são os requisitos arquiteturais da aplicação.

Deve ser respondida, de forma sucinta, ao longo da execução do projeto. As questões que não se aplicam devem ser respondidas com “N/A”.

### Desempenho

##### Qual a velocidade de resposta esperada da aplicação?

Tempo ao primeiro byte recebido pelo browser na requisição principal inferior a 1 segundo em ao menos 80% das requisições, e tempo de carga médio total inferior a 5 segundos (+/- 1 segundo).

##### Há diferentes classes de operações para as quais os usuários têm expectativas diferentes?

Não.

##### Existe janela batch? O que executa nela?

Não.

##### Há batches com restrições de desempenho específicas? Em caso afirmativo, especifique.

Não.

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

##### Qual a perspectiva de crescimento em quais tabelas críticas sem impactar o desempenho da aplicação?

N/A.

##### Há possibilidade de saturar um link de comunicação que não pode ter a capacidade/velocidade aumentada?

Não.

##### Que dimensões podem ser escaladas?

CPU, memória, distribuição geográfica, número de máquinas no cluster do [ElasticSearch], número de servidores de aplicação.

##### Qual a principal estratégia para escalar a aplicação: ampliando os nós de uma topologia fixa ou adicionando novos nós?

Adicionando novos nós. O balanceamento de carga feito no [HAProxy] pode suportar quantos nós forem necessários, e as funcionalidades de _clustering_ do [ElasticSearch] lidam com a distribuição e _caching_ dos dados.

### Disponibilidade

##### Qual o percentual de disponibilidade requerido?

De 97% (aproximadamente 50 minutos ao dia) a 99.8% (aproximadamente 3 minutos ao dia).

##### A disponibilidade varia ao longo do dia, da semana, do mês ou do ano ou em função de localização?

Não, mas pode variar em função do número de publicações (deployments) feitos.

##### Há interrupções controladas planejadas? Qual a programação?

Ao menos uma cada vez a cada duas semanas (novas publicações). Entretanto, a estratégia azul/verde será utilizada para evitar interrupções completas no acesso ao serviço.

### Confiabilidade

##### Há componentes com confiabilidade menor que a requerida para o sistema?

Não.

##### Que estratégias estão em curso para tornar mais confiáveis os recursos menos confiáveis?

N/A.

##### Qual o tempo médio de falha por severidade?

N/A.

##### Como a confiabilidade pode ser avaliada antes da implantação?

Testes de carga (load) e imersão (soak) antes da implantação inicial em produção, e no ambiente de homologação a cada publicação subsequente.

### Segurança

##### Quais operações precisam ser seguras?

Nenhuma.

##### Como os usuários serão administrados?

N/A.

##### Como serão dadas permissões aos usuários para acessarem operações seguras?

N/A.

##### Quais os diferentes níveis de segurança e como mapear:

N/A.

##### Segurança por operação

N/A.

##### Segurança por tipo de objeto

N/A.

##### Segurança por instância de objeto

N/A.

### Manutenibilidade

##### Há facilidade de contratação de pessoas com as habilidades técnicas requeridas e de atraí-las para a área?

Sim.

##### Que tipos de mudanças são esperados na primeira rodada de manutenção? Quais são suas prioridades relativas?

N/A.

##### Que tipo de testes de regressão é necessário para garantir que as manutenções não degradem as funcionalidades existentes?

Testes automatizados (unitários e integração) devem ser mantidos e evoluidos em paralelo à implementação de quaisquer correções e funcionalidades.

### Flexibilidade

##### Há algum comportamento do sistema que precisa ser alterado regularmente sem necessidade de alteração na aplicação?

Páginas estáticas (localizadas em `src/main/resources/conteudo`), templates (em `src/main/resources/templates`) e estilos [SASS] (`src/main/assets`) podem requerer alterações.

Estas devem seguir o mesmo fluxo de publicação das alterações de código.

##### Isso pode estar contido em uma base de dados?

Não é recomendável, por incetivar a criação de processos de publicação de alterações externos à [integração contínua][CI].

[CI]:http://en.wikipedia.org/wiki/Continuous_integration

##### Há regras que podem ser aplicadas em tempo de execução por um motor de regras?

Não.

##### Há funções executadas por meio de scripts do usuário? Caso afirmativo, como será realizada a garantia de qualidade desses?

Não.

### Usabilidade

##### Qual o perfil dos usuários da aplicação?

Público em geral.

##### Há operações que devem ser realizadas o mais rapidamente possível, por meio da redução de ações do usuário?

Sim, e estão contempladas na análise de experiência do usuário (UX).

##### Há operações que requerem apresentações específicas para ajudar o usuário a realizá-las?

Sim, e estão contempladas na análise de experiência do usuário (UX).

##### Como é resolvido o equilíbrio entre integridade de dados e a habilidade de cancelar uma operação em andamento?

Não há operações transacionais na aplicação.

##### Que tipos de treinamentos são esperados?

A análise de experiência de usuário, e desenvolvimento de suas recomendações, deve ser suficiente para a operação do sistema sem nenhum tipo de treinamento.

##### Há algum sistema de ajuda incluído?

Sim.

### Configuração

##### Quais os parâmetros cuja configuração é necessária nas máquinas que hospedarão a solução?

Utilizar a distribuição CentOS 7.x, 64 bits, e rede em VLAN conforme descrito no diagrama físico da solução. Todos os outros parâmetros de configuração estarão em `/etc/sysconfig/guia-de-servicos`, com comentários descritivos no próprio arquivo.

### Personalização

##### Que aspectos do sistema podem ser personalizados pelo usuário?

N/A.

##### Como o usuário realiza essa configuração?

N/A.

##### Qual a estratégia para definir os padrões?

N/A.

### Portabilidade

##### Portabilidade de dados entre a aplicação e outras soluções?

A aplicação disponibiliza URLs para a exportação de dados em formatos XML, JSON e é possível replicar os índices do [ElasticSearch] utilizando outras instâncias do mesmo com facilidade.

##### Portabilidade entre diferentes versões de um único banco de dados?

N/A.

##### Portabilidade para um banco de dados de outro fabricante? Caso afirmativo, qual/quais e em que situação?

N/A.

##### Portabilidade de browser? Quais marcas e versões?

Adotamos os padrões HTML 5, CSS 3 e JavaScript 1.8.5 (ECMAScript 5.1), compatíveis com todos os browsers modernos. Testamos a aplicação em:

- Chrome 41
- Safari 8
- iOS 8 (MobileSafari)

##### Portabilidade de sistema operacional?

N/A.

### Conformidade com padrões

##### Quais padrões legais se aplicam?

N/A.

##### Quais padrões técnicos se aplicam?

* [ePING]
* [ePWG]
* [eMAG]

[ePING]:http://www.governoeletronico.gov.br/acoes-e-projetos/e-ping-padroes-de-interoperabilidade
[ePWG]:http://www.governoeletronico.gov.br/acoes-e-projetos/padroes-brasil-e-gov
[eMAG]:http://www.governoeletronico.gov.br/acoes-e-projetos/e-MAG

##### Quais outros padrões se aplicam?

N/A.

##### Quais padrões de desenvolvimento se aplicam?

N/A.

##### Nomenclatura de bases de dados

N/A.

##### Padrões arquiteturais internos

Model-View-Controller ([MVC])

[MVC]:http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller

##### Padrões de linguagem e de codificação

Utilizamos o [Google Java Style](https://google-styleguide.googlecode.com/svn/trunk/javaguide.html), com exceção da obrigação das informações de direito autoral e _javadoc_ no prólogo de cada arquivo, e da permissão ao uso de _wildcard imports_.

##### Padrões de testes e de revisões

Utilizamos o [JUnit] tanto para testes unitários (um método ou classe por teste) quanto de integração (um subconjunto do sistema por teste).

[JUnit]:http://junit.org

##### Padrões de apresentação

Conforme definidos no [Guia de Estilo do Portal Institucional Padrão][Guia de Estilos].

[Guia de Estilos]:http://www.secom.gov.br/orientacoes-gerais/comunicacao-digital/guia-de-estilo-identidade-padrao-comunicacao-digital-fev2015.pdf

##### Modelos ou metodologias de ciclo de vida

Descritos no Plano de Iterações.

### Internacionalização

##### Quais os idiomas suportados?

Português Brasileiro (pt_BR).

##### Em que ordem?

N/A.

##### Tipo de codificação de caracteres? Simples ou multi?

UTF-8, em todos os documentos e código.

### Interoperabilidade

##### Com que soluções a aplicação interagirá imediatamente?

* [Google Analytics][GA] ou [Piwik] (ainda a decidir)

[GA]:http://www.google.com/analytics/
[Piwik]:http://piwik.org/

##### Que outras soluções estão previstas?

N/A.

##### Com que categorias de aplicações internas e externas pode ser necessário interagir?

N/A.

##### Que funcionalidades da aplicação serão expostas como um serviço?

N/A.

##### Que funcionalidades da aplicação precisam ser expostas como web service ou via um portal?

N/A.

### Auditoria e rastreabilidade

##### Existe a necessidade de auditar ou rastrear as ações dos usuários na aplicação?

Sim, através da coleta externa de métricas.

##### Quais as ações e informações serão registradas?

Acessos e ativações de busca e serviços do Governo Federal.

##### Por quanto tempo, no mínimo, o registro de quem fez o que deve ser mantido?

N/A.

### Transações

##### Quais as fronteiras mais importantes das transações de banco de dados e aplicação?

A aplicação não possui acessos transacionais.

##### O padrão otimista de locking é apropriado ou é necessário algo mais complexo em alguma ou todas as situações?

A aplicação não possui acessos transacionais.

### Administração

##### Que tipos de intervenções “ao vivo” são requeridos?

N/A.

##### Há necessidade de realizar configuração remota da aplicação?

Não.

##### Há consoles de administração que serão usadas para gerenciar a aplicação?

Não, mas a JVM oferece informações de diagnóstico e telemetria através de JMX.

Visão de Implementação
----

Nesta seção descreva sucintamente a estrutura geral do modelo de implementação, a divisão do software em camadas e subsistemas no modelo de implementação e todos os componentes significativos do ponto de vista da arquitetura.

### Pacotes de Design Significativos do Ponto de Vista da Arquitetura

##### `br.gov.servicos.busca`

Objetos relativos à busca de serviços e outros objetos de domínio.

##### `br.gov.servicos.frontend`

Objetos relacionados a especificidades do ambiente Web.

##### `br.gov.servicos.config`

Configurações e definições padrão do Spring.

##### `br.gov.servicos.legado`

Adaptadores e mapeadores para o formato de exportação do Guia de Serviços legado (versão atualmente em produção).

##### `br.gov.servicos.conteudo`

Gerência de conteúdo (CMS) rudimentar embutido na aplicação.

### Componentes

##### Logs e auditoria

Logs são gerados utilizando a biblioteca [Slf4j] e gravados conforme a configuração em `src/main/resources/logback.xml`. Cada requisição HTTP ganha um _ticket_ (um UUID) e este é logado juntamente às mensagens pertinentes a esta.

##### Servidores Web

Utilizamos o [Apache Tomcat 8][Tomcat], embutido pelo [Spring-Boot], para a aplicação. Para balanceamento de carga e proxy/cache reverso, utilizamos o [HAProxy].

[Tomcat]:http://tomcat.apache.org/
[HAProxy]:http://www.haproxy.org/

##### Repositórios, busca e acesso a dados

Utilizam o [Spring Data], que implementa interfaces em tempo de execução com as funcionalidades requeridas pelas assinatudas dos métodos declarados, ou acessam dados diretamente através do `ElasticsearchTemplate`, quando as funcionalidades do [Spring Data] não são suficientes.

Lista dos subsistemas localizados em cada camada e o diagrama de componentes.