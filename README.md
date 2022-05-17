# DragonsGame

clone repository and run via gradle wrapper e.g.
```
gradlew bootRun -Pargs="-targetScore 1000" -Dorg.gradle.java.home="C:/Program Files/Java/jdk-11.0.15"
```
Need to specify path to JDK11 or later as app doesn't seem to run with Java 8 as http requests return 403 FORBIDDEN response due to some missing user-agent headers. Works with Java 11 & Java 16 out of the box though.
