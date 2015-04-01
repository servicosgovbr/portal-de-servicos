# Ferramentas utilizadas

## No navegador

### [HTML 5](http://www.w3.org/TR/html5/)

Última versão do padrão internacional.

### [SASS](http://sass-lang.com/)

Extensão da sintaxe do CSS com variáveis, templates, blocos e muitas outras funcionalidades.

### [jQuery](http://jquery.org/)

Biblioteca JavaScript comumente utilizada para adicionar comportamentos à página.

### [Modernizr](http://modernizr.com/)

Biblioteca que simplifica a criação de páginas compatíveis com múltiplos navegadores.

## No servidor

### [Java 8][JDK8]

Devida à sua ubiquidade e facilidade de manutenção, ferramental existente, bibliotecas maduras e ampla adoção na infra-estrutura do Governo Brasileiro.

### [Lombok](http://projectlombok.org/)

Gerador de código cerimonial para Java com diversos utilitários e extensões à sintaxe da linguagem. Para a utilização em IDEs, é necessário um [plugin](https://github.com/mplushnikov/lombok-intellij-plugin).

### [Tomcat](http://tomcat.apache.org)

Servidor web em Java amplamente utilizado.

### [Slf4j](http://slf4j.org)

Biblioteca para gravar, filtrar e organizar a saída de logs.

### [Spring Data](http://projects.spring.io/spring-data/)

Facilidades para acesso a dados e geração simplificada de repositórios, queries e controles transacionais.

### [Spring Boot][BOOT]

Traz o framework Spring e diversas funcionalidades de configuração por convenção, autoconfiguração e empacotamento de aplicações Java.

### [Spring MVC][MVC]

Possibilita a escrita de serviços web de alta performance com código intuitivo, direto e fácil de testar.

### [Thymeleaf][THYME]

Aumenta a sintaxe de arquivos HTML e XML para permitir construções poderosas de exibição de dados.

### [ElasticSearch][ES]

Mecanismo de busca de dados e documentos, descrito em mais detalhes na seção "[ElasticSearch](./elasticsearch.md)".

### [Markdown]

Linguagem de marcação de texto simples e orientada à produção de textos eficiente e compatível com HTML.

### [Pegdown](https://github.com/sirthias/pegdown)

Processador de [Markdown] em Java.

### [Slugify](https://github.com/slugify/slugify)

Gerador de _slugs_, strings de texto simples e que podem ser utilizadas seguramente em trechos de URLs ou identificadores internos de objetos.

## Em desenvolvimento

### [IntelliJ IDEA 14 CE](https://www.jetbrains.com/idea/download/)

Ambiente de desenvolvimento integrado altamente produtivo para aplicações Java.

### [Jolokia][JOLOKIA]

Utilizado em desenvolvimento para facilitar o acesso a objetos Java Management Extensions (JMX).

### [Gradle][GRADLE]

Ferramenta de construção de aplicações Java compatível com repositórios Maven e de fácil extensão.

### [Snap CI][Snap] 

Serviço de integração contínua desenvolvido pela [ThoughtWorks]. Se integra ao Github permitindo que, a cada novo código submetido, uma versão da aplicação seja compilada, testada, empacotada e publicada em um ambiente predefinido.

### [Git](http://git-scm.org)

Ferramenta de controle de versão amplamente utilizada.

### [GitBook](http://gitbook.com)

Ferramenta de formatação e geração de documentação em formatos HTML, PDF, ePub, Mobi, etc.

### [Vagrant](http://vagrantup.com)

Automação de máquinas virtuais.

## Em testes

### [JUnit][JUNIT]

Biblioteca para testes unitários e de integração amplamente utilizada em aplicações Java.

### [Mockito][MOCKITO]

Biblioteca para a criação de objetos falsos (mocks) para facilitar a escrita e aumentar a qualidade de testes.

### [JaCoCo](http://www.eclemma.org/jacoco/) (Java Code Coverage)

Permite acompanhar e gerar relatórios para encontrar código não executado durante os testes automatizados (_cobertos_). 

### [Coveralls](https://coveralls.io/r/servicosgovbr/guia-de-servicos?branch=master)

Permite visualizar a porcentagem de cobertura de testes da aplicação, visando garantir o acompanhamento público da qualidade de nossos testes automatizados.

## Para comunicação

### Google Groups ([discussão](https://groups.google.com/d/forum/guiadeservicos-discussao) e [anúncios](https://groups.google.com/d/forum/guiadeservicos-anuncios))

Duas listas de discussão foram criadas para o facilitar a comunicação no projeto: 

* Discussão: discussões gerais sobre o projeto (com links, dúvidas, debates, etc.)
* Anúncios: divulgação de anúncios (de baixo tráfego, somente para informativos gerais, novas publicações e eventos maiores)

## [Github](http://github.com/servicosgovbr/guia-de-servicos)

Github é o repositório onde o código fonte do projeto é armazenado.

A utilização do Github reforça nosso compromisso com a transparência com relação ao trabalho desenvolvido pelo time. Qualquer cidadão pode visualizar e revisar o código além de permitir que os interessados contribuam com novas funcionalidades, correções ou melhorias, seguindo o [guia de contribuição](../desenvolvimento/como-contribuir.md).

Uma nova organização foi criada no Github para de agrupar qualquer projeto ou subprojeto relacionado ao Guia de Serviços.

## [Github Issues](http://github.com/servicosgovbr/guia-de-servicos/issues)

O Github Issues é uma ferramenta integrada ao Github que permite a criação de tarefas a serem analisadas e desenvolvidas pelo time. Utilizamos esta ferramenta para registrar e gerenciar nossas hipóteses no decorrer do projeto.

Por ser uma ferramenta aberta, ela permite a colaboração de quaisquer interessados no projeto, seja sugerindo novas hipóteses, melhorias, reportando problemas ou contribuindo com código e documentações.

## [Waffle](http://waffle.io/servicosgovbr/guia-de-servicos)

O Waffle é um quadro virtual e integrado ao Github Issues que gera visualizações em tempo real de cada uma das tarefas e seu estado atual.

Utilizamos esta ferramenta para dar mais visibilidade às tarefas que estão sendo desenvolvidas pelo time e quem são os responsáveis por elas.

### [Git-Crypt][GITCRYPT]

Permite gerenciar arquivos com informações sigilosas, mesmo em repositórios Git públicos, através de [GPG].

[Markdown]:http://daringfireball.net/projects/markdown/
[GPG]:https://www.gnupg.org/
[GITCRYPT]:https://www.agwa.name/projects/git-crypt/
[ThoughtWorks]:http://thoughtworks.com
[JDK8]:https://www.java.com/
[BOOT]:http://projects.spring.io/spring-boot/
[MVC]:http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html
[SEC]:http://projects.spring.io/spring-security/
[THYME]:http://www.thymeleaf.org
[GRADLE]:http://www.gradle.org
[JUNIT]:http://junit.org
[MOCKITO]:http://mockito.org/
[JOLOKIA]:http://www.jolokia.org/
[ES]:http://elasticsearch.org
[Snap]:http://snap-ci.com
