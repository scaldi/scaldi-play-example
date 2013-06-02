import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "scaldi-play-example"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    jdbc,
    anorm,
    "com.github.scaldi" %% "scaldi-play" % "0.2"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings()
}
