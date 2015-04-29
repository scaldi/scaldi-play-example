name := "scaldi-play-example"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "org.scaldi" %% "scaldi-play" % "0.5.5"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
