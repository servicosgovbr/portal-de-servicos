# Autenticação

Para autenticar utilizando a API do editor é necesário  fazer uma requisiçao POST para o endereço https://servicos.gov.br/api/v1/autenticar, conforme descrito na documentção disponível em: https://servicos.gov.br/api/v1/docs#!/autenticacao/post_autenticar .

Caso a autenticação seja realizada com sucesso, é retornado um token [JWT](https://jwt.io/), o qual deve ser utilizado nas requisições que requerem autenticação.

**Exemplo de requisição para autenticar:**
```Curl
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
   "email": "api%40servicos.gov.br", \ 
   "senha": "12345678" \ 
 }' 'https://servicos.gov.br/api/v1/autenticar'
```
