name := "scaldi-play-example"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "org.scaldi" %% "scaldi-play" % "0.5-play2.4-1"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
