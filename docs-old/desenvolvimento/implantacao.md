Implantação
----

### Visão de Implantação

Diagrama geral de um ambiente:

[![Diagrama de um ambiente similar à produção](./ambiente.jpg)](./ambiente.graphml)

O sistema pode ser escalado através da adição de máquinas, tanto servidores de aplicação quanto de servidores de busca, permitindo escalabilidade horizontal.

Teremos 3 ambientes de implantação distintos: desenvolvimento, homologação e produção. Os dois primeiros possuem requisitos de hardware idênticos, enquanto produção possui mais processamento, memória e disco.

### Desenvolvimento e Homologação



Componentes de Software
----

- Lombok (anotações)
- Pegdown (compilação de arquivos de conteúdo [Markdown])
- Slf4j (logging)
- Slugify (geração de IDs)
- Spring-Bboot (framework de desenvolvimento)
- Spring-Data-ElasticSearch (acesso ao [ElasticSearch])
- Thymeleaf (templates)
- Tomcat (servidor web)

Mais definições e detalhes sobre a arquitetura na seção [Arquitetura](./arquitetura.md)

[Markdown]:http://daringfireball.net/projects/markdown/

Instalação e Configuração
----

O passo `ARCHIVE` do [Snap-CI] gera arquivos [RPM] compatíveis com os ambientes descritos acima. Basta instalar o RPM nos servidores de aplicação e reiniciar:

	rpm -hiv https://snap-ci.com/servicosgovbr/guia-de-servicos/branch/master/…/guia-de-servicos-1.0.…-1.noarch.rpm

Para a URL exata do RPM para a implantação específica a fazer, é necessário consultar a lista de _build pipelines_ do [Snap-CI], em [https://snap-ci.com/servicosgovbr/guia-de-servicos/branch/master](https://snap-ci.com/servicosgovbr/guia-de-servicos/branch/master).

[Snap-CI]:http://snap-ci.com/
[RPM]:http://www.rpm.org/