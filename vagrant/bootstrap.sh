#!/bin/env sh
sudo -v

echo "Atualizando repositório Yum"
sudo yum makecache fast -y
sudo yum update -y

mkdir gds
pushd gds
  echo "Instalando Java 8"
  wget -c --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u40-b25/jdk-8u40-linux-x64.rpm"
  sudo rpm -Uvh jdk-8u40-linux-x64.rpm
  java -version

  echo "Instalando ElasticSearch"
  sudo rpm -Uvh https://download.elasticsearch.org/elasticsearch/elasticsearch/elasticsearch-1.5.0.noarch.rpm
popd
