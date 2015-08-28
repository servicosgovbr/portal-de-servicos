Tanto o Portal quanto o Editor de Serviços utilizam variáveis de ambiente para configuração. Todas elas têm valores padrão, e algumas são redefinidas na configuração dos contêiners usando o [Docker-Compose] (mais especificamente, através da diretiva `environment` dos arquivos `docker-compose.yml` do [Portal](https://github.com/servicosgovbr/docker/blob/master/portal-de-servicos/docker-compose.yml) e do [Editor](https://github.com/servicosgovbr/docker/blob/master/editor-de-servicos/docker-compose.yml)).

Os arquivos que listam as configurações padrão são:

* Portal de Serviços: [`scripts/portal-de-servicos.default.config`](https://github.com/servicosgovbr/portal-de-servicos/blob/master/scripts/portal-de-servicos.default.config)
* Editor de Serviços: [`scripts/editor-de-servicos.default.config`](https://github.com/servicosgovbr/editor-de-servicos/blob/master/scripts/editor-de-servicos.default.config)

[Docker-Compose]:http://www.docker.com/compose