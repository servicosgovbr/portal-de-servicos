# Amazon Web Services

Para criar uma instância da aplicação na AWS EC2, é necessário utilizar uma imagem CentOS 7, 64 bits em uma instância com memória e processamento adequados. Para testes e utilização leve, o _free tier_ das máquinas `t2.micro` é suficiente.

O script `scripts/iniciar-instancia-ec2` pode ser usado como base.

Após a criação da instância, é necessária a instalação do pacote RPM, que pode ser feito de duas maneiras:
 
* Gerando um novo pacote com base no código atual, através do comando `./gradlew buildRpm`, copiando-o para a instância e instalando manualmente através do `yum install`
* Utilizando um dos pacotes gerados pelo [Snap] a cada ciclo de [Entrega Contínua], através do `yum install`

O serviço deve ser reiniciado após a instalação, utilizando o `systemctl`:
 
```
sudo systemctl daemon-reload
sudo systemctl restart portal-de-servicos 
```

## Entrega Contínua

Os passos descritos na seção anterior são feitos automaticamente a cada _push_, através do script `scripts/alpha-deploy`. Para mais detalhes, veja a seção [Entrega Contínua].

[Entrega Contínua]:./entrega-continua.md