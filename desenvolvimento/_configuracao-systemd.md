A distribuição Linux CentOS 7 utiliza a ferramenta [systemd] para controle das operações do sistema operacional, e permite uma diretiva em sua configuração, [`EnvironmentFile`][systemd-envfile], que utilizamos para dizer ao [systemd] de onde devem ser carregadas as variáveis de ambiente para um determinado sistema em execução.

No caso do Guia de Serviços, tanto em ambientes [Vagrant] quanto em [homologação e produção][prod], este arquivo está instalado em `/etc/sysconfig/guia-de-servicos`, e deve ser revisado de acordo com o ambiente de cada instalação.

A seguir está uma lista de todas as configurações utilizadas, seus possíveis valores e possíveis modificações necessárias:

#### `SPRING_PROFILES_ACTIVE`

Lista os perfis ativos do [Spring Boot][spring-boot], separados por vírgula. Por exemplo:

```bash
SPRING_PROFILES_ACTIVE=production,clustering
```

Os valores podem ser uma combinação dos seguintes:

##### `production`

Habilita opções relacionadas à execução do Guia de Serviços em ambientes de produção, descritas na seção `application-production.yaml` acima.

##### `clustering`

Habilita opções relacionadas à execução do Guia de Serviços utilizando um cluster de máquinas do [ElasticSearch], ao invés de inicializar um nodo embutido em memória local, útil durante o desenvolvimento do sistema.

##### `monitoring`

Habilita o envio de informações de uso da aplicação ao sistema de monitoramento e métricas [Piwik].

#### `SPRING_DATA_ELASTICSEARCH_CLUSTERNODES`

Sobrescreve o valor da propriedade `spring.data.elasticsearch.clusterNodes` do arquivo `application.yaml`, e deve conter uma lista dos IPs e portas dos nodos do [ElasticSearch] do ambiente corrente, separados por vírgula. Por exemplo:
 
```bash
SPRING_DATA_ELASTICSEARCH_CLUSTERNODES="10.10.10.10:9300,10.10.10.11:9300,10.10.10.12:9300" 
```

#### `MAIL_PROTOCOL`

O protocolo utilizado para envio de emails. Por default, `smtp`.

#### `MAIL_HOST`

O servidor de emails utilizado para envio. Por default, `localhost`.

#### `MAIL_PORT`

A porta em que o Guia de Serviços deve se conectar ao servidor de emails. Por default, `2500`.

#### `MAIL_TIMEOUT`

O _timeout_ para estabelecimento de conexões e espera por atividade em uma conexão estabelecida, em milisegundos. Por default, `5000`.

#### `MAIL_SMTP_AUTH`

Caso o servidor de envio de emails exija autenticação, este valor deve ser `true`. Por default, `false`.

#### `MAIL_SMTP_STARTTLS`

Caso o servidor de envio de emails suporte, ou mesmo exija, a extensão de criptografia [STARTTLS], este valor deve ser `true`. Por default, `false`. 

#### `MAIL_FROM`

O endereço de email que originará as mensagens vindas do Guia de Serviços. Por default, `nao-responda@servicos.gov.br`.

#### `MAIL_TO`

O endereço de email para qual as mensagens de notificação da operação do Guia de Serviços serão enviados. Por default, `servicos@planejamento.gov.br`.

#### `MAIL_USERNAME`

Caso o servidor de envio de email exija autenticação, o nome do usuário da conta a ser utilizada. Por default, vazio.

#### `MAIL_PASSWORD`

Caso o servidor de envio de email exija autenticação, a senha da conta a ser utilizada. Por default, vazio.

#### `GDS_PIWIK_URL`

A URL do servidor [Piwik] que será utilizada para monitoramento e métricas, caso o perfil `monitoring` (descrito acima) esteja presente. Por default, `https://estatisticas.presidencia.gov.br/`.

#### `GDS_PIWIK_TOKEN`

O token para acesso à API de métricas do [Piwik]. Geralmente, 32 caracteres hexadecimais. Por default, vazio.

#### `GDS_PIWIK_SITE`

O identificador do site dentro do [Piwik]. Por default, `2`.

[spring-boot-config]:http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config
[spring-boot]:http://projects.spring.io/spring-boot/
[systemd]:http://www.freedesktop.org/wiki/Software/systemd/
[systemd-envfile]:http://www.freedesktop.org/software/systemd/man/systemd.exec.html
[YAML]:http://yaml.org/
[ElasticSearch]:./elasticsearch.md
[Thymeleaf]:http://www.thymeleaf.org
[Vagrant]:./deploy-vagrant.md
[JavaMail]:http://www.oracle.com/technetwork/java/javamail/index.html
[prod]:./deploy-homologacao-producao.md
[Piwik]:http://www.piwik.org
[STARTTLS]:http://en.wikipedia.org/wiki/STARTTLS