# Publicar Serviço

Para publicar um serviço utilizando a API é necessário estar autenticado com um usuário do tipo "Usuário Api" e ter a permissão "Publicar e Despublicar".

Para autenticar basta seguir os passos descritos em [Autenticação](autenticacao.md).

Estando Autenticado, basta enviar um requisição PUT para o endereço https://servicos.gov.br/api/v1/servicos/publicacao com os parâmetros necessários conforme documentação disponível em https://servicos.gov.br/api/v1/docs#!/servico/put_servicos_publicacao .


Quando o identificador do serviço é informado, o serviço existente será atualizado com as novas informações enviadas. Para publicar um novo, basta não informar o identificador do mesmo.

Após realizar a requisição com sucesso, é retornado o serviço que foi inserido. Caso contrário, é retornado uma mensagem contendo os erros que ocorreram ao  publicar o serviço.

**Exemplo de requisição para publicar um serviço:**
```
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' --header 'cpf: 68847811309' --header 'nome: Teste' --header 'email: teste@servicos.gov.br' --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJub21lIjoiSmFuc2VyIiwic3ViIjoiamFuc2VyLmZlcnJlaXJhQGJhc2lzLmNvbS5iciIsImV4cCI6MTU2MDQ2MDU4MjcwMiwib3JnYW8iOiJTZXJ2acOnbyBGZWRlcmFsIGRlIFByb2Nlc3NhbWVudG8gZGUgRGFkb3MgKFNFUlBSTykiLCJ2YWxpZCI6dHJ1ZSwiZXhwaXJlZCI6ZmFsc2V9.sy7e5Z4yKZ39-AHmgBlqevBxq7ksBp0hCs3hUPLEFKoqnR91ryRL8lHNkVfFOhsVB5Wy9s71xYAQ9IbWd2kG9Q' -d '{ \ 
   "id": null, \ 
   "nome": "serviço de exemplo", \ 
   "sigla": "ex", \ 
   "descricao": "exemplo", \ 
   "contato": "string", \ 
   "gratuito": "string", \ 
   "servicoDigital": true, \ 
   "linkServicoDigital": "string", \ 
   "nomesPopulares": { \ 
     "item": [ \ 
       { \ 
         "item": "string" \ 
       } \ 
     ] \ 
   }, \ 
   "solicitantes": { \ 
     "solicitante": [ \ 
       { \ 
         "tipo": "string", \ 
         "requisitos": "string" \ 
       } \ 
     ] \ 
   }, \ 
   "validadeDocumento": { \ 
     "tipo": "Válido por", \ 
     "quantidade": 0, \ 
     "unidade": "dias", \ 
     "descricao": "string" \ 
   }, \ 
   "orgao": { \ 
     "dbId": 1 \ 
   }, \ 
   "segmentosDaSociedade": { \ 
     "item": [ \ 
       { \ 
         "item": "Cidadãos" \ 
       } \ 
     ] \ 
   }, \ 
   "areasDeInteresse": { \ 
     "item": [ \ 
       "Educação e Pesquisa" \ 
     ] \ 
   }, \ 
   "palavrasChave": { \ 
     "item": [ \ 
       { \ 
         "item": "string 1" \ 
       }, \ 
 { \ 
 "item": "string 2" \ 
 },{ \ 
 "item": "string 3" \ 
 } \ 
     ] \ 
   }, \ 
   "legislacoes": { \ 
     "item": [ \ 
       { \ 
         "item": "string" \ 
       } \ 
     ] \ 
   }, \ 
   "condicoesAcessibilidade": "string", \ 
   "tratamentoPrioritario": "string", \ 
   "tratamentoDispensadoAtendimento": "string", \ 
   "etapas": [ \ 
     { \ 
       "titulo": "string", \ 
       "descricao": "string", \ 
       "documentos": { \ 
         "documentos": [ \ 
           { \ 
             "nome": "string" \ 
           } \ 
         ], \ 
         "casos": [ \ 
           { \ 
             "descricao": "string", \ 
             "item": [ \ 
               { \ 
                 "item": "string" \ 
               } \ 
             ], \ 
             "canalDePrestacao": [ \ 
               { \ 
                 "tipo": "E-mail", \ 
                 "descricao": "string", \ 
                 "procedimentoSistemaIndisponivel": "string", \ 
                 "tempoEstimadoPeriodo": "minutos", \ 
                 "tempoEstimadoEspera": 0 \ 
               } \ 
             ], \ 
             "custo": [ \ 
               { \ 
                 "descricao": "string", \ 
                 "moeda": "R$", \ 
                 "valor": 0, \ 
                 "valorVariavel": "string", \ 
                 "statusCustoVariavel": 0 \ 
               } \ 
             ], \ 
             "documento": [ \ 
               { \ 
                 "descricao": "string", \ 
                 "ondeObter": "string", \ 
                 "observacoes": "string" \ 
               } \ 
             ] \ 
           } \ 
         ] \ 
       }, \ 
       "custos": { \ 
         "custos": [ \ 
           { \ 
             "descricao": "string", \ 
             "moeda": "R$", \ 
             "valor": 0, \ 
             "valorVariavel": "string", \ 
             "statusCustoVariavel": 0 \ 
           } \ 
         ], \ 
         "casos": [ \ 
           { \ 
             "descricao": "string", \ 
             "item": [ \ 
               { \ 
                 "item": "string" \ 
               } \ 
             ], \ 
             "canalDePrestacao": [ \ 
               { \ 
                 "tipo": "E-mail", \ 
                 "descricao": "string", \ 
                 "procedimentoSistemaIndisponivel": "string", \ 
                 "tempoEstimadoPeriodo": "minutos", \ 
                 "tempoEstimadoEspera": 0 \ 
               } \ 
             ], \ 
             "custo": [ \ 
               { \ 
                 "descricao": "string", \ 
                 "moeda": "R$", \ 
                 "valor": 0, \ 
                 "valorVariavel": "string", \ 
                 "statusCustoVariavel": 0 \ 
               } \ 
             ], \ 
             "documento": [ \ 
               { \ 
                 "descricao": "string", \ 
                 "ondeObter": "string", \ 
                 "observacoes": "string" \ 
               } \ 
             ] \ 
           } \ 
         ] \ 
       }, \ 
       "canaisDePrestacao": { \ 
         "canaisDePrestacao": [ \ 
           { \ 
             "tipo": "E-mail", \ 
             "descricao": "string", \ 
             "procedimentoSistemaIndisponivel": "string", \ 
             "tempoEstimadoPeriodo": "minutos", \ 
             "tempoEstimadoEspera": 0 \ 
           } \ 
         ], \ 
         "casos": [ \ 
           { \ 
             "descricao": "string", \ 
             "item": [ \ 
               { \ 
                 "item": "string" \ 
               } \ 
             ], \ 
             "canalDePrestacao": [ \ 
               { \ 
                 "tipo": "E-mail", \ 
                 "descricao": "string", \ 
                 "procedimentoSistemaIndisponivel": "string", \ 
                 "tempoEstimadoPeriodo": "minutos", \ 
                 "tempoEstimadoEspera": 0 \ 
               } \ 
             ], \ 
             "custo": [ \ 
               { \ 
                 "descricao": "string", \ 
                 "moeda": "R$", \ 
                 "valor": 0, \ 
                 "valorVariavel": "string", \ 
                 "statusCustoVariavel": 0 \ 
               } \ 
             ], \ 
             "documento": [ \ 
               { \ 
                 "descricao": "string", \ 
                 "ondeObter": "string", \ 
                 "observacoes": "string" \ 
               } \ 
             ] \ 
           } \ 
         ] \ 
       }, \ 
       "tempoTotalEstimado": { \ 
         "ate": { \ 
           "max": 0, \ 
           "unidade": "minutos" \ 
         }, \ 
         "entre": { \ 
           "min": 0, \ 
           "max": 0, \ 
           "unidade": "minutos" \ 
         }, \ 
         "emMedia": { \ 
           "max": 0, \ 
           "unidade": "minutos" \ 
         }, \ 
         "atendimentoImediato": { \ 
           "tipo": "Atendimento Imediato" \ 
         }, \ 
         "naoEstimadoAinda": { \ 
           "tipo": "Não Estimando Ainda" \ 
         }, \ 
         "descricao": "string" \ 
       } \ 
     } \ 
   ], \ 
   "tempoTotalEstimado": { \ 
     "ate": { \ 
       "max": 0, \ 
       "unidade": "minutos" \ 
     }, \ 
     "entre": { \ 
       "min": 0, \ 
       "max": 0, \ 
       "unidade": "minutos" \ 
     }, \ 
     "emMedia": { \ 
       "max": 0, \ 
       "unidade": "minutos" \ 
     }, \ 
     "atendimentoImediato": { \ 
       "tipo": "Atendimento Imediato" \ 
     }, \ 
     "naoEstimadoAinda": { \ 
       "tipo": "Não Estimando Ainda" \ 
     }, \ 
     "descricao": "string" \ 
   }, \ 
   "servicosRelacionados": [ \ 
     ] \ 
 }' 'https://servicos.gov.br/api/v1/servicos/publicacao'

 ```
