name := "colordiff"
scalaVersion := "2.12.3"
crossScalaVersions := Seq("2.11.11")
libraryDependencies ++= Seq(
  "com.googlecode.java-diff-utils" % "diffutils" % "1.3.0",
  "org.scalatest" %% "scalatest" % "3.0.3" % Test
)

scalafmtOnCompile in ThisBuild := true

releaseCrossBuild := true
//format off
publishTo := {
  if (isSnapshot.value)
    Some(
      "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
  else
    Some(
      "releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
}
//format on
