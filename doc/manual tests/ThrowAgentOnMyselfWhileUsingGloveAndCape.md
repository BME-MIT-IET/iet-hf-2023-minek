# Teszt: Throw Agent On Myself While Using Glove And Cape

## Leírás

Ágens magára kenése miközben köpenyt és kesztyűt visel.

## Ellenőrzött funkcionalitás

Felszerelések viselkedésének tedztelése ágens kenése közben.

## Teszt

### Kiinduló állapot

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Rendelkezik egy már lecraftolt felejtő ágenssel, kesztyűvel és köpennyel.

### Teszt menete

1. A Virológus 1. bal kattintással kattint magára.
2. Itt a megjelenő listából kiválasztja a "Throw an agent" opciót.
3. A megjelenő listából kiválasztja a "Forget" ágenst.

#### Elvárt kimenet

- Virológus 1.: 82.3%-os eséllyel nem fertőzött semmilyen vírussal, egy üres mezőn áll. Rendelkezik egy köpennyvel.
