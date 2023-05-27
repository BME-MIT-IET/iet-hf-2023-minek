# Teszt: Hit Bear Virologist With Axe

## Leírás:
Medve vírussal fertőzött virológus megcsapása baltával.

## Ellenőrzött funkcionalitás:
A medve vírussal fertőzött virológust ha megcsapjuk baltával, akkor az a virológus meghal és
a baltánk elhasználódik, ezáltal eltávolítódik a felszereléseink közül.

## Teszt:

#### Kiinduló állapot:
- Virológus 1.: egy üres mezőn áll. Van nála egy balta.
- Virológus 2.: egy medvével megfertőzött labor mezőn áll, ami szomszédos Virológus 1. mezejével. Medvevírusos.

#### Teszt menete:
1. A Virológus 2. bal egér gombbal kattintva a szomszédos mezőn előhozza a legördülő mezőt.
2. A Virológus 2. a menüpontokból kiválasztja a "Move to" pontot.

#### Elvárt kimenet:
- Virológus 1.: eltört a baltája.
- Virológus 2.: megölték a baltával

#### Valódi kimenet:
A balta használata nincs megfelelően lekezelve a játékban, így annak használatát nem lehetett célravezetően tesztelni.