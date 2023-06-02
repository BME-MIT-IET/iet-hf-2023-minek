# Teszt: Protection Agent Crafting From Bag

## Leírás

Védő ágens készítése zsákban tárolt anyagból.

## Ellenőrzött funkcionalitás

Új ágens létrehozása, anyag, ágens tárolása virológusnál. A virológus sikeresen létrehozza a védő ágenst, így a zsákjában lévő anyag mennyisége nullára csökken, a védő ágens pedig hozzáadódik a kraftolt ágenseinek listájához.

## Teszt

### Kiinduló állapot

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Rendelkezik a védő ágens genetikus kódjával, egy táskával, benne 1 aminosav és 1 nukleotid nyersanyaggal.

### Teszt menete

1. A Virológus 1. bal kattintással kattint a craft gombra.
2. Itt a megjelenő listából kiválasztja a "Protection" ágenst ezzel megkísérelve annak kraftolását.

#### Elvárt kimenet

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Rendelkezik a védő ágens genetikus kódjával, egy táskával és egy lecraftolt felejtő ágenssel.
