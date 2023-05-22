# Teszt: Throw Agent While Numb

## Leírás

Bénult virológus ágenst próbál dobni egy másik virológusra.

## Ellenőrzött funkcionalitás

Ágens dobás működése. Annak tesztelése, hogy a virológus nem képes ágenst dobni bénult állapotban.

## Teszt

### Kiinduló állapot

- Virológus 1.: Bénult állapotban van, egy üres mezőn áll. Virológus 2.-vel azonos mezőn tartózkodik. Rendelkezik egy már lecraftolt felejtő ágenssel.
- Virológus 2.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Virológus 1.-vel azonos mezőn tartózkodik. Nem rendelkezik semmilyen védőfelszereléssel.

### Teszt menete

1. A Virológus 1. bal kattintással kattint Virulógus 2.-re.
2. Itt a megjelenő listából kiválasztja a "Throw an agent" opciót.
3. A megjelenő listából kiválasztja a "Forget" ágenst.

#### Elvárt kimenet

- Virológus 1.: Bénult állapotban van, egy üres mezőn áll. Virológus 2.-vel azonos mezőn tartózkodik. Rendelkezik egy már lecraftolt felejtő ágenssel.
- Virológus 2.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Virológus 1.-vel azonos mezőn tartózkodik. Nem rendelkezik semmilyen védőfelszereléssel.
