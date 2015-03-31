Local
----

Tentamos facilitar o desenvolvimento de novas funcionalidades por membros da comunidade, e todas as contribuições são bem-vindas. Para começar, é necessário um ambiente de desenvolvimento Java atualizado, com:

* [git][GIT]
* [git-crypt][GITCRYPT]
* [GPG][GPG]
* [JDK 8][JDK8]
* Sua IDE favorita (nós preferimos [IntelliJ IDEA 14 CE][IDEA14CE])

Para começar, clone o repositório:

```
cd ~/projetos # ou o diretório de sua preferência
git clone "https://github.com/servicosgovbr/guia-de-servicos.git"
```

A seguir, gere o arquivo de projeto do [IntelliJ IDEA][IDEA14CE]:

```
./gradlew clean idea
```

Abra o arquivo `guia-de-servicos.ipr` no IntelliJ IDEA e você está pronto para navegar pelo código. 

Caso você não tenha desenvolvido utilizando o [Lombok] antes, a IDE marcará diversos arquivos com problemas de compilação. Será necessário instalar o plugin para que as coisas funcionem normalmente. Este plugin precisa ser manualmente instalado.

Para o IntelliJ IDEA, procure nos repositórios de plugins pelo [Lombok IntelliJ Plugin][PLUGIN].

[Lombok]:http://projectlombok.org/


Vagrant
----

Criamos também um conjunto de _boxes_ para o [Vagrant][VAGRANT]. Para utilizá-lo, confira que você está usando uma versão recente do Vagrant (ao menos 1.6.5) e rode:

```
vagrant up
```

É possível que as máquinas virtuais fiquem em um estado inconsistente, ou alguma das atualizações no código de provisionamento e instalação seja incompatível com versões anteriores das mesmas. Nestes casos, para recriá-las, basta:

```
vagrant destroy -f
vagrant up
```

### Rede interna

Para acessar qualquer uma das máquinas através de `ssh`, utilize o comando `vagrant ssh <nome>`, onde `nome` pode ser:

- `app`: servidor de aplicação, com o balanceador de carga e a última versão da aplicação instalada em execução. Possui o IP `10.133.133.11`.
- `es1`: primeiro nodo do ElasticSearch, com o IP `10.133.133.22`
- `es2`: segundo nodo do ElasticSearch, com o IP `10.133.133.33`

Uma alternativa mais rápida ao `vagrant ssh` é utilizar o arquivo `.sshconfig` (localizado na raiz do projeto) para carregar as configurações de acesso. Acesse as máquinas com os seguintes comandos:

```
ssh -F .sshconfig app-vagrant
ssh -F .sshconfig es1-vagrant
ssh -F .sshconfig es2-vagrant
```

[GIT]:http://git-scm.org
[GITCRYPT]:https://www.agwa.name/projects/git-crypt/
[GPG]:https://www.gnupg.org/
[JDK8]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[IDEA14CE]:https://www.jetbrains.com/idea/download/
[VAGRANT]:http://vagrantup.com
[PLUGIN]:https://github.com/mplushnikov/lombok-intellij-plugin
