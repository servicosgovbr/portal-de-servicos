### No servidor de aplicação 1 (`app1`):

- Verificar que o hostname da máquina está presente no arquivo `/etc/hosts`
- Sobrescrever `/etc/sysconfig/guia-de-servicos-overrides` com o arquivo em `secrets/guia-de-servicos.producao.config` (ou o equivalente, enviado por email)
- Limpar os índices do ElasticSearch:

```bash
app1~$ curl -XDELETE 'http://10.17.0.6:9200/gds-importador'
app1~$ curl -XDELETE 'http://10.17.0.7:9200/gds-importador'
```

- Rodar o script `app-node-update` com a versão a instalar como parâmetro:

```bash
app1~$ wget https://raw.githubusercontent.com/servicosgovbr/guia-de-servicos/master/scripts/prod-like/app-node-update
app1~$ chmod +x app-node-update
app1~$ sudo ./app-node-update 1234
```

- Aguardar a inicialização da aplicação
- Verificar que a propriedade `build` é a versão esperada:

```bash
app1~$ curl "localhost:8080/info"
```

```json
{
  "build": "1234",
  "time": "2015-05-22 09:36",
  "git": {
    "branch": "master",
    "commit": {
      "id": "7df4e9d",
      "time": "2015-05-22 09:34"
    }
  }
}
```

- Verificar que a saúde do cluster está `UP`:

```bash
app1~$ curl "localhost:8080/health"
```
```json
{
  "status": "UP",
  "guiaDeServicosIndex": {
    "status": "UP",
    "gds-importador": "ok (638 docs)",
    "gds-persistente": "ok (4 docs)"
  },
  "elasticSearch": {
    "status": "GREEN",
    "nodes": 2,
    "node-0": "Dromedan",
    "node-1": "Arides"
  },
  "diskSpace": {
    "status": "UP",
    "free": 1781682176,
    "threshold": 10485760
  }
}
```

### No servidor de aplicação 2 (`app2`):

- Verificar que o hostname da máquina está presente no arquivo `/etc/hosts`
- Sobrescrever `/etc/sysconfig/guia-de-servicos-overrides` com o arquivo em `secrets/guia-de-servicos.producao.config` (ou o equivalente, enviado por email)
- Rodar o script `app-node-update` com a versão a instalar como parâmetro:

```bash
app2~$ wget https://raw.githubusercontent.com/servicosgovbr/guia-de-servicos/master/scripts/prod-like/app-node-update
app2~$ chmod +x app-node-update
app2~$ sudo ./app-node-update 1234
```

- Aguardar a inicialização da aplicação
- Verificar que a propriedade `build` é a versão esperada:

```bash
app2~$ curl "localhost:8080/info"
```

```json
{
  "build": "1234",
  "time": "2015-05-22 09:36",
  "git": {
    "branch": "master",
    "commit": {
      "id": "7df4e9d",
      "time": "2015-05-22 09:34"
    }
  }
}
```

- Verificar que a saúde do cluster está `UP`:

```bash
app2~$ curl "localhost:8080/health"
```

```json
{
  "status": "UP",
  "guiaDeServicosIndex": {
    "status": "UP",
    "gds-importador": "ok (638 docs)",
    "gds-persistente": "ok (0 docs)"
  },
  "elasticSearch": {
    "status": "GREEN",
    "nodes": 2,
    "node-0": "Dromedan",
    "node-1": "Arides"
  },
  "diskSpace": {
    "status": "UP",
    "free": 1781682176,
    "threshold": 10485760
  }
}
```

## No balanceador de carga (`lb`):

- Verificar que todos os servidores de aplicação estão visíveis (em verde) no `/lb-stats`
- Abrir página principal e verificar que lista de categorias (menu lateral) possui 15 elementos
- Verificar que o conteúdo de `/robots.txt` aponta para o `sitemap.xml`:

```bash
lb~$ curl -s "10.17.0.3/robots.txt"
```

```
Sitemap: http://10.17.0.3/sitemap.xml

User-agent: *
Disallow:
```

- Verificar que o código-fonte da página possui o site ID do Piwik correto:

```bash
lb~$ curl -s "10.17.0.3" | grep 'var siteId'
```

```javascript
    var siteId = '2';
```