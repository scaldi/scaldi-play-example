name := "scaldi-play-example"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  guice,
  ehcache,
  "org.scaldi" %% "scaldi-play" % "0.5.17",
  "org.specs2" %% "specs2-core" % "4.2.0" % "test",
  "org.specs2" %% "specs2-junit" % "4.2.0" % "test"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

routesGenerator := InjectedRoutesGenerator

lazy val root = (project in file(".")).enablePlugins(PlayScala)