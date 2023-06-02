# Teszt: Learn Genetic Code

## Leírás

Virológus megtanul egy számára új genetikai kódot egy labor faláról.

## Ellenőrzött funkcionalitás

Effekt létrehozása, ágens létrehozása, genetikai kód létrehozása, laboratórium létrehozása, genetikai kód tanulása, megtanult genetikai kód tárolása.

## Teszt

### Kiinduló állapot

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy laboratórium mezőn áll, amelyen megtalálható a "protection" ágens genetikus kódja.

### Teszt menete

1. A Virológus 1. bal kattintással kattint az általa elfoglalt laboratórium mezőre.
2. Itt a megjelenő listából kiválasztja a "Touch this field" opciót, ezzel letapogatva a mezőt.
3. A következő körben a Virológus 1. bal kattintással kattint az általa elfoglalt laboratórium mezőre.
4. Itt a megjelenő listából kiválasztja az "Interact with this field" opciót.

#### Elvárt kimenet

- A jobb felső sarokban az előző akció eredménye jelenik meg. Ez a "protection" ágens genetikus kódjának megtanulása volt.
- A bal alsó sarokban megnyitott craft menüben egyszer szerepel a "protection" ágens genetikus kódja.
- Virológus 1.: Nem fertőzött semmilyen vírussal, egy laboratórium mezőn áll, amelyen megtalálható a "protection" ágens genetikus kódja. A virológus rendelkezik a "protection" ágens genetikus kódjával.
