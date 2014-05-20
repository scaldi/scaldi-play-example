name := "scaldi-play-example"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.0"

libraryDependencies ++= Seq(
  "org.scaldi" %% "scaldi-play" % "0.3.3"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
