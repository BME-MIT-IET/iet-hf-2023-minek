# Teszt:  ThrowForgetAgentOnSelfOnSelfProtected

## Leírás:
Felejtő ágens dobása önmagára védelem alatt.

## Ellenőrzött funkcionalitás:
Ágens eltárolása virológusnál, ágens dobása önmagára, ágens kivédése.

## Teszt:

#### Kiinduló állapot:
- Virológus 1.: a virológus egy elkészített protection és egy elkészített forget ágenssel.

#### Teszt menete:
1. A Virológus 1. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
2. A Virológus 1. a menüpontokból kiválasztja a "Throw an agent" pontot.
3. A Virológus 1. a "Craft" előugró menüpontok közül kiválasztja a már elkészített protection ágenst, ezzel magára dobva azt.
4. A Virológus 1. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
5. A Virológus 1. a menüpontokból kiválasztja a "Throw an agent" pontot.
6. A Virológus 1. a "Craft" előugró menüpontok közül kiválasztja a már elkészített forget ágenst, ezzel magára dobva azt.

#### Elvárt kimenet:
- Virológus 1.: a forget hatása nem fog kiterjedni rá