import sbt.url

organization := "com.github.spark-ds"
name := "logic-schema"
version := "0.0.5"

homepage := Option(url("https://github.com/spark-ds/logic-schema"))
licenses := Seq("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0"))
description := """spark-ds/logic-schema is a Scala library for data specification and validation across dataframes(hence logic schema), to help developing applications based on Apache Spark."""
scmInfo := Option(ScmInfo(
    url("https://github.com/spark-ds/logic-schema"),
    "scm:git@github.com:spark-ds/logic-schema.git"))
developers := List(
    Developer(
        id    = "dj707chen",
        name  = "DJ Chen",
        email = "@dj707chen",
        url   = url("https://github.com/dj707chen")
    )
)

publishTo := sonatypePublishToBundle.value
sonatypeRepository := "https://s01.oss.sonatype.org/service/local"
sonatypeCredentialHost := "s01.oss.sonatype.org"

scalaVersion := "2.12.13"   // Released on January 12, 2021
val sparkVersion = "3.1.1"  // Released on Mar 02, 2021
val sparkSQL = "org.apache.spark" %% "spark-sql" % sparkVersion
val sparkCore = "org.apache.spark" %% "spark-core" % sparkVersion
val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.15.3"   // 16-02-2021 Maven central
val scalaLogging ="com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"

// val scalaTest = "org.scalatest" %% "scalatest" % "3.2.6" % Test  // 06-Mar-2021, Removed FlatSpec or FeatureSpec, will not compile
val scalaTest = "org.scalatest" %% "scalatest" % "3.1.4" % Test  // 28-Aug-2020

libraryDependencies ++= Seq(
    sparkCore,
    sparkSQL,
    scalaCheck,
    scalaLogging,
    scalaTest,
    logback
)
