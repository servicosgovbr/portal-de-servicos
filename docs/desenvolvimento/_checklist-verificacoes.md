- Verifique que os contêineres estão executando corretamente:

```bash
docker-compose ps
```

O comando acima deve produzir saída similar à seguinte:

```
   Name                  Command               State                                 Ports                                
-------------------------------------------------------------------------------------------------------------------------
editor1       /bin/sh -c sh /run.sh            Up      8090/tcp                                                           
es1           /docker-entrypoint.sh elas ...   Up      9200/tcp, 9300/tcp                                                          
portal1       /bin/sh -c sh /run.sh            Up      8080/tcp                                                  
postgres      /docker-entrypoint.sh postgres            Up      5432/tcp
```

Você pode verificar o log dos contêineres utilizando o seguinte comando:

```
docker-compose logs
```
