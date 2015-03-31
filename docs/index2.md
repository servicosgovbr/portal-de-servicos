# Guia de Serviços

## Introdução

Esta é a documentação oficial do Guia de Serviços Públicos do Governo Federal.

### Sobre este documento

Este conjunto de documentos serve aos desenvolvedores, gestores e colaboradores contribuindo com a criação do projeto, e também para aqueles que querem começar a contribuir e extrair valor tecnológico de sua implementação de alguma forma.

#### Convenções adotadas

Utilizamos níveis de cabeçalhos para facilitar a navegação entre os documentos. 

Links entre documentos (e a outros, externos), sempre que possível.

Palavras em inglês que não são termos técnicos corriqueiros ("site", por exemplo) não recebem marcação especial. Termos específicos e de uso pouco frequente ("clustering", por exemplo) são grafados em itálico.

Termos técnicos que se referem diretamente ao código, configurações ou partes da sintaxe dos mesmos são grafados em fonte monoespaçada (`@Controller`, por exemplo).

## Princípios

Durante a iniciação do projeto, chegamos aos seguintes princípios para guiar seu desenvolvimento e quaisquer decisões de design, priorização e execução a serem tomadas. Detalharemos cada um deles nas próximas seções, mas é importante tê-los em mente desde o início. Estes princípios orientadores são:

### O cidadão é o principal beneficiado

O benefício maior do Guia de Serviços é que o cidadão consiga cumprir seus objetivos. É preciso conhecê-lo, ouvi-lo e atendê-lo. É dele que surgem demandas para evolução dos serviços públicos.

### Um serviço deve ser simples para todos

O serviço público envolve diversas pessoas, do cidadão aos servidores
públicos. Todos no processo devem ser atendidos. A tecnologia deve ser a facilitadora de processos e sempre ser desenvolvida
para permitir o ganho de escala e a automação destes.

### Aprender com o cidadão (design por feedback)

Evoluir os serviços a partir de dados de uso. Aprender com o uso real da
plataforma é o caminho mais adequado para entender o que o cidadão precisa e então modelar o sistema para atender suas necessidades.

### Os serviços estão em constante evolução

Os diversos serviços estarão em diferentes graus de maturidade. Ou seja, não é preciso que um serviço esteja "pronto" para estar no Guia de Serviços. Sua presença no Guia agregará o valor possível em seu atual nível de maturidade.

### Adoção a partir de exemplos

Em vez de uma abordagem impositiva, a adoção dos novos padrões e modelo de maturidade de serviços por parte dos órgãos públicos deve ocorrer através de exemplos de boas práticas e resultados positivos.
 
### Os serviços devem ser tangíveis

Para ser percebida e influente, a experiência do serviço deve ser presente, com pontos de contato entre cidadão e governo claros. Métricas acionáveis deverão orientar a evolução e explicitar resultados.

## Sobre o projeto

#### Equipe

O projeto do Guia de Serviços conta com duas equipes multi-disciplinares e com formação bastante diversa. A composição de equipes desta forma nos dá uma gama maior de ideias a testar, e em conjunto vivências de praticamente todo o tipo de cidadão a quem queremos atender.

##### Governo Federal

* [Andrea Ricciardi](mailto:andrea.ricciardi@planejamento.gov.br)
* [Augusto Herrmann](mailto:augusto.herrmann@planejamento.gov.br)
* [Bruno Palvarini](mailto:bruno.palvarini@planejamento.gov.br)
* [Christian Miranda](mailto:christian.miranda@planejamento.gov.br)
* [Carlos Vieira](mailto:edu.carlos.vieira@gmail.com)
* [Elise Gonçalves](mailto:elise.goncalves@planejamento.gov.br)
* [Esaú Mendes](mailto:esau.mendes@planejamento.gov.br)
* [Everson Aguiar](mailto:everson.aguiar@planejamento.gov.br)
* [Fabrício Almeida](mailto:fabricio.fontenele@planejamento.gov.br)
* [Izabel Garcia](mailto:izabel.garcia@planejamento.gov.br)
* [Jonathas Duarte](mailto:jonathas.duarte@inss.gov.br)
* [Maurício Formiga](mailto:mauricio.formiga@planejamento.gov.br)
* [Nitai Silva](mailto:nitai.silva@planejamento.gov.br)
* [Sergio Nascimento](mailto:sergio.nascimento@sdh.gov.br)
* [Silvia Belarmino](mailto:silvia.belarmino@planejamento.gov.br)

##### ThoughtWorks

* [Alexandre Klaser](mailto:aklaser@thoughtworks.com)
* [Barbara Wolff Dick](mailto:bdick@thoughtworks.com)
* [Carlos Villela](mailto:cvillela@thoughtworks.com)
* [Clarissa Martins](mailto:cmartins@thoughtworks.com)
* [Claudia Melo](mailto:cmelo@thoughtworks.com)
* [Erick Pintor](mailto:epintor@thoughtworks.com)
* [Jean Kirchner](mailto:jkirchne@thoughtworks.com)
* [Olivia Janequine](mailto:oliviaj@thoughtworks.com)
* [Samantha Rosa](mailto:srosa@thoughtworks.com)

##### Contribuidores

A natureza do código aberto do projeto nos trouxe alguns contribiudores externos às duas equipes, também. Somos muito gratos a:

* [Gabriel Such](mailto:gabrielsuch@gmail.com)
* [Rodrigo Alencar](mailto:ralencar@thoughtworks.com)
* [Thiago Felix](mailto:tfelix@thoughtworks.com)

Em seções seguintes, detalharemos como contribuir com o código aberto do Guia de Serviços.

#### Contato

##### Listas de Discussão

Criamos duas listas de discussão para o desenvolvimento do Guia de Serviços: uma para a discussão geral sobre o projeto (com links, dúvidas, debates, etc.) e outra para anúncios (de baixo tráfego, somente para informativos gerais, novas publicações e eventos maiores):

* [guiadeservicos-anuncios][ANUNCIOS] ([email](mailto:guiadeservicos-anuncios@googlegroups.com))
* [guiadeservicos-discussao][DISCUSSAO] ([email](mailto:guiadeservicos-discussao@googlegroups.com))

Caso você queira apenas acompanhar o andamento do projeto, recomenda-se filiar apenas à lista de anúncios. Para desenvolvedores, o ideal é assinar ambas as listas.

##### Chat

Caso queria conversar com o time do projeto, basta entrar em nossa sala no Gitter:

* [Gitter]

Estamos online geralmente nos dias úteis das 9 às 19:00, horário de Brasília.

[ANUNCIOS]:https://groups.google.com/d/forum/guiadeservicos-anuncios
[DISCUSSAO]:https://groups.google.com/d/forum/guiadeservicos-discussao
[Gitter]:https://gitter.im/servicosgovbr/guia-de-servicos?utm_source=share-link&utm_medium=link&utm_campaign=share-link

#### Como relatar problemas

Durante o desenvolvimento do projeto, muitas coisas estarão em transição e procuramos aprender o máximo possível com todos os erros e acertos ao longo do caminho. Caso algum problema tenha persistido em aparecer, a melhor maneira de vê-lo solucionado é relatando-o.

A equipe do Guia de Serviços usa o [GitHub Issues][GHISSUES] para acompanhar questões, bugs e contribuições de novo código pela comunidade.

Se você encontrou um problema no Guia de Serviços, ou tem uma sugestão a fazer, este é o lugar ideal para começar.

##### Criando um relatório de erro

É provável que seu problema já tenha sido relatado por outro membro da equipe ou contribuidor. Para evitar trabalho desnecessário, faça uma rápida busca no [GitHub Issues][GHISSUES] e veja se o problema já foi reportado antes de começar.

Caso contrário, seu próximo passo será [abrir umo novo relatório][GHINEW]. O ideal é que ele contenha um título e uma descrição clara do problema, bem como toda e qualquer informação que possa ajudar a corrigi-lo:

* Versão do sistema operacional
* Versão do navegador utilizado
* Ambiente utilizado (desenvolvimento, homologação, produção)
* Link da página que estava acessando
* _Ticket_ da página de erro, se houver
* Quaisquer _stack traces_ gerados
* Arquivos de log relevantes
* Mensagens de erro no console JavaScript

Inclua o máximo possível de informações relevantes. Se possível, anexe a captura de tela que demonstra o erro.

[GHISSUES]:https://github.com/servicosgovbr/guia-de-servicos/issues
[GHINEW]:https://github.com/servicosgovbr/guia-de-servicos/issues/new

## Serviços

### O que é um serviço?

### Etapas da oferta de serviços

A oferta de um serviço no Guia pode ser dividida em três etapas:

#### Busca

O usuário está procurando pelo serviço que melhor atende às suas necessidades, através da busca, navegação e leitura casual de chamadas, links, banners, etc.

É importante acompanhar métricas e estudos relacionados à navegabilidade do sistema para que usuários encontrem os serviços que procuram de forma rápida, fácil e intuitiva, sem treinamento específico.

#### Escolha

O usuário encontrou o serviço que procurava e está obtendo informações sobre como acessá-lo corretamente. Neste ponto, ele determina os requisitos para o acesso ao serviço, entende os passos necessários por todas as partes envolvidas e é direcionado à ação.

Estas ações variam de acordo com a natureza do serviço prestado. Alguns serviços oferecem ações meramente informativas, outros o preenchimento de formulários, agendamento de visitas presenciais a postos de atendimento, e em outros todos os passos para a realização do serviço podem ser executados online.

É importante levar esta diversidade em consideração ao desenhar mecanismos de interação com os serviços, para que todos sejam executados com sucesso pelos usuários.

#### Acompanhamento

O usuário agiu após a escolha de um serviço (agendando uma visita presencial a um posto de atendimento, por exemplo) e quer acompanhar os próximos passos da prestação do mesmo.

Esta etapa pode variar bastante em função da natureza do serviço prestado: de um simples agradecimento ao registro de um protocolo que pode consolidar diversos passos de execução prestados em diversos órgãos diferentes.

### Modelos de maturidade

### Critérios para escolha

Decidimos que os seguintes critérios serão utilizados para escolher serviços a destacar e priorizar durante o desenvolvimento:

#### Relevância para o cidadão

Medida em número de acessos/solicitações ao serviço. A baixa utilização de determinados serviços pode ser uma indicação de que ele precisa ser revisto, mas entende-se que nem todos os serviçõs são de uso geral pela população, e meramente comparar um serviço a outro através deste número não é produtivo.

#### Relevância para o órgão

Medida subjetiva sob o ponto de vista do órgão, pela importância do serviço em relação ao conjunto dos outros serviços que oferece.

#### Complexidade externa

Determinada pelo número de pré-requisitos, número de passos e/ou interações com outros órgãos. Este critério difere da complexidade _interna_ do serviço, pois não leva em consideração o número de passos necessários para a execução do serviço que são internos ao órgão.

#### Oferta

Determinado pelo número atual de canais de prestação do serviço.

#### Desempenho

Medido pelo tempo médio da transação, isto é, o tempo necessário para ir da primeira ação necessária à execução do serviço até sua conclusão.

#### Acessibilidade e empatia

Determinadas pela proximidade percebida com a linguagem e perspectiva do cidadão.

#### Padronização

Determinada pelo grau de aderência ao atuais padrões de serviços públicos.

## Métricas

## Metodologia de desenvolvimento

### Desenvolvimento orientado a hipóteses

### Kanban e Iterações

#### Gerenciamento

##### Github Issues

##### Waffle

## Desenvolvimento

### Introdução

#### Objetivos técnicos

### Arquitetura

#### Model-View-Controller

##### Apresentação (View)

##### Controladores

##### Modelos e objetos de domínio

##### Outros componentes

### Infra-estrutura

#### Ambientes de desenvolvimento

#### Ambientes de homologação e produção

### Implantação e execução

#### Desenvolvimento local

#### Vagrant

#### Amazon WebServices

#### Outros

### Testes

#### Unitários

#### Integração

#### Cobertura

#### Interface

#### Checagens estáticas

### Requisitos Não-Funcionais

#### Acessibilidade

#### Auditabilidade

#### Backups

#### Capacidade

#### Compatibilidade de plataformas

##### Servidores

##### Navegadores

#### Dependências de terceiros

#### Disponibilidade

#### Documentação

#### Escalabilidade

#### Extensibilidade

#### Gerência de configuração, falhas e desastres

##### Interoperabilidade

#### Performance

#### Privacidade

#### Usabilidade

