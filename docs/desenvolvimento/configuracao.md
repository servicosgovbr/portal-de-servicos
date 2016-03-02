# Configuração

O Portal de Serviços utiliza o [sistema de configurações padrão][spring-boot-config] do [Spring Boot][spring-boot], de 
forma simplificada. Utilizamos apenas dois dos mecanismos disponibilizados pela plataforma: variáveis de ambiente 
externas ao pacote JAR, e um arquivo [YAML] com todos os defaults necessários para utilização, já embutidos no JAR.

## Variáveis de ambiente

{% include "./_configuracao-variaveis-ambiente.md" %}

[spring-boot-config]:http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config
[spring-boot]:http://projects.spring.io/spring-boot/
[YAML]:http://yaml.org/
[ElasticSearch]:./elasticsearch.md
[Thymeleaf]:http://www.thymeleaf.org
[prod]:./deploy-homologacao-producao.md
[Piwik]:http://www.piwik.org
[STARTTLS]:http://en.wikipedia.org/wiki/STARTTLS