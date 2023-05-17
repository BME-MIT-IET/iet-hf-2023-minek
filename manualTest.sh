#!/bin/bash

./gradlew build -x test;
java -classpath ./build/classes/java/main szoftlab.main.TestMain;
read
