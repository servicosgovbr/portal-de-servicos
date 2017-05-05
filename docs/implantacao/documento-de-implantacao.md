# Documento de Implantação

Neste documento estão todas as informações necessárias à implantação e operação do Portal de Serviços e Editor de Serviços em ambientes de, ou similares a, produção.

{%include '../desenvolvimento/infraestrutura.md' %}

## Configuração

{% include "../desenvolvimento/_configuracao-variaveis-ambiente.md" %}

## Acessos e Conexões

Através de acordos técnicos e conversas com os responsáveis pela infraestrutura do Ministério do Planejamento, as seguintes regras acessos de ***firewall*** foram definidas como essenciais para garantir a interoperabilidade do Portal de Serviços: 

**Acessos que devem estar liberados na entrada das máquinas de Desenvolvimento e Produção**

|   |Desenvolvimento (189.9.150.163)|Produção (189.9.151.72)|
|---|-------------------------------|-----------------------|
| Internet                  |80, 443    |80, 443            |  
| Ministério do Planejamento|22, 80, 443, 8080, 5601|80, 443, 8080, 5601|  

**Acessos que devem estar liberados na saída das máquinas de Desenvolvimento e Produção**

|   |Servidor Git (192.30.252.0/22)|
|---|------------------------------|
|Desenvolvimento (189.9.150.163)|  22, 80, 443 |
|Produção (189.9.151.72)        |   22, 80, 443|

## Checklist de Implantação

{%include './_checklist-implantacao.md' %}

----

{%include '../desenvolvimento/backups.md' %}

----

{%include '../desenvolvimento/monitoramento.md' %}

---

{%include '../desenvolvimento/escalabilidade.md' %}
