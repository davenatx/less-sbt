sbtPlugin := true

organization := "me.lessis"

name := "less-sbt"

sbtVersion in Global := "0.13.0-Beta2"

scalaVersion in Global := "2.10.2-RC2"

version <<= sbtVersion(v =>
  if (v.startsWith("0.11") || v.startsWith("0.12") || v.startsWith("0.13")) "0.1.11-SNAPSHOT"
  else error("unsupported sbt version %s" format v)
)

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies += "org.mozilla" % "rhino" % "1.7R3"

seq(scriptedSettings:_*)

seq(lsSettings:_*)

(LsKeys.tags in LsKeys.lsync) := Seq("sbt", "less")

description := "Sbt plugin for compiling Less CSS sources"

// ls bug https://github.com/softprops/ls/issues/54
//(externalResolvers in LsKeys.lsync) <<= (publishTo) map { _.get :: Nil }

homepage :=
  Some(url("https://github.com/softprops/less-sbt"))

//publishTo := Some(Resolver.url("sbt-plugin-releases", url(
//  "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"
//))(Resolver.ivyStylePatterns))

publishTo := Some(Classpaths.sbtPluginReleases) 

publishMavenStyle := false

publishArtifact in Test := false

licenses <<= version(v=>Seq("MIT" -> url(
  "https://github.com/softprops/less-sbt/blob/%s/LICENSE" format v)))

pomExtra := (
  <scm>
    <url>git@github.com:softprops/less-sbt.git</url>
    <connection>scm:git:git@github.com:softprops/less-sbt.git</connection>
  </scm>
  <developers>
    <developer>
      <id>softprops</id>
      <name>Doug Tangren</name>
      <url>https://github.com/softprops</url>
    </developer>
  </developers>
)
