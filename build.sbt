name := "fape-workspace"

scalaVersion := "2.10.5"

lazy val fapePlanning = Project("planning", file("planning")) aggregate(graphs, constraints, anml, svgPlot) dependsOn(graphs, constraints, anml, svgPlot)

lazy val graphs = Project("graphs", file("graphs"))

lazy val constraints = Project("constraints", file("constraints")) aggregate(graphs) dependsOn(graphs)

lazy val anml = Project("anml-parser", file("anml-parser")) aggregate(graphs) dependsOn(graphs)

lazy val svgPlot = Project("svg-plot", file("svg-plot"))

libraryDependencies ++= Seq(
  "net.openhft" % "koloboke-api-jdk6-7" % "0.6.7" % "runtime")


packSettings

packMain := Map(
  "planner" -> "fape.Planning",
  "planning-server" -> "fape.Server"
)

packJvmOpts := Map(
  "planner" -> Seq("-ea"),
  "planning-server" -> Seq()
)