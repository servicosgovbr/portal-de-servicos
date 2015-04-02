# Disponibilidade

O sistema garante que os dados persistidos são íntegros e consistentes de forma que transações que aconteçam em paralelo no mesmo conjunto de dados não possuam inconsistências, bem como que consultas realizadas a conjuntos de dados que possuam semântica implícita para mais de um registro sejam sempre consistentes e íntegras, impossibilitando leitura de dados inseridos por outra transação.

Exceto por manutenções programadas ou eventos fora do controle da equipe, o sistema estará disponível ininterruptamente. Paradas de manutenção serão comunicadas com antecedência.

A disponibilidade requerida da plataforma ([infraestrutura](./infraestrutura.md), aplicação, etc) é de 97% (aproximadamente 50 minutos ao dia) a 99.8% (aproximadamente 3 minutos ao dia), e pode variar em função do número de publicações (deployments) feitos.
