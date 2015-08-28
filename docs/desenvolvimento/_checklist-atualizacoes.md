Todos os comandos a seguir devem ser rodados como super-usuário (root), e presumem uma máquina [CentOS] 7 64bit, que já tem os contêineres [Docker] rodando.

- Atualize o repositório [servicosgovbr/docker](https://github.com/servicosgovbr/docker) no diretório `/root`:

```bash
cd /root
git pull
```

O comando acima deve produzir saída similar à seguinte:

```
remote: Counting objects: 27, done.
remote: Compressing objects: 100% (17/17), done.
remote: Total 27 (delta 15), reused 22 (delta 10), pack-reused 0
Unpacking objects: 100% (27/27), done.
From github.com:servicosgovbr/docker
   1f736fb..f069a55  master     -> origin/master
First, rewinding head to replay your work on top of it...
Fast-forwarded master to f069a5552fec4c8bc9226c7941bb89fe0420ed75.
```

- Rode os contêineres:

```bash
docker-compose up -d
```

O comando acima deve produzir saída similar à seguinte:

```
Creating cadvisor...
Creating editor2...
Creating editor1...
Creating es2...
Creating es1...
Creating logstash...
Creating logspout...
Creating portal2...
Creating kibana...
Creating portal1...
Creating balanceador...
```

{% include '../desenvolvimento/_checklist-verificacoes.md' %}

A instalação está concluída.

[Docker]:http://www.docker.com
[Docker-Compose]:http://www.docker.com/compose
[Git]:http://git-scm.org
