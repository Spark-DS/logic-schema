import sbt.url

organization := "com.github.spark-ds"
name := "logic-schema"
version := "0.0.4"

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

scalaVersion := "2.12.12"
val sparkVersion = "2.4.4"
val sparkSQL = "org.apache.spark" %% "spark-sql" % sparkVersion
val sparkCore = "org.apache.spark" %% "spark-core" % sparkVersion
val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.15.2"

val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

libraryDependencies ++= Seq(sparkCore, sparkSQL, scalaCheck, scalaTest)
