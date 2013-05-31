import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "raspi-cache"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "postgresql" % "postgresql" % "8.4-701.jdbc4",  
    "org.squeryl" %% "squeryl" % "0.9.5-6"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    testOptions in Test := Nil
    // Add your own project settings here      
  )

}
