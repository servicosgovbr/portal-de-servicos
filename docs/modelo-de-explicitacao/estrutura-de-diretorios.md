# Estatura de diretórios

A seguinte estrutura de diretórios para organizar o repositório de catas de serviço é utilizada:

## Visão geral:

```
cartas-servico
├── schema
│   └── tipos-genericos.xsd
└── vN
    └── schema
    |   ├── servico.xsd
    |   └── exemplos
    |       └── servico.xml
    └── servicos
        └── segunda-via-cpf.xml
```

## Diretórios:

### cartas-servico/schema/

Arquivos no formato XSD contendo definições genéricas e independentes de qualquer versão de carta de serviço.

### cartas-servico/vN/schema/

Onde "N" é o número da versão, contém arquivos no formato XSD que definem os dados presentes nos serviços que estão
naquela versão. Estes XSDs procuram estabelecer o que é necessário para aquela versão, utilizando-se de tipos mais 
genéricos definidos no diretório `cartas-servico/schema`.

### cartas-servico/vN/schema/exemplos/

Exemplos no formato XML, automaticamente validados conforme a definição no formato XSD com mesmo nome.

Estes arquivos servem tanto como ferramenta de teste para validar as definições do esquema XSD quanto como arquivo base 
para criação de novos serviços no formato XML.

### cartas-servico/vN/servicos/

Repositório de serviços, no formato XML, contendo todos os serviços de uma versão específica.
