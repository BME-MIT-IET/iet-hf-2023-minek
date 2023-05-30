# Teszt Throw Numb Agent On Self

## Leírás
Bénító ágens dobása saját magamra.

## Ellenőrzött funkcionalitás
Új ágens létrehozása, ágens eltárolása virológusnál, ágens dobása magamra.

## Teszt

#### Kiinduló állapot
- Virológus 1.: egy labor mezőn áll, amely tartalmaz egy numb genetikai kódot. A virológus rendelkezik az ágens elkészítéséhez szükséges aminosavval és nukleotiddal. Nincs rajta semmilyen más effekt.

#### Teszt menete
1. A Virológus 1. bal kattintással kattint a labor mezőre
2. Itt a legördülő menüből kiválasztja a „Touch this field” menüpontot, ezzel felveszi a numb genetikai kódot.
3. A labor mezőn a legördülő menüt előhívva kiválasztja a Virológus 1. az "Interact with this field" opciót.
4. Ennek hatására a Virológus 1. megtanulja a felvett genetikai kódot.
5. Virológus 1. a "Craft" menüpontra kattintva kiválasztja a "Numb" ágenst, mellyel létrehozza azt.
6. A Virológus 1. bal egérgombbal kattintva a mezőn előhozza a legördülő menüt.
7. A Virológus 1. a menüpontokból kiválasztja a "Throw an agent" pontot.
8. A Virológus 1. a "Craft" előugró menüpontok közül kiválasztja a már elkészítéett numb ágenst, ezzel a saját magára dobva azt.

#### Elvárt kimenet
- Virológus 1.: az elkészített ágenseinek száma csökken. A virológus bénult lesz, a következő körben nem tud cselekedni.