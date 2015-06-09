# Como contribuir

Esta documentação é escrita no formato [Markdown], e a versão mais recente é publicada automaticamente através da infraestrutura do projeto de [Entrega Contínua](../desenvolvimento/entrega-continua.md).

Para atualizar um documento, basta editá-lo a partir do próprio [Github do projeto][github] e seguir as convenções do formato [Markdown]. O Github disponibiliza uma pré-visualização no próprio editor.

Caso queira ter uma cópia local da documentação que queira editar, ou seja um desenvolvedor que quer atualizar a documentação em conjunto com novas funcionalidades antes de gerar um _commit_, instale a ferramenta [gitbook-cli] e rode:

```
cd docs
gitbook serve
```

A pré-visualização deve estar disponível no endereço [http://localhost:4000](http://localhost:4000), e quaisquer edições ficam disponíveis momentos após serem salvas. A recompilação é automática.

[gitbook-cli]:https://github.com/GitbookIO/gitbook
[Markdown]:http://daringfireball.net/projects/markdown/
[github]:https://github.com/servicosgovbr/portal-de-servicos/tree/master/docs
