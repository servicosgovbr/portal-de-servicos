# Definição técnica

Os serviços são descritos em um arquivo no formato [XML] ou [JSON], de forma a facilitar sua importação tanto pelo Portal de 
Serviços quanto por quaisquer outros aplicativos que desejem utilizar seus dados.

Para garantir que os documentos XML estão corretamente formatados e contém [a estrutura desejada][MER], também foi criado
um modelo (_schema_) em formato [XSD] para os serviços, [também disponível no repositório do projeto][SERVICOSXSD]. Já para a garantia no documento JSON foi criado um modelo (_schema_) também em formato JSON para os serviços, tal esquema pode ser acessado na [documentação da API][API_URL].

Este schema pode ser utilizado para geração automática de código de leitura e escrita de arquivos XML que aderem ao formato
modelado, utilizando ferramentas como [JAXB], [xml4js], etc.

[XSD]:http://www.w3.org/TR/xmlschema11-1/
[XML]:http://www.w3.org/XML/
[MER]:http://servicosgovbr.github.io/portal-de-servicos/modelo-de-explicitacao/mer.html
[SERVICOSXSD]:https://github.com/servicosgovbr/cartas-de-servico/blob/master/cartas-servico/v3/servico.xsd
[JAXB]:https://en.wikipedia.org/wiki/Java_Architecture_for_XML_Binding
[xml4js]:https://www.npmjs.com/package/xml4js
[JSON]:http://www.json.org/
[API_URL]:https://servicos.gov.br/api/v1/docs