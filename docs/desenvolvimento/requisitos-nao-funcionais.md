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

**Documentação do Código**

Recomenda-se evitar a documentação do código através de comentários, e sim através de testes automatizados. Trechos de código comentados como maneira de evitar sua execução, ou blocos `if(false)` são extremamente desencorajados.

## Reflection (Reflexão)

A utilização de reflexões em qualquer forma (_annotations_, _aspects_, etc.) deve ser detalhadamente comentada e deve estar bem descrita no documento de arquitetura, ou na documentação do framework/biblioteca que a provê.

# Confiabilidade e Disponibilidade

## Dos Dados

O sistema deverá garantir que os dados persistidos sejam íntegros e consistentes de forma que transações que aconteçam em paralelo no mesmo conjunto de dados, possuam controles de concorrência para evitar estas inconsistências.

O sistema deverá garantir que consultas realizadas a conjuntos de dados que possuam semântica implícita para mais de um registro sejam sempre consistentes e íntegras, impossibilitando leitura de dados que foram inseridos por outra transação.

## Horário de Disponibilidade

O sistema deverá estar disponível ininterruptamente. Paradas de manutenção deverão ser avisadas previamente com 3 dias de antecedência através de mensagem para os gestores do projeto.

## Tolerância à Perda de Dados

Nenhuma transação efetivada pode ser perdida. Ao ser apresentada uma mensagem de confirmação de transação efetivada com sucesso, deve-se garantir que nenhum dado foi perdido.

# Segurança

## Registro de Eventos

O sistema deverá possuir um mecanismo de registro de eventos durante a sua execução (_log_) que armazene estes eventos primariamente em um banco de dados criado para tal finalidade. O sistema deve guardar o log das operações com o SGBD e log do servidor web.

Senhas ou outros dados secretos jamais devem ser registrados em quaisquer dos níveis de log e sob nenhuma condição.

Os níveis de log devem seguir os seguintes critérios:

* `DEBUG` e `TRACE`: quaisquer informações relevantes ao desenvolvimento da aplicação. Podem estar habilitados durante homologação e produção, mas é necessário considerar seu uso demasiado de memória caso a aplicação enfrente períodos de alta demanda.
* `INFO`: registro de execução e eventos de operações relacionadas ao negócio. Due deve conter informações valiosas para futura análise de comportamento: endereço IP da requisição, identificador da transação ou requisição, _timestamp_, etc.
* `WARN`: registro de eventos menos esperados que podem causar instabilidades na aplicação: falha ao se comunicar com um serviço externo que pode ser tentada novamente, por exemplo.
* `ERROR`: registro de eventos fatais à operação das aplicações e serviços, mas que não afetem a sua integridade e não envolvam perda de dados: timeout de conexão com o banco de dados, dados ausentes em uma operação que deveria retornar dados mandatoriamente, serviço externo não acessível, etc.
* `FATAL`: qualquer erro que impeça que a aplicação registre corretamente informações e incorra em perda de informações ou comprometa a sua integridade. Implica na parada da aplicação e notificação imediata à administração do sistema para que o problema seja sanado e a aplicação retome seu funcionamento. Neste estado a aplicação não deve permitir o acesso de usuários.

### Campos de Log

O log deve conter os seguintes campos:

- Timestamp do início da operação
- Tempo de execução, em milissegundos
- Nível de evento (conforme definido na seção anterior)
- Identificação do ambiente (IP ou nome da máquina)
- Pacote, classe e, se possível, método
- Transação: identificador único de transação em formato [UUID](http://en.wikipedia.org/wiki/Universally_unique_identifier)
- ID do usuário, caso autenticado
- Informações da funcionalidade
- Ticket do erro, caso tenha ocorrido um erro

Por exemplo, no caso do usuário executar uma busca que não obeteve resultados, mas ocorreu com sucesso:

```
- Timestamp: "1424985454"
- Tempo de execução: "4"
- Nível de evento: "INFO"
- Identificação do ambiente: "vm01"
- Pacote, classe e, se possível, método: "br.gov.servicos.busca.BuscaController#busca(String)"
- Transação: "08644dd6-bdfd-11e4-8eab-7831c1d2da14"
- ID do usuário: "null"
- Informações da funcionalidade: "Busca por '' não obteve resultados"
- Ticket do erro: "null"
```

### Escolha de Funcionalidades de Log

As funcionalidades a serem logadas devem ser escolhidas de acordo com cada módulo ou sistema, obedecendo os critérios desejados e objetivando a visualização dos eventos significativos pelos usuários administradores do sistema.

A rigor, cada transação está eleita para ser logada.

## Tratamento de Erros

Este requisito visa principalmente a segurança do sistema. O usuário não deve ter acesso a nenhum detalhe interno da aplicação, como nomes de classes ou dependências utilizadas.

O sistema deve possuir um mecanismo de tratamento de erro orientado a _tickets_, no qual todas as mensagens de erro exibidas ao usuário (classes de erro HTTP acima de 400) devem ser informados para o usuário ocultados por um número de _ticket_. Por exemplo:

```
A aplicação detectou uma falha inesperada. Por favor, tente a operação novamente. Caso a falha persista, entre em contato com o suporte utilizando o identificador *7831c1d2da14*.
```

O _ticket_ deve utilizar o último bloco de dígitos do identificador da transação, com 12 caracteres hexadecimais. Todas as mensagens de log relacionadas à ação do usuário na mesma requisição HTTP devem estar atreladas ao mesmo UUID, o que facilita a busca e depuração.

Já as falhas de negócio (erros de validação, por exemplo) devem retornar mensagens amigáveis, informando ao usuário o contexto da falha e a ação a ser tomada. Estas falhas não devem produzir erros de nível `WARN`, `ERROR` ou `FATAL`, já que não há o que o administrador do sistema possa fazer para remediá-las.

## Auditoria Mínima

Quaisquer transações de escrita (modificação ou remoção) iniciadas por um usuário devem conter os seguintes detalhes no log:

- IP de acesso do qual partiu a requisição
- ID do usuário
- Ação
- Referência textual ao conteúdo alterado

## Segurança de Acesso ao Log

Logs deverão ser armazenados de forma que um usuário mal-intencionado que consiga acesso a eles não possa corrompê-los. Recomenda-se fortemente não permitir que logs sejam alterados por nenhum usuário do sistema, exceto por um processo de rotação que move registros de logs antigos para outro local, afim de liberar espaço em disco.

## Autenticação

### HTTPS

O acesso às partes restritas do sistema deverá ser feito exclusivamente através do protocolo HTTPS.

### Bloqueio de Acesso a URL sem Autenticação/Autorização

O sistema deverá bloquear qualquer acesso a URLs privadas da aplicação, sendo que no caso de usuário não autenticado, o mesmo deverá ser redirecionado para a página de login.

Estas tentativas de acesso devem ser logadas com nível `WARN` para posterior análise.

# Sigilo dos Dados

Há crucial necessidade de garantir confidencialidade das informações dos cidadãos armazenadas e trafegadas entre o Guia e os órgãos.

## Políticas de Segurança

Recomenda-se a elaboração políticas de criação e renovação de senhas fortes.

Recomenda-se a criptografia de dados em descanso, utilizando algoritmos padrão de mercado.

# Portabilidade

## Navegadores

Todas as funcionalidades deverão funcionar corretamente nos principais navegadores para desktops e dispositivos móveis em suas versões mais recentes e na versão posterior a esta (n-1). A codificação deve feita em **HTML 5**.

# Usabilidade

## Interface com Usuário

Em termos de comportamento, padronização e consistência de componentes visuais, devem ser seguidas as orientações descritas no [Guia de Estilo do Portal Institucional Padrão](http://www.secom.gov.br/orientacoes-gerais/comunicacao-digital/guia-de-estilo-identidade-padrao-comunicacao-digital-fev2015.pdf), versão 5.0 ou a mais recente disponível.

## Padrões do Governo

Deverão ser seguidas as recomendações descritas na última versão da [Cartilha de Usabilidade do Governo Eletrônico](http://www.governoeletronico.gov.br/acoes-e-projetos/padroes-brasil-e-gov/cartilha-de-usabilidade).

# Acessibilidade

O ambiente deve estar aderente às recomendações da última versão disponível do [Modelo de Acessibilidade de Governo Eletrônico (e-MAG)](http://www.governoeletronico.gov.br/acoes-e-projetos/e-MAG). O sítio deve ter percentual de 100% segundo métrica desenvolvida pela SLTI. No [AccessMonitor](http://www.acessibilidade.gov.pt/accessmonitor/), deve obter média máxima. A conformidade com os padrões será verificada pela equipe de consultores do SISP.

### Internacionalização

Pelo fato de a solução ser utilizada por usuários brasileiros e estrangeiros, deve estar prevista a incorporação de recursos de internacionalização ao sistema para futuras traduções.

## Padrões Web em Governo Eletrônico

O ambiente deve seguir as normas estabelecidas nos [Padrões Web de Governo Eletrônico (e-PWG)](http://www.governoeletronico.gov.br/acoes-e-projetos/padroes-brasil-e-gov).

## Design e Resolução

Utilizar design responsivo e resolução conforme [orientações](http://www.secom.gov.br/orientacoes-gerais/comunicacao-digital/guia-de-estilo-identidade-padrao-comunicacao-digital-mai201409.pdf).

# Tecnologia e infraestrutura

## Ambientes

Desenvolvimento, homologação e produção.

## Dedicação da Infraestrutura

A contratada tem liberdade para definir a infraestrutura mantendo o compromisso de atender aos níveis de serviço definidos.

## Hardware e Topologia

A contratada tem liberdade para definir a infraestrutura mantendo o compromisso de atender aos níveis de serviço definidos.

# Tecnologia e Infraestrutura

## Linguagem de Programação e Frameworks de Desenvolvimento

A contratada tem liberdade para definir a tecnologia mantendo o compromisso de atender aos níveis de serviço definidos, desde que todo o sistema seja construído utilizando plataformas de software livre e considerando os princípios da administração pública, inclusive o da economicidade, propondo uma solução durável e flexível para evolução a custos razoáveis ao longo do tempo.

# Desempenho

## Quantidade de Acessos Diários

Atualmente a quantidade de visitas diárias está em torno de 60.000. A previsão é que aumente a cada ano.

## Tempo de Resposta

O tempo de resposta equivale ao tempo (em milisegundos) entre o último byte da entrada de uma requisição nos servidores do Guia de Serviços e sua respectiva saída até o último byte ser enviado para o cliente.

Este tempo deve ser monitorado cotinuamente e o desvio padrão não deve ultrapassar 50%, exceto em casos de interrupção na rede ou outras circunstâncias externas.

## Cenários de Testes de Desempenho

Os testes dos requisitos não funcionais da aplicação, propostos nesse documento, serão realizados considerando cenários de testes que serão definidos, de forma conjunta, entre cliente e equipe de desenvolvimento.

Os cenários de testes propostos devem representar os fluxos das funcionalidades mais importantes/significativas do sistema. Dessa maneira, é garantido que estas funções serão testadas e atenderão aos critérios definidos nesse documento.

Cada cenário deve conter o seguinte conjunto de informações:

- Objetivo: descreve, de forma resumida, o objetivo do teste realizado pelo cenário
- Critérios de sucesso
- Parâmetros de teste
  - Massa de dados
  - Perfis dos usuários
  - Informações sobre objetos que serão consultados, criados, alterados ou excluídos pela execução do cenário
- Procedimentos de teste
  - Quantidade de usuários (requisições)
  - Parâmetros de simultaneidade e iterações
  - Passos do cenário
- Evidências a serem coletadas
- Melhorias propostas/implementadas

## Sazonalidade

Os picos ocorrem nos períodos dos serviços mais utilizados (Declaração de Imposto de Renda, Inscrições em programas do governo tais como ENEM, Prouni, etc).

## Configuração do servidor Web

Para requisições compatíveis, o servidor web deve utilizar compressão gzip e/ou deflate.

O servidor deve definir a validade máxima do cache para os recursos estáticos. Para os recursos que são relacionados a dados privados, não deve ser permitido mantê-los em cache.

Obter no mínimo 85 pontos no [PageSpeed Insights](https://developers.google.com/speed/pagespeed/insights/), tanto em dispositívos móveis quanto desktops, e nenhum erro (vermelho) relacionado ao próprio servidor.

# Documentação do sistema

## Documentos de Projeto/Arquitetura

Diagramas compartilhados durante o desenvolvimento do projeto devem ser mantidos em um local acessível e organizado de forma a facilitar o acesso posterior. Caso seja utilizada uma lista de discussão para tal, é necessário que ela tenha uma ferramentas de busca adequada.

Demais artefatos de documentação podem ser solicitados durante a execução do projeto.

É recomendável o uso de [UML As Sketch](http://martinfowler.com/bliki/UmlAsSketch.html), em preferência à geração de documentação extensiva, porém de baixo valor.

## Documentação do código

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
