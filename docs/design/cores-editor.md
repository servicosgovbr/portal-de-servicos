# Cores

<style>
.swatch {
  max-width: 150px;
  min-width: 150px;
  width: 150px;
  max-height: 150px;
  min-height: 150px;
  height: 150px;
  border: 1px solid black;
}
</style>
 
Definimos as cores a serem utilizadas no site do Portal de Serviços com base no [tema branco] do [Manual de Identidade Visual do Governo Federal][estilos].

Foram cinco cores definidas para estilizar o site, e estas são utilizadas com o objetivo de proporcionar uma melhor legibilidade, organização, contraste e acessibilidade das informações. 

[tema branco]:https://github.com/plonegovbr/brasil.gov.temas#id6
[estilos]:http://www.secom.gov.br/orientacoes-gerais/comunicacao-digital/guia-de-estilo-identidade-padrao-comunicacao-digital-fev2015.pdf

| COR         | CÓDIGO		| EXEMPLO
| ----------- | :---------: | ------------------------------------------------------------------ |
| Azul Claro  | `#2c66ce`   | <div class="swatch" style="background-color: #2c66ce">&nbsp;</div> |
| Azul Escuro | `#1f4da3`   | <div class="swatch" style="background-color: #1f4da3">&nbsp;</div> |
| Branco      | `#ffffff`   | <div class="swatch" style="background-color: #ffffff">&nbsp;</div> |
| Cinza claro | `#f0f2f1`   | <div class="swatch" style="background-color: #f0f2f1">&nbsp;</div> |
| Cinza médio | `#d5d5d5`   | <div class="swatch" style="background-color: #d5d5d5">&nbsp;</div> |
| Cinza escuro| `#606060`   | <div class="swatch" style="background-color: #606060">&nbsp;</div> |

### Contraste

A escolha de cores está em conformidade com o [padrão AA da W3C](http://www.w3.org/WAI/WCAG20/quickref/#qr-visual-audio-contrast-contrast) de contraste para acessibilidade de páginas da internet, atingindo notas superiores a 4.5 em todas as combinações utilizadas no Portal de Serviços. Para verificação, foi utilizada a ferramenta [Colour Contrast Check](http://snook.ca/technical/colour_contrast/colour.html#fg=33FF33,bg=333333), referenciada pela própria W3C. As tabelas abaixo apresentam os resultados da avaliação.

| COMBINAÇÕES UTILIZADAS                                                                   | NOTA |
| -----------------------------------------------------------------------------------------| :--: |
|<p style="background-color: #f0f2f1; color: #606060"> Cinza Escuro sobre Cinza Claro </p> | 5.59 |
|<p style="background-color: #f0f2f1; color: #2c66ce"> Azul Claro sobre Cinza Claro </p>   | 4.79 |
|<p style="background-color: #f0f2f1; color: #1f4da3"> Azul Escuro sobre Cinza Claro </p>  | 7.05 |
|<p style="background-color: #ffffff; color: #2c66ce"> Azul Claro sobre Branco </p>        | 5.39 |
|<p style="background-color: #ffffff; color: #1f4da3"> Azul Escuro sobre Branco </p>       | 7.93 |
|<p style="background-color: #ffffff; color: #606060"> Cinza Escuro sobre Branco </p>      | 6.29 |
|<p style="background-color: #2c66ce; color: #ffffff"> Branco sobre Azul Claro </p>        | 5.39 |

Para referência, listamos abaixo as combinações que não podemos utilizar, por não estarem em conformidade.

| COMBINAÇÕES NÃO UTILIZADAS                                                               | NOTA |
| -----------------------------------------------------------------------------------------| :--: |
|<p style="background-color: #d5d5d5; color: #2c66ce"> Azul Claro sobre Cinza Médio </p>   | 3.67 |
|<p style="background-color: #d5d5d5; color: #606060"> Cinza Escuro sobre Cinza Médio </p> | 4.28 |