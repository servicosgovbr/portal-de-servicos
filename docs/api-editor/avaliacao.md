# Avaliar Serviço

Para avaliar um serviço utilizando a API é necessário estar autenticado com um usuário do tipo "Usuário Api" e ter a permissão "Avaliar".

Para autenticar basta seguir os passos descritos em [Autenticação](autenticacao.md).

Estando Autenticado, basta enviar um requisição POST para o endereço https://servicos.gov.br/api/v1/servicos/avaliacao com os parâmetros necessários conforme documentação disponível em https://servicos.gov.br/api/v1/docs#!/servico/post_servicos_avaliacao .

Quando o valor da propreidade "informacaoUtil" for "false",  é necessário informar o motivo pelo qual a informação  não  foi útil no atributo "motivoInformacaoNaoUtil".

É necessário informar o identificador do serviço, o qual é possível obtê-lo na lista de serviços, fazendo uma requisição GET para o endereço https://servicos.gov.br/api/v1/servicos .

Após realizar a requisição com sucesso, é retornado uma mensagem informando que avaliação foi realizada com sucesso. Caso contrário, é retornado uma mensagem contendo os erros que ocorreram ao  enviar a avaliação.

**Exemplo de requisição para avaliação positiva:**
```Curl
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJub21lIjoiSmFuc2VyIiwic3ViIjoiamFuc2VyLmZlcnJlaXJhQGJhc2lzLmNvbS5iciIsImV4cCI6MTU2MDQ2MDU4MjcwMiwib3JnYW8iOiJTZXJ2acOnbyBGZWRlcmFsIGRlIFByb2Nlc3NhbWVudG8gZGUgRGFkb3MgKFNFUlBSTykiLCJ2YWxpZCI6dHJ1ZSwiZXhwaXJlZCI6ZmFsc2V9.sy7e5Z4yKZ39-AHmgBlqevBxq7ksBp0hCs3hUPLEFKoqnR91ryRL8lHNkVfFOhsVB5Wy9s71xYAQ9IbWd2kG9Q' -d '{ \ 
   "idServico": 1, \ 
   "informacaoUtil": true, \ 
   "dataAvaliacao": "2019-06-12" \ 
 }' 'https://servicos.gov.br/api/v1/servicos/avaliacao'
```

**Exemplo de requisição para avaliação negativa:**

```Curl
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJub21lIjoiSmFuc2VyIiwic3ViIjoiamFuc2VyLmZlcnJlaXJhQGJhc2lzLmNvbS5iciIsImV4cCI6MTU2MDQ2MDU4MjcwMiwib3JnYW8iOiJTZXJ2acOnbyBGZWRlcmFsIGRlIFByb2Nlc3NhbWVudG8gZGUgRGFkb3MgKFNFUlBSTykiLCJ2YWxpZCI6dHJ1ZSwiZXhwaXJlZCI6ZmFsc2V9.sy7e5Z4yKZ39-AHmgBlqevBxq7ksBp0hCs3hUPLEFKoqnR91ryRL8lHNkVfFOhsVB5Wy9s71xYAQ9IbWd2kG9Q' -d '{ \ 
   "idServico": 1, \ 
   "informacaoUtil": false, \ 
   "informacaoUtil": false, \ 
   "motivoInformacaoNaoUtil": "Motivo teste" \ 
 }' 'https://servicos.gov.br/api/v1/servicos/avaliacao'
```
