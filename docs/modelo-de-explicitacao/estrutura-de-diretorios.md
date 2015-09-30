# Estrutura de diretórios

A seguinte estrutura de diretórios para organizar o repositório de catas de serviço é utilizada:

## Visão geral

```
cartas-servico
└── vN
    ├── servico.xsd
    └── servicos
        ├── segunda-via-cpf.xml
        ├── emissao-de-passaporte.xml
        └── ...
```

### cartas-servico/vN/servico.xsd

Onde "N" é o número da versão, as definições em formato XML Schema (XSD) dos dados presentes nos serviços que estão
naquela versão. 

### cartas-servico/vN/servicos/*.xml

Repositório de serviços, no formato XML, contendo todos os serviços daquela uma versão do formato.

## Versão atual

A versão atual do modelo das cartas de serviços é a 3, e pode ser visualizada no [repositório oficial do projeto][GH].

[GH]:https://github.com/servicosgovbr/cartas-de-servico/tree/master/cartas-servico/v3
