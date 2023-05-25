# Teszt: Move To Field With Chorea

## Leírás

Virológus choreával fertőzött mezőre mozog.

## Ellenőrzött funkcionalitás

Virológus mozgása, virológus megkenése ágenssel, chorea ágens működése.
A virológus megfertőződik vitustánccal, de továbbra sem tud olyan mezőre lépni, amely nem szomszédos a jelenlegi pozíciójával.

## Teszt

### Kiinduló állapot

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Az egyik vele szomszédos, szintén üres mező choreával fertőzött.

### Teszt menete

1. A Virológus 1. bal kattintással kattint a szomszédos mezőre, amely choreával fertőzött.
2. Itt a legördülő menüből kiválasztja a „Move here” menüpontot, ezzel rálép a szomszédos mezőre.
következő kör:
3. A Virológus 1. bal kattintással kattint egy vele szomszédos mezőre.
4. Itt a legördülő menüből kiválasztja a „Move here” menüpontot, ezzel megpróbálva rálépni.
Következő kör:
5. A Virológus 1. bal kattintással kattint egy vele nem szomszédos mezőre.

#### Elvárt kimenet

- Virológus 1.: chorea vírussal fertőzött, egy a fertőzött mezővel szomszédos mezőn áll, amely bármelyik ilyen mező lehet. A kattintás hatására a nem szomszédos mezőn nem jelenik meg legördülő menü.
