# Backups

Backups devem ser feitos dos seguintes caminhos:

| Caminho                           | Incremental? |
|-----------------------------------|--------------|
| /root/.ssh                        | Não          |
| /usr/share/elasticsearch/es1/data | Sim          |  
| /usr/share/elasticsearch/es2/data | Sim          |  
| /var/log/**/*                     | Sim          |

A retenção dos mesmos pode variar dependendo do ambiente. Recomenda-se ao menos 30 dias.

## Usuários e Permissões

Para efetuar o backup  dos dados do banco de dados que dá suporte à aplicação, contidos no contêiner dbdata, é necessário realizar os seguintes passos:

1. Criar novo contêiner docker que se conecta com a pasta `/dbdata` onde está localizado os dados do banco
2. Esse contêiner deve ter uma pasta `/backup` compartilhada com o sistema
3. Executar comando para comprimir a pasta `/dbdata` na pasta `/backup`:
```
docker run --volumes-from dbdata -v $(pwd):/backup ubuntu bash -c "tar czvf /backup/backup-$(date '+%d_%m_%y_%H_%M').tar.gz /dbdata"
```

Para recuperar os dados a partir de um backup:

1. Pare a execução dos contêineres no host do Docker
`cd ~/docker/` e `docker-compose stop`
2. Execute o data volume contêiner dbdata com o arquivo backup.tar descomprimido:
```
docker run --volumes-from dbdata -v $(pwd):/backup ubuntu bash -c "rm -rf /dbdata/* && cd /tmp && tar xzvf /backup/backup-22_12_15_07_51.tar.gz && mv dbdata/* ../dbdata/"
```
3. Ligue novamente os contêineres:	
`docker-compose up`

Recomenda-se que o backup dos dados dos usuários seja efetuado diariamente por uma rotina automática. Considerando que o volume de dados armazenados será pequeno, o armazenamento diário não deverá ser um problema. Além disso, recomenda-se que os backups mais antigos que sete dias sejam descartados automaticamente. Essa rotina de deleção automática deverá ser executada somente se backups dos últimos sete dias existirem de fato.

**Atenção**: recomenda-se  que o backup seja armazenado em um volume externo da máquina host do Docker.
