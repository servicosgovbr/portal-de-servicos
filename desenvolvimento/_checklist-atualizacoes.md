Todos os comandos a seguir devem ser rodados como super-usuário (root), e presumem uma máquina [CentOS] 7 64bit, que já tem os contêineres [Docker] rodando.

- Atualize os repositórios [servicosgovbr/portal](https://git.planejamento.gov.br/sti/portal-servicos) e [servicosgovbr/editor](https://git.planejamento.gov.br/sti/portal-servicos-editor-de-servicos) no diretório `/root/`:

```bash
cd /root/portal-servicos-editor-de-servicos
git pull
cd /root/portal-servicos
git pull
```

- Parar os contêineres:

```bash

cd /root/portal-servicos/docker/docker-compose-builder/docker-compose
docker-compose stop
```
- Baixe as novas imagens:

```bash
cd /root/portal-servicos/docker/docker-compose-builder/docker-compose
docker-compose pull
```
- Rode os contêineres:

```bash
cd /root/portal-servicos/docker/docker-compose-builder/docker-compose
docker-compose up -d
```


{% include '../desenvolvimento/_checklist-verificacoes.md' %}

A instalação está concluída.

Se a atualização não for bem sucedida, o seguinte comando poderá ser executado, no diretório padrão do Docker, como solução de contorno:
`./scripts/volta_estado_inicial`.
Esse script irá apagar todos os contêineres e imagens locais do Docker. Imagens contendo as últimas versões do ambiente serão obtidas da Internet. Além disso, o ambiente será construído de maneira automática novamente.

**Lembrete:** Antes de executar o comando acima, é muito importante que um backup válido dos usuários e permissões exista. Caso contrário, todos os usuários criados para aquele ambiente serão perdidos. Maiores informações sobre como proceder com a criação de um backup podem ser encontradas na seção de Backup (Usuários e Permissões).

[Docker]:http://www.docker.com
[Docker-Compose]:http://www.docker.com/compose
[Git]:http://git-scm.org
