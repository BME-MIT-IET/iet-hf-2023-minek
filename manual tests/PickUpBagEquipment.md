# Teszt: Pick Up Bag Equipment

## Leírás

Zsák felvétele a mezőről.

## Ellenőrzött funkcionalitás

 Equipment letapogatása, equipment felvétele. Annak tesztelése, hogy a virológus fel tudja-e venni a mezőn lévő equipmentet. Egy nem lebénult virológus fel tudja venni egy óvóhelyen állva az ott lévő zsák felszerelést.

## Teszt

### Kiinduló állapot

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy óvóhely mezőn áll. A mezőn van egy zsák.

### Teszt menete

1. A Virológus 1. bal kattintással kattint az általa elfoglalt óvóhely mezőre.
2. Itt a megjelenő listából kiválasztja a "Touch this field" opciót, ezzel letapogatva a mezőt.
3. A következő körben a Virológus 1. bal kattintással kattint az általa elfoglalt óvóhely mezőre.
4. Itt a megjelenő listából kiválasztja a "Interact with this field" opciót.
5. A következő körben a Virológus 1. bal kattintással kattint a jobb alsó sarokban lévő equipments gombra.

#### Elvárt kimenet

- A jobb felső sarokban megjelenik a "Picked up equipment: Bag" felirat.
- A jobb alsó sarokban megnyitott inventoryban megjelenik a zsák ikonja.
- Virológus 1.: Nem fertőzött semmilyen vírussal, egy óvóhely mezőn áll. A virológus rendelkezik egy zsákal.
