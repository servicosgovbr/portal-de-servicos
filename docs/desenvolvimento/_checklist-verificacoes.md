- Verifique que os contêineres estão executando corretamente:

```bash
docker-compose ps
```

O comando acima deve produzir saída similar à seguinte:

```
   Name                  Command               State                                 Ports                                
-------------------------------------------------------------------------------------------------------------------------
balanceador   haproxy -f /usr/local/etc/ ...   Up      0.0.0.0:5601->5601/tcp, 0.0.0.0:80->80/tcp, 0.0.0.0:8080->8080/tcp 
cadvisor      /usr/bin/cadvisor                Up      8080/tcp                                                           
editor1       /bin/sh -c sh /run.sh            Up      8090/tcp                                                           
editor2       /bin/sh -c sh /run.sh            Up      8090/tcp                                                           
es1           /docker-entrypoint.sh elas ...   Up      9200/tcp, 9300/tcp                                                 
es2           /docker-entrypoint.sh elas ...   Up      9200/tcp, 9300/tcp                                                 
kibana        /docker-entrypoint.sh kibana     Up      5601/tcp                                                           
logspout      /bin/logspout                    Up      80/tcp, 8000/tcp                                                   
logstash      /docker-entrypoint.sh logs ...   Up      5000/tcp                                                           
portal1       /bin/sh -c sh /run.sh            Up      8080/tcp                                                           
portal2       /bin/sh -c sh /run.sh            Up      8080/tcp                                                           
```

- Verifique que a versão do Portal de Serviços é a desejada:

```bash
curl -k https://localhost/info
```

O comando acima deve produzir saída similar à seguinte:

```json
{
  "build": "1529",
  "time": "2015-08-28 00:43",
  "git": {
    "branch": "master",
    "commit": {
      "id": "e5e9f74",
      "time": "2015-08-28 00:37"
    }
  }
}
```

- Verifique que a versão do Editor de Serviços é a desejada:

```bash
curl -k https://localhost/editar/info
```

O comando acima deve produzir saída similar à seguinte:

```json
{
  "build": "627",
  "time": "2015-08-28 00:42",
  "git": {
    "branch": "master",
    "commit": {
      "id": "4b85c92",
      "time": "2015-08-28 00:39"
    }
  }
}
```

- Verificar que a saúde do cluster está `UP`:

```bash
curl -k https://localhost/health
```

O comando acima deve produzir saída similar à seguinte:

```json
{
  "status": "UP",
  "portalDeServicosIndex": {
    "status": "UP",
    "portal-de-servicos": "ok (622 docs)"
  },
  "elasticSearch": {
    "status": "GREEN",
    "nodes": 3,
    "node-0": "es1",
    "node-1": "logstash-d9ccaa4d20c4-1-11624",
    "node-2": "es2"
  },
  "cache": {
    "status": "UNKNOWN"
  },
  "diskSpace": {
    "status": "UP",
    "free": 17378258944,
    "threshold": 10485760
  }
}
```

Caso alguma das verificações não tenha sucesso, você pode verificar o log dos contêineres utilizando o seguinte comando:

```
docker-compose logs
```
