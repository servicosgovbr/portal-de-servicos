# Definição técnica

Todos os serviços do governo brasileiro deverão se descritos em um arquivo no formato XML
de forma que facilite sua importação tanto pelo guia de serviços quanto por quais quer
outros aplicativos que desejem utilizar seus dados.

A fim de criar um contrato padrão, extensível e validável para a descrição e formatação dos
documentos XML que irão descrever cada serviço, serão criados arquivos XSDs que definem e
validam os XMLs de serviços.

Um arquivo [XSD (XML Schema Definition)][XSD], Definição de esquema XML, é uma recomendação da 
[W3C (World Wide Web Consortium)][W3C] que especifica como descrever formalmente documentos em formato [XML].

Vantagens:
- Contrato unificado para especificação dos elementos que compões cada arquivo XML
- Define quais os tipos aceitos por cada elemento do documento
- Pode ser utilizado para validar o arquivo XML que especifica

[XSD]:http://www.w3.org/TR/xmlschema11-1/
[W3C]:http://w3c.org/
[XML]:http://www.w3.org/XML/
