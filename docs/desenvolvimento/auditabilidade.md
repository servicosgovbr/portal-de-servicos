# Auditabilidade

Quaisquer transações de escrita (modificação ou remoção) iniciadas por um usuário devem conter informações de auditoria nos logs. Mais detalhes na seção [Segurança](./seguranca.md).

### Armazenamento e Limpeza de Logs
Para garantir o funcionamento da aplicação e a saúde do ambiente, um rotacionamento de logs foi implementado. Os logs gerados pelas aplicações, Portal de Serviços e Editor de Serviços, são redirecionados para o ElasticSearch. 

O serviço responsável por remover os índices dos logs gerados do ElasticSearch é chamado de **ElasticSearch Curator**. Atualmente, essa ferramenta está configurada para limpar todos os índices criados que ultrapassem 20GB. Uma verificação é realizada a cada duas horas.

A configuração da ferramenta para limpeza dos logs é realizada nos scripts da infra-estrutura da aplicação. Maiores detalhes podem ser encontrados no [código do Portal de Serviços] e na [documentação oficial] da ferramenta escolhida.

[código do Portal de Serviços]: https://github.com/servicosgovbr/docker/pull/7
[documentação oficial]: https://github.com/elastic/curator