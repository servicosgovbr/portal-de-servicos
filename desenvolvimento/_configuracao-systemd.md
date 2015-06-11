A distribuição Linux CentOS 7 utiliza a ferramenta [systemd] para controle das operações do sistema operacional. Em uma diretiva de configuração, [`EnvironmentFile`][systemd-envfile], dizemos ao [systemd] de onde devem ser carregadas as variáveis de ambiente para um determinado sistema em execução.

No caso do Portal de Serviços, tanto em ambientes [Vagrant] quanto em [homologação e produção][prod], este arquivo está instalado em `/etc/sysconfig/portal-de-servicos`, e deve ser revisado de acordo com o ambiente de cada instalação.

Este é o arquivo de configuração padrão adicionado ao pacote [RPM] da aplicação, que lista as configurações utilizadas:

<pre><code class="lang-bash">{% include '../../scripts/portal-de-servicos.default.config' %}</code></pre>

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