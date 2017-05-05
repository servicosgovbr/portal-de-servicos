# Infraestrutura

![Diagrama da rede em ambiente similar a produção](/desenvolvimento/ambiente-docker.png)

## Ambientes Docker

Estes ambientes contém uma máquina virtual com as seguintes aplicações Docker:

* Portal de Serviços
* Editor de Serviços
* Postgres
* ElasticSearch

Estas estão definidas na configuração, em formato [Docker-Compose], do [repositório relacionado][DOCKER-REPO].

[Docker-Compose]:http://docker.com/compose
[DOCKER-REPO]:https://git.planejamento.gov.br/sti/portal-servicos-editor-de-servicos/tree/1.0.31-OS9-Sprint6/docker

## Portas e firewalls

Todo o tráfego deve ser direcionado a estas portas:

* 80/tcp (HTTP): Tráfego para o Portal de Serviços (`/*`) e Editor de Serviços (`/editar/*`)

### Portas internas

Estas são as portas utilizadas internamente para comunicação entre contêineres:

| Origem             | Destino            | Portas              | Notas                        |
|--------------------|--------------------|---------------------|------------------------------|
| Portal de Serviços | ElasticSearch      | 9300/tcp            |                              |
| Portal de Serviços | Postgres           | 5432/tcp            |                              |
| Editor de Serviços | Postgres           | 5432/tcp            |                              |


### Volumes externos

Os seguintes arquivos e diretórios são disponibilizados da máquina host para os contêineres:

| Contêiner            | Volume                            | Backup? | Notas                                                |
|----------------------|-----------------------------------|---------|------------------------------------------------------|
| Editor de Serviços   | /root/.ssh                        | Sim     | Chave SSH para publicação no Github, somente leitura |
| ElasticSearch        | /usr/share/elasticsearch/es1/data | Sim     |                                                      |
| Postgres             | /var/lib/postgresql/data          | Sim     |                                                      |
