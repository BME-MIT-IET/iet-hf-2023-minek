# Teszt: Forget Agent Crafting From Bag

## Leírás

Felejtő ágens készítése zsákban tárolt anyagból.

## Ellenőrzött funkcionalitás

Új ágens létrehozása, anyag, ágens tárolása virológusnál. A virológus sikeresen létrehozza a bénító ágenst, így a zsákjában lévő anyag mennyisége nullára csökken, a bénító ágens pedig hozzáadódik a kraftolt ágenseinek listájához

## Teszt

### Kiinduló állapot

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Rendelkezik a felejtő ágens genetikus kódjával, egy táskával, benne 1 aminosav és 1 nukleotid nyersanyaggal.

### Teszt menete

1. A Virológus 1. bal kattintással kattint a craft gombra.
2. Itt a megjelenő listából kiválasztja a "Forget" ágenst ezzel megkísérelve annak kraftolását.

#### Elvárt kimenet

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Rendelkezik a felejtő ágens genetikus kódjával, egy táskával és egy lecraftolt felejtő ágenssel.
