# Objetivo

Este documento tem o objetivo de definir requisitos não funcionais e de comportamento do Guia de Serviços Públicos do Governo Federal que serão utilizados como parâmetro para julgá-lo operacionalmente. Além disso, esse produto de trabalho descreve a infraestrutura tecnológica de software, hardware e redes utilizada e aspectos relacionados a estas.

# Codificação

## Codificação de Caracteres (Encoding)

Usar UTF-8 em todos os arquivos de código, dados, configuração e documentação.

## Organização do Código

O código Java deve ser organizado em pacotes, a partir de `br.gov.servicos`. Código de produção deve estar em um diretório separado do código de testes automatizados, mas ambos podem compartilhar o esquema de pacotes, afim de facilitar a escrita de testes unitários que dependem de membros com modificadores de acesso mais fechados.

### Convenções de Estilo de Codificação

O código escrito deverá estar em conformidade com as convenções e regras de estilo de codificação da sua respectiva linguagem ou plataforma de desenvolvimento.

## Modificadores de Acesso

Elementos no código devem apresentar modificadores de acesso condizentes com a menor permissão possível, visando manter o encapsulamento o mais estrito possível. Métodos e propriedades acessíveis apenas ao mesmo objeto deverão ser marcados como `private`, a objetos do mesmo pacote como `default`, e assim por diante. É recomendado evitar declarar métodos e propriedades como `public`, a menos que façam de fato parte da interface pública daquele objeto.

Uma exceção se faz para quando métodos ou propriedades devem ser acessados por frameworks ou bibliotecas. Nestes casos, a declaração deve acompanhar um comentário explicitando a dependência, como por exemplo `/* usado apenas pelo Spring */`.

## Testes Automatizados

Visando a clareza do código, a simplicidade no entendimento e a facilidade corretiva e evolutiva, a codificação do sistema deverá possuir testes automatizados descritivos de todas suas funcionalidades.

Excetuam-se testes automatizados para funcionalidades existentes em frameworks ou bibliotecas utilizados, a menos que necessários como forma de documentar o comportamento de uma funcionalidade específica dos mesmos, ou explicitar um defeito encontrado.

### Classes

Todas as classes devem possuir um teste unitário equivalente. Por convenção, este teste deve estar no mesmo pacote que a classe a ser testada, com o acréscimo da palavra `Test` em seu nome. Por exemplo, para `br.gov.servicos.busca.BuscaController` deve existir uma classe `br.gov.servicos.busca.BuscaControllerTest`.

Algumas classes podem possuir um teste de integração, onde são testadas interações com outras módulos do sistema. Nestes casos, convencionou-se sufiá-los com `IntegrationTest`, e seus nomes não precisam necessariamente estar atrelados a uma classe existente, mas sim a uma fatia de funcionalidade do sistema. Por exemplo, `br.gov.servicos.busca.BuscasComunsIntegrationTest`

### Métodos

Um ou mais testes unitários devem existir para todos os métodos públicos de uma classe, com nomes que detalham seu funcionamento e comportamento esperado para as entradas daquele teste. Por exemplo, se o método `busca(String termo)` retorna uma objeto `List<Servico>` com os serviços ordenados pela relevância em relação ao termo, deve haver um teste unitário chamado `buscaPorTermoRetornaListaComServicosOrdenadosPorRelevancia`, com as verificações pertinentes.

Encoraja-se fazer apenas uma verificação (`assert…` ou `verify…`) por teste unitário.

### Pacotes

Pacotes devem conter fatias _horizontais_ de funcionalidade, de uma ponta a outra no sistema. Por exemplo, `br.gov.servicos.servico` pode conter objetos de domínio e seus agregados (`Servico`, `LinhaDaVida`, `AreDeInteresse`), repositórios (`ServicoRepository`), controllers (`ServicoController`) e outras classes que colaboram entre si para oferecer a funcionalidade.

### Arquivos de Configuração

O uso de arquivos de configuração em texto deve ser minimizado ao máximo, e configuração declarativa e programática deve ser utilizada sempre que for uma opção.

Opções de linha de comando ou variáveis de ambiente utilizadas que possam alterar o comportamento do sistema devem ser documentadas _in loco_.

### Artefatos

A construção do código deve gerar artefatos únicos, com as seguintes características:

**Reproduzíveis**

Dada uma revisão do repositório (por exemplo, `9359cd`), duas máquinas diferentes devem construir exatamente o mesmo artefato, com exceção a _timestamps_ e informações sobre o ambiente de construção que podem ser incluídas no artefato em si. Todas as funcionalidades e características dos dois artefatos gerados devem ser idênticas.

**Atômicos**

Artefatos não podem ser construídos parcialmente. Caso sua construção falhe, eles devem ser imediatamente descartados pelo sistema de builds.

**Específicos**

Um artefato pode depender de uma plataforma (por exemplo, `Linux`), uma arquitetura (`x86-64`), uma distribuição (`CentOS`) e um conjunto de pacotes instalados no ambiente (`build-essentials`, `elasticsearch`, etc). Sempre que possível, as versões exatas destes devem ser especificadas e verificadas durante a instalação e execução do artefato.

É extremamente desaconselhável depender de versões flutuantes (`-SNAPSHOT`), e recomenda-se utilizar as versões mais recentes assim que possível, especialmente em caso de atualizações de segurança.

**Descritivos**

Deve ser possível inspecionar um artefato para verificar informações sobre ele. Nestas, devem estar contidos:

* Versão ou revisão
* Data de geração do artefato
* Dependências e suas versões
* Requisitos de plataforma, arquitetura, distribuição e as versões das mesmas
* Arquivos contidos, e suas permissões e assinaturas (MD5, SHA1, etc)
* Localização onde estes arquivos serão instalados

## Métricas de Qualidade

Todas as métricas de qualidade de código devem ser comprovadas através de relatórios gerados automaticamente por ferramentas especializadas de medição e monitoramento de código, e disponibilizados para cada artefato construído.

**Cobertura de Testes Unitários**

O indicador de cobertura de testes unitários busca identificar o percentual de código do sistema que é processado durante a execução do conjunto de testes unitários.

Espera-se que, para um artefato seja considerado aceitável:

* 100% dos testes unitários executem corretamente
* 100% dos testes unitários possuam validações (`verify…`, `assert…`, etc)
* 75% do código de produção (ou mais) seja executado

**Checagem de Bugs Prováveis**

Todo o código deve ser checado automaticamente por bugs prováveis, utilizando as ferramentas [PMD](http://pmd.sourceforge.net/) e [FindBugs](http://findbugs.sourceforge.net), ou o equivalente apropriado em outras linguagens.

Deve-se manter a configuração destas ferramentas em seus padrões, e violações dos limites estabelecidos devem impedir a construção de artefatos candidatos a homologação.

Caso haja alguma modificação nelas, é exigida documentação e racionalização explícita para cada caso, na configuração em si, ou se possível em um comentário no próprio código (acima da anotação `@SuppressWarnings`, por exemplo).

**Nível de Duplicação de Código**

O indicador de Nível de Duplicação de Código visa encontrar trechos de códigos replicados sem a utilização correta de técnicas de reuso. A prática de copiar trechos de códigos aumenta desnecessariamente o tamanho do projeto, amplia as possibilidades de defeitos e dificulta a manutenção.

Considerando o método de extração da métrica para todo o código implementado, agrupando o resultado em função dos principais elementos da arquitetura, tais como:

- Classes contendo a lógica de apresentação 
- Classes com código de integração entre sistemas
- Classes de acesso ao banco de dados
- Classes contendo a regra de negócio

Para encontrar trechos de código duplicados, é importante definir a quantidade de tokens que será considerado para análise, onde um token representa um conjunto de caracteres com real significado. Por exemplo, utilizando a linguagem de programação JAVA, o comando System.out.println seria quebrado em cinco tokens: "System" "." "out" "." "println".

Deve ser reestruturado todo trecho de código duplicado, considerando a quantidade de tokens igual a 100. Ou seja, é considerado um trecho de código duplicado quando esse possuir 100 ou mais tokens presentes em outro trecho.

**Documentação do Código**

Recomenda-se evitar a documentação do código através de comentários, e sim através de testes automatizados. Trechos de código comentados como maneira de evitar sua execução, ou blocos `if(false)` são extremamente desencorajados.

**Complexidade Ciclomática (CCN por Método)**

O indicador de CCN (Cyclomatic Complexity Number) por método visa calcular a complexidade dos métodos, considerando principalmente os desvios de execução no código, e identificar possíveis riscos e melhorias arquiteturais, tendo como objetivo principal manter os métodos com CCN inferior ao valor determinado, normalmente 20. Um valor elevado em um método pode indicar um mau design (excesso de responsabilidade) e o valor constantemente pequeno pode indicar excesso de delegação e, consequentemente, baixa coesão e dispersão.

Considerando-se somente os métodos das classes que implementam as regras de negócio e a integração, deve-se evitar totalmente CCN >= 51 e manter em até 5% do código na faixa de CCN > 20 e <= 50.

| CCN | Descrição |
| --- | --- |
| 1 até 10 | Código simples sem risco |
| 11 até 20 | Código normal |
| 21 até 50 | Código complexo, alto risco |
| >= 50 | Código não testável, risco altíssimo |

**Granularidade (LOC por Elemento Estrutural)**

O indicador LOC (Lines of Code) extrai informações sobre o tamanho de um elemento estrutural, permitindo apontar potenciais riscos ao projeto. No mínimo 95% das classes devem ter no máximo 1000 linhas de código e 95% dos métodos deve ter no máximo 100 linhas de código.

**Divisão em camadas - ausência de violações de camadas**

Esse indicador identifica as chamadas entre camadas não permitidas pela arquitetura. Em um sistema cuja arquitetura está baseada em camadas, duas regras fundamentais devem ser obedecidas: camadas superiores devem acessar somente a camada imediatamente inferior e uma camada inferior nunca deve acessar uma superior. Entende-se por violação de camadas, quando uma das duas regras citadas acima for infringida.

Além das violações de dependência, esse indicador também identifica as violações de responsabilidades das camadas. Por exemplo: classes na camada de negócio não devem montar trechos de código SQL (Structured Query Language), que é de responsabilidade da camada de persistência, ou métodos na camada de negócio não devem construir trechos de tela HTML (HyperText Markup Language), que é responsabilidade da camada de apresentação.

Deve-se evitar a ocorrência de violação de camadas no sistema, a não ser em casos excepcionais onde for justificada tal prática, por razões técnicas previamente acordadas.

## Reflection (Reflexão)

A utilização de reflexões em qualquer forma (_annotations_, _aspects_, etc.) deve ser detalhadamente comentada e deve estar bem descrita no documento de arquitetura.

Deve-se ter em mente a dificuldade natural de entendimento do funcionamento de reflexões, dado o poder de transformação de comportamento deste recurso. Por estes mesmos motivos, sua utilização deve ser restrita às fases de desenvolvimento do sistema e às fases posteriores de evolução, mas nunca devem ser criadas em ações corretivas sem uma devida análise arquitetural de forma a evitar um estado de caos no código fonte que impeça a evolução posterior do sistema.

# Confiabilidade e disponibilidade

## Prevenção de _Dirty Read_ (Leitura Suja)

O sistema deverá garantir que os dados persistidos sejam íntegros e consistentes de forma que transações que aconteçam em paralelo no mesmo conjunto de dados, possuam controles de concorrência para evitar estas inconsistências.

O sistema deverá garantir que consultas realizadas a conjuntos de dados que possuam semântica implícita para mais de um registro sejam sempre consistentes e íntegras, impossibilitando leitura de dados que foram inseridos por outra transação, mas ainda não foram _comitados_ (_dirty read_). Ou seja, t_oda transação __com o__ SGBD deve respeitar as regras ACID (Atomicidade, __C__ onsistência, Isolamento e Durabilidade)_

## Horário de disponibilidade

O sistema deverá estar disponível ininterruptamente. Paradas de manutenção deverão ser avisadas previamente com 3 dias de antecedência através de mensagem para os gestores do projeto.

## Tolerância à perda de dados

Nenhuma transação efetivada pode ser perdida. Ao ser apresentada uma mensagem de confirmação de transação efetivada com sucesso, deve-se garantir que nenhum dado foi perdido.

# Segurança

## Registro de Eventos

O sistema deverá possuir um mecanismo de registro de eventos durante a sua execução (_log_) que armazene estes eventos primariamente em um banco de dados criado para tal finalidade. O sistema deve guardar o log das operações com o SGBD e log do servidor web.

Senhas jamais devem ser registradas em quaisquer dos níveis de log (nem para depuração) e sob nenhuma condição. A garantia de "não repúdio", um dos princípios de segurança de sistemas, só será atingida através de medidas estritas como esta, inclusive.

### Níveis de Eventos (Evolução do projeto – Versão 2.0)

Os critérios para _log_ em duplicidade no banco de dados devem ser estabelecidos através de uma configuração do sistema pela escolha de um ou mais tipos de eventos registrados segundo os níveis descritos a seguir.

- Info: registro de execução de operações relacionadas ao negócio, eventos de login, etc. É o registro padrão de operações que deve conter informações valiosas para futura análise de comportamento em caso de falhas, como por exemplo o endereço IP da requisição, o login do usuário caso autenticado, o timestamp, etc.
- Warn: registro de eventos menos esperados que podem causar instabilidades na aplicação. Falhas previstas de concorrência de operações que demandaram um rollback, etc.
- Error: registro de eventos fatais à operação das aplicações e serviços, mas que não afetem a sua integridade e não envolvam perda de dados. Timeout de conexão com o banco de dados, dados ausentes em uma operação que deveria retornar dados mandatoriamente, serviço externo não acessível, etc.
- Fatal: qualquer erro que impeça que a aplicação registre corretamente informações e incorra em perda de informações ou comprometa a sua integridade. Impossibilidade de conexão com o banco de dados, falhas de escrita no log, etc. Implica na parada da aplicação e notificação imediata à administração do sistema para que o problema seja sanado e a aplicação retome seu funcionamento. Neste estado a aplicação não deve permitir nem mesmo o login de usuários por estar incapaz de registrar as tentativas de login e seus IPs de origem.
- Debug e Trace: estes níveis ficam a cargo da equipe de desenvolvimento, sendo mantida a regra do não registro de senhas sob qualquer condição. Em caso de necessidade de debug ou trace de senhas, estas devem ser feitas em memória através do debugger da própria plataforma, mas nunca no log.

### Campos de Log

O log deve conter os seguintes campos:

- Versão: Versão do layout para o Log (valor inicial 1) 
- Timestamp do início da operação;
- Nível de Evento (Conforme definido na seção anterior);
- Transação: obtido do componente de autorização caso o usuário esteja logado. Poderá ficar em branco, caso o usuário não esteja logado; ???
- Pacote e Método;
- ID do usuário (caso autenticado);
- Tempo de Execução (em milissegundos);
- Informações da Funcionalidade;
- Versão do sistema.
- (Módulo do sistema – se for dividido em módulos).
- Ticket do erro (Caso tenha ocorrido o erro).

Por exemplo, no caso do usuário salvar uma informação relativa a um Órgão/Entidade do sistema, o _log_ poderia conter as seguintes informações:

```
Versão: 1
Timestamp: 14:33:14,872000000
Nível de Evento: Info
Transação: MANORGENT
Pacote e Método: pacote.pacote.pacote.salvarOrgaoEntidade
ID do usuário: 05163217658
Tempo de Execução: 850
Informações da Funcionalidade: Codigo:99;Denominacao:Ministério do Planejamento, Orçamento e Gestão;Sigla:MP;
```

### Escolha de Funcionalidades de _Log_

As funcionalidades a serem logadas devem ser escolhidas de acordo com cada módulo ou sistema, obedecendo os critérios desejados e objetivando a visualização dos eventos significativos pelos usuários administradores do sistema.

A rigor, cada transação está eleita para ser logada.

## Tratamento de Erro (Evolução do projeto – Versão 2.0)

Este requisito visa principalmente a segurança do sistema. Em concordância com boas práticas de segurança, o usuário não deve ter acesso a nenhum detalhe interno da aplicação a fim de evitar que usuários mal intencionados explorem possíveis vulnerabilidades nos sistemas.

Seguindo esta abordagem, o sistema deve possuir um mecanismo de tratamento de erro orientado a "_ticket_" no qual todos os erros que não forem de negócio devem ser informados para o usuário ocultados por um número de _ticket_. Exemplo:

_"A aplicação detectou uma falha inesperada. Por favor, tente a operação novamente. Caso a falha persista, entre em contato com o suporte"._

O sistema deve disponibilizar opção para envio de mensagem na mesma tela que contém a mensagem do erro que deverá estar associado a um ticket.

O _ticket_ deve ter o seguinte formato:

[timestamp] + [primeiros 10 caracteres do session\_id]

O código da falha pode ser visto no _log_ do sistema e deve ser registrado de uma forma que viabilize a implementação de uma funcionalidade de administração (fora do escopo deste documento) que permita a consulta às falhas e às pilhas de exceção contendo todos os detalhes das mesmas incluindo a mensagem opcional do usuário. As falhas devem ser registradas de acordo com todas as práticas definidas neste documento.

Já as falhas de negócio retornam mensagens amigáveis, informando ao usuário o contexto da falha e a ação a ser tomada. A visualização das falhas de negócio são requisitos funcionais que ultrapassam o escopo deste documento e devem ser detalhadas nos documentos de requisitos funcionais de cada módulo ou sistema.

### Campos Persistidos em BD em Caso de Falha

- Timestamp;
- ID de sessão (jsessionid ou equivalente);
- IP da máquina de origem da requisição;
- IP do servidor que atendeu à requisição;
- ID do usuário (caso autenticado);
- Página URL;
- Tipo da exceção (exception type);
- Detalhe da exceção: stacktrace do erro;
- Transação: obtido do componente de autorização, caso o usuário esteja logado. Poderá ficar em branco, caso o usuário não esteja logado;
- Ticket: detalhado na seção 4.2;
- Mensagem do usuário (opcional).

Os dois primeiros campos acima são utilizados para formar o _ticket _descrito na seção anterior e são armazenados separados no _log_.

No momento da ocorrência do erro, os campos são persistidos e a mensagem é enviada para o usuário. Caso o usuário deseje enviar uma mensagem, o sistema deverá associá-la ao ticket oculto.

## Auditoria Mínima

Também em consonância com o princípio de segurança do não repúdio, as ações de homologação, publicação, desativação e exclusão de Serviços Públicos que contêm registros de informações em banco de dados devem conter os seguintes campos:

- IP de acesso do qual partiu a requisição;
- ID do usuário;
- timestamp da operação;
- Ação;
- Referência textual ao conteúdo publicado.

## Bloqueio de Acesso ao Log

O armazenamento do _log_ deverá ser feito de forma que impeça que um usuário mal-intencionado com acesso ao servidor de aplicação, edite ou apague o conteúdo do log. Desta forma, o usuário do servidor de aplicação que escreverá no log, deverá possuir apenas permissão para "_appendar_" o arquivo.

## Autenticação

### HTTPS

O sistema estará sujeito a acesso autorizado por usuários espalhados por todo o Brasil e exterior, em todas as esferas de governo. Por este motivo o acesso às partes restritas do sistema, incluindo interface de login, deverá ser feito exclusivamente através do protocolo HTTPS.

### Bloqueio de Acesso a URL sem Autenticação/Autorização

O sistema deverá bloquear qualquer acesso a URLs privadas da aplicação, sendo que no caso de usuário não autenticado, o mesmo deverá ser redirecionado para a página de login. No caso de usuários autenticados, mas que não possuem acesso à URL solicitada, o mesmo deverá ser redirecionado a uma página de erro, com a seguinte informação: "Acesso não autorizado a esta página."

Estas tentativas de acesso devem ser logadas com nível **WARN** para posterior análise, caso necessário. (Evolução do projeto – Versão 2.0)

### Níveis de autenticação (Evolução do projeto – Versão 2.0)

Haverá necessidade de autenticação, preferencialmente fazendo uso de mecanismo padronizado (SSO), que possa ser aproveitado para autenticação e autorização em outros sistemas. Deverão ser atribuídos níveis de autenticação dependendo das informações ou tipo de autenticação feita por parte do cidadão. Poderá haver alguns tipos de autenticação: cadastro simples validado com CPF, nome e data de nascimento do usuário na Receita Federal; autenticação digital; confirmação de autenticação por sistemas externos (Bancos, Correios, etc).

### Single Sign On/Single Sign Out(Evolução do projeto – Versão 2.0)

Deverá ser adotado o conceito de Single Sign On\Single Sign Out, com o intuito de centralizar a autenticação\logout do usuário no ambiente de forma transparente para o usuário, permitindo que, ao realizar a autenticação\logout em uma tela única de acesso seja também realizada sua autenticação\logout nas demais aplicações do governo. Será utilizado o Forge Rock/OpemAM alinhado com a DTI.

## Sigilo dos dados

Há crucial necessidade de garantir confidencialidade das informações dos cidadãos armazenadas e trafegadas entre o Guia e os órgãos.

## Políticas de segurança (Evolução do projeto – Versão 2.0)

Devem ser utilizados mecanismos para garantia de segurança nas informações como um todo, desde elaboração de políticas de renovação de senhas fortes como utilização de algoritmos de estado segundo Decreto nº 7845, Art 2º.

# Portabilidade

## Navegadores

Todas as funcionalidades do ambiente deverão funcionar corretamente nos principais navegadores para desktops e dispositivos móveis em suas versões mais recentes e nas versões posteriores à implantação do sistema. A codificação deve feita em **HTML 5**. Deve ser utilizada uma maneira mais segura de manter o código compatível nivelando o desenvolvimento pelos motores de renderização. Cada browser utiliza um motor de renderização que é responsável pelo processamento do código da página.

# Usabilidade

## Interface com usuário

Em termos de comportamento, padronização e consistência de componentes visuais, devem ser seguidas as orientações descritas em   
http://www.secom.gov.br/orientacoes-gerais#b\_start=0&c6=%2Fclientes%2Fsecom%2Fsecom%2Forientacoes-gerais%2Fcomunicacao-digital&c1=Manuais.

## Padrões do governo

Deverão ser seguidas as recomendações descritas na última versão da Cartilha de Usabilidade do Governo Eletrônico disponível em [http://www.governoeletronico.gov.br/acoes-e-projetos/padroes-brasil-e-gov/cartilha-de-](http://www.governoeletronico.gov.br/acoes-e-projetos/padroes-brasil-e-gov/cartilha-de-usabilidade) [usabilidade](http://www.governoeletronico.gov.br/acoes-e-projetos/padroes-brasil-e-gov/cartilha-de-usabilidade)

## Prevenção de erros do usuário

A descrição das mensagens de erro deve ser clara para o usuário e orientá-lo sobre como resolver o problema, ou seja, ao notificar um erro deve-se mostrar como resolvê-lo, detalhando o que precisa ser realizado para a operação ocorrer com sucesso.

Exemplos:

- O CPF precisa ser um valor válido.
- O título do serviço não pode conter mais de XXX caracteres.

O sistema deve solicitar a confirmação de ações mais críticas, como por exemplo, exclusões.

# Acessibilidade

O ambiente deve estar aderente às recomendações da última versão disponível do Modelo de Acessibilidade de Governo Eletrônico e-MAG disponíveis em ( [http://www.governoeletronico.gov.br/acoes-e-projetos/e-MAG](http://www.governoeletronico.gov.br/acoes-e-projetos/e-MAG)). O sítio deve ter percentual de 100% segundo métrica desenvolvida pela SLTI. No AccessMonitor ( [www.acessibilidade.gov.pt/accessmonitor/](http://www.acessibilidade.gov.pt/accessmonitor/)) deve obter média máxima. A conformidade com os padrões será verificada pela equipe de consultores do SISP.

### Internacionalização (Evolução do projeto – Versão 2.0)

Pelo fato de a solução ser utilizada por usuários brasileiros e estrangeiros, deve estar prevista a incorporação de recursos de internacionalização ao sistema para futuras traduções.

### Solicitação assíncrona de informação

A atualização de um objeto específico na página deve apenas carregar o objeto solicitado e não a página inteira. Como exemplo, na criação de listas com campos complexos (canais de prestação do serviço, por exemplo) dentro do serviço, a página de serviço não deve ser recarregada a cada inserção de elemento da lista. Também pode-se citar como exemplo o acesso aos itens de menu de opções do sistema.

## Padrões web em Governo Eletrônico

O ambiente deve seguir as normas estabelecidas nos Padrões Web de Governo Eletrônico (e-PWG) disponíveis em http://www.governoeletronico.gov.br/acoes-e-projetos/padroes-brasil-e-gov.

## Design e resolução

Utilizar design responsivo e resoluçãoconforme orientações em http://www.secom.gov.br/orientacoes-gerais/comunicacao-digital/guia-de-estilo-identidade-padrao-comunicacao-digital-mai201409.pdf

# Qualidade Técnica e Arquitetural da Solução

Com o objetivo de garantir um alto padrão de qualidade para o código-fonte do sistema, e dessa forma minimizar os custos com correções e evoluções, aumentando a produtividade efetiva dos desenvolvedores, um conjunto de indicadores deve ser estabelecido e acompanhado com esse intuito.

As métricas devem favorecer os princípios abaixo:

- Eliminar duplicação de código-fonte
- Restringir a complexidade local do código-fonte
- Garantir que as unidades do código-fonte foram testadas suficientemente
- Promover a clareza do código-fonte
- Reduzir o acoplamento entre classes

A seguir a proposta de métricas a serem mensuradas e respeitadas para o projeto:

- Todos os testes de unidade devem concluir com sucessoMeta: 100 % de sucesso nos testes unitários;
- Medir a porcentagem de linhas de código que devem estar cobertas por testes de unidade. Somente classes dos pacotes de negócio e fachada são consideradas.Meta: 75 % de cobertura de testes unitários;
- O número de trechos de código duplicados, considerando cem ou mais tokens, mesmo entre módulos diferentes deve ser igual a zero.Meta: Nível de duplicação de código: 0;
- Todas as classes dos pacotes de fachada, negócio e persistência devem possuir documentação javadoc para si e para todos os seus métodos públicos e protegidos, excetuando-se os getters,setters e construtores. Meta: Documentação de código: 100 %;
- Porcentagem de classes que podem ter mais de mil linhas.Meta: Percentual de classes com número de linhas > 1000: até 5 %;
- Porcentagem de métodosque podem ter mais de cem linhas.Meta: Percentual de métodos com número de linhas > 100: até 5 %;
- O percentual de métodos com complexidade ciclomática superior a 20 e inferior a 50 deve ser menor ou igual a 5%.Meta: Complexidade Ciclomática > 20 e < 50: < 5%;
- Nenhum método pode ter complexidade ciclomática superior a 50.Meta: Complexidade Ciclomática > 50: 0%;
- Nenhuma violação de camadas é permitida. Um objeto em uma camada somente pode acessar objetos nessa mesma camada ou na camada imediatamente inferior.Meta: Violação de Camadas: 0;

# Serviços de Integração (Evolução do projeto – Versão 2.0)

_(a ser definido)_

# Tecnologia e infraestrutura

## Ambientes

Desenvolvimento, homologação e produção.

## Dedicação da infraestrutura

A contratada tem liberdade para definir a infraestrutura mantendo o compromisso de atender aos níveis de serviço definidos.

## Hardware e topologia

A contratada tem liberdade para definir a infraestrutura mantendo o compromisso de atender aos níveis de serviço definidos.

# Tecnologia e infraestrutura

## Linguagem de programação e Framework de desenvolvimento

A contratada tem liberdade para definir a tecnologia mantendo o compromisso de atender aos níveis de serviço definidos, desde que todo o sistema seja construído utilizando plataforma livre e considerando os princípios da administração pública, inclusive o da economicidade, propondo uma solução durável e flexível para evolução a custos razoáveis ao longo do tempo.

## Componentes de SW

Devem ser considerados componentes proprietários ou de mercado que sejam comuns ao desenvolvimento de todo o sistema. Tais componentes serão armazenados no Repositório de Componentes Reutilizáveis do Projeto e devem trazer informações acerca da sua aplicabilidade, requisitos e restrições arquiteturais, configurações, interfaces e exemplos de uso.

Estes componentes, que serão utilizados na implementação dos mecanismos arquiteturais listados abaixo, devem ser altamente coesos e fracamente acoplados, sendo construídos com o foco em reuso.

## Mecanismos arquiteturais

Sugere-se que o Repositório de Componentes Reutilizáveis documente, no mínimo, os seguintes componentes e mecanismos arquiteturais:

- Validação de dados (tanto do lado Cliente como Servidor);
- Exibição de mensagens (erro, atenção, sucesso, etc.);
- Integração com sistemas externos e legados;
- Paginação de dados;
- Geração de relatórios;
- Controle de transações;
- Tratamento de exceções;
- Parametrizações do sistema;
- Controle de concorrência (deve ser tratado no Banco de Dados e, em função disso, a aplicação deve nascer clusterizável);
- Habilitação e Autorização (segurança);
- Componente de persistência;
- Componentes visuais (grids, tabelas, menus, formulários, etc);
- Componentes de pesquisa;
- Monitoramento, log e cache de objetos.

Além destes mecanismos gerais, é de responsabilidade da contratada, junto à sua equipe de Arquitetura de Sistemas, identificar componentes reutilizáveis específicos para o negócio sob modelagem.

## Conformidade com padrões

Para o desenvolvimento e construção do sistema, os principais padrões a serem considerados são:

- Padrão arquitetural: A ser definido pela contratada. Cenários arquiteturalmente relevantes podem ser detalhados em um Documento de Arquitetura específico.
- Padrão de Programação e Convenções de Escrita de Código: Dependente da linguagem de programação.
- Padrões de Mecanismos Arquiteturais e Componentes de Software: a serem definidos no Repositório de Componentes Reutilizáveis.
- Padrão de Interoperabilidade entre sistemas: protocolos padrão W3C para Web Services.

# Desempenho

## Quantidade de acesso diário

Atualmente a quantidade de visitas diárias está em torno de 60.000. A previsão é que aumente a cada ano.

## Tempo de resposta de funcionalidade

O tempo de resposta equivale ao tempo (em segundos) entre a entrada de uma requisição (botão, link, etc.) nos servidores do Guia de Serviços e sua respectiva saída, incluindo o tempo de interoperação com sistemas externos e o tempo de resposta da intranet. Este tempo equivale, desta forma, ao "tempo de host".

Não serão contemplados no cálculo do tempo de resposta o tempo de transferência dos dados na internet e o processamento que o software cliente (navegador) realiza (como a renderização de uma página).

A resposta esperada sempre considera o método do percentil 90%. Ou seja, o tempo de resposta especificado equivale ao tempo de resposta máximo para 90% das requisições realizadas aleatoriamente pelos usuários.

As requisições não consideradas como funcionalidades (como requisições AJAX, abertura de menu, paginação, etc.) terão tempo de resposta máximo de 2 (dois) segundos.

As funcionalidades serão classificadas sob duas perspectivas:

- De importância para o negócio: a classificação de importância para o negócio leva em consideração a importância da funcionalidade para o negócio. Podem ser classificadas como críticas, importantes e de menor uso. Essa classificação é definida pelo cliente;
- De complexidade de processamento: a classificação de complexidade de processamento leva em consideração vários fatores que devem ser analisados para avaliação do processamento. Podem ser classificadas como de complexidade baixa, média, alta e altíssima. Essa classificação é definida pela equipe de desenvolvimento.

É importante destacar que somente os passos mais importantes devem ser classificados. O objetivo é que, para agilizar, somente os passos mais importantes e significativos sejam mensurados e avaliados conforme critérios de aceites acordados.

Além disso, após a definição e refinamento dos cenários (definição dos passos), os tempos de respostas e critérios de aceites sejam definidos, levando em consideração as classificações citadas. (falta definir os cenários e tempos)

## Cenários de Testes de Desempenho

Os testes dos requisitos não funcionais da aplicação, propostos nesse documento, serão realizados considerando cenários de testes que serão definidos, de forma conjunta, entre cliente e equipe de desenvolvimento.

Os cenários de testes propostos devem representar os fluxos das funcionalidades mais importantes/significativas do sistema. Dessa maneira, é garantido que estas funções serão testadas e atenderão aos critérios definidos nesse documento.

Cada cenário deve conter o seguinte conjunto de informações:

- Objetivo: descreve, de forma resumida, o objetivo do teste realizado pelo cenário;
- Critérios de Sucesso: Os critérios de aceite do teste;
- Parâmetros de Teste: Descreve qual a massa de dados utilizada pelo teste. Define tanto os perfis dos usuários quanto as informações dos objetos que serão consultados, criados, alterados ou excluídos pela execução do cenário;
- Procedimentos de Teste: Define a quantidade de usuários (requisições) e parâmetros de simultaneidade e iterações. Além disso, define os passos do cenário.
- Evidências a serem coletadas: As informações coletadas após a execução do teste;
- Melhorias propostas/implementadas;

## Sazonalidade

Os picos ocorrem nos períodos dos serviços mais utilizados (Declaração de Imposto de Renda, Inscrições em programas do governo tais como ENEM, Prouni, etc).

## Configuração do servidor Web

- O servidor Web deve comprimir os recursos com ferramentas como Gzip e/ou Deflate;
- O servidor deve definir a validade máxima do cache para os recursos estáticos. Para os recursos que são relacionados a dados privados, não devem ser permitidos o cache;
- Obter no mínimo de 85 pontos no PageSpeed Insights no mobile e no desktop e nenhum erro (vermelho) relacionado ao próprio servidor.https://developers.google.com/speed/pagespeed/insights/ 

# Documentação do sistema

## Documentos de Engenharia de Software

A documentação de Engenharia de software é o conjunto de documentos textuais e visuais (diagramas) utilizados para definir e explicar os componentes, as APIs, estruturas de dados e algoritmos presentes na solução.

Documentos de Projeto/Arquitetura

Os documentos de especificação de projeto do sistema (diagramas UML) integrada ao código. **Não há a intenção de avaliar previamente tais artefatos para fins de validação**.

Entretanto, para fins de gestão do conhecimento e controle do ciclo de vida do produto, quando aplicável, devem ser entregues os seguintes documentos, em períodos a serem definidos **:**

- Modelo de Classes;
- Modelo de Dados;
- Outros diagramas UML (Statemachine, Atividades, Componentes, etc.), quando necessários, para esclarecer partes complexas do sistema ou quando forem requeridos pelo cliente, de maneira previamente acordada.

Obs: podem ser geradas versões preliminares destes documentos durante a execução das iterações do projeto, com objetivo de ser avaliadas antecipadamente pelo Ministério do Planejamento – MP.

Demais artefatos de documentação podem ser solicitados durante a execução do projeto, em comum acordo entre Contratada e MP.

Documentação do código

**É obrigatório documentar o código das principais classes de serviços (negócio) e de integração**** bem como do código dos scripts do lado cliente**. Os implementadores devem fazer uso dos recursos de documentação integrados à plataforma ou à linguagem (por exemplo, Javadoc), e gerar os relatórios apropriados. Deve contemplar:

- Clareza, objetividade, ortografia e boa apresentação;
- Padronização dos produtos conforme os modelos definidos;
- A documentação elaborada e fornecida terá que estar em língua portuguesa, com exceção de relatórios ou outras saídas geradas por ferramenta automatizada, que poderão estar em língua inglesa.
- O material técnico e administrativo (artefatos do projeto) deve ser armazenado em repositório com controle de versionamento e outras funcionalidades que garantam o uso concorrente e colaborativo dos seus arquivos.

## Documentos de infraestrutura e produção

Quando aplicável, devem ser entregues os seguintes documentos, em tempos definidos pela contratante **:**

- Documentação de Hardware (HW) e Software (SW) / Configuração de Hardware e Software (CHS);
- Memória de Cálculo para Atendimento dos Níveis de Serviço (NS) e das Características de Serviço (CNS);
- Plano de Capacidade;
- Testes de Capacidade e Desempenho (Plano / Execução / Resultados);
- Procedimentos e Recursos para Monitoração / Auditoria / Acompanhamento;
- Plano de Implantação / Plano de Contingência;
- Análise de Segurança;

Obs: podem ser geradas versões preliminares destes documentos durante a execução das iterações do projeto, com objetivo de serem avaliadas antecipadamente pelo MP.

# Restrições tecnológicas

## Gerenciamento de banco de dados

A critério da contratada, entre os gratuitos, consolidados no mercado e de código aberto, ex: PostgreSQL, MySQL.

## Applets

Não utilizar tecnologia ActiveX. O uso de applets restringe-se a situações específicas envolvendo requisitos de segurança que manipulam certificado digital ou outro recurso semelhante.

# Integridade dos dados

## Lógica de validação

Deve-se evitar a duplicidade da lógica de validação de regras de negócio:

- a validação deve ser implementada no lado do servidor ("server-side"). 
- se houver necessidade de validar um campo antes do envio do formulário como um todo, este deve ser validado através de uma chamada Ajax.
- qualquer validação que for realizada na camada de apresentação deverá ser refeita na camada de negócio para se garantir a integridade das informações a serem armazenadas no banco de dados.
- são permitidas validações simples de campos geradas via javascript por componentes JSF (Ex.: validação de tipos de campos, numérico, data, faixa de valores).

## Exclusão lógica

A aplicação não deve executar operações de DELETE no banco de dados (exclusão física), sendo que as exclusões serão lógicas, baseadas em um indicador de exclusão. A única exceção é referente aos serviços públicos que não foram homologados nenhuma vez.

As consultas do sistema não devem retornar os registros excluídos logicamente. As regras de negócio, porém, podem explicitamente definir um comportamento diverso destes.

## Normalização de Dados

A aplicação deverá criar as tabelas e armazenar os dados da forma mais normalizada possível. Quando a normalização dos dados prejudicar substancialmente a performance do sistema deverá ser feita a desnormalização, com o aval da equipe requisitante. A normalização deverá ser feita, no mínimo, até a 3 FN.

1_Dirty Read_, ou leitura suja, ocorre quando uma transação lê os dados que já foram escritos por outra transação, mas não foram _comitados_ ainda.
