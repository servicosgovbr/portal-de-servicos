Local
----

Tentamos facilitar o desenvolvimento de novas funcionalidades por membros da comunidade, e todas as contribuições são bem-vindas. Para começar, é necessário um ambiente de desenvolvimento Java atualizado, com:

* [git][GIT]
* [git-crypt][GITCRYPT]
* [GPG][GPG]
* [JDK 8][JDK8]
* Sua IDE favorita (nós preferimos [IntelliJ IDEA 14 CE][IDEA14CE])

!!! tip "Lombok e sua IDE"
	O plugin do Lombok precisa ser manualmente instalado na sua IDE. Para o IntelliJ IDEA, procure nos repositórios de plugins pelo [Lombok IntelliJ Plugin][PLUGIN].

Para começar, clone o repositório:

```
cd ~/projetos # ou o diretório de sua preferência
git clone "https://github.com/servicosgovbr/guia-de-servicos.git"
```

A seguir, gere o arquivo de projeto do [IntelliJ IDEA][IDEA14CE]:

```
./gradlew clean idea
```

Abra o arquivo `guia-de-servicos.ipr` no IntelliJ IDEA e você está pronto para desenvolver.

Vagrant
----

Criamos também uma _box_ para o [Vagrant][VAGRANT]. Para utilizá-la, confira que você está usando uma versão recente do Vagrant e rode:

```
vagrant up
```

[GIT]:http://git-scm.org
[GITCRYPT]:https://www.agwa.name/projects/git-crypt/
[GPG]:https://www.gnupg.org/
[JDK8]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[IDEA14CE]:https://www.jetbrains.com/idea/download/
[VAGRANT]:http://vagrantup.com
[PLUGIN]:https://github.com/mplushnikov/lombok-intellij-plugin

Ambientes da CGSTI/DTI
----

### Desenvolvimento

Este ambiente contém 5 máquinas virtuais:

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

No ambiente de desenvolvimento, também há um servidor de Integração Contínua (Jenkins), já existente e configurado, que deve ter acesso [ssh] às máquinas acima, através de .

#### Balanceador de Carga

CentOS 7, 64 bits, com 2gb de RAM e 50+ GB de disco. Roda um servidor [nginx] na porta 80, e um servidor [ssh] na porta 22.

[nginx]:http://www.nginx.org
[ssh]:http://www.openssh.com/

#### Servidores de Aplicação

CentOS 7, 64 bits, com 2gb de RAM e 50+ GB de disco. Roda a aplicação sob a [Java Virtual Machine][JVM] versão `1.8.0_40` ou superior, na porta 8080, e um servidor [ssh] na porta 22.

[JVM]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

#### Servidores de Busca

CentOS 7, 64 bits, com 2gb de RAM e 50+ GB de disco. Roda o [ElasticSearch] sob a [Java Virtual Machine][JVM] versão `1.8.0_40` ou superior, nas portas 9200 e 9300, e um servidor [ssh] na porta 22.


### Homologação

Este ambiente contém 5 máquinas virtuais:

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

#### Balanceador de Carga

CentOS 7, 64 bits, com 2gb de RAM e 50+ GB de disco. Roda um servidor [nginx] na porta 80, e um servidor [ssh] na porta 22.

[nginx]:http://www.nginx.org
[ssh]:http://www.openssh.com/

#### Servidores de Aplicação

CentOS 7, 64 bits, com 2gb de RAM e 50+ GB de disco. Roda a aplicação sob a [Java Virtual Machine][JVM] versão `1.8.0_40` ou superior, na porta 8080, e um servidor [ssh] na porta 22.

[JVM]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

#### Servidores de Busca

CentOS 7, 64 bits, com 2gb de RAM e 50+ GB de disco. Roda o [ElasticSearch] sob a [Java Virtual Machine][JVM] versão `1.8.0_40` ou superior, nas portas 9200 e 9300, e um servidor [ssh] na porta 22.


### Produção

Este ambiente contém 5 máquinas virtuais:

* Balanceador de carga
* Servidor de Aplicação 1 (Azul)
* Servidor de Aplicação 2 (Verde)
* Servidor de Busca 1
* Servidor de Busca 2

|VM         |OS    |Versão|Arquitetura|Cores|Memória|Disco  |Portas        |
|-----------|------|------|-----------|-----|-------|-------|--------------|
|Balanceador|CentOS|7     |64bit      |4    |4 GB   |100+ GB|22, 80        |
|Aplicação 1|CentOS|7     |64bit      |4    |4 GB   |100+ GB|22, 8080      |
|Aplicação 2|CentOS|7     |64bit      |4    |4 GB   |100+ GB|22, 8080      |
|Busca 1    |CentOS|7     |64bit      |4    |4 GB   |100+ GB|22, 9200, 9300|
|Busca 2    |CentOS|7     |64bit      |4    |4 GB   |100+ GB|22, 9200, 9300|

#### Balanceador de Carga

CentOS 7, 64 bits, com 4 cores, 4gb de RAM e 100+ GB de disco. Roda um servidor [nginx] na porta 80, e um servidor [ssh] na porta 22.

[nginx]:http://www.nginx.org
[ssh]:http://www.openssh.com/

#### Servidores de Aplicação

CentOS 7, 64 bits, com 4 cores, 4gb de RAM e 100+ GB de disco. Roda a aplicação sob a [Java Virtual Machine][JVM] versão `1.8.0_40` ou superior, na porta 8080, e um servidor [ssh] na porta 22.

[JVM]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

#### Servidores de Busca

CentOS 7, 64 bits, com 4 cores, 4gb de RAM e 100+ GB de disco. Roda o [ElasticSearch] sob a [Java Virtual Machine][JVM] versão `1.8.0_40` ou superior, nas portas 9200 e 9300, e um servidor [ssh] na porta 22.

