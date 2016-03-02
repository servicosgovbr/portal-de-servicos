Todos os comandos a seguir devem ser executados como super-usuário (root), e presumem uma máquina [CentOS] 7 64bit, sem nenhuma configuração adicional efetuada. As versões do CentOS utilizadas para homologação foram: 7.1 e 7.2. Além disso, ser **64bit** é realmente necessário, pois é a única plataforma suportada pelo Docker na arquitetura X86.

### Configuração Inicial

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

- Instale o [Docker], caso não esteja instalado:

```bash
curl -sSL https://get.docker.com | sh
```

- Inicie o serviço do [Docker] com o Storage Driver escolhido (caso queira alterar o Storage Driver padrão do Docker, pule este passo e execute os passos da próxima seção **Alterando o Storage Driver do Docker**):

```bash
systemctl start docker
```

- Verifique que o serviço do [Docker] inicializou corretamente:

```bash
docker info
```

O comando acima deve produzir saída similar à seguinte:

```
Containers: 0
Images: 0
Storage Driver: overlay
 Backing Filesystem: extfs
Execution Driver: native-0.2
Logging Driver: json-file
Kernel Version: 3.19.0-15-generic
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

### Clone Repositório Docker

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

### Redirecionando de tráfego

É possível que o ambiente em que a instalação esteja sendo realizada possua restrições de firewall. Algumas das restrições que enfrentamos anteriormente, em ambiente do governo, foram restrições de tráfego na porta 22 para o GitHub. Nesse caso, sugere-se uma solução de contorno. Essa solução de contorno irá redirecionar todo o tráfego da porta 22 para a porta 443. Considera-se que a porta 443 não possua restrições, e que esse tipo de redirecionamento seja permitido na infra-estrutura em que está sendo realizada a instalação.

Para isso, a seguinte alteração é necessária no arquivo `/root/.ssh/config`:

```
Host github.com
  Hostname ssh.github.com
  Port 443
```

O código acima deve ser adicionado no arquivo `config`. Caso o arquivo não exista, sugere-se a criação do arquivo. Maiores informações podem ser encontradas na [documentação oficial] do Github.

Para testar a conexão e aceitar o certificado de acesso, utilize o seguinte comando:

```
ssh -T git@github.com
```

O resultado esperado é a seguinte mensagem:

```
You've successfully authenticated, but GitHub does not provide shell access.
```

### Chaves SSH

Para o correto funcionamento do Editor de Serviços, os seguintes passos são necessários:

1. Criar uma nova chave SSH, conforme página de [SHH Keys] do GitHub. A chave criada não deve conter nenhum tipo de passphrase. Sugere-se que essa chave seja associada a um e-mail válido de uma conta existente no GitHub e que o usuário esteja autorizado a acessar os projetos da [organização do servicos.gov.br].
2. Colocar essa chave na pasta `/root/.ssh`.
3. Importar chave com permissão de leitura e escrita no repositório de `cartas-de-servicos`. Detalhes sobre esse processo podem ser encontrados na nota abaixo, ou na página de [Deploy Keys] do GitHub. A chave importada deve ser utilizada exclusivamente no repositório de cartas. Caso a chave for utilizada em algum outro lugar, uma mensagem de erro será exibida.

**Notas:**

É importante que o usuário que irá realizar a operação de importação tenha as devidas permissões no repositório `cartas-de-servico` do GitHub. Caso o usuário não tenha permissões suficientes, é necessário contactar os administradores do repositório.

Caso, não for possível encontrar o repositório que a chave deve ser importada, acessar o seguinte [repositório] no GitHub. Estando na página do repositório, os seguintes passos devem ser realizados:

1. Clicar em "Settings"
2. No lado direto inferior da interface, clicar em "Deploy Keys"
3. Clicar em "Add Deploy Key"
4. Criar um nome para a nova chave, no campo "Title"
5. Inserir o conteúdo da chave pública criada anteriormente no campo "Key"
6. Selecionar a opção "Allow write access"
7. Clicar no botão "Add Key" para adicionar a chave

Para visualizar as chaves de deploy você pode acessar o seguinte [link](https://github.com/servicosgovbr/cartas-de-servico/settings/keys).

### Certificado Digital SSL

O Editor de Serviços utiliza um certificado digital para aumentar a segurança da comunicação entre usuário e a aplicação. Deve ser utilizado o certificado auto assinado enviado por email, ou um do mesmo padrão criado a partir dos passos a seguir.

#### Geração de um certificado digital

Para gerar esse certificado digital corretamente é necessário criar um arquivo `.pem`, obtido a partir do certificado original. Para gerar esse arquivo `.pem`, os seguintes passos são necessários:

- Copiar arquivos disponibilizados do certificado digital para `/root/docker/balanceador/ssl/private`
- Remover a senha da chave privada: `openssl rsa -in servicos.gov.br.key -out servicos.gov.br.key.out`
- Gerar arquivo .cer: `openssl pkcs7 -print_certs -in SERVICOSGOVBR.p7b -out SERVICOSGOVBR.cer`
- Converter o certificado de pkcs7 para pkcs12, de forma a colocar a chave privada e o certificado no mesmo arquivo: `openssl pkcs12 -export -in SERVICOSGOVBR.cer -inkey servicos.gov.br.key.out -out SERVICOSGOVBR.pfx`
- Converter de pkcs12 para .pem, formato que o balanceador sabe trabalhar: `openssl pkcs12 -in SERVICOSGOVBR.pfx -out SERVICOSGOVBR.pem -nodes`

### Utilizando variáveis de ambiente

Antes de inicializar os contêineres, é necessário que as seguintes variáveis de ambiente sejam configuradas para a instalação inicial:

- `export EDS_CARTAS_REPOSITORIO='git@github.com:servicosgovbr/cartas-de-servico.git'`
- `export PDS_CARTAS_REPOSITORIO='https://github.com/servicosgovbr/cartas-de-servico.git'`

Para utilização do PIWIK, é necessário antes da inicialização dos contêiners configurar as seguintes variáveis de ambiente (os valores abaixo são referentes ao PIWIK do portal de serviços no Ministério do Planejamento):

- `export PDS_PIWIK_ENABLED='true'`
- `export PDS_PIWIK_SITE=2`
- `export PDS_PIWIK_URL="https://estatisticas.presidencia.gov.br/"`
- `export PDS_PIWIK_TOKEN=''` (Para acessar o TOKEN do portal instalado no Ministério do Planejamento entrar em contato com a Coordenação-Geral de Dados e Serviços Públicos Digitais. Nos outros casos deve ser utilizado PIWIK e TOKEN próprios)

Para habilitar robôs de busca, configurar a seguinte variável de ambiente:

- `export FLAGS_PERMITIR_ROBOS='true'`

Após executar esses comandos, precisamos persistir os valores das váriaveis. Devemos criar um arquivo em `/etc/profile.d/repositorios.sh` com o seguinte conteúdo:

```
export EDS_CARTAS_REPOSITORIO='git@github.com:servicosgovbr/cartas-de-servico.git'
export PDS_CARTAS_REPOSITORIO='https://github.com/servicosgovbr/cartas-de-servico.git'
export PDS_PIWIK_ENABLED='true'
export PDS_PIWIK_SITE=2
export PDS_PIWIK_URL="https://estatisticas.presidencia.gov.br/"
export PDS_PIWIK_TOKEN='' 
export FLAGS_PERMITIR_ROBOS='true' 
```
Ressaltamos que para acessar o TOKEN entrar em contato com a Coordenação-Geral de Dados e Serviços Públicos Digitais.

### Construindo os Contêineres

Construa e rode os contêineres:

```bash
cd /root/docker
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

**IMPORTANTE**

Devido a [Issue #12](https://github.com/servicosgovbr/docker/issues/12) é necessário realizar na primeira atualização os seguintes comandos:

```
docker-compose stop portal1
docker-compose kill portal1
docker rm -f portal1 (Neste comando ocorrerá um erro de remoção do container, pode prosseguir com os outros comandos)
docker-compose up -d portal1

docker-compose stop portal2
docker-compose kill portal2
docker rm -f portal2 (Neste comando ocorrerá um erro de remoção do container, pode prosseguir com os outros comandos)
docker-compose up -d portal2

docker-compose stop editor1
docker-compose kill editor1
docker rm -f editor1 (Neste comando ocorrerá um erro de remoção do container, pode prosseguir com os outros comandos)
docker-compose up -d editor1

docker-compose stop editor2
docker-compose kill editor2
docker rm -f editor2 (Neste comando ocorrerá um erro de remoção do container, pode prosseguir com os outros comandos)
docker-compose up -d editor2
```

A instalação está concluída.

### Alterando o Storage Driver do Docker

Esta seção é direcionada caso opte por alterar o Storage Driver padrão do Docker. Caso o Docker já esteja instalado e rodando contêineres, é preciso executar o passo abaixo:

- Remova os contêineres e as imagens, depois pare o serviço do Docker e remova a pasta /var/lib/docker/:

```
docker stop $(docker ps -a -q)
docker kill $(docker ps -a -q)
docker rm -f $(docker ps -a -q)

docker rmi $(docker images -a -q)

systemctl stop docker

rm -rf /var/lib/docker/
```

Agora para trocar o Storage Driver, execute os seguintes passos:

- Verifique se Storage Driver escolhido (overlay, aufs, devicemapper e etc) está habilitado:

```bash
lsmod | grep overlay
```

- Caso não esteja, habilite o Storage Driver escolhido:
   
```bash
modprobe overlay
```

- Execute o serviço novamente, com o novo Storage Driver escolhido (overlay, aufs, devicemapper e etc):

```bash
docker daemon --storage-driver=overlay &
```

### Instalações Auxiliares

Os passos dessa seção devem ser seguidos **apenas** se a máquina utilizada para implantação já possui a configuração inicial e uma instalação prévia realizada.

- No repositório local [servicosgovbr/docker](https://github.com/servicosgovbr/docker), pare e remova os contêineres:

```
docker stop $(docker ps -a -q)
docker kill $(docker ps -a -q)
```

- Reinicie o Docker:

```bash
systemctl restart docker
```

- Remova o repositório local [servicosgovbr/docker](https://github.com/servicosgovbr/docker)

- Clone o repositório [servicosgovbr/docker](https://github.com/servicosgovbr/docker) no diretório `/root`:

```bash
cd /root
git clone https://github.com/servicosgovbr/docker
```

- O certificado digital deverá ser instalado novamente na pasta do `docker`, conforme instruções da seção "Certificado Digital SSL"

- Construa e rode os contêineres:

```bash
cd /root/docker
./build-all # caso prefira baixar as imagens do docker hub, omita este passo
docker-compose up -d
```

[Docker]:http://www.docker.com
[Docker-Compose]:http://www.docker.com/compose
[Git]:http://git-scm.org
[SHH Keys]:https://help.github.com/articles/generating-ssh-keys/
[Deploy Keys]:https://developer.github.com/guides/managing-deploy-keys/
[documentação oficial]:https://help.github.com/articles/using-ssh-over-the-https-port/
[repositório]:https://github.com/servicosgovbr/cartas-de-servico
[organização do servicos.gov.br]:https://github.com/servicosgovbr
