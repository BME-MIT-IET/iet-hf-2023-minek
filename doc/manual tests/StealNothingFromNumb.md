# Teszt: Steal Nothing From Numb

## Leírás

Semmi rablása bénult virológustól.

## Ellenőrzött funkcionalitás

Lopás működése. Annak tesztelése, hogy a virológus nem képes lopni olyan virológustól akinél nincs semmi

## Teszt

### Kiinduló állapot

- Virológus 1.: Nem fertőzött semmilyen vírussal, nincs nála semmilyen felszerelés vagy alapanyag. Virológus 2.-vel azonos mezőn tarózkodik.
- Virológus 2.: Bénító effekt hatása alatt áll, nincs nála semmilyen felszerelés vagy alapanyag. Virológus 1.-gyel azonos mezőn tarózkodik.

### Teszt menete

1. A Virológus 1. bal kattintással kattint Virológus 2.-re.
2. Itt a legördülő menüből kiválasztja a „Steal” menüpontot.

#### Elvárt kimenet

- Virológus 1.: Nem fertőzött semmilyen vírussal, nincs nála semmilyen felszerelés vagy alapanyag. Virológus 2.-vel azonos mezőn tarózkodik.
- Virológus 2.: Nincs nála semmilyen felszerelés vagy alapanyag. Virológus 1.-gyel azonos mezőn tarózkodik.
