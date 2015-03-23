#!/bin/env sh

echo 'Atualizando repositório Yum'
yum makecache fast -y
yum update -y

mkdir -p 'gds'
pushd 'gds'
  if [ -f 'jdk-8u40-linux-x64.rpm' ]; then
    echo 'Java 8 já foi baixado'
  else
    echo 'Baixando Java 8'
    wget -c \
      --quiet \
      --no-cookies \
      --no-check-certificate \
      --header 'Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie' \
      'http://download.oracle.com/otn-pub/java/jdk/8u40-b25/jdk-8u40-linux-x64.rpm'
  fi

  echo 'Instalando Java 8'
  rpm -Uvh 'jdk-8u40-linux-x64.rpm'

  $(java -version 2>&1 | grep -q "1.8.0_42") || (echo "Java 8 não foi instalado corretamente" && exit -1)

  echo 'Instalando ElasticSearch'
  rpm -Uvh https://download.elasticsearch.org/elasticsearch/elasticsearch/elasticsearch-1.5.0.noarch.rpm

  systemctl daemon-reload
  systemctl enable elasticsearch.service
popd
