name := "ZeromqScalaExample"
version := "0.1"
scalaVersion := "2.12.6"
libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5",
  "org.zeromq" %% "jeromq" % "0.4.3" from "http://central.maven.org/maven2/org/zeromq/jeromq/0.4.3/jeromq-0.4.3.jar",
  "joda-time" % "joda-time" % "2.9.9"
)
resolvers += Resolver.sbtPluginRepo("releases")
