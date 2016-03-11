# Persistência de Acesso e Permissões

## Reiniciar Editor de Serviços 

Após qualquer alteração no arquivo `permissoes.yaml`, é necessário reiniciar a aplicação do Editor de Serviços. Os seguintes passos são essencias para garantir que essa mudança seja refletida corretamente:

1. Acessar ambiente remoto utilizando SSH: 
`ssh <usuário>@<ip do servidor>`
2. Utilizar o usuário root:
`sudo su`
3. Ir para o diretório do docker:
`cd /root/docker/`
4. Parar as instâncias do Editor de Serviços:
`docker stop editor1 editor2`
5. Atualizar imagem local para conter a última versão do código do Editor de Serviços:
`./build editor-de-servicos`
6. Reiniciar as instâncias do Editor de Serviços:
`docker-compose up -d`
7. Reiniciar a instância do Balanceador:
`docker restart balanceador`

## Acesso local ao banco de dados

Para acessar os dados do banco de dados H2, que é utilizado somente para desenvolvimento local, é necessário:

- Adicionar o bloco de código abaixo na classe `WebMVCConfig.java` do repositório do Editor de Serviços:

```
public ServletRegistrationBean h2servletRegistration() {
    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
    registration.addUrlMappings("/console/*");
    registration.addInitParameter("webAllowOthers", "true");
    return registration;
}
```

- Liberar acesso a URL relativa no arquivo `application.yaml` do Editor de Serviços. Para isso, adicionar a string `/console/**` na propriedade `security.ignored`
- Executar o Editor de Serviços utilizando o Gradle
- Acessar a URL `localhost:8080/console`


## Migração de banco de dados

Para migração do banco de dados, o Editor de Serviços utiliza a ferramenta [Flyway][FLYWAY].

Cada vez que uma nova alteração no banco é requisitada, precisamos criar um arquivo de migração. A ferramenta Flyway vai executar essa migração caso não tenha sido executada ainda naquele ambiente.

Os arquivos de migração devem ser colocados na pasta **src/main/resources/db/migration/**. Eles devem estar no formato SQL e devem ter o prefixo ```V<N>__``` (N é a versão da migração). O numero da versão deve ser um digito acima da versão mais nova existente. Por exemplo, se a migração com versão mais nova na pasta seja *V15\_\_adicionar\_coluna.sql*, então a nova migração deve ter o prefixo ```V16__```.

**É importante ter cuidado para não perder dados com as instruções SQL adicionadas nos arquivos de migração.**

[FLYWAY]:http://flywaydb.org/

## Backup de Usuários e Permissões

Para efetuar o backup  dos dados do banco de dados que dá suporte à aplicação, contidos no contêiner dbdata, é necessário realizar os seguintes passos:

1. Criar novo contêiner docker que se conecta com a pasta `/dbdata` onde está localizado os dados do banco
2. Esse contêiner deve ter uma pasta `/backup` compartilhada com o sistema
3. Executar comando para comprimir a pasta `/dbdata` na pasta `/backup`:
```
docker run --volumes-from dbdata -v $(pwd):/backup ubuntu bash -c "tar czvf /backup/backup-$(date '+%d_%m_%y_%H_%M').tar.gz /dbdata"
```

Para recuperar os dados a partir de um backup:

1. Pare a execução dos contêineres no host do Docker
`cd ~/docker/` e `docker-compose stop`
2. Execute o data volume contêiner dbdata com o arquivo backup.tar descomprimido:
```
docker run --volumes-from dbdata -v $(pwd):/backup ubuntu bash -c "rm -rf /dbdata/* && cd /tmp && tar xzvf /backup/backup-22_12_15_07_51.tar.gz && mv dbdata/* ../dbdata/"
```
3. Ligue novamente os contêineres:	
`docker-compose up`

Recomenda-se que o backup dos dados dos usuários seja efetuado diariamente por uma rotina automática. Considerando que o volume de dados armazenados será pequeno, o armazenamento diário não deverá ser um problema. Além disso, recomenda-se que os backups mais antigos que sete dias sejam descartados automaticamente. Essa rotina de deleção automática deverá ser executada somente se backups dos últimos sete dias existirem de fato.

**Atenção**: recomenda-se  que o backup seja armazenado em um volume externo da máquina host do Docker. 
