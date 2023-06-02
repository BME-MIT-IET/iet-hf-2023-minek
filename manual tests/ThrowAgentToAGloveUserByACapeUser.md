# Teszt: Throw Agent To A Glove User By A Cape User

## Leírás
Ágens kenése kesztyűs virológusra míg nekem van köpenyem.

## Ellenőrzött funkcionalitás
Felszerelések viselkedésének demonstrálása ágens kenése közben.

## Teszt

#### Kiinduló állapot
- Virológus 1.: a virológus egy elkészített bénító ágenssel. Köpeny van rajta.
- Virológus 2.: a Virológus 1-gyel megegyező mezőn áll. Van nála egy kesztyű.

#### Teszt menete
1. A Virológus 1. bal egérgombbal kattintva a mezőn előhozza a legördülő mezőt.
2. A Virológus 1. a menüpontokból kiválasztja a "Throw an agent" pontot.
3. A Virológus 1. a "Craft" előugró menüpontok közül kiválasztja a már elkészített bénító ágenst, ezzel Virológus 2-re dobva azt.

#### Elvárt kimenet
- Virológus 1.: a bénító ágens hatása nem fog kiterjedni rá, az elkészített bénító ágens nem lesz nála.
- Virológus 2.: a bénító ágens hatása nem fog kiterjedni rá