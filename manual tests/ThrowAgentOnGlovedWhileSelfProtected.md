# Teszt: Throw Agent On Gloved While Self Protected

## Leírás
Bénító ágens dobása kesztyűs virológusra, védelem alatt.

## Ellenőrzött funkcionalitás
Ágens dobása másra, ágens visszadobása, ágens kivédése.

## Teszt

#### Kiinduló állapot
- Virológus 1.: a virológus egy elkészített bénító ágenssel. A védő ágens hatása hat rajta.
- Virológus 2.: a Virológus 1-gyel megegyező mezőn áll. Van nála egy kesztyű.

#### Teszt menete
1. A Virológus 1. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
2. A Virológus 1. a menüpontokból kiválasztja a "Throw an agent" pontot.
3. A Virológus 1. a "Craft" előugró menüpontok közül kiválasztja a már elkészített bénító ágenst, ezzel Virológus 2-re dobva azt.

#### Elvárt kimenet
- Virológus 1.: a bénító ágens hatása nem fog kiterjedni rá, az elkészített bénító ágens nem lesz nála.
- Virológus 2.: a bénító ágens hatása nem fog kiterjedni rá