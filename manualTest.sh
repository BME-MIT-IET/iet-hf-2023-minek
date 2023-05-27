#!/bin/bash

./gradlew build -x test;
java -classpath ./build/classes/java/test/szoftlab/main TestMain;
read
