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

- Instale o [Docker]:

```bash
curl -sSL https://get.docker.com | sh
```

- Crie os volumes de dados e metadados do Docker:

```bash
pvcreate /dev/sdb1
vgcreate vg-docker /dev/sdb1
lvcreate -L 7G -n data vg-docker
lvcreate -L 2G -n metadata vg-docker
```

- Inicie o serviço do [Docker]:

```bash
systemctl start docker
```

- Verifique que o serviço do [Docker] inicializou corretamente:

```bash
docker info
```

O comando acima deve produzir saída contendo texto similar ao seguinte:

```
Containers: 0
 Running: 0
 Paused: 0
 Stopped: 0
Images: 0
Server Version: 1.10.2
Storage Driver: devicemapper
 Pool Name: docker-253:1-124711-pool
 Pool Blocksize: 65.54 kB
 Base Device Size: 10.74 GB
 Backing Filesystem: xfs
 Data file: /dev/vg-docker/data
 Metadata file: /dev/vg-docker/metadata
 Data Space Used: 2.989 GB
 Data Space Total: 8.59 GB
 Data Space Available: 5.601 GB
 Metadata Space Used: 7.025 MB
 Metadata Space Total: 1.074 GB
 Metadata Space Available: 1.067 GB
 Udev Sync Supported: true
 Deferred Removal Enabled: false
 Deferred Deletion Enabled: false
 Deferred Deleted Device Count: 0
 Library Version: 1.02.107-RHEL7 (2015-12-01)
```

(note os nomes do `Data file` e `Metadata file`, que são os volumes LVM criados no passo anterior.)

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
docker-compose version 1.4.0, build 4d72027
docker-py version: 1.7.2
CPython version: 2.7.11
OpenSSL version: OpenSSL 1.0.2g  1 Mar 2016
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


- Clone o repositório [servicosgovbr/editor](https://git.planejamento.gov.br/sti/portal-servicos-editor-de-servicos) no diretório `/root`:
- Clone o repositório [servicosgovbr/portal](https://git.planejamento.gov.br/sti/portal-servicos) no diretório `/root`:

```bash
cd /root
git clone https://git.planejamento.gov.br/sti/portal-servicos-editor-de-servicos
git clone https://git.planejamento.gov.br/sti/portal-servicos
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

1. Criar uma nova chave SSH, conforme página de [SSH Keys] do GitHub. A chave criada não deve conter nenhum tipo de passphrase. Sugere-se que essa chave seja associada a um e-mail válido de uma conta existente no GitHub e que o usuário esteja autorizado a acessar os projetos da [organização do servicos.gov.br].
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

### Utilizando variáveis de ambiente

Antes de inicializar os contêineres, é necessário que as seguintes variáveis de ambiente sejam configuradas para a instalação inicial:

- `export EDS_CARTAS_REPOSITORIO='git@github.com:servicosgovbr/cartas-de-servico.git'`
- `export PDS_CARTAS_REPOSITORIO='https://github.com/servicosgovbr/cartas-de-servico.git'`

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
export PORTAL_URL='http://servicos.nuvem.gov.br'
export EDITOR_URL='http://servicos.nuvem.gov.br'
export CAPTCHA_KEYS_SITE='6LfHRQwUAAAAACGReFwiW-fQ_fR78WQYXagi_sBR'
export CAPTCHA_KEYS_SECRET='6LfHRQwUAAAAAES36H-lRrPUPlZNAP5hyFU2KsI3'
export MAIL_REMETENTE='governodigital@planejamento.gov.br'
export MAIL_SUBJECT='Recuperar acesso'
```
Ressaltamos que para acessar o TOKEN entrar em contato com a Coordenação-Geral de Dados e Serviços Públicos Digitais.

### Construindo os Contêineres

Construa e rode os contêineres:

```bash
cd /root/portal-servicos-editor-de-servicos/docker/docker-compose-builder
./run buildDockerfiles prod editor <Versão>

cd /root/portal-servicos/docker/docker-compose-builder
./run buildDockerfiles prod portal <Versão>

cd /root/portal-servicos/docker/docker-compose-builder/docker-compose

docker-compose up -d

Versão: versão que será criado a imagem do Docker
```


A instalação está concluída.

### Restaurando o Repositório Local

Os passos dessa seção devem ser seguidos **apenas** se a máquina utilizada para implantação já possui a configuração inicial e uma instalação prévia realizada.

- No repositório local [servicosgovbr/editor](https://git.planejamento.gov.br/sti/portal-servicos-editor-de-servicos) e [servicosgovbr/portal](https://git.planejamento.gov.br/sti/portal-servicos), pare e remova os contêineres:

```
docker stop $(docker ps -a -q)
docker kill $(docker ps -a -q)
```

- Reinicie o Docker:

```bash
systemctl restart docker
```

- Remova os repositórios local [servicosgovbr/portal](https://git.planejamento.gov.br/sti/portal-servicos) e [servicosgovbr/editor](https://git.planejamento.gov.br/sti/portal-servicos-editor-de-servicos)

- Clone o repositório [servicosgovbr/editor](https://git.planejamento.gov.br/sti/portal-servicos-editor-de-servicos) no diretório `/root`:
- Clone o repositório [servicosgovbr/portal](https://git.planejamento.gov.br/sti/portal-servicos) no diretório `/root`:

```bash
cd /root
git clone https://git.planejamento.gov.br/sti/portal-servicos-editor-de-servicos
git clone https://git.planejamento.gov.br/sti/portal-servicos
```

- O certificado digital deverá ser instalado novamente na pasta do `docker`, conforme instruções da seção "Certificado Digital SSL"

- Construa e rode os contêineres:

```bash
cd /root/portal-servicos-editor-de-servicos/docker/docker-compose-builder
./run buildDockerfiles prod editor <Versão>

cd /root/portal-servicos/docker/docker-compose-builder
./run buildDockerfiles prod portal <Versão>

cd /root/portal-servicos/docker/docker-compose-builder/docker-compose

docker-compose up -d

Versão: versão que será criado a imagem do Docker
```

[Docker]:http://www.docker.com
[Docker-Compose]:http://www.docker.com/compose
[Git]:http://git-scm.org
[SSH Keys]:https://help.github.com/articles/generating-ssh-keys/
[Deploy Keys]:https://developer.github.com/guides/managing-deploy-keys/
[documentação oficial]:https://help.github.com/articles/using-ssh-over-the-https-port/
[repositório]:https://github.com/servicosgovbr/cartas-de-servico
[organização do servicos.gov.br]:https://github.com/servicosgovbr
