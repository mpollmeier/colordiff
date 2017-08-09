name := "colordiff"
organization := "com.michaelpollmeier"
scalaVersion := "2.12.3"
crossScalaVersions := Seq("2.11.11")
libraryDependencies ++= Seq(
  "com.googlecode.java-diff-utils" % "diffutils" % "1.3.0",
  "org.scalatest" %% "scalatest" % "3.0.3" % Test
)

scalafmtOnCompile in ThisBuild := true

publishTo := { // format: off
  if (isSnapshot.value) Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
  else Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
} 
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }
pomExtra :=
  <scm>
    <url>git@github.com:mpollmeier/colordiff.git</url>
    <connection>scm:git:git@github.com:mpollmeier/colordiff.git</connection>
  </scm>
    <developers>
      <developer>
        <id>mpollmeier</id>
        <name>Michael Pollmeier</name>
        <url>http://www.michaelpollmeier.com</url>
      </developer>
    </developers>
// format: on

import ReleaseTransformations._
releaseCrossBuild := true
releasePublishArtifactsAction := PgpKeys.publishSigned.value
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _)),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _)),
  pushChanges
)
