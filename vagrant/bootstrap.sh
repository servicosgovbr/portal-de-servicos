#!/bin/env sh
sudo -v

echo "Atualizando repositório Yum"
sudo yum makecache fast
sudo yum update

