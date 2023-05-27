# Teszt: Agent Gets Older - Forget

## Leírás:
A virológuson lévő Felejtő (Forget) ágens élettartamának csökkentése.

## Ellenőrzött funkcionalitás:
Mivel az effekteknek limitált időtartamig van hatásuk, ezeket csökkenteni kell körönként,
hogy ne maradjon a virológuson a végtelenségig a hatásuk.

## Teszt:

#### Kiinduló állapot:
- Virológus 1.: egy üres mezőn áll.
- Virológus 2.: a Virológus 1-gyel megegyező mezőn áll. Van nála egy forget ágens.

#### Teszt menete:
1. A Virológus 2. bal egér gombbal kattintva a másik játékoson előhozza a legördülő mezőt.
2. A Virológus 2. a menüpontokból kiválasztja a "Throw affect" pontot.
3. A Virológus 1. kiválasztja a Craft menüből a Forget ágenst.
4. A Virológus 1. elfelejtette a megtanult genetikai kódokat.
5. A Virológus 1. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
6. A Virológus 1. a menüpontokból kiválasztja a "Touch this field" pontot.
7. A Virológus 1. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
8. A Virológus 1. a menüpontokból kiválasztja a "Touch this field" pontot.

#### Elvárt kimenet:
- Virológus 1.: a körök végére a felejtés hatása elmúlt.
- Virológus 2.: a forget ágenseinek száma 1-gyel csökkent.