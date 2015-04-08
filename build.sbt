name := "scaldi-play-example"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-cache" % "2.4.0-M3",
  "org.scaldi" %% "scaldi-play" % "0.5-play-2.4.0-M3-4"
)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

lazy val root = (project in file(".")).enablePlugins(PlayScala)
