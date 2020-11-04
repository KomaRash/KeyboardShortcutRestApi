name := "KeyboardShortcutRestApi"

version := "0.1"

scalaVersion := "2.13.3"
scalacOptions ++= Seq(
  "-unchecked",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "utf8"
)
organization in ThisBuild := "com.github.KomaRash"
lazy val catsDependency = Seq("org.typelevel" %% "cats-core" % "2.0.0",
  "co.fs2" %% "fs2-core" % "2.1.0")
lazy val http4sVersion = "0.21.0"
lazy val http4sDependency = Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.http4s" %% "http4s-scala-xml" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "io.circe" %% "circe-core" % "0.13.0",
  "io.circe" %% "circe-generic" % "0.13.0",
  "io.circe" %% "circe-literal" % "0.13.0",
  "io.circe" %% "circe-parser" % "0.13.0",
  "com.beachape" %% "enumeratum-circe" % "1.6.1",
  "joda-time" % "joda-time" % "2.10.6",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "ch.qos.logback" % "logback-classic" % "1.0.9"
)

libraryDependencies ++= catsDependency
libraryDependencies ++= http4sDependency
