# Teszt: Bear Steps On Field With Virologists

## Leírás:
Virológus megfertőz más virológusokat a medve vírussal egy másik mezőn.

## Ellenőrzött funkcionalitás:
A medvevírus terjedésének demonstrálása, meg tud fertőzni a medvevírussal ellátott virológus más virológusokat, ha egy másik mezőre látogat (feltéve, hogy a mezőn van másik virológus)

## Teszt:

#### Kiinduló állapot:
- Virológus 1.: medve vírussal fertőzött
- Virológus 2.: Virológus 1.-gyel szomszédos mezőn, nem medve vírusos
- Virológus 3.: Virológus 1.-gyel szomszédos mezőn, Virológus 2-vel megegyező mezőn, nem medve vírusos

#### Teszt menete:
1. A Virológus 1. bal kattintással kattint a szomszédos mezőre, ahol Virológus 2. és Virológus 3. tartózkodik.
2. Itt a legördülő menüből kiválasztja a „Move here” menüpontot, ezzel rálép a szomszédos mezőre.

#### Elvárt kimenet:
- Virológus 1.: medve vírussal fertőzött, egy mezőn áll Virológus 2.-vel és Virológus 3.-mal
- Virológus 2.: Virológus 1.-gyel és Virológus 2.-vel azonos mezőn áll, medve vírussal fertőzött
- Virológus 3.: Virológus 1.-gyel és Virológus 3.-mal azonos mezőn áll, medve vírussal fertőzött