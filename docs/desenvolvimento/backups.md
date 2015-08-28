# Backups

Backups devem ser feitos dos seguintes caminhos:

| Caminho                           | Incremental? |
|-----------------------------------|--------------|
| /root/.ssh                        | Não          |
| /usr/share/elasticsearch/es1/data | Sim          |  
| /usr/share/elasticsearch/es2/data | Sim          |  
| /var/log/**/*                     | Sim          |

A retenção dos mesmos pode variar dependendo do ambiente. Recomenda-se ao menos 30 dias.