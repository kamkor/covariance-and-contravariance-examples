
lazy val root = (project in file(".")).
  settings(
    organization := "kamkor",
    version := "1.0.0",
    scalaVersion := "2.11.6",

    name := "covariance-and-contravariance-examples",

    crossPaths := false,

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.2.4" % Test,
      "org.scalacheck" %% "scalacheck" % "1.12.2" % Test,
      "com.novocode" % "junit-interface" % "0.11" % Test
    ),

    testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v"),

    libraryDependencies ++= Seq(
      "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
      "ch.qos.logback" % "logback-classic" % "1.1.2"
    )
  )
