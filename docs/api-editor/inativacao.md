# Inativar Serviço

Para inativar um serviço utilizando a API é necessário estar autenticado com um usuário do tipo "Usuário Api" e ter a permissão "Inativar".

Para autenticar basta seguir os passos descritos em [Autenticação](autenticacao.md).

Estando Autenticado, basta enviar um requisição DELETE para o endereço https://servicos.gov.br/api/v1/servicos/inativar/{idServico} com os parâmetros necessários conforme documentação disponível em https://servicos.gov.br/api/v1/docs#!/servico/delete_servicos_inativar_idServico .

É necessário informar o identificador do serviço, o qual é possível obtê-lo na lista de serviços, fazendo uma requisição GET para o endereço https://servicos.gov.br/api/v1/servicos .

Após realizar a requisição com sucesso, é retornado o serviço que foi inativado. Caso contrário, é retornado uma mensagem contendo os erros que ocorreram ao inativar o serviço.

**Exemplo de requisição para inativar um serviço:**
```
curl -X DELETE --header 'Accept: application/json' --header 'cpf: 68847811309' --header 'nome: Teste' --header 'email: teste@servicos.gov.br' --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJub21lIjoiSmFuc2VyIiwic3ViIjoiamFuc2VyLmZlcnJlaXJhQGJhc2lzLmNvbS5iciIsImV4cCI6MTU2MDQ2MDU4MjcwMiwib3JnYW8iOiJTZXJ2acOnbyBGZWRlcmFsIGRlIFByb2Nlc3NhbWVudG8gZGUgRGFkb3MgKFNFUlBSTykiLCJ2YWxpZCI6dHJ1ZSwiZXhwaXJlZCI6ZmFsc2V9.sy7e5Z4yKZ39-AHmgBlqevBxq7ksBp0hCs3hUPLEFKoqnR91ryRL8lHNkVfFOhsVB5Wy9s71xYAQ9IbWd2kG9Q' 'https://servicos.gov.br/api/v1/servicos/inativar/1'

```
