# Teszt:  Steal Cape From Not Numb

## Leírás:
Köpeny lopása nem lebénult virológustól.

## Ellenőrzött funkcionalitás:
Köpeny tárolása, köpeny ellopása.

## Teszt:

#### Kiinduló állapot:
- Virológus 1.: egy shelter mezőn áll, amely egy köpenyt tartalmaz.
- Virológus 2.: Virológus 1.-gyel megegyező mezőn áll.

#### Teszt menete:
1. A Virológus 1. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
2. A Virológus 1. a menüpontokból kiválasztja a "Touch this field" pontot.
3. A Virológus 1. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
4. A Virológus 1. a menüpontokból kiválasztja a "Interact with this field" pontot.
5. Ezzel felvesz a mezőről egy köpenyt.
6. Virológus 2. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
7. A Virológus 2. a menüpontok között nem lát semmit

#### Elvárt kimenet:
- Virológus 1.: rajta van a köpeny
- Virológus 2.: nem tudott lopni