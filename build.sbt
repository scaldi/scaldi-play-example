name := "scaldi-play-example"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.scaldi" %% "scaldi" % "0.5-SNAPSHOT"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
