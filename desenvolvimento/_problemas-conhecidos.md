#### Alterações na rede, firewall ou iptables

O Docker necessita ser reiniciado quando uma mudança estrutural ou de configuração de redes é realizada na máquina host.

Presumindo uma máquina [CentOS] 7 64bit, que já possui os contêineres [Docker] rodando, recomendamos que os seguintes comandos sejam executados:

- No repositório local [servicosgovbr/docker](https://github.com/servicosgovbr/docker), pare e remova os contêineres:

```bash
docker stop $(docker ps -a -q)
docker kill $(docker ps -a -q)
```

- Reinicie o Docker:

```bash
systemctl restart docker
```

- Inicie os contêineres novamente:

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
**Lembrete:** Antes de executar os passos descritos acima, é muito importante que um backup válido dos usuários e permissões exista. Caso contrário, todos os usuários criados para aquele ambiente serão perdidos. Maiores informações sobre como proceder com a criação de um backup podem ser encontradas na seção de Backup (Usuários e Permissões).

[Docker]:http://www.docker.com