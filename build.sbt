import Dependencies._

enablePlugins(ScalaJSPlugin)

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.rhdzmota",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "scalajs-hello-world",

    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.5",
      "org.querki" %%% "jquery-facade" % "1.2",
      "com.lihaoyi" %%% "utest" % "0.6.3" % "test"
    ),

    jsDependencies += "org.webjars" % "jquery" % "2.2.1" / "jquery.js" minified "jquery.min.js",
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    skip in packageJSDependencies := false,
    scalaJSUseMainModuleInitializer := true,

    testFrameworks += new TestFramework("utest.runner.Framework")
  )
