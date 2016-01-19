# Escalabilidade

É possível aumentar a capacidade de carga do sistema de duas maneiras:

## Aumentando a capacidade do servidor (vertical)

Servidores estão atualmente provisionados de acordo com a seção "[Implantação e execução](../desenvolvimento/implantacao-e-execucao.md)". Para adicionar mais capacidade, é necessário revisar as configurações pertinentes e analisar a performance total novamente (ver: "[Performance](../desenvolvimento/performance.md)").

## Aumentando a quantidade de servidores (horizontal)

Para adicionar um servidor de aplicação, será necessário:

* Dar um novo IP externo ao host [Docker]
* Criar um balanceador de carga com o IP externo anterior (ao qual o servicos.gov.br aponta)
* Configurar o balanceador de carga criado para redirecionar tráfego ao host [Docker]
* Criar novas instâncias do host [Docker], idênticas, conforme necessário
* Adicioná-las ao balanceador de carga criado no passos anteriores
 
Ao fim destes passos, a arquitetura deverá seguir o diagrama abaixo:

![Diagrama de ambiente com dois hosts](/desenvolvimento/ambiente-docker-escalado.png)
