# Teszt: Agent Gets Older - Numb

## Leírás:
A virológuson lévő védő (Numb) ágens élettartamának csökkentése.

## Ellenőrzött funkcionalitás:
Mivel az effekteknek limitált időtartamig van hatásuk, ezeket csökkenteni kell körönként, hogy ne maradjon a virológuson a végtelenségig a hatásuk.

## Teszt:

#### Kiinduló állapot:
- Virológus 1.: egy üres mezőn áll, a bénító ágens hatása alatt van.

#### Teszt menete:
1. A Virológus 1. bal kattintással kattint a szomszédos mezőre.
2. Itt a legördülő menüből kiválasztja a „Move here” menüpontot, ezzel nem történik semmi mert bénult.
3. Eltelik 3 kör.
4. A Virológus 1. bal kattintással kattint a szomszédos mezőre.
5. Itt a legördülő menüből kiválasztja a „Move here” menüpontot, ezzel rálép a szomszédos mezőre.

#### Elvárt kimenet:
- Virológus 1.: a körök végére a bénítés hatása elmúlik.