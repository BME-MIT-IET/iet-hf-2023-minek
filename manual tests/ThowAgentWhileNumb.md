# Teszt: ThrowAgentWhileNumb

## Leírás:
Bénult virológus ágenst próbál dobni egy másik virológusra.

## Ellenőrzött funkcionalitás:
Új effekt létrehozása, mező
létrehozása, effekt hozzáadása virológushoz, ágens dobása, bénító effekt hatásossága.

## Teszt:

#### Kiinduló állapot:
- Virológus 1.: egy labor mezőn áll, amely tartalmaz egy numb genetikai kódot. A virológus rendelkezik az ágens elkészítéséhez szükséges aminosavval és nukleotiddal. Numb-bsn sebzett.
- Virológus 2.: Virológus 1.-gyel megegyező mezőn áll.

#### Teszt menete:
1. A Virológus 1. bal kattintással kattint a labor mezőre
2. Itt a legördülő menüből kiválasztja a „Touch this field” menüpontot, ezzel felveszi a numb genetikai kódot.
3. A labor mezőn a legördülő menüt előhívva kiválasztja a Virológus 1. az "Interact with this field" opciót.
4. Ennek hatására a Virológus 1. megtanulja a felvett genetikai kódot.
5. Virológus 1. a "Craft" menüpontra kattintva kiválasztja a "Numb" ágenst, melyel létrehozza azt.
6. A Virológus 1. bal egér gombbal kattintva a mezőn előhozza a legördülő mezőt.
7. A Virológus 1. a menüpontokból kiválasztja a "Throw an agent" pontot.
8. A Virológus 1. a "Craft" előugró menüpontok közül kiválasztja a már elkészítéett numb ágenst, ezzel a másik játékosra dobja azt.

#### Elvárt kimenet:
- Virológus 1.: az elkészített ágenseinek száma nem csökken
- Virológus 2.: nem került rá a numb ágens hatása