# Gradle build integráció

## Gradle telepítése

A Gradle telepítését követően a projekt gyökerében a `gradle init` parancsot kiadva legenerálódtak a szükséges gradle fájlok.
A script alapértelmezett értékekkel töltötte fel a `build.gradle` és a `settings.gradle` fájlokat, valamint létrejött egy 
wrapper script is, a `gradlew`, ami a buildelés menetét hivatott megkönnyíteni. Mivel a gradle a forrásfájlokat a `src/main/java`
mappastruktúrában várja és a projektben ezek egyszerűen csak a `src/` mappában voltak, így a buildelés 
csak a fájlok áthelyezését követően volt sikeres. A buildeléshez a projekt mappájában 
kellett kiadni a `./gradlew build` parancsot, ezt követően pedig a `./gradlew run` paranccsal vált futtathatóvá az alkalmazás.

## Gradle build automatizálása GitHub Actions segítségével

A GitHub repository Actions fülére kattintva megjelentek az elérhető actionök. Ezek közül a "Java with Gradle" opciót választva
létrejött egy `yml` fájl, mely tartalmazta a szükséges beállításokat. A fájlt a `github/workflows/gradle.yml` helyre 
helyezve és a módosításokat commitolva a GitHub minden commit után lefuttatta a gradle build-et a megadott beállításokkal.
Bár az első futás során az actiön hibára futott, a `gradlew` szkript jogosultságait módosítva sikerült a hibát megszüntetni.

# JaCoCo integráció

## JaCoCo telepítése

A JaCoCo telepítéséhez a `build.gradle` fájlba a `plugins` részhez hozzáadtuk a `jacoco` plugint, majd a `test` illetve a `jacocoTestReport`
szekciókat kiegészítettük az [ajánlott beállításokkal](https://docs.gradle.org/current/userguide/jacoco_plugin.html#sec:jacoco_report_configuration). 

## JaCoCo integráció GitHub Actions segítségével

A JaCoCo integrációjához a `build.gradle` fájlban a `jacocoTestReport`
szekcióban a `reports` részhez hozzáadtuk a `csv.required = true` beállítást, majd a `github/workflows/gradle.yml` fájlban
hozzáadtuk a `cicirello/jacoco-badge-generator@v2.8.1` actiont, mely a build után létrehoz egy `jacoco.svg` fájlt, ami a
GitHub Actions fülön megjelenik. A badge megjelenítéséhez a `README.md` fájlba a `jacoco.svg` fájl elérési útját
kellett beilleszteni.
