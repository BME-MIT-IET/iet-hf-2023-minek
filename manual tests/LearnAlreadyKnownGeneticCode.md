# Teszt: Learn Already Known Genetic Code

## Leírás

Már megtanult genetikus kódot megpróbálja a virológus újra megtanulni.

## Ellenőrzött funkcionalitás

A virológus nem tud megtanulni egy már megtanult genetikus kódot. Nem történik semmi. Nem szabad ismételten megtanulnia ezáltal duplikálnia a tudását a virológusnak.

## Teszt

### Kiinduló állapot

- Virológus 1.: Nem fertőzött semmilyen vírussal, egy laboratórium mezőn áll, amelyen megtalálható a felejtő ágens genetikus kódja. A virológus rendelkezik a felejtő ágens genetikus kódjával.

### Teszt menete

1. A Virológus 1. bal kattintással kattint az általa elfoglalt laboratórium mezőre.
2. Itt a megjelenő listából kiválasztja a "Touch this field" opciót, ezzel letapogatva a mezőt.
3. A következő körben a Virológus 1. bal kattintással kattint az általa elfoglalt laboratórium mezőre.
4. Itt a megjelenő listából kiválasztja a "Interact with this field" opciót.

#### Elvárt kimenet

- A jobb felső sarokban az előző akció eredménye jelenik meg. Ha ez nem a felejtő ágens genetikus kódjának megtanulása volt akkor ez mindenképpen eltérő a "Learned genetic code: Forget" felirattól.
- A bal alsó sarokban megnyitott craft menüben egyszer szerepel a felejtő ágens genetikus kódja.
- Virológus 1.: Nem fertőzött semmilyen vírussal, egy laboratórium mezőn áll, amelyen megtalálható a felejtő ágens genetikus kódja. A virológus rendelkezik a felejtő ágens genetikus kódjával.
