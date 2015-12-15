# Persistência de Acesso e Permissões

## Migração de banco de dados

Para migração do banco de dados o editor utiliza a ferramenta [Flyway][FLYWAY].

Cada vez que uma nova alteração no banco é requisitada precisamos criar um arquivo de migração. A ferramenta Flyway vai executar essa migração caso não tenha sido executada ainda naquele ambiente.

Os arquivos de migração devem ser colocados na pasta **src/main/resources/db/migration/**. Ele deve estar no formato SQL e deve ter o prefixo ```V<N>__``` (N é a versão da migração). O numero da versão deve ser um digito acima da versão mais nova existente. Por exemplo, se a migração com versão mais nova na pasta seja *V15\_\_adicionar\_coluna.sql*, então a nova migração deve ter o prefixo ```V16__```.

**É importante ter cuidado para não perder dados com as instruções SQL adicionadas nos arquivos de migração.**

[FLYWAY]:http://flywaydb.org/