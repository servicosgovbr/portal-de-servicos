# Repositório Yum

Os pacotes [RPM] do Portal de Serviços estão disponíveis em um repositório Yum, com as seguintes configurações:

```
[portal-de-servicos]
name = Portal de Serviços
baseurl = https://s3-sa-east-1.amazonaws.com/servicosgovbr/centos/7/
enabled=1
gpgcheck=1
gpgkey=https://raw.githubusercontent.com/servicosgovbr/portal-de-servicos/master/src/main/resources/static/GPG-KEY
```

Um arquivo com estes conteúdos pode ser adicionado ao diretório `/etc/yum.repos.d`, seguido do comando `sudo yum makecache fast -y`, para que o Yum atualize as definições internas de listas de pacotes disponíveis.

De maneira similar, os pacotes do Editor de Serviços estão disponíveis no repositório com as seguintes configurações:

```
[editor-de-servicos]
name = Editor de Serviços
baseurl = https://s3-sa-east-1.amazonaws.com/servicosgovbr/centos/7/
enabled=1
gpgcheck=1
gpgkey=https://raw.githubusercontent.com/servicosgovbr/editor-de-servicos/master/src/main/resources/static/GPG-KEY
```

A chave [GPG] utilizada para assinar os pacotes de ambos os repositórios é:

```
pub   2048R/2E1F2BA2 2015-04-07
      Key fingerprint = 1F90 DD30 DAF2 DCDF 4F27  DC78 F68F 9EB4 2E1F 2BA2
uid                  Portal de Serviços <gpg@servicos.gov.br>
sub   2048R/BA141101 2015-04-07
```

E seu conteúdo (também disponível atraves da URL na seção `gpgkey` da configuração acima) é:

```
-----BEGIN PGP PUBLIC KEY BLOCK-----
Version: GnuPG/MacGPG2 v2.0.22 (Darwin)

mQENBFUj7DsBCAC8sotytVL/ZTAqCI4gZGIVcogVXo1AsjySXTR9mVAEet3EElDM
dIs2WaD/oLUFqG2TtSDLd3RhrufIHpe4t4pISKoHE8jaHecQR8q9y9Fbyjd5swba
DIrulD8/b/qNIXr21PRjoJ1II5KxkKvA3Ax8RUui8f7fGg3SY3slfPqKfp81K6gM
+LoISVOhvxgiPDj5AtZUtarFdsGs1ssCcYD8axOgU9lEj2JZxO5ntUff+L9l8Ftg
Fi7i4pwdsz6RwZTr551qtrZc2gXesnCgJBl5y45AaIlIeRfZ5xpIz/7mQFqf5Vl8
FYdxF2WNslyTkpZBgSdPIA9mFEEK77YWtKP5ABEBAAG0J0d1aWEgZGUgU2VydmnD
p29zIDxncGdAc2Vydmljb3MuZ292LmJyPokBOAQTAQIAIgUCVSPsOwIbAwYLCQgH
AwIGFQgCCQoLBBYCAwECHgECF4AACgkQ9o+etC4fK6IJEAf9GvYWYq8XbjQEcThO
5Yf4gm1OaISd2mt6TD0BABz6fLi23454RmM9hoGThb/IPfabHH0ZKq0YFEqA3cwI
UlzoT4VycYoZSr1EMxiJsAWyf6n/y5Gt9fwnuRKBOeLnPd5gdK5+ukWlc89U08F4
oCTrpqif/9eOw0tDeHD15rbNR4DZk8CYquT05RpMdJ0EMpYg6GYwffH8NEFg/bRZ
R9w7TdyHVB6BFqNuVmxn/Scg2W/Vwb5rk1P0GMOIawUBrLE82pZGuEyD9I8g5L6u
CbPmJ97HimGYBlgptkzImc2viCi/KfFSmlrNvWOnDkviHmyME5TzhAZd4Wunnwo6
wN5mvLkBDQRVI+w7AQgA1U/T1BhDlinoc1Vsm5RyqaoPwnl7U/V1ieJ9IPC76Q6Q
pOLca2LN9IQldWRfB3mN7lG+ioxbXIx110A4AHugPLO9eUtj5LqoHk6pgia5YcM/
qsE24NwKjEmLHokOrVUqawVgEEFJR+udWBeBY7xL0wD/ZQIKbDKL/avf39z2vHfd
egqm46l8DkFjeRZueceLD/N2XFOuw8DHulb+7Rs7mako4kdXmyE3T/bxo7zBuybr
W0gpKwI0e+kBL6q4m1I472jUQY6hvmjsj8J0D9g5PIMXwtpMSQVFlMWSxP8+KrgV
2w8ug03BpDk/d5G+sWUiVPpVDVfSaA2nxTWL9i5NWQARAQABiQEfBBgBAgAJBQJV
I+w7AhsMAAoJEPaPnrQuHyui81UH/1iww/lx6UhB2rioLu5S9hIdZk+B3uuLFI/+
lIBh+tp9SgYmVbbD9re649wiCUzvyup9zWbwwq1/yC403ooDOFhQ1685ZKtMHDHV
37GQktbJcADd8feor60/lmJVfll8zarOQRn4EGY9laZK46lGB6GdFrpkvzifyNDF
U+m7ZteaJOBqv0l+iwnoSS/wIcQI8ISX1KRCDmR2dxFO1Gnx53S1+yIryL2fINZn
zAQfjQCD/hjXQHUk5o2rMKlMUF2iPJwlx139wJ/o5BtvKXg/1oGBruMa8dVGGy4+
FWNjBIi/4Zz3o/QwwjUGKeAYRpllp8knOk+6foxRzuYIkov92Lg=
=vCrJ
-----END PGP PUBLIC KEY BLOCK-----
```

[RPM]:http://www.rpm.org
[GPG]:https://www.gnupg.org/
