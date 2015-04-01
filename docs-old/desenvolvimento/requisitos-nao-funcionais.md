# Objetivo

Define requisitos não-funcionais e de comportamento do Guia de Serviços Públicos do Governo Federal.



## Organização do Código

O código Java deve ser organizado em pacotes, a partir de `br.gov.servicos`.

Código de produção deve estar em um diretório separado do código de testes automatizados, mas ambos podem compartilhar o esquema de pacotes.

### Convenções de Estilo de Codificação

Utilizar as convenções e regras de estilo de codificação da respectiva linguagem.

### Modificadores de Acesso

Elementos no código devem apresentar modificadores de acesso condizentes com a menor permissão possível.

Métodos e propriedades acessíveis apenas ao mesmo objeto deverão ser marcados como `private`, a objetos do mesmo pacote como `default`, e assim por diante.

É recomendado evitar declarar métodos e propriedades como `public`, a menos que façam de fato parte da interface pública daquele objeto.

Uma exceção se faz para quando métodos ou propriedades devem ser acessados por frameworks ou bibliotecas. Nestes casos, a declaração deve acompanhar um comentário explicitando a dependência, como por exemplo `/* usado apenas pelo Spring */`.

## Testes Automatizados

O sistema deverá possuir testes automatizados descritivos de todas suas funcionalidades.

Excetuam-se testes automatizados para funcionalidades existentes em frameworks ou bibliotecas utilizados, a menos que necessários como forma de documentar o comportamento de uma funcionalidade específica dos mesmos, ou explicitar um defeito encontrado.

### Classes

Todas as classes devem possuir um teste unitário equivalente.

Por convenção, este teste deve estar no mesmo pacote que a classe a ser testada, com o acréscimo da palavra `Test` em seu nome. Por exemplo, para `br.gov.servicos.busca.BuscaController` deve existir uma classe `br.gov.servicos.busca.BuscaControllerTest`.

Algumas classes podem possuir um teste de integração, onde são testadas interações com outras partes do sistema. Nestes casos, convencionou-se sufixá-los com `IntegrationTest`. Seus nomes não precisam estar atrelados a uma classe existente. Por exemplo, `br.gov.servicos.busca.BuscasComunsIntegrationTest`.

### Métodos

Um ou mais testes unitários devem existir para todos os métodos públicos de uma classe, com nomes que detalham seu funcionamento e comportamento esperado para as entradas daquele teste.

Por exemplo, se o método `busca(String termo)` retorna uma objeto `List<Servico>` com os serviços ordenados pela relevância em relação ao termo, deve haver um teste unitário chamado `buscaPorTermoRetornaListaComServicosOrdenadosPorRelevancia`, com as verificações pertinentes.

Encoraja-se fazer apenas uma verificação (`assert…` ou `verify…`) por teste unitário.

### Pacotes

Pacotes devem conter fatias _horizontais_ de funcionalidade, de uma ponta a outra no sistema.

Por exemplo, `br.gov.servicos.servico` pode conter objetos de domínio e seus agregados (`Servico`, `LinhaDaVida`, `AreDeInteresse`), repositórios (`ServicoRepository`), controllers (`ServicoController`) e outras classes que colaboram entre si para oferecer a funcionalidade.

## Arquivos de Configuração

O uso de arquivos de configuração em texto deve ser minimizado ao máximo, e configuração declarativa e programática deve ser utilizada sempre que for uma opção.

Opções de linha de comando ou variáveis de ambiente utilizadas que possam alterar o comportamento do sistema devem ser documentadas _in loco_.

## Artefatos

A construção do código deve gerar artefatos únicos, com as seguintes características:

### Reproduzíveis

Dada uma revisão do repositório (por exemplo, `9359cd`), duas máquinas diferentes devem construir exatamente o mesmo artefato, com exceção a _timestamps_ e informações sobre o ambiente de construção que podem ser incluídas no artefato em si. Todas as funcionalidades e características dos dois artefatos gerados devem ser idênticas.

### Atômicos

Artefatos não podem ser construídos parcialmente. Caso sua construção falhe, eles devem ser imediatamente descartados pelo sistema de builds.

### Específicos

Um artefato pode depender de uma plataforma (por exemplo, `Linux`), uma arquitetura (`x86-64`), uma distribuição (`CentOS`) e um conjunto de pacotes instalados no ambiente (`build-essentials`, `elasticsearch`, etc). Sempre que possível, as versões exatas destes devem ser especificadas e verificadas durante a instalação e execução do artefato.

É extremamente desaconselhável depender de versões flutuantes (`-SNAPSHOT`), e recomenda-se utilizar as versões mais recentes assim que possível, especialmente em caso de atualizações de segurança.

### Descritivos

Deve ser possível inspecionar um artefato para verificar informações sobre ele. Nestas, devem estar contidos:

* Versão ou revisão
* Data de geração do artefato
* Dependências e suas versões
* Requisitos de plataforma, arquitetura, distribuição e as versões das mesmas
* Arquivos contidos, e suas permissões e assinaturas (MD5, SHA1, etc)
* Localização onde estes arquivos serão instalados

## Métricas de Qualidade

Todas as métricas de qualidade de código devem ser comprovadas através de relatórios gerados automaticamente por ferramentas especializadas de medição e monitoramento de código, e disponibilizados para cada artefato construído.

### Cobertura de Testes Unitários

O indicador de cobertura de testes unitários busca identificar o percentual de código do sistema que é processado durante a execução do conjunto de testes unitários.

Espera-se que, para um artefato seja considerado aceitável:

* 100% dos testes unitários executem corretamente
* 100% dos testes unitários possuam validações (`verify…`, `assert…`, etc)
* 75% do código de produção (ou mais) seja executado

Devem ser feitas exclusões, para não deturpar os dados do relatório de cobertura, código que tenha sido gerado automaticamente (por exemplo, _bindings_ JAXB).

### Checagem de Bugs Prováveis

Todo o código deve ser checado automaticamente por bugs prováveis, utilizando as ferramentas [PMD](http://pmd.sourceforge.net/) e [FindBugs](http://findbugs.sourceforge.net), ou o equivalente apropriado em outras linguagens.

Deve-se manter a configuração destas ferramentas em seus padrões, e violações dos limites estabelecidos devem impedir a construção de artefatos candidatos a homologação.

Caso haja alguma modificação nelas, é exigida documentação e racionalização explícita para cada caso, na configuração em si, ou se possível em um comentário no próprio código (acima da anotação `@SuppressWarnings`, por exemplo).

### Documentação do Código

Recomenda-se evitar a documentação do código através de comentários, e sim através de testes automatizados. Trechos de código comentados como maneira de evitar sua execução, ou blocos `if(false)` são extremamente desencorajados.

## Reflection (Reflexão)

A utilização de reflexões em qualquer forma (_annotations_, _aspects_, etc.) deve ser detalhadamente comentada e deve estar bem descrita no documento de arquitetura, ou na documentação do framework/biblioteca que a provê.

# Confiabilidade e Disponibilidade


## Tolerância à Perda de Dados

Nenhuma transação efetivada pode ser perdida. Ao ser apresentada uma mensagem de confirmação de transação efetivada com sucesso, deve-se garantir que nenhum dado foi perdido.


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

# Acessibilidade

O ambiente deve estar aderente às recomendações da última versão disponível do [Modelo de Acessibilidade de Governo Eletrônico (e-MAG)](http://www.governoeletronico.gov.br/acoes-e-projetos/e-MAG). O sítio deve ter percentual de 100% segundo métrica desenvolvida pela SLTI. No [AccessMonitor](http://www.acessibilidade.gov.pt/accessmonitor/), deve obter média máxima. A conformidade com os padrões será verificada pela equipe de consultores do SISP.

# Internacionalização

Pelo fato de a solução ser utilizada por usuários brasileiros e estrangeiros, deve estar prevista a incorporação de recursos de internacionalização ao sistema para futuras traduções.

# Padrões Web em Governo Eletrônico

O ambiente deve seguir as normas estabelecidas nos [Padrões Web de Governo Eletrônico (e-PWG)](http://www.governoeletronico.gov.br/acoes-e-projetos/padroes-brasil-e-gov).

## Design e Resolução

Utilizar design responsivo e resolução conforme [orientações](http://www.secom.gov.br/orientacoes-gerais/comunicacao-digital/guia-de-estilo-identidade-padrao-comunicacao-digital-mai201409.pdf).

# Tecnologia e infraestrutura

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

# Documentação do Sistema

## Documentos de Projeto/Arquitetura

Diagramas compartilhados durante o desenvolvimento do projeto devem ser mantidos em um local acessível e organizado de forma a facilitar o acesso posterior. Caso seja utilizada uma lista de discussão para tal, é necessário que ela tenha uma ferramentas de busca adequada.

Demais artefatos de documentação podem ser solicitados durante a execução do projeto.

É recomendável o uso de [UML As Sketch](http://martinfowler.com/bliki/UmlAsSketch.html), em preferência à geração de documentação extensiva, porém de baixo valor.

## Documentação do Código

É obrigatório documentar, através de testes automatizados, o código de todas as funcionalidades. O relatório de execução destes testes deve contemplar:

- Clareza, objetividade, ortografia e boa apresentação
- Padronização dos produtos conforme os modelos definidos
- Em língua portuguesa (excetuadas partes geradas pela ferramenta automatizada, que podem estar em língua inglesa)
- Deve ser armazenado em repositório com controle de versionamento

## Documentos de Infraestrutura e Produção

Os seguintes documentos devem ser mantidos e relacionados:

- Documentação do hardware utilizado
- Documentação do software utilizado (sistema operacional, bibliotecas, etc)
- Configuração de hardware e software (preferencialmente, automatizada)
- Testes de Capacidade e Desempenho (Plano / Execução / Resultados)
- Procedimentos e Recursos para Monitoração / Auditoria / Acompanhamento
- Plano de Implantação / Plano de Contingência
- Análise de Segurança
