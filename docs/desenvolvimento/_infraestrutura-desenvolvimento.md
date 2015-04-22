## Ambientes de desenvolvimento e homologação

Estes ambientes contém 5 máquinas virtuais:

* Balanceador de carga
* Servidor de Aplicação 1 (Azul)
* Servidor de Aplicação 2 (Verde)
* Servidor de Busca 1
* Servidor de Busca 2

|VM         |OS    |Versão|Arquitetura|Cores|Memória|Disco |Portas        |
|-----------|------|------|-----------|-----|-------|------|--------------|
|Balanceador|CentOS|7     |64bit      |2    |2 GB   |50+ GB|22, 80        |
|Aplicação 1|CentOS|7     |64bit      |2    |2 GB   |50+ GB|22, 8080      |
|Aplicação 2|CentOS|7     |64bit      |2    |2 GB   |50+ GB|22, 8080      |
|Busca 1    |CentOS|7     |64bit      |2    |2 GB   |50+ GB|22, 9200, 9300|
|Busca 2    |CentOS|7     |64bit      |2    |2 GB   |50+ GB|22, 9200, 9300|

No ambiente de desenvolvimento, também há um servidor de Integração Contínua (Jenkins), já provisionado e configurado, que deve ter acesso [ssh] às máquinas acima, através de chaves compartilhadas com a equipe de desenvolvimento do sistema.

#### Balanceador de Carga

CentOS 7, 64 bits, com 2gb de RAM e 50+ GB de disco. Roda um servidor [HAProxy] na porta 80, e um servidor [ssh] na porta 22.

[HAProxy]:http://www.haproxy.org/
[ssh]:http://www.openssh.com/

#### Servidores de Aplicação

CentOS 7, 64 bits, com 2gb de RAM e 50+ GB de disco. Roda a aplicação sob a [Java Virtual Machine][JVM] versão `1.8.0_40` ou superior, na porta 8080, e um servidor [ssh] na porta 22.

[JVM]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

#### Servidores de Busca

CentOS 7, 64 bits, com 2gb de RAM e 50+ GB de disco. Roda o [ElasticSearch] sob a [Java Virtual Machine][JVM] versão `1.8.0_40` ou superior, nas portas 9200 e 9300, e um servidor [ssh] na porta 22.

[ElasticSearch]:https://www.elastic.co/products/elasticsearch