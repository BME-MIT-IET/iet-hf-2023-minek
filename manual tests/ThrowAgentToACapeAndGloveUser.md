# Teszt:  Throw Agent To A Cape And Glove User

## Leírás:
Köpenyt és kesztyűt viselő virológus támadása.

## Ellenőrzött funkcionalitás:
Felszerelések viselkedésének demonstrálása ágens kenése közben.

## Teszt:

#### Kiinduló állapot:
- Virológus 1.: egy üres mezőn áll. Van nála vitustánc ágens.
- Virológus 2.: a Virológus 1-gyel megegyező mezőn áll. Van nála egy kesztyű és egy köpeny.

#### Teszt menete:
1. A Virológus 1. bal egér gombbal kattintva a másik játékoson előhozza a legördülő mezőt.
2. A Virológus 1. a menüpontokból kiválasztja a "Throw affect" pontot.

#### Elvárt kimenet:
- Virológus 1.: az ágenseinek száma nem csökkent, nem sikerült a dobás.
- Virológus 2.: a köpeny és a kesztyű megvétte az ágenstől.