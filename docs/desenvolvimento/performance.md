# Performance

Dadas as seguintes métricas de performance de aplicações web:

#### TTFB (_Time to First Byte_)

Tempo que o servidor levou para começar a enviar a resposta da requisição principal, desconsiderando assets (imagens, javascript, css etc).

#### TTLB (_Time to Last Byte_)

Idem ao ítem anterior, mas conta o tempo que o servidor levou para enviar o último byte da resposta. Geralmente estes dois números diferem se o conteúdo for processado em blocos e enviado usando APIs de streaming.

#### TTRS (_Time to Render Start_)

Tempo até que o navegador comece a renderizar a página. Pode depender de algumas requisições a assets além do conteúdo principal.

#### TTUP (_Time to Usable Page_)

Tempo que leva para o usuário ter uma página usável/útil em mãos, mesmo que nem todo o conteúdo tenha terminado de carregar ainda.

#### ATFT (_Above-The-Fold Time_)

Tempo que a parte acima da dobra (_above the fold_) da página leva para renderizar. Relacionado ao TTUP, mas pode acontecer antes ou depois dele, dependendo do navegador e quantidade de assets carregados de forma assíncrona.

#### TPLT (_Total Page Load Time_)

Tempo total de carga, parsing e exibição da página, incluindo todos os assets. Esta medida é importante pois é o tempo em que navegador marca a página como "carregando" para o usuário, sem otimizações de carga assíncrona.

## Métricas utilizadas

Para o Portal de Serviços, decidimos medir apenas **TTFB** na requisição principal (a URL diretamente acessada pelo usuário), que deve estar abaixo de 1 segundo, e **TLPT**, inferior a 5 segundos (+/- 1 segundo).
