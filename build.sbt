name := "scaldi-play-example"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-cache" % "2.4.0",
  "org.scaldi" %% "scaldi-play" % "0.5.6-RC1",
  "org.specs2" %% "specs2-core" % "3.5" % "test",
  "org.specs2" %% "specs2-junit" % "3.5" % "test"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalacOptions in Test ++= Seq("-Yrangepos")

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

lazy val root = (project in file(".")).enablePlugins(PlayScala)
