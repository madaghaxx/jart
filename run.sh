#!/bin/bash
javac -d ./build Main.java src/*.java
java -cp  build Main
