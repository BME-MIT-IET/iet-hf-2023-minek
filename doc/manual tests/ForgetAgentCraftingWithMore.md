# Teszt: Forget Agent Crafting With More

## Leírás

Felejtő ágens kraftolása több, mint elegendő nyersanyaggal

## Ellenőrzött funkcionalitás

Új ágens létrehozása, anyag, ágens tárolása virológusnál. A virológus sikeresen létrehozza a felejtő ágenst, ami bekerül a kraftolt ágensei közé, anyagának mennyisége pedig annyival csökken, amennyi a felejtő ágens kraftolásához szükséges volt.

## Teszt

### Kiinduló állapot

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Rendelkezik a felejtő ágens genetikus kódjával, több mint elegendő (1 aminosav, 1 nukleotid) nyersanyaggal egy felejtő ágens kraftolásához.

### Teszt menete

1. A Virológus 1. bal kattintással kattint a craft gombra.
2. Itt a megjelenő listából kiválasztja a "Forget" ágenst ezzel megkísérelve annak kraftolását.

#### Elvárt kimenet

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy üres mezőn áll. Rendelkezik a felejtő ágens genetikus kódjával, a kiinduló állapothoz képest 1 aminosavval és 1 nukleotiddal kevesebb nyersanyaggal és egy lecraftolt felejtő ágenssel.

## Teszt eredménye

Tesztelő: Farkasházi Levente

Eredmény: Sikertelen

Probléma: A teszt során a virológus sikeresen létrehozta a felejtő ágenst, ami bekerült a kraftolt ágensei közé, anyagának mennyisége pedig annyival csökkent, amennyi a felejtő ágens kraftolásához szükséges volt. Ennek ellenére virológus által birtokolt nyersanyagok mennyisége nem változott a felhasználói felületen. Csak a következő akció után változott meg a mennyiség.
