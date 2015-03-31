# Amazon Web Services

Para criar uma instância da aplicação na AWS EC2, é necessário utilizar uma imagem CentOS 7, 64 bits em uma instância com memória e processamento adequados. Para testes e utilização leve, o _free tier_ das máquinas `t2.micro` é suficiente.

O script `scripts/launch-ec2-instance` pode ser usado como base.

Após a criação da instância, é necessária a instalação do pacote RPM gerado (`guia-de-servicos-1.0.*-1.noarch.rpm`) na instância. Para isto, basta copiar o arquivo para a instância e instalar o RPM usando `yum install`. O serviço deve ser reiniciado após a instalação, utilizando o `systemctl`:
 
```
sudo systemctl daemon-reload
sudo systemctl restart guia-de-servicos 
```

## Entrega Contínua

Os passos descritos na seção anterior são feitos automaticamente a cada _push_, através do script `scripts/alpha-deploy`. Para mais detalhes, veja a seção [Entrega Contínua](./entrega-continua.md).