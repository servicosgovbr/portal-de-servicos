# Homologação e Produção

Ambientes gerenciados pelo Governo Federal (de acordo com a [infraestrutura](./infraestrutura.md)) têm a aplicação e o cluster de [ElasticSearch](elasticsearch.md) implantados internamente ao Ministério do Planejamento.

Os ambientes possuem configuração similar ao [ambiente Vagrant](./deploy-vagrant.md), com a adição de funcionalidades relacionadas a segurança, prevenção a ataques, backup e monitoramento.

## Instalação

A instalação dos ambientes é simplificada através dos scripts existentes no diretório `scripts/prod-like`, residente no [repositório no Github][gh]:

### `lb-node-install`

Este script deve ser executado, como `root`, em uma máquina destinada a ser o balanceador de carga. Ele instala, configura e inicia o [haproxy] de forma a distribuir a carga das requisições externas entre os servidores de aplicação.

São aceitos dois parâmetros em linha de comando, um para cada IP dos servidores de aplicação (caso não informados, são utilizados `10.16.0.13` e `10.16.0.12` por padrão).

Exemplo:

```sh
sudo /bin/bash scripts/prod-like/lb-node-install '10.16.0.13' '10.16.0.12'
```

### `app-node-install`

Este script deve ser executado, como `root`, em uma máquina destinada a servir a aplicação do Guia de Serviços. Este script:

- Configura o [repositório Yum] do Guia de Serviços
- Instala as ferramentas `wget` e `deltarpm`, caso não existentes no sistema
- Instala a JDK 1.8.0_40, caso não existente
- Instala, configura e inicia o _daemon_ do Guia de Serviços (`guia-de-servicos`).

Exemplo:

```sh
sudo /bin/bash scripts/prod-like/app-node-install
```

### `es-node-install`

Este script deve ser executado, como `root`, em uma máquina destinada a servir o motor de busca do Guia de Serviços, [ElasticSearch].

São aceitos dois parâmetros em linha de comando, um para cada IP dos servidores de busca (caso não informados, são utilizados `10.16.0.11` e `10.16.0.9` por padrão).
 
Este script:

- Configura o [repositório Yum](./repositorio-yum.md) do ElasticSearch
- Instala as ferramentas `wget` e `deltarpm`, caso não existentes no sistema
- Instala a JDK 1.8.0_40, caso não existente
- Instala, configura e inicia o _daemon_ do [ElasticSearch] (`elasticsearch`).

```sh
sudo /bin/bash scripts/prod-like/es-node-install '10.16.0.11' '10.16.0.9'
```

[gh]:https://github.com/servicosgovbr/guia-de-servicos/tree/master/scripts/prod-like
[haproxy]:http://www.haproxy.org
[ElasticSearch]:./elasticsearch.md