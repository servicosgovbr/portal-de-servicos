# Vagrant

[![Diagrama da rede no Vagrant](/desenvolvimento/ambiente-vagrant.svg)](/desenvolvimento/ambiente-vagrant.graphml)

Criamos também um conjunto de _boxes_ para o [Vagrant]. Para utilizá-lo, confira que você está usando uma versão recente do [Vagrant] (ao menos 1.6.5) e rode:

```
vagrant up
```

É possível que as máquinas virtuais fiquem em um estado inconsistente, ou alguma das atualizações no código de provisionamento e instalação seja incompatível com versões anteriores das mesmas. Nestes casos, para recriá-las, basta:

```
vagrant destroy -f
vagrant up
```

## Execução da aplicação

A aplicação, uma vez instalada nas máquinas virtuais `app1` e `app2`, fica disponível nas portas `8082` e `8083` da máquina host. Uma outra máquina, `lb`, balanceia a carga entre as duas e escuta requisições na porta `8081`. Acessando `http://localhost:8081`, deve ser possível ver a última versão implantada.
 
Para atualizar a versão implantada, utilize a tarefa `vagrantDeploy`: 

```
./gradlew vagrantDeploy
```

## Rede interna

Para acessar qualquer uma das máquinas através de `ssh`, utilize o comando `vagrant ssh <nome>`, onde `nome` pode ser:

- `bastion`: máquina vazia para experimentos e desenvolvimento local, com o IP `10.16.0.180`.
- `lb`: balanceador de carga, com o IP `10.16.0.10`.
- `app1`: primeiro nodo servidor de aplicação, com o IP `10.16.0.13`.
- `app2`: segundo nodo servidor de aplicação, com o IP `10.16.0.12`.
- `es1`: primeiro nodo do ElasticSearch, com o IP `10.16.0.11`
- `es2`: segundo nodo do ElasticSearch, com o IP `10.16.0.9`

Uma alternativa mais rápida ao `vagrant ssh` é utilizar o arquivo `.sshconfig` (localizado na raiz do projeto) para carregar as configurações de acesso. Acesse as máquinas com os seguintes comandos:

```
ssh -F .sshconfig bastion
ssh -F .sshconfig lb-vagrant
ssh -F .sshconfig app1-vagrant
ssh -F .sshconfig app2-vagrant
ssh -F .sshconfig es1-vagrant
ssh -F .sshconfig es2-vagrant
```

[Vagrant]:http://vagrantup.com
