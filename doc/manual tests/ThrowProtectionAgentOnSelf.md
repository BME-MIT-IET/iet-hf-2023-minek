# Teszt: Throw Protection Agent On Self

## Leírás

Védelmező ágens kenése saját magára.

## Ellenőrzött funkcionalitás

A virológus saját magára keni a védelmező ágenst, így a védelmező effekt bekerül az aktív effektjei közé.

## Teszt

### Kiinduló állapot

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Rendelkezik egy már lecraftolt védelmező ágenssel.

### Teszt menete

1. A Virológus 1. bal kattintással kattint magára.
2. Itt a megjelenő listából kiválasztja a "Throw an agent" opciót.
3. A megjelenő listából kiválasztja a "Protection" ágenst.

#### Elvárt kimenet

- Virológus 1.: Fertőzött a védelmező vírussal, egy üres mezőn áll.
