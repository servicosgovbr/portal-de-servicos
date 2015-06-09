# Entrega Contínua

Diversos princípios de Entrega Contínua são utilizados no projeto do Portal de Serviços. Nesta seção, descreveremos brevemente como eles estão implementados.

## Integração Contínua

Utilizamos o [Snap CI](http://snap-ci.com) para realizar a execução de todos os testes e implantações necessários a cada _push_ no [repositório Git do projeto](http://github.com/servicosgovbr/portal-de-servicos).

Os seguintes passos estão configurados no _pipeline_:

### TESTES

Roda todos os testes unitários, de integração, e as checagens estáticas ([FindBugs, Checkstyle, etc.](./ferramentas-utilizadas.md)). Finalmente, publica os resultados do relatório de cobertura de testes no [Coveralls]. Todos os relatórios de execução também são publicados como artefatos, que podem ser baixados em formato HTML e XML.

[Coveralls]:https://coveralls.io/r/servicosgovbr/portal-de-servicos?branch=master

### PACOTES

Cria um arquivo JAR contendo todas as dependências do projeto (uberjar) necessárias para a execução da apliação, e embute este JAR em um arquivo RPM específico para nossa plataforma-alvo (CentOS 7 x64). Este RPM é então publicado em um [repositório Yum](./repositorio-yum.md), e fica disponível para instalação e atualização.

### ALPHA

(Re)instala o pacote RPM gerado no passo anterior em [uma instância do CentOS 7 x64 rodando na Amazon Web Services EC2](http://ec2-54-94-244-36.sa-east-1.compute.amazonaws.com), e reinicia os serviços necessários. Após alguns segundos, a nova versão da aplicação está reiniciada e pronta para receber requisições.

### DOCS

Atualiza a _branch_ `gh-pages`, utilizada pelo [Github Pages](https://pages.github.com/) com o resultado da compilação da documentação (localizada no diretório `docs`) pelo [Gitbook](http://gitbook.com). Esta fica disponível em [servicosgovbr.github.io/portal-de-servicos](http://servicosgovbr.github.io/portal-de-servicos).

### AVISO

Anuncia o fim do processo [na sala de bate-papo do Gitter](https://gitter.im/servicosgovbr/portal-de-servicos).