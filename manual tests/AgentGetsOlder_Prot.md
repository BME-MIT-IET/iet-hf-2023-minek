# Teszt: Agent Gets Older - Protection

## Leírás:
A virológuson lévő védő (Protection) ágens élettartamának csökkentése.

## Ellenőrzött funkcionalitás:
Mivel az effekteknek limitált időtartamig van hatásuk, ezeket csökkenteni kell körönként, hogy ne maradjon a virológuson a végtelenségig a hatásuk.

## Teszt:

#### Kiinduló állapot:
- Virológus 1.: egy üres mezőn áll, a védő ágens hatása alatt van.
- Virológus 2.: a Virológus 1-gyel megegyező mezőn áll. Van nála egy felejtő ágens.

#### Teszt menete:
1. Eltelik 3 kör.
2. A Virológus 2. bal egérgombbal kattintva a másik játékoson előhozza a legördülő mezőt.
3. A Virológus 2. a menüpontokból kiválasztja a "Throw an agent" pontot.
4. A Virológus 1. elfelejtette a megtanult genetikai kódokat.

#### Elvárt kimenet:
- Virológus 1.: a körök végére a felejtés hatása alá került, a védő effekt nem védte meg.
- Virológus 2.: a felejtés ágenseinek száma 1-gyel csökkent.