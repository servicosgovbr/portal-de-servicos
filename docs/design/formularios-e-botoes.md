# Formulários e botões

Formulários seguem as convenções do [eMAG].

[eMAG]:http://www.governoeletronico.gov.br/acoes-e-projetos/e-MAG

<style>
.exemplo {
  padding: 22px;
  border: 1px solid #f7f7f7;
  background-color: #ffffff;
}

.exemplo > label {
  display: block;
}
</style>

## Entrada de texto

<div class="exemplo">
  <input type="text"></input>
</div>

```
<input type="text"></input>
```

## Entrada de texto para busca

<div class="exemplo">
  <input type="search"></input>
</div>

```
<input type="search"></input>
```

## Botão de ação

<div class="exemplo">
  <input type="button" value="Acionar"></input>
</div>

```
<input type="button" value="Acionar"></input>
```

## Botão de escolha simples (radio)

<div class="exemplo">
  <label for="input-0">
    <input id="input-0" type="radio" name="radio-input" value="0"></input>
    Opção 0
  </label>
  
  <label for="input-1">
    <input id="input-1" type="radio" name="radio-input" value="1"></input>
    Opção 1
  </label>
</div>

```
  <label for="input-0">
    <input id="input-0" type="radio" value="0"></input>
    Opção 0
  </label>
```

## Caixa de seleção (dropdown)

## Caixa de opção (checkbox)

