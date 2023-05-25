# Teszt: Chorea Agent Crafting With Enough

## Leírás:
Vitustánc ágens kraftolása elegendő nyersanyaggal.

## Ellenőrzött funkcionalitás:
Új ágens létrehozása, anyag, ágens tárolása virológusnál, genetiaki kód tanulás.

## Teszt:

#### Kiinduló állapot:
- Virológus 1.: egy labor mezőn áll, amelyen vitustánc genetikai kód található. Rendelkezik aminosavval és nukleotiddal, ami az ágensek készítéséhez elegendő.

#### Teszt menete:
1. A Virológus 1. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
2. A Virológus 1. a menüpontokból kiválasztja a "Touch this field" pontot.
3. A Virológus 1. a menüpontokból kiválasztja a "Interract with field" pontot.
4. Ezzel megtanulta a vitustánc genetaki kódot.
5. A Virológus 1. bal egér gombbal kattintva a "Craft" mezőn előhozza a legördülő mezőt.
6. Ebből kiválasztja a vitustáncot.

#### Elvárt kimenet:
- Virológus 1.: elkészítette az ágenst, a nukleotidjai és aminosavjai a készítést követően csökkentek.