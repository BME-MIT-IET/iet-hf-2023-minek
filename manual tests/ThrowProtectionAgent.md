# Teszt: ThrowProtectionAgent

## Leírás:
Védő ágens dobása másra.

## Ellenőrzött funkcionalitás:
Új ágens létrehozása, ágens eltárolása virológusnál, ágens dobása másra.

## Teszt:

#### Kiinduló állapot:
- Virológus 1.: egy labor mezőn áll, amely tartalmaz egy protection genetikai kódot. A virológus rendelkezik az ágens elkészítéséhez szükséges aminosavval és nukleotiddal.
- Virológus 2.: Virológus 1.-gyel megegyező mezőn áll

#### Teszt menete:
1. A Virológus 1. bal kattintással kattint a labor mezőre
2. Itt a legördülő menüből kiválasztja a „Touch this field” menüpontot, ezzel felveszi a protection genetikai kódot.
3. A labor mezőn a legördülő menüt előhívva kiválasztja a Virológus 1. az "Interact with this field" opciót.
4. Ennek hatására a Virológus 1. megtanulja a felvett genetikai kódot.
5. Virológus 1. a "Craft" menüpontra kattintva kiválasztja a "Protection" ágenst, melyel létrehozza azt.
6. A Virológus 1. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
7. A Virológus 1. a menüpontokból kiválasztja a "Throw an agent" pontot.
8. A Virológus 1. a "Craft" előugró menüpontok közül kiválasztja a már elkészítéett protection ágenst, ezzel a másik játékosra dobja azt.

#### Elvárt kimenet:
- Virológus 1.: az elkészített ágenseinek száma 1-gyel csökkent
- Virológus 2.: protection ágens hatásával rendelkezik.