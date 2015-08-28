# Infraestrutura

![Diagrama da rede em ambiente similar a produção](/desenvolvimento/ambiente-docker.png)

## Ambientes Docker

Estes ambientes contém uma máquina virtual com as seguintes aplicações Docker:

* Balanceador de carga (haproxy)
* Portal de Serviços 1
* Portal de Serviços 2
* Editor de Serviços 1
* Editor de Serviços 2
* ElasticSearch 1
* ElasticSearch 2
* cAdvisor
* Logspout
* Logstash

Estas estão definidas na configuração, em formato [Docker-Compose], do [repositório relacionado][DOCKER-REPO].

[Docker-Compose]:http://docker.com/compose
[DOCKER-REPO]:https://github.com/servicosgovbr/docker

## Portas e firewalls

Por padrão, o balanceador é o único contêiner que expõe portas abertas para o host. Todo o tráfego deve ser direcionado a estas portas, e o balanceador lida com a distribuição interna. São elas:

* 80/tcp (HTTP): Tráfego para o Portal de Serviços (`/*`) e Editor de Serviços (`/editar/*`)
* 8080/tcp (HTTP): Tráfego para o cAdvisor (`/*`) e estatísticas do HAProxy (`/lb-stats`)
* 5601/tcp (HTTP): Tráfego para o Kibana (`/*`)

### Portas internas

Estas são as portas utilizadas internamente para comunicação entre contêineres:

| Origem             | Destino            | Portas              | Notas                        |
|--------------------|--------------------|---------------------|------------------------------|
| Balanceador        | Portal de Serviços | 8080/tcp            |                              |
| Balanceador        | Editor de Serviços | 8090/tcp            |                              |
| Balanceador        | cAdvisor           | 8080/tcp            |                              |
| Balanceador        | Kibana             | 5601/tcp            |                              |
| Balanceador        | ElasticSearch      | 9200/tcp e 9300/tcp | Usadas apenas para monitoria |
| Balanceador        | Logstash           | 5000/tcp            | Usada apenas para monitoria  |
| Portal de Serviços | ElasticSearch      | 9300/tcp            |                              |
| Logspout           | Logstash           | 5000/udp            |                              |
| Logstash           | ElasticSearch      | 9300/tcp            |                              |
| Kibana             | ElasticSearch      | 9200/tcp            | Usa apenas o ElasticSearch 1 |

### Volumes externos

Os seguintes arquivos e diretórios são disponibilizados da máquina host para os contêineres:

| Contêiner            | Volume                            | Backup? | Notas                                                |
|----------------------|-----------------------------------|---------|------------------------------------------------------|
| Editor de Serviços 1 | /root/.ssh                        | Sim     | Chave SSH para publicação no Github, somente leitura |
| Editor de Serviços 2 | /root/.ssh                        | Sim     | Chave SSH para publicação no Github, somente leitura |
| cAdvisor             | /var/run                          | Não     |                                                      |
| cAdvisor             | /                                 | Não     | Somente leitura                                      |
| cAdvisor             | /sys                              | Não     | Somente leitura                                      |
| cAdvisor             | /var/lib/docker/                  | Não     | Somente leitura                                      |
| Logspout             | /var/run/docker.sock              | Não     |                                                      |
| ElasticSearch 1      | /usr/share/elasticsearch/es1/data | Sim     |                                                      |
| ElasticSearch 2      | /usr/share/elasticsearch/es2/data | Sim     |                                                      |
| Logstash             | /root/docker/logstash             | Não     | Somente leitura                                      |

