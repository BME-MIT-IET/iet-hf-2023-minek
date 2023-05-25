# Teszt: Steal Nucleotid From Not Numb

## Leírás

Nucleotid lopása nem lebénult virológustól

## Ellenőrzött funkcionalitás

Nucleotid tárolása, Nucleotid ellopása. A támadó virológus nem tud nukleotidot lopni a másik virológustól, mert az nincs lebénulva.

## Teszt

### Kiinduló állapot

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Virológus 2.-vel azonos mezőn tartózkodik.
- Virológus 2.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Virológus 1.-gyel azonos mezőn tartózkodik. Rendelkezik tetszőleges mennyiségű nukleotiddal.

### Teszt menete

1. A Virológus 1. bal kattintással kattint Virológus 2.-re.
2. Itt a legördülő menüből kiválasztja a „Steal” menüpontot (ha megjelenik ez a menüpont).

#### Elvárt kimenet

- A kattintás után megjelenő menüben nem jelenik meg a "Steal" menüpont.
- Virológus 1.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Virológus 2.-vel azonos mezőn tartózkodik.
- Virológus 2.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Virológus 1.-gyel azonos mezőn tartózkodik. Rendelkezik tetszőleges mennyiségű nukleotiddal.
