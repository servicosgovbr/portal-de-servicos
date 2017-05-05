# Backups

Backups devem ser feitos dos seguintes caminhos:

| Caminho                           | Incremental? |
|-----------------------------------|--------------|
| /root/.ssh                        | Não          |
| /usr/share/elasticsearch/es1/data | Sim          |  
| /usr/share/elasticsearch/es2/data | Sim          |  
| /var/log/**/*                     | Sim          |
| /var/lib/postgresql/data          | Sim          |

A retenção dos mesmos pode variar dependendo do ambiente. Recomenda-se ao menos 30 dias.

## Usuários e Permissões

Para efetuar o backup dos dados do banco de dados que dá suporte à aplicação, contidos no NFS SERVER, é necessário realizar os seguintes passos:

1. Compactar a pasta `/exports` onde está localizado os dados do banco no servidor do NFS SERVER
2. Esse servidor deve ter uma pasta `/exports` compartilhada com o sistema
3. Executar comando para realizar o backup a pasta `/exports` no servidor do NFS SERVER preservando as permissão de usuários e grupo da pasta:
```
rsync -av /exporte.$(date '+%d_%m_%y_%H_%M')
```

Para recuperar os dados a partir de um backup:

```
mv exporte.data-atual /exports
```

Recomenda-se que o backup dos dados dos usuários seja efetuado diariamente por uma rotina automática. Considerando que o volume de dados armazenados será pequeno, o armazenamento diário não deverá ser um problema. Além disso, recomenda-se que os backups mais antigos que sete dias sejam descartados automaticamente. Essa rotina de deleção automática deverá ser executada somente se backups dos últimos sete dias existirem de fato.

**Atenção**: recomenda-se  que o backup seja armazenado em um volume externo da máquina host do Docker.
