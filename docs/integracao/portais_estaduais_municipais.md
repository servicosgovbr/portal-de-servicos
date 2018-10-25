# Portais Estaduais e Municipais


## Passos da Integração

Os portais de servicos estaduais e municipais que desejem ter seus serviços públicos exibidos no Portal de Serviços Federal devem ter um endpoint em XML ou JSON que lista os serviços com name, description, url, popular_names, keywords:

Atenção: O endpoint tem que ser público sem nenhum tipo de autenticação.

```
{
name (string): Nome do Serviço,
description (string): Descrição do Serviço,
url (url): URL para mais detalhes do Serviço,
popular_names (Array[string], optional): Nomes populares do Serviço,
keywords (Array[string], optional): Palavras Chaves relacionadas ao Serviço
}
```


Exemplo em JSON:
```json
{
    "name": "Serviço para o cidadão",
    "description": "Descrição detalhada do serviço.",
    "url": "https://www.meuportal.gov.br/servicos/servico-para-cidadao",
    "popular_names": [
      {
        "name": "Serviço importante",
      },
      {
        "name": "Outro nome popular",
      }
    ],
    "keywords": [
      {
        "name": "Obter serviço",
      },
      {
        "name": "SPC",
      }
    ]
}
```

Após a disponibilização desse endpoint entre em contato com os responsáveis pelo Portal de Serviços para que possam configurar a integração.

## Visualização no Portal de Serviços

Na pesquisa a visualização no Portal de Serviços Federais será :

[![Portal de Serviços - Pesquisa de Serviços](./pesquisa_servicos.png)](./pesquisa_servicos.png)

A visualização dos detalhes no Portal de Serviços Federais será:

[![Portal de Serviços - Pesquisa de Serviços](./template_servico.png)](./template_servico.png)
