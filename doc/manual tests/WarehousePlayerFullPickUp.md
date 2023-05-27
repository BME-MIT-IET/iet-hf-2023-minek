# Teszt: Warehouse Player Full Pick Up

## Leírás:
Raktárból anyag felvétele úgy, hogy a játékos megtelt.

## Ellenőrzött funkcionalitás:
A virológus raktárkészlete megtelt, viszont megpróbálja felvenni a raktárban található
anyagot. Nem fog neki sikerülni, mivel ha tele a raktárkészlete nem fogja tudni elraktározni a
felvett anyagot. Anyagkészlete se nem növekszik se nem csökken.

## Teszt:

#### Kiinduló állapot:
- Virológus 1.: egy warehouse mezőn áll, ami aminosavot tartalmaz. A virológus zsebe teljesen teli van mind aminosavval, mind pedig nukleotiddal.

#### Teszt menete:
1. A Virológus 1. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
2. A Virológus 1. a menüpontokból kiválasztja a "Touch this field" pontot.
3. A Virológus 1. a menüpontokból kiválasztja a "Interract with field" pontot.

#### Elvárt kimenet:
- Virológus 1.: a birtokolt aminosavak és nukleotidok száma egyáltalán nem változott.