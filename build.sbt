name := "scaldi-play-example"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-cache" % "2.5.0",
  "org.scaldi" %% "scaldi-play" % "0.5.14",
  "org.specs2" %% "specs2-core" % "3.5" % "test",
  "org.specs2" %% "specs2-junit" % "3.5" % "test"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

routesGenerator := InjectedRoutesGenerator

lazy val root = (project in file(".")).enablePlugins(PlayScala)