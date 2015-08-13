# Versões de especificação de serviços

A fim de criar um modelo evolutivo, baseado nos diferentes tipos de maturidade e complexidade de serviços públicos, 
as definições dos serviços em formato XSD serão categorizadas de acordo com as informações disponíveis para cada nível 
de maturidade de serviço.

Além de categorizar os serviços públicos, este modelo também pode servir como guia para que os serviços evoluam em 
termos de maturidade e melhor qualidade da informação disponibilizada.

A versão da especificação do serviço deve estar presente nos atributos `targetNamespace` e `xmlns` do XSD e XML, 
respectivamente. Por exemplo:

Arquivo XSD:
```xml
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
           targetNamespace="http://servicos.gov.br/carta/v1/servico">

	<xs:element name=”servico”>...</xs:element>

</xs:schema>
```

Arquivo XML:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<servico xmlns="http://servicos.gov.br/carta/v1/servico">
	...
</servico>
```

O `targetNamespace`, no arquivo XSD, exibe a subcategoria `v1` que compreende as definições necessárias para que um 
serviço esteja categorizado neste nível de maturidade.

Consequentemente, o arquivo XML referencia a versão correspondende ao nível de maturidade que se deseja alcançar.

Sendo assim, para evoluir um serviço, basta referenciar o nível desejado, submeter sua definição de serviço em formato 
XML para validação contra o XSD correspondente e adequar suas informações para que este alcance o próximo nível.

Esperamos assim, dar suporte a uma evolução gradual e constante dos serviços públicos, visando sempre alcançar o nível 
de maturidade necessário para atender a população de forma efetiva.
