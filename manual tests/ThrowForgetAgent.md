# Teszt: Throw Forget Agent

## Leírás

Felejtő ágens dobása egy másik virológusra.

## Ellenőrzött funkcionalitás

Felejtő ágens dobása azonos mezőn tartózkodó virológusra. A virológus rádobja a felejtő ágenst egy másik virológusra, így az hozzáadódik a megkent virológus aktív effektjeihez és elfelejti az addig megtanult genetikai kódokat.

## Teszt

### Kiinduló állapot

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Virológus 2.-vel azonos mezőn tartózkodik. Rendelkezik egy már lecraftolt felejtő ágenssel.
- Virológus 2.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Virológus 1.-gyel azonos mezőn tartózkodik. Nem rendelkezik semmilyen védőfelszereléssel. Van már megtanult genetikai kódja.

### Teszt menete

1. A Virológus 1. bal kattintással kattint Virulógus 2.-re.
2. Itt a megjelenő listából kiválasztja a "Throw an agent" opciót.
3. A megjelenő listából kiválasztja a "Forget" ágenst.

#### Elvárt kimenet

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Virológus 2.-vel azonos mezőn tartózkodik.
- Virológus 2.: Fertőzött a felejtő ágenssel vírussal, egy üres mezőn áll. Virológus 1.-gyel azonos mezőn tartózkodik. Nem rendelkezik semmilyen védőfelszereléssel. Nem rendelkezik megtanult genetikai kóddal.
