## colordiff for scala
[![Build Status](https://github.com/mpollmeier/colordiff/workflows/release/badge.svg)](https://github.com/mpollmeier/colordiff/actions?query=workflow%3Arelease)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.michaelpollmeier/colordiff_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.michaelpollmeier/colordiff_2.12)

Unix colordiff:
![unix](unix-diff.png)

Scala colordiff:
![scala](scala-colordiff.png)

## Usage
```scala
val original = List("changeme")
val revised = List("CHANGED")
colordiff.ColorDiff(original, revised)
```


