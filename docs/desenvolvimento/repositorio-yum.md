# Repositório Yum

Os RPMs do Guia de Serviços estão disponíveis em um repositório Yum, com as seguintes configurações:

```
[guia-de-servicos]
name = Guia de Serviços
baseurl = https://s3-sa-east-1.amazonaws.com/servicosgovbr/centos/7/
enabled=1
gpgcheck=1
gpgkey=https://raw.githubusercontent.com/servicosgovbr/guia-de-servicos/master/src/main/resources/static/GPG-KEY
```

A chave GPG utilizada para assinar os pacotes e o repositório é:

```
pub   2048R/2E1F2BA2 2015-04-07
      Key fingerprint = 1F90 DD30 DAF2 DCDF 4F27  DC78 F68F 9EB4 2E1F 2BA2
uid                  Guia de Serviços <gpg@servicos.gov.br>
sub   2048R/BA141101 2015-04-07
```