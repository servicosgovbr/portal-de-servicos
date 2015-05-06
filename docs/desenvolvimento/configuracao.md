# Configuração

O Guia de Serviços utiliza o [sistema de configurações padrão][spring-boot-config] do [Spring Boot][spring-boot], de forma simplificada. Utilizamos apenas dois dos mecanismos disponibilizados pela plataforma: variáveis de ambiente externas ao pacote JAR, e um arquivo [YAML] com todos os defaults necessários para utilização, já embutidos no JAR.

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