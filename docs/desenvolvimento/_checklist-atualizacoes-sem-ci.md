Para realizar uma atualização	em ambientes hospedados em datacenters do governo, que não possuem **acesso** a ferramenta de integração contínua, os seguintes passos são necessários:

1. Garantir que a configuração inicial do docker já foi realizada na máquina host, conforme seção inicial de implantação
2. Acessar ambiente utilizando SSH: 
`ssh <usuário>@<ip do servidor>`
3. Utilizar o usuário root:
`sudo su`
4. Ir para o diretório do docker:
`cd /root/docker/`
5. Executar script bash que atualiza as instâncias do Portal de Serviços e do Editor de Serviços:
`./scripts/update_portal_container` e `./scripts/update_editor_container`
6. Caso queira atualizar todas as instâncias, executar o script bash:
`./scripts/update_portal_de_servicos`
7. Para verificar que as atualizações estão sendo aplicadas corretamente, verificar os logs do Docker. Por exemplo: 
  `docker logs -f editor1`
