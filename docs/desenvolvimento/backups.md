# Backups

Backups devem ser feitos dos seguintes caminhos:

```
/etc/sysconfig/portal-de-servicos
/etc/systemd/system/portal-de-servicos.service
/opt/portal-de-servicos/**/*
/var/run/portal-de-servicos/**/*
/var/log/**/*
```

A retenção dos mesmos pode variar dependendo do ambiente. Recomenda-se ao menos 30 dias.