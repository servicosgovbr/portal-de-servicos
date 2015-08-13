# Exemplo de XSD e XML

Este é um exemplo simples, contendo apenas dois campos, afim de ilustrar como modelar
especificações de serviços utilizando o formato ```XSD```.

Campos:
- `titulo`: campo obrigatório contento no mínimo 5 e no máximo 255 caracteres
- `descricao`: campo obrigatório contento no mínimo 50 e no máximo 500 caracteres

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
           targetNamespace="http://servicos.gov.br/carta/v1/servico"
           elementFormDefault="qualified"
           attributeFormDefault="unqualified"
           version="1.0">

    <xs:element name="servico">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="titulo" type="textoCurto"/>
                <xs:element name="descricao" type="textoLongo"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>

    <xs:simpleType name="textoCurto">
        <xs:restriction base="xs:string">
            <xs:minLength value="5"/>
            <xs:maxLength value="255"/>
        </xs:restriction>
    </xs:simpleType>

    <xs:simpleType name="textoLongo">
        <xs:restriction base="xs:string">
            <xs:minLength value="50"/>
            <xs:maxLength value="500"/>
        </xs:restriction>
    </xs:simpleType>

</xs:schema>
```

Arquivo XML validado pelo XSD acima definido:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<servico xmlns="http://servicos.gov.br/carta/v1/servico"
         version="1.0">

  <titulo>Retificadora On Line (Extrato da DIRPF)</titulo>

  <descricao>
      Alterar alguns dados da declaração, diretamente pela internet, sem a utilização do
      programa IRPF e nem do Receitanet. Não há necessidade de preencher novamente os dados que não serão alterados.
  </descricao>

</servico>
```
