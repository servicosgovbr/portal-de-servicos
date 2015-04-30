# Configuração

O Guia de Serviços utiliza o [sistema de configurações padrão][spring-boot-config] do [Spring Boot][spring-boot], de forma simplificada. Utilizamos apenas dois dos mecanismos disponibilizados pela plataforma: variáveis de ambiente externas ao pacote JAR, e arquivos [YAML] com todos os defaults necessários para cada perfil de utilização, já embutidos no JAR.

## Arquivos YAML

### `application.yaml`

O arquivo `application.yaml` está localizado no código-fonte em `src/main/resources`. Durante a compilação do pacote JAR, ele é embutido e fica na raiz do _classpath_ (ou seja, `classpath:application.yaml`).

Nele estão contidas opções _default_ para configurações das seguintes categorias:
 
#### `spring`

Configurações gerais disponibilizadas (e documentadas) pelo ambiente do [Spring Boot][spring-boot] e algumas de suas bibliotecas.

#### `endpoints`

Configurações das URLs de monitoramento e depuração que o [Spring Boot][spring-boot] disponibiliza, mas que podem oferecer riscos de segurança.

#### `server`

Configurações do servidor web [Tomcat] embutido na aplicação. No caso do Guia de Serviços, configuramos apenas a porta padrão em que o servidor aguarda por conexões, `8080`. 

#### `mail`

Configurações de envio de email. Algumas funcionalidades do Guia de Serviços enviam emails de notificação aos administradores do sistema, e para isto é necessário configurar alguns dos parâmetros da biblioteca [JavaMail].

#### `gds`

Configurações específicas ao Guia de Serviços. Nesta seção ficam todas as configurações que afetam o comportamento específico do código do Guia de Serviços, bem como algumas tabelas _de-para_. 

### `application-production.yaml`

É possível definir arquivos [YAML] específicos para perfis de execução no [Spring Boot][spring-boot].

No caso do Guia de Serviços, o arquivo `src/main/resources/application-production.yaml`, carregado após o `src/main/resources/application.yaml`, sobrescreve as seguintes configurações:

#### `spring.thymeleaf.cache`

Habilita o cache do sistema de renderização de templates [Thymeleaf] 

#### `spring.data.elasticsearch.clusterNodes`

Utiliza o valor `10.16.0.11:9300,10.16.0.9:9300`, apontando o cluster do [ElasticSearch] para as máquinas definidas na rede [Vagrant] e no ambiente de desenvolvimento, onde `es1` é `10.16.0.11`, e `es2` é `10.16.0.9`.

#### `endpoints.enabled`

Desabilita as URLs de monitoramento e depuração que o [Spring Boot][spring-boot] disponibiliza, mas que podem oferecer riscos de segurança.

####`endpoints.info.enabled`

Habilita a URL de monitoramento `/info`, útil para verificar a versão do corrente do sistema.

#### `endpoints.health.enabled`

Habilita a URL de monitoramento `/health`, que faz uma breve verificação do estado de saúde do sistema.

#### `endpoints.health.sensitive`

Expõe apenas o estado geral (`UP`, `DOWN`, etc) do sistema, ao invés de detalhes que podem conter informações sensíveis, como detalhes de quanto espaço em disco está disponível, ou IPs da rede interna, por exemplo.

## Arquivo de variáveis de ambiente do [systemd]

{% include "./_configuracao-systemd.md" %}

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