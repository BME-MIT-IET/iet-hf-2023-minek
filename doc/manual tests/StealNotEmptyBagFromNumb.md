# Teszt:  Steal Not Empty Bag From Numb

## Leírás:
Nem üres zsák lopása lebénult virológustól.

## Ellenőrzött funkcionalitás:
Új ágens létrehozása, ágens eltárolása virológusnál, ágens dobása másra, anyag tárolása zsákba, zsák tárolása, zsák ellopása.

## Teszt:

#### Kiinduló állapot:
- Virológus 1.: egy warehouse mezőn áll, ami aminosavat tartalmaz. Van továbbá 10 numb ágense.
- Virológus 2.: egy shelter mezőn áll, amin táska van. A mező szomszédos Virológus 1. mezőjével.

#### Teszt menete:
1. A Virológus 2. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
2. A Virológus 2. a menüpontokból kiválasztja a "Touch this field" pontot.
3. A Virológus 2. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
4. A Virológus 2. a menüpontokból kiválasztja a "Interact with this field" pontot.
5. Ezzel felvesz a mezőről egy aminosavat.
6. Virológus 2. bal egér gombbal kattintva a mellette lévő warehouse mezőn előhozza a legördülő mezőt.
7. A Virológus 2. a menüpontok közül kiválasztja a "Move to" opciót.
8. A Virológus 2. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
9. A Virológus 2. a menüpontokból kiválasztja a "Touch this field" pontot.
10. A Virológus 2. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
11. A Virológus 2. a menüpontokból kiválasztja a "Interact with this field" pontot.
12. Ezzel felvett egy táskát.
13. A Virológus 1. bal egér gombbal előhívja a legördülő mezőt a másik játékosra kattintva.
14. A Virológus 1. kiválasztja a "Throw agent" menüpontot
15. Virológus 1. a "Craft" menüből kiválasztja a Numb ágenst
16. Virológus 1. bal egér gombbal előhívja a legördülő mezőt a másik játékosra kattintva.
17. Virológus 1. kiválasztja a "Steal" menüpontot.


#### Elvárt kimenet:
- Virológus 1.: nála van az ellopott táska
- Virológus 2.: numb hatása alatt áll, nincs nála táska