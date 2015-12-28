# Persistência de Acesso e Permissões

## Acesso local ao banco de dados

Para acessar os dados do banco de dados H2, que roda localmente, é necessário:

- Adicionar o bloco de código abaixo na classe WebMVCConfig.java do Editor de Serviços:

```
public ServletRegistrationBean h2servletRegistration() {
    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
    registration.addUrlMappings("/console/*");
    registration.addInitParameter("webAllowOthers", "true");
    return registration;
}
```

- Liberar acesso a URL relativa no arquivo application.yaml do Editor de Serviços. Para isso, adicionar a string `/console/**` na propriedade `security.ignored`
- Executar o Editor de Serviços utilizando o Gradle
- Acessar a URL `localhost:8080/console`


## Migração de banco de dados

Para migração do banco de dados, o Editor de Serviços utiliza a ferramenta [Flyway][FLYWAY].

Cada vez que uma nova alteração no banco é requisitada, precisamos criar um arquivo de migração. A ferramenta Flyway vai executar essa migração caso não tenha sido executada ainda naquele ambiente.

Os arquivos de migração devem ser colocados na pasta **src/main/resources/db/migration/**. Eles devem estar no formato SQL e devem ter o prefixo ```V<N>__``` (N é a versão da migração). O numero da versão deve ser um digito acima da versão mais nova existente. Por exemplo, se a migração com versão mais nova na pasta seja *V15\_\_adicionar\_coluna.sql*, então a nova migração deve ter o prefixo ```V16__```.

**É importante ter cuidado para não perder dados com as instruções SQL adicionadas nos arquivos de migração.**

[FLYWAY]:http://flywaydb.org/