# Teszt: Steal While Numb

## Leírás

Virológus lopni próbál lebénultan.

## Ellenőrzött funkcionalitás

Lopás működése. Annak tesztelése, hogy a virológus nem képes lopni bénult állapotban.

## Teszt

### Kiinduló állapot

- Virológus 1.: Bénító effekt hatása alatt áll, nincs nála semmilyen felszerelés vagy alapanyag. Virológus 2.-vel azonos mezőn tarózkodik.
- Virológus 2.: Bénító effekt hatása alatt áll, van nála valamilyen felszerelés vagy alapanyag. Virológus 1.-el azonos mezőn tarózkodik.

### Teszt menete

1. A Virológus 1. bal kattintással kattint Virológus 2.-re.
2. Itt a legördülő menüből kiválasztja a „Steal” menüpontot.

#### Elvárt kimenet

- Virológus 1.: Van nála valamilyen felszerelés vagy alapanyag. Virológus 2.-vel azonos mezőn tarózkodik.
- Virológus 2.: Nincs nála semmilyen felszerelés vagy alapanyag. Virológus 1.-el azonos mezőn tarózkodik.
