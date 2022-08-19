ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "Concurrency-In-ZIO"
  )
resolvers += Resolver.sonatypeRepo("snapshots")
libraryDependencies += "dev.zio" %% "zio" % "1.0.15+3-dae3104f-SNAPSHOT"