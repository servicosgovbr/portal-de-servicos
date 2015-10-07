# Estrutura de diretórios

A seguinte estrutura de diretórios para organizar o repositório de catas de serviço é utilizada:

## Visão geral

```
conteudo
├── servico.xsd
└── servicos
    ├── segunda-via-cpf.xml
    ├── emissao-de-passaporte.xml
    └── ...
```

### conteudo/servico.xsd

São as definições em formato XML Schema (XSD) dos dados presentes nos serviços.

### conteudo/servicos/*.xml

Repositório de serviços, no formato XML, contendo todos os serviços.