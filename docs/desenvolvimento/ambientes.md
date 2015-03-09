Local
----

Tentamos facilitar o desenvolvimento de novas funcionalidades por membros da comunidade, e todas as contribuições são
bem-vindas. Para começar, você vai precisar de um ambiente de desenvolvimento Java atualizado, com:

* [git][GIT]
* [git-crypt][GITCRYPT]
* [GPG][GPG]
* [JDK 8][JDK8]
* Sua IDE favorita (nós preferimos [IntelliJ IDEA 14 CE][IDEA14CE])

!!! tip "Lombok e sua IDE"
	O plugin do lombok precisa ser manualmente instalado na sua IDE. Para o IDEA procure nos repositórios de plugins pelo _Lombok_

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

Criamos também uma _box_ para o [Vagrant][VAGRANT]. Para utilizá-la, confira que você está usando uma versão recente do
Vagrant e rode:

```
vagrant up
```

[GIT]:http://git-scm.org
[GITCRYPT]:https://www.agwa.name/projects/git-crypt/
[GPG]:https://www.gnupg.org/
[JDK8]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[IDEA14CE]:https://www.jetbrains.com/idea/download/
[VAGRANT]:http://vagrantup.com
