#!/bin/bash

set -e
set -o pipefail

echo "Gerando pacote RPM localmente..."
bash -c ./gradlew buildRpmLocal
RPM_FILE="$(find build/rpmbuild/RPMS/noarch/ -iname *.rpm | head -n 1)"

echo "Copiando arquivo para um local onde o Docker consiga acessar"
cp $RPM_FILE /opt/portal-de-servicos.rpm



