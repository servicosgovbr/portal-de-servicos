_Definição extraída do Termo de Referência:_

> Plano de Iterações pelo Método Ágil: documenta como as equipes do projeto irão utilizar o Método Ágil (SCRUM) ao longo do projeto.  Deverá indicar a ferramenta apropriada para uso do Método. Também faz parte do plano de projetos uma visão mais pormenorizada da metodologia a ser adotada e de seus fatores críticos de sucesso. No entanto, o método a ser adotado só será definido na primeira fase do projeto assim como todas as ferramentas que serão utilizadas.

Metodologia
===========

Mantendo em mente nossos princípios base, definidos durante as primeiras interações entre o time da ThoughtWorks e o time do Guia de Serviços, os quais são:

0. O cidadão é o principal beneficiado
0. Um serviço deve ser simples para todos
0. Aprender com o cidadão (design por _feedback_)
0. Os serviços estão em constante evolução
0. Adoção a partir de exemplos
0. Os serviços devem ser tangíveis

Elaboramos uma metodologia de trabalho que, assim como os serviços públicos que visamos evoluir, estará em constante aprimoramento. Esta metodologia busca o incremento ou refinamento de funcionalidades e mecanismos que visam prover ao cidadão maior eficácia e efetividade na busca, escolha e acompanhamento desses serviços.

Visando um ambiente de constante aprendizado e guiado a partir de evidências que validem nossas premissas, escolhemos um modelo de aprendizado por experimentação baseado em hipóteses. Hipóteses são derivadas de pressupostos e ideias cuja aplicação acreditamos que irão agregar valor ao produto sendo construído.

Segue abaixo um exemplo de hipótese definida durante nossa semana de iniciação:


    Acreditamos que:
        ao adaptar a linguagem da descrição do serviço

    Para:
       o cidadão

    Obteremos como resultado:
        o usuário ter o entendimento com clareza se ele é elegível ou não para aquele serviço

    Saberemos que atingimos o resultado quando:
        * reduz a proporção de serviços indeferidos
        * diminui o número de atendimentos que não viram processos
        * reduz o número de exigências e/ou reduz o número de diligências


As hipóteses serão priorizadas de acordo com o valor entregue ao cidadão versus o esforço necessário para que o valor seja entregue visando que, de forma iterativa e incremental, dando pequenos passos e acumulando aprendizado, possamos validar ou refutar nossas hipóteses.

Uma hipótese pode ser escrita no escopo de um nível de oferta de serviço (busca, escolha, ou acompanhamento) e pode afetar um serviço em específico ou diversos serviços ao mesmo tempo.

Hipóteses podem surgir organicamente a partir da discussão de possíveis abordagens e otimizações ao atual Guia de Serviços e suas ofertas. São coisas que acreditamos que possuem um valor para alguém e somente através da criação de mecanismos que nos aproximem deste "alguém" é que teremos condições de verificar se efetivamente há um acréscimo de valor ao construir ou modificar algo.

Métricas assumem um papel essencial na validação de nossas hipóteses. Visando sempre aprender com o cidadão, cada hipótese deverá sempre ser acompanhada de uma métrica que nos ajude a compreender se a premissa assumida até então é valida ou não. Como resultado, teremos um aprendizado baseado em evidências que nos permitirá refinar, eliminar ou criar novas hipóteses e experimentos.

Seguindo este pensamento de que nosso resultado vai além do software entregue e se baseia no aprendizado gerado por cada hipótese testada, o fluxo de trabalho foi organizado na seguinte forma:

* _Ideias_ -> _Para rodar_ -> _Em progresso_ -> _Para produção_ -> ***Aprendemos***

Cada passo do fluxo de trabalho compreende uma parte de nosso experimento em busca de validar ou refutar uma hipótese. Sendo eles:

* ***Ideias:*** Hipóteses a serem priorizadas
* ***Para rodar:*** Hipóteses priorizadas a serem desenvolvidas
* ***Em progresso:*** Hipóteses sendo desenvolvidas por um ou mais membros do time
* ***Para produção:*** Hipóteses prontas para ser publicadas e testadas
* ***Aprendemos:*** Hipóteses testadas, métricas avaliadas e aprendizado registrado

Nossas hipóteses serão priorizadas e desenvolvidas visando iterações de duas semanas de trabalho. Com uma retrospectiva a cada duas iterações para refletirmos sobre o trabalho que tem sido feito e avaliar possíveis alterações no fluxo de trabalho, ferramentas utilizadas, ou quaisquer outros pontos de melhoria que possam ser atacados para tornar o projeto mais produtivo.

Teremos também, todos os dias pela manhã, uma reunião que chamamos de "Reunião em Pé" (tradução do termo _stand-up meeting_). Está é uma reunião rápida, de aproximadamente 15 minutos, visando o alinhamento do time com relação ao trabalho a ser efetuado além de identificar possíveis impedimentos que precisam ser resolvidos.

Um _Release_ é caracterizado como um marco de aprendizado consolidado e aumento da maturidade do Guia de Serviços.

_Apresentações de Iteração_ serão feitas a cada iteração, comunicando nosso aprendizado com relação ao trabalho desenvolvido até então através de documentos e apresentações que poderão ser utilizadas para o acompanhamento do projeto.

Para nos auxiliar no desenvolvimento e acompanhamento de nossas hipóteses, escolhemos as seguintes ferramentas:

Ferramentas de trabalho
=======================

### Repositório de código fonte: [Github](https://github.com/servicosgovbr)

Github é o repositório onde o código fonte do projeto será armazenado.

A utilização do Github reforça nosso compromisso com a transparência com relação ao trabalho desenvolvido pelo time. Qualquer cidadão pode visualizar e revisar o código além de permitir que os interessados contribuam com novas funcionalidades, correções ou melhorias.

Uma nova organização foi criada no Github para de agrupar qualquer projeto ou subprojeto relacionado ao Guia de Serviços.

![Github](https://raw.githubusercontent.com/servicosgovbr/guia-de-servicos/master/docs/estrategia/plano_iteracoes/images/github.png)

Referências:

* Organização: [https://github.com/servicosgovbr](https://github.com/servicosgovbr)
* Projeto do Guia de Serviços: [https://github.com/servicosgovbr/guia-de-servicos](https://github.com/servicosgovbr/guia-de-servicos)

### Gerenciamento de hipóteses: [Github Issues](https://github.com/servicosgovbr/guia-de-servicos/issues)

Ferramenta integrada ao Github que permite a criação de tarefas a serem analisadas e desenvolvidas pelo time. Utilizaremos esta ferramenta para registrar e gerenciar nossas hipóteses no decorrer do projeto.

Por ser uma ferramenta aberta, ela permite a colaboração de quaisquer interessados no projeto, seja sugerindo novas hipóteses, melhorias, reportando problemas ou, até mesmo, contribuindo com código e documentações.

![Github Issues](https://raw.githubusercontent.com/servicosgovbr/guia-de-servicos/master/docs/estrategia/plano_iteracoes/images/github_issues.png)

Referências:

* Tarefas do projeto: [https://github.com/servicosgovbr/guia-de-servicos/issues](https://github.com/servicosgovbr/guia-de-servicos/issues)

### Quadro de tarefas: [Waffle](https://waffle.io/servicosgovbr/guia-de-servicos)

Waffle é um quadro virtual e integrado ao Github Issues que gera visualizações em tempo real de cada uma das tarefas e seu estado atual.

Utilizaremos esta ferramenta para dar mais visibilidade com relação as tarefas que estão sendo desenvolvidas pelo time e quem é o responsável por ela.

![Waffle](https://raw.githubusercontent.com/servicosgovbr/guia-de-servicos/master/docs/estrategia/plano_iteracoes/images/waffle.png)

Referências:

* Quadro de tarefas do Guia de Serviços: [https://waffle.io/servicosgovbr/guia-de-servicos](https://waffle.io/servicosgovbr/guia-de-servicos)

### Ferramenta de integração contínua: [Snap-CI](https://snap-ci.com/servicosgovbr/guia-de-servicos/branch/master)

Snap-CI é uma ferramenta de integração contínua. Ela se integra ao Github permitindo que, a cada novo código submetido, uma nova versão da aplicação seja compilada, testada, empacotada e publicada em um ambiente predefinido.

Inicialmente estamos publicando as novas versões no Heroku. Uma plataforma gratuita, na núvem, para publicação de aplicações.

Ao automatizar o processo de publicação do projeto, permitimos ciclos de feedback mais curtos, para identificar e agir sobre qualquer problema o mais rápido possível, melhorando assim a qualidade do produto final.

![Snap-CI](https://raw.githubusercontent.com/servicosgovbr/guia-de-servicos/master/docs/estrategia/plano_iteracoes/images/snap_ci.png)

Referências:

* Integração contínua do projeto: [https://snap-ci.com/servicosgovbr/guia-de-servicos/branch/master](https://snap-ci.com/servicosgovbr/guia-de-servicos/branch/master)

### Monitoramento da cobertura de testes: [Coveralls](https://coveralls.io/r/servicosgovbr/guia-de-servicos?branch=master)

Acreditamos que TDD, Test Driven Development (desenvolvimento orientado a testes), seja uma técnica crucial para o sucesso de projetos de desenvolvimento de software. Esta técnica consiste na criação de testes automatizados durante o desenvolvimento de cada nova funcionalidade da aplicação, obtendo como resultado testes que garantem a consistência e qualidade do software ao longo de todo o projeto.

Coveralls permite visualizar a porcentagem de cobertura de testes da aplicação visando garantir o acompanhamento constante da qualidade de nossos testes automatizados.

![Coveralls](https://raw.githubusercontent.com/servicosgovbr/guia-de-servicos/master/docs/estrategia/plano_iteracoes/images/coveralls.png)

Referências:

* Análise de cobertura de testes: [https://coveralls.io/r/servicosgovbr/guia-de-servicos?branch=master](https://coveralls.io/r/servicosgovbr/guia-de-servicos?branch=master)

### Repositório de documentação: [Read the Docs](http://guia-de-servicos.readthedocs.org/pt_BR/latest/)

Read the Docs é uma ferramenta que compila textos escritos em formato Markdown e os disponibiliza em uma página na internet de forma navegável e indexada para buscas.

A documentação do projeto será escrita em formato Markdown, armazenada no Github do próprio projeto e, a cada alteração, Read the Docs irá automaticamente gerar uma nova versão da documentação e disponibilizá-la online.

![Read the Docs](https://raw.githubusercontent.com/servicosgovbr/guia-de-servicos/master/docs/estrategia/plano_iteracoes/images/read_the_docs.png)

Referências:

* Documentação do projeto: [http://guia-de-servicos.readthedocs.org/pt_BR/latest/](http://guia-de-servicos.readthedocs.org/pt_BR/latest/)

### Listas de email: [Google Groups](https://groups.google.com/d/forum/guiadeservicos-discussao)

Duas listas de discussão foram criadas para o facilitar a comunicação no projeto: 

* ***Discussão:*** uma lista para a discussões gerais sobre o projeto (com links, dúvidas, debates, etc.)
* ***Anúncios:*** lista criada para divulgação de anúncios (de baixo tráfego, somente para informativos gerais, novas publicações e eventos maiores)

![Google Groups](https://raw.githubusercontent.com/servicosgovbr/guia-de-servicos/master/docs/estrategia/plano_iteracoes/images/google_groups.png)

Referências:

* Lista de anúncios: [https://groups.google.com/d/forum/guiadeservicos-anuncios](https://groups.google.com/d/forum/guiadeservicos-anuncios)
* Lista de discussões: [https://groups.google.com/d/forum/guiadeservicos-discussao](https://groups.google.com/d/forum/guiadeservicos-discussao)

### Comunicação por meio de mensagens instantâneas: [Gitter](https://gitter.im/servicosgovbr/guia-de-servicos)

Gitter é um comunicador instantâneo que permite a participação de qualquer interessado no projeto.

![Gitter](https://raw.githubusercontent.com/servicosgovbr/guia-de-servicos/master/docs/estrategia/plano_iteracoes/images/gitter.png)

Referências:

* Sala do projeto: [https://gitter.im/servicosgovbr/guia-de-servicos](https://gitter.im/servicosgovbr/guia-de-servicos)
