# Teszt: Warehouse Numb Pick Up

## Leírás

Raktárból anyagfelvétel bénultan.

## Ellenőrzött funkcionalitás

Egy lebénult virológusnak nem szabad tudnia felvennie anyagot egy raktárból ami anyagot tartalmaz, ezáltal az anyagkészlete nem növekszik. Bénult állapotban nem tud semmit sem csinálni.

## Teszt

### Kiinduló állapot

- Virológus 1.: Bénult állapotban van, egy raktár mezőn áll. Nem rendelkezik semmilyen felszereléssel vagy alapanyaggal.

### Teszt menete

1. A Virológus 1. bal kattintással kattint az általa elfoglalt raktár mezőre.
2. Itt a megjelenő listából kiválasztja az "Interact with this field" menüpontot.

#### Elvárt kimenet

- Virológus 1.: Bénult állapotban van, egy raktár mezőn áll. Nem rendelkezik semmilyen felszereléssel vagy alapanyaggal.
