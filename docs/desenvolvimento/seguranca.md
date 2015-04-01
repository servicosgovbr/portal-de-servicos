# Segurança

## Registro de eventos

O sistema deverá possuir um mecanismo de registro de eventos durante a sua execução (_log_) que armazene estes eventos primariamente em um banco de dados criado para tal finalidade. O sistema deve guardar o log das operações com o SGBD e log do servidor web.

Senhas ou outros dados secretos jamais devem ser registrados em quaisquer dos níveis de log e sob nenhuma condição.

Os níveis de log devem seguir os seguintes critérios:

* `DEBUG` e `TRACE`: quaisquer informações relevantes ao desenvolvimento da aplicação. Podem estar habilitados durante homologação e produção, mas é necessário considerar seu uso demasiado de memória caso a aplicação enfrente períodos de alta demanda.
* `INFO`: registro de execução e eventos de operações relacionadas ao negócio. Due deve conter informações valiosas para futura análise de comportamento: endereço IP da requisição, identificador da transação ou requisição, _timestamp_, etc.
* `WARN`: registro de eventos menos esperados que podem causar instabilidades na aplicação: falha ao se comunicar com um serviço externo que pode ser tentada novamente, por exemplo.
* `ERROR`: registro de eventos fatais à operação das aplicações e serviços, mas que não afetem a sua integridade e não envolvam perda de dados: timeout de conexão com o banco de dados, dados ausentes em uma operação que deveria retornar dados mandatoriamente, serviço externo não acessível, etc.
* `FATAL`: qualquer erro que impeça que a aplicação registre corretamente informações e incorra em perda de informações ou comprometa a sua integridade. Implica na parada da aplicação e notificação imediata à administração do sistema para que o problema seja sanado e a aplicação retome seu funcionamento. Neste estado a aplicação não deve permitir o acesso de usuários.

### Campos de log

O log deve conter os seguintes campos:

- Timestamp do início da operação
- Tempo de execução, em milissegundos
- Nível de evento (conforme definido na seção anterior)
- Identificação do ambiente (IP ou nome da máquina)
- Pacote, classe e, se possível, método
- Transação: identificador único de transação em formato [UUID](http://en.wikipedia.org/wiki/Universally_unique_identifier)
- ID do usuário, caso autenticado
- Informações da funcionalidade
- Ticket do erro, caso tenha ocorrido um erro

Por exemplo, no caso do usuário executar uma busca que não obeteve resultados, mas ocorreu com sucesso:

```
- Timestamp: "1424985454"
- Tempo de execução: "4"
- Nível de evento: "INFO"
- Identificação do ambiente: "vm01"
- Pacote, classe e, se possível, método: "br.gov.servicos.busca.BuscaController#busca(String)"
- Transação: "08644dd6-bdfd-11e4-8eab-7831c1d2da14"
- ID do usuário: "null"
- Informações da funcionalidade: "Busca por '' não obteve resultados"
- Ticket do erro: "null"
```

### Escolha de funcionalidades de log

As funcionalidades a serem logadas devem ser escolhidas de acordo com cada módulo ou sistema, obedecendo os critérios desejados e objetivando a visualização dos eventos significativos pelos usuários administradores do sistema.

A rigor, cada transação está eleita para ser logada.

## Tratamento de erros

Este requisito visa principalmente a segurança do sistema. O usuário não deve ter acesso a nenhum detalhe interno da aplicação, como nomes de classes ou dependências utilizadas.

O sistema deve possuir um mecanismo de tratamento de erro orientado a _tickets_, no qual todas as mensagens de erro exibidas ao usuário (classes de erro HTTP acima de 400) devem ser informados para o usuário ocultados por um número de _ticket_. Por exemplo:

```
A aplicação detectou uma falha inesperada. Por favor, tente a operação novamente. Caso a falha persista, entre em contato com o suporte utilizando o identificador *7831c1d2da14*.
```

O _ticket_ deve utilizar o último bloco de dígitos do identificador da transação, com 12 caracteres hexadecimais. Todas as mensagens de log relacionadas à ação do usuário na mesma requisição HTTP devem estar atreladas ao mesmo UUID, o que facilita a busca e depuração.

Já as falhas de negócio (erros de validação, por exemplo) devem retornar mensagens amigáveis, informando ao usuário o contexto da falha e a ação a ser tomada. Estas falhas não devem produzir erros de nível `WARN`, `ERROR` ou `FATAL`, já que não há o que o administrador do sistema possa fazer para remediá-las.

## Auditoria mínima

Quaisquer transações de escrita (modificação ou remoção) iniciadas por um usuário devem conter os seguintes detalhes no log:

- IP de acesso do qual partiu a requisição
- ID do usuário
- Ação
- Referência textual ao conteúdo alterado

## Segurança de acesso ao log

Logs deverão ser armazenados de forma que um usuário mal-intencionado que consiga acesso a eles não possa corrompê-los. Recomenda-se fortemente não permitir que logs sejam alterados por nenhum usuário do sistema, exceto por um processo de rotação que move registros de logs antigos para outro local, afim de liberar espaço em disco.
