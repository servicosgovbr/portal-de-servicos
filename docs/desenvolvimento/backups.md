# Backups

Backups devem ser feitos dos seguintes arquivos e diretórios:

```
/etc/systemd/system/guia-de-servicos.service
/opt/guia-de-servicos/**/*
/var/run/guia-de-servicos/**/*
/var/log/guia-de-servicos/**/*

```

A retenção dos mesmos pode variar dependendo do ambiente. Recomenda-se ao menos 30 dias.