# Vagrant

[![Diagrama da rede no Vagrant](/desenvolvimento/ambiente-vagrant.svg)](/desenvolvimento/ambiente-vagrant.graphml)

Criamos também um conjunto de _boxes_ para o [Vagrant][VAGRANT]. Para utilizá-lo, confira que você está usando uma versão recente do Vagrant (ao menos 1.6.5) e rode:

```
vagrant up
```

É possível que as máquinas virtuais fiquem em um estado inconsistente, ou alguma das atualizações no código de provisionamento e instalação seja incompatível com versões anteriores das mesmas. Nestes casos, para recriá-las, basta:

```
vagrant destroy -f
vagrant up
```

## Execução da aplicação

A aplicação, uma vez instalada na máquina virtual `app`, fica disponível nas porta `8081` da máquina host. Acessando `http://localhost:8081`, deve ser possível ver a última versão implantada.
 
Para atualizar a versão implantada, utilize a tarefa `vagrantDeploy`: 

```
./gradlew vagrantDeploy
```

## Rede interna

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

[VAGRANT]:http://vagrantup.com
