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