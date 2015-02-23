name := "scaldi-play-example"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-cache" % "2.4.0-M2",
  "org.scaldi" %% "scaldi-play" % "0.5-play2.4-3"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
