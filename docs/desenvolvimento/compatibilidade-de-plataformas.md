# Compatibilidade de plataformas

## Codificação (_encoding_)

Todos os arquivos de código, dados, configuração e documentação estão em UTF-8.

## Servidores

A aplicação é compatível com qualquer sistema capaz de rodar um ambiente [Java 8](http://www.java.com). Sistemas baseados no Unix são preferíveis, e os pacotes RPM gerados pela construção são compatíveis com qualquer sistema Linux baseado em [RPM]s e [systemd] moderno.

A maioria da [equipe](../sobre-o-projeto/equipe.md) utiliza sistemas MacOS X ou Linux de diversas distribuições para desenvolvimento, sem maiores problemas.

[RPM]:http://www.rpm.org
[systemd]:http://www.freedesktop.org/wiki/Software/systemd/

## Navegadores

A aplicação é compatível com a maioria dos navegadores modernos, utilizando a seguinte grade de compatibilidade:

| Navegador           | Nota C | Nota B | Nota A |
|---------------------|--------|--------|--------|
| Internet Explorer   | < 8    | < 10   | >= 10  |
| Google Chrome       | < 30   |        | >= 30  |
| Apple Safari        | < 4    |        | >= 4   |
| Opera               | < 11   |        | >= 11  |
| Mozilla Firefox     | < 4    |        | >= 4   |
| Mobile Safari (iOS) | < 7    |        | >= 7   |
| Android WebKit      | < 4    |        | >= 4   |

### Nota A

Navegadores nota A estão identificados, são capazes de renderizar todas as _tags_ que utilizamos no HTML das páginas, e relativamente comuns em uso. Bugs em navegadores nota A são tratados como alta prioridade.

Testamos rotineiramente com os seguintes navegadores nota A:

- Google Chrome 42
- Internet Explorer 11
- Mozilla Firefox 37
- Mobile Safari (iOS) 8

### Nota B

Navegadores nota B estão identificados, mas são incapazes de renderizar todas as _tags_ e diretivas CSS que utilizamos. São comuns o suficiente para tratamento diferente dos navegadores nota C, e bugs são tratados como alta prioridade.

Testamos rotineiramente com os seguintes navegadores nota B:

- Internet Explorer 8
- Internet Explorer 9
- Internet Explorer 10

### Nota C

Navegadores nota C estão identificados, mas são incapazes de renderizar todas as _tags_ e diretivas CSS que utilizamos. São relativamente incomuns, e portanto bugs nestes recebem prioridade baixa.
