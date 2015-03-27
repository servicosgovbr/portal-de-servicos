echo 'Parando Guia de Serviços'
systemctl stop guia-de-servicos

rpm -Uvh $(find guia-de-servicos*.rpm -type f)

systemctl daemon-reload
systemctl start guia-de-servicos

