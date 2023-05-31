# Non-functional tests

## Performance

A teszteléshez a [VisualVM](https://visualvm.github.io/) profiling programot használtuk. Ezzel a program futása közbeni CPU időt és memória használatot mértük. A teszt során bebizonyosodott az a feltételezésünk, hogy a játék erőforrás igényei nagyon minimálisak, ennek ellenére jelentős optimalizáció (~20%) lenne elérhető a fejlesztési folyamatokban használt, de a végleges játékhoz nem szükséges logolás kikapcsolásával.

## Security

A teszteléshez a [CodeQL](https://securitylab.github.com/tools/codeql) statikus kódelemzőt használtuk. A tesztelés során nem találtunk semmilyen sérülékenységet. Továbbá megállapítottuk, hogy a Java nyelv és a JVM biztonsági funkciói is jelentős mértékben hozzájárulnak a játék biztonságához. Természetesen az sem elhanyagolandó, hogy a játék offline, így nem áll szemben semmilyen külső támadással. Az egyetlen terület ami tényleg sebezhetőnek bizonyult, a csalás volt, ami könnyedén megnehezíthető egy kör vége után megjelenő köztes képernyővel.

## Usability

A dokumentumban a felhasználói felület használhatóságát vizsgáltuk, ami ergonómiai szempontból számos könnyen javítható problémát tartalmaz. A menü és maga a játékmenet egyaránt tartalmazott használhatóság szempontjából kissé meggondolatlan elemeket és layoutokat, ezekre részletes kritikát és kézenfekvő megoldási tervet is adtunk. Leginkább kritikus problémaként kiemelendő a játékmezőn való navigáció, ezen belül is a tábla mazőinek hitboxa, amely jelentősen rontotta a játkélményt.

## Portability

A játék hordozhatósága kapcsán a Java nyelv és a JVM platformfüggetlenségét taglaltuk. Kitértünk rá, hogy miért az egyik legjobb választás ezen a téren a Java, melynek "Írd meg egyszer, futtasd bárhol" szlogenje pont erről szól.
