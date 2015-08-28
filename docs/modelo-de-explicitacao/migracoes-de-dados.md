# Migrações de dados

A antiga plataforma do servicos.gov.br exportava um arquivo XML no seguinte formato:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<dados>
    <servicos>
        <servico>
            <titulo>Auxílio-Doença</titulo>
            <descricao>
                Benefício concedido ao segurado impedido de trabalhar...
            </descricao>
            <requisitos>
                ...
            </requisitos>
            <etapas>
                ...
            </etapas>
            <canaisPrestacaoServico>
                <canalPrestacaoServico>
                    <tipoCanalPrestacaoServico>
                        <titulo>Presencial</titulo>
                    </tipoCanalPrestacaoServico>
                    <descricao>Serviço prestado nas Agência da Previdência Social.</descricao>
                    <url></url>
                </canalPrestacaoServico>
                ...
            </canaisPrestacaoServico>
            <taxa></taxa>
            <informacoesUteis>
                <informacaoUtil>
                    <tipoInformacaoUtil>
                        <titulo>Agendamento</titulo>
                    </tipoInformacaoUtil>
                    <descricao>Agende aqui seu atendimento.</descricao>
                    <url>http://www2.dataprev.gov.br/sabiweb/agendamento/inicio.view</url>
                </informacaoUtil>
                ...
            </informacoesUteis>
            <compromissosAtendimento>
                ...
            </compromissosAtendimento>
            <legislacoes>
                ...
            </legislacoes>
            <palavrasChave>
                ...
            </palavrasChave>
            <orgaoResponsavel>
                <titulo>Ministerio da Previdencia Social - MPS</titulo>
            </orgaoResponsavel>
            <orgaoPrestador>
                <titulo>Instituto Nacional do Seguro Social - INSS.</titulo>
                <endereco></endereco>
                <telefone></telefone>
            </orgaoPrestador>
            <publicosAlvo>
                <publicoAlvo>
                    <titulo>Serviços aos cidadãos</titulo>
                    <linhasDaViva>
                        <linhaDaVida>
                            <titulo>Cuidando da saúde</titulo>
                            <eventoslinhaDaVida>
                                <eventolinhaDaVida>
                                    <titulo>Seguro de vida e patrimonial</titulo>
                                </eventolinhaDaVida>
                                ...
                            </eventoslinhaDaVida>
                        </linhaDaVida>
                        ...
                    </linhasDaViva>
                </publicoAlvo>
                ...
            </publicosAlvo>
            <areasInteresse>
                <area>
                    <titulo>Previdência Social</titulo>
                </area>
                ...
            </areasInteresse>
        </servico>
        ...
    </servicos>
</dados>
```

## Campos convertidos

Para as migrações de dados para a versão mais recente, os seguintes campos foram mapeados:

| Legado                 | v3                           | Notas                                                                 |
|------------------------|------------------------------|-----------------------------------------------------------------------|
| titulo                 | nome                         | Obrigatório, presente em todos os serviços legados                    |
| -                      | sigla                        | Sempre vazio                                                          |
| -                      | nomes-populares              | Sempre vazio                                                          |
| descricao              | descricao                    | Obrigatório, presente em todos os serviços legados, contém formatação |
| -                      | solicitantes                 | Sempre vazio                                                          |
| -                      | tempo-total-estimado         | Sempre vazio                                                          |
| requisitos             | -                            | Sempre vazio, não importado                                           |
| etapas                 | -                            | Sempre vazio, não importado                                           |
| canaisPrestacaoServico | etapa[0]/canais-de-prestacao | Adicionados à etapa 0, já que na v3 os canais pertencem às etapas     |
| taxa                   | etapa[0]/custos              | Adicionados à etapa 0, já que na v3 os custos pertencem às etapas     |
| informacoesUteis       | descricao                    | Adicionados ao final da descrição, como links Markdown                |
| orgaoPrestador         | -                            | Descartado                                                            |
| orgaoResponsavel       | orgao                        | Importamos apenas o slug, no atributo "id"                            |
| publicosAlvo           | segmentos-da-sociedade       | Algumas conversões de conteúdo realizadas                             |
| areasInteresse         | areas-de-interesse           | Convertido do VCGE 1.0 para VCGE 2.0                                  |

## Campos com tratamento especial

### descricao

No XML legado, o campo `descricao` possuía formatação especial em muitos dos serviços descritos. Ela foi mantida, mas não é
necessariamente compatível com [Markdown]. Na maioria dos casos, requer atenção manual.

### tipoCanalPrestacaoServico

Alguns campos do tipo `informacaoUtil` foram convertidos em canais de prestação por possuir um link direto para a prestação
ou agendamento do serviço.

A seguinte tabela de conversão foi aplicada:

| Legado            | v3                 |
|-------------------|--------------------|
| Agendamento       | Web - Agendamento  |
| Aplicativo móvel  | Aplicativo móvel   |
| E-mail            | E-mail             |
| Fax               | Fax                |
| Mobile            | Aplicativo móvel   |
| Outros            | Postal             |
| Postal            | Postal             |
| Presencial        | Presencial         |
| SMS               | SMS                |
| Telefone          | Telefone           |
| Web               | Web                |

### informacaoUtil

Foi decidido não mapear um campo específico para `informacaoUtil`, e aqueles no formato legado que não possuíam os tipos
`Web` ou `Agendamento` foram convertidos em links Markdown ao final da descrição principal do serviço (`descricao`).

## Resultados

Ao final da conversão, a carta exemplificada acima fica assim:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<servico xmlns="http://servicos.gov.br/v3/schema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://servicos.gov.br/v3/schema ../servico.xsd">
    <nome>Auxílio-Doença</nome>
    <sigla/>
    <nomes-populares/>
    <descricao>
        Benefício concedido ao segurado impedido de trabalhar...
    </descricao>
    <gratuito/>
    <solicitantes/>
    <tempo-total-estimado>
        <descricao/>
    </tempo-total-estimado>
    <etapas>
        <etapa>
            <titulo/>
            <descricao/>
            <documentos/>
            <custos/>
            <canais-de-prestacao>
                <default>
                    <canal-de-prestacao tipo="web-agendar">
                        <descricao>http://www2.dataprev.gov.br/sabiweb/agendamento/inicio.view</descricao>
                    </canal-de-prestacao>
                    <canal-de-prestacao tipo="presencial">
                        <descricao>Serviço prestado nas Agência da Previdência Social.</descricao>
                    </canal-de-prestacao>
                </default>
            </canais-de-prestacao>
        </etapa>
    </etapas>
    <orgao id="ministerio-da-previdencia-social-mps"/>
    <segmentos-da-sociedade>
        <item>Cidadãos</item>
    </segmentos-da-sociedade>
    <eventos-da-linha-da-vida>
        <item>Cuidados com a saúde</item>
    </eventos-da-linha-da-vida>
    <areas-de-interesse>
        <item>Previdência Social</item>
    </areas-de-interesse>
    <palavras-chave/>
    <legislacoes/>
</servico>
```