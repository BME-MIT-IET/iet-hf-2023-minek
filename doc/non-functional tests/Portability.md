# Portability

A hordozhatóság azt takarja, hogy egy adott nyelven írt program mennyire futtatható 
ugyanúgy más platformokon, milyen könnyen vihető az át egyik környezetből a másikba.

A program Java nyelven íródott, mely a platformfüggetlen mivolta miatt a
hordozhatóságot nagy mértékben elősegíti.

## Java

A Java egyik szlogenje, az "Írd meg egyszer, futtasd bárhol" pontosan leírja, hogy a nyelv megalkotásának
céljai között is nagy szerepet játszott a hordozhatóság. Ez itt is jól működik, a program fut
Windows-on, MacOS-en és Linuxon is egyaránt.

## CPU

A Java fordítóprogramok a forráskódot úgynevezett Java bájtkódra fordítja. Más nyelveknél ez egy adott CPU-nak megfelelő
kód lenne, viszont a Java bájtkód egy "nem létező" CPU-ra fordul. Ezt a kódot ezután egy Java Virtual Machine (JVM)
lefuttatja egy java virtuális gépen. Így a különböző CPU-k szempontjából hordozható a programunk.

## Operációs rendszer, grafikus megjelenítés

A legtöbb nyelv a Windowson írt programot nem könnyen portolja más (Unix, Macintosh) környezetbe, viszont a 
Java ezt a problémát megoldotta. A Java könyvtárai olyan függvényeket tartalmaz, mely úgymond egy képzeletbeli
operációs rendszerrel kommunikál. Ehhez hasonlóan működik a grafikus felhasználói felület is. Mivel minden
Java implementáció tartalmaz olyan könyvtárakat, amelyek ezt a virtuális operációs rendszert/GUI-t megvalósítják,
így a program hordozhatósága szempontjából itt se látok semmilyen problémát.

## Konklúzió

A Java programunk bájtkódjai több platformra is átvihetőek, és a platformnak megfelelő JVM-en tudjuk azt futtatni, 
A programunk hordozható.
