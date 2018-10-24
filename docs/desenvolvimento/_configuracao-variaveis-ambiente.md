Tanto o Portal quanto o Editor de Serviços utilizam variáveis de ambiente para configuração. Todas elas têm valores padrão, e algumas são redefinidas na configuração dos contêiners usando o [Docker-Compose] (mais especificamente, através da diretiva `environment` dos arquivos `docker-compose.yml` do [Portal](https://git.planejamento.gov.br/sti/portal-servicos/blob/1.0.20-OS9-Sprint6/docker/docker-compose-builder/docker-compose/docker-compose.prod.yml) e do [Editor](https://git.planejamento.gov.br/sti/portal-servicos-editor-de-servicos/blob/1.0.31-OS9-Sprint6/docker/docker-compose-builder/docker-compose/docker-compose.prod.yml)).

Os arquivos que listam as configurações padrão são:

* Portal de Serviços: [`scripts/portal-de-servicos.default.config`](https://git.planejamento.gov.br/sti/portal-servicos/blob/1.0.20-OS9-Sprint6/scripts/portal-de-servicos.default.config)
* Editor de Serviços: [`scripts/editor-de-servicos.default.config`](https://git.planejamento.gov.br/sti/portal-servicos-editor-de-servicos/blob/1.0.31-OS9-Sprint6/docker/docker-compose-builder/docker-compose/docker-compose.prod.yml)

[Docker-Compose]:http://www.docker.com/compose
