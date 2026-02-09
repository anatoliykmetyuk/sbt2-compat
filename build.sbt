name := "sbt2-compat"
organization := "com.github.sbt"
version := "0.1.0-SNAPSHOT"
description := "A compatibility library; provides a unified API for sbt plugins cross-building for sbt 1.x and sbt 2.x"

def scala212 = "2.12.20"
def scala3 = "3.7.3"
scalaVersion := scala3
crossScalaVersions := Seq(scala3, scala212)

enablePlugins(SbtPlugin)

(pluginCrossBuild / sbtVersion) := {
  scalaBinaryVersion.value match {
    case "2.12" => "1.5.8"
    case _      => "2.0.0-RC7"
  }
}

Compile / scalacOptions ++= {
  scalaBinaryVersion.value match {
    case "2.12" => Seq("-Xsource:3", "-feature", "-unchecked")
    case _      => Seq("-feature", "-unchecked")
  }
}
