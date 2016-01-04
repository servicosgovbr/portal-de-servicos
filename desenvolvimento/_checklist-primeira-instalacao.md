Todos os comandos a seguir devem ser rodados como super-usuário (root), e presumem uma máquina [CentOS] 7 64bit, sem nenhuma configuração adicional efetuada.

- Verifique que o kernel instalado é, no mínimo, 3.10, rodando em modo x64:

```bash
uname -r 
3.10.0-229.el7.x86_64
```

Caso contrário, é preciso realizar as atualizações necessárias antes de continuar.

- Desabilite o [SELinux](https://wiki.centos.org/HowTos/SELinux) temporariamente:

```bash
setenforce 0
```

- Instale o [Docker]:

```bash
curl -sSL https://get.docker.com | sh
```

- Inicie o serviço do [Docker]:

```bash
systemctl start docker
```

- Verifique que o serviço do [Docker] inicializou corretamente:

```bash
docker ps
```

O comando acima deve produzir saída similar à seguinte:

```
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
```

- Verifique que o [Docker] consegue baixar e instanciar contêineres:

```bash
docker run alpine echo "olá"
```

O comando acima deve produzir saída similar à seguinte:

```
Unable to find image 'alpine:latest' locally
latest: Pulling from library/alpine
31f630c65071: Already exists 
library/alpine:latest: The image you are pulling has been verified. Important: image verification is a tech preview feature and should not be relied on to provide security.
Digest: sha256:c471fce1d08618adf4c6c0d72c047b9f3d5ef82cae0ca9a157ce1c800d42722f
Status: Downloaded newer image for alpine:latest
olá
```

- Configure o serviço do [Docker] para inicializar durante o boot:

```bash
chkconfig docker on
```

- Instale o [Docker-Compose]:

```bash
curl -L https://github.com/docker/compose/releases/download/1.4.0/docker-compose-`uname -s`-`uname -m` > /usr/bin/docker-compose
chmod +x /usr/bin/docker-compose
```

- Verifique a instalação do [Docker-Compose]

```bash
docker-compose version
```

O comando acima deve produzir saída similar à seguinte:

```
docker-compose version: 1.4.0
docker-py version: 1.3.1
CPython version: 2.7.9
OpenSSL version: OpenSSL 1.0.1e 11 Feb 2013
```

- Instale o [Git]:

```bash
yum install -y git
```

O comando acima deve produzir saída similar à seguinte:

```
...

Installed:
  git.x86_64 0:1.8.3.1-4.el7                                                                                                                                                                                       

...
```

- Clone o repositório [servicosgovbr/docker](https://github.com/servicosgovbr/docker) no diretório `/root`:

```bash
cd /root
git clone https://github.com/servicosgovbr/docker
```

O comando acima deve produzir saída similar à seguinte:

```
Cloning into 'docker'...
remote: Counting objects: 152, done.
remote: Compressing objects: 100% (61/61), done.
remote: Total 152 (delta 34), reused 0 (delta 0), pack-reused 85
Receiving objects: 100% (152/152), 18.87 KiB | 0 bytes/s, done.
Resolving deltas: 100% (77/77), done.
```

### Chaves SSH

Para o correto funcionamento do Editor de Serviços, os seguintes passos são necessários:

1. Criar uma nova chave SSH, conforme página de [SHH Keys] do GitHub 
2. Colocar essa chave na pasta `/root/.ssh` 
3. Importar chave com permissão de leitura e escrita no repositório de `cartas-de-servicos`. Detalhes sobre esse processo podem ser encontrados na página de [Deploy Keys] do GitHub 

### Certificado Digital SSL

O Editor de Serviços utiliza um certificado digital para aumentar a segurança da comunicação entre usuário e a aplicação. Para gerar esse certificado digital corretamente é necessário criar um arquivo `.pem`, obtido a partir do certificado original. Para gerar esse arquivo `.pem`, os seguintes passos são necessários:

- Copiar arquivos disponibilizados do certificado digital para `/root/docker/balanceador/ssl/private`
- Remover a senha da chave privada: `openssl rsa -in servicos.gov.br.key -out servicos.gov.br.key.out`
- Gerar arquivo .cer: `openssl pkcs7 -print_certs -in SERVICOSGOVBR.p7b -out SERVICOSGOVBR.cer`
- Converter o certificado de pkcs7 para pkcs12, de forma a colocar a chave privada e o certificado no mesmo arquivo: `openssl pkcs12 -export -in SERVICOSGOVBR.cer -inkey servicos.gov.br.key.out -out SERVICOSGOVBR.pfx`
- Converter de pkcs12 para .pem, formato que o balanceador sabe trabalhar: `openssl pkcs12 -in SERVICOSGOVBR.pfx -out SERVICOSGOVBR.pem -nodes`

### Construindo os Contêineres 

Construa e rode os contêineres:

```bash
cd docker
./build-all # caso prefira baixar as imagens do docker hub, omita este passo
docker-compose up -d
```

O comando acima deve produzir saída similar à seguinte:

```
Pulling cadvisor (google/cadvisor:latest)...
latest: Pulling from google/cadvisor
511136ea3c5a: Pull complete
46e263e5de56: Pull complete
cf677a5f718c: Pull complete
...
Creating cadvisor...
Creating editor2...
Creating editor1...
Creating es2...
Creating es1...
Creating logstash...
Creating logspout...
Creating portal2...
Creating kibana...
Creating portal1...
Creating balanceador...
```

Caso a seguinte mensagem seja exibida:

```
Cannot start container faf8df3b73d4ddbfad550942020413e98ae8dd3bf84dc19b2b880273e261e5f2: Cannot link to a non running container: /logspout AS /balanceador/logspou
```

Ocorreu um problema na inicialização do Logspout (que precisa do Logstash rodando para concluir com sucesso), devido à ordem com que os serviços iniciam no Docker-Compose. Este problema pode ser resolvido aguardando por volta de 1 minuto (ou tempo suficiente para o Logstash inicializar completamente) e rodando `docker-compose up -d` novamente.

{% include '../desenvolvimento/_checklist-verificacoes.md' %}

A instalação está concluída.

[Docker]:http://www.docker.com
[Docker-Compose]:http://www.docker.com/compose
[Git]:http://git-scm.org
[SHH Keys]:https://help.github.com/articles/generating-ssh-keys/ 
[Deploy Keys]:https://developer.github.com/guides/managing-deploy-keys/
