name := "colordiff"
organization := "com.michaelpollmeier"
scalaVersion := "2.13.1"
crossScalaVersions := Seq("2.10.6", "2.11.12", "2.12.8", "2.13.1")
libraryDependencies ++= Seq(
  "com.googlecode.java-diff-utils" % "diffutils" % "1.3.0",
  "org.scalatest" %% "scalatest" % "3.0.8" % Test
)
enablePlugins(GitVersioning)

scmInfo := Some(
  ScmInfo(url("https://github.com/mpollmeier/colordiff"),
          "scm:git@github.com:mpollmeier/colordiff.git"))
developers := List(
  Developer("mpollmeier",
            "Michael Pollmeier",
            "michael@michaelpollmeier.com",
            url("https://michaelpollmeier.com")))
homepage := Some(url("https://github.com/mpollmeier/colordiff"))
licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))
publishTo := sonatypePublishToBundle.value
Global/useGpgPinentry := true
