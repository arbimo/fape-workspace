name := "fape-all"

lazy val fapePlanning = Project("planning", file("planning")) aggregate(graphs, constraints, anml) dependsOn(graphs, constraints, anml)

lazy val graphs = Project("graphs", file("graphs"))

lazy val constraints = Project("constraints", file("constraints")) aggregate(graphs) dependsOn(graphs)

lazy val anml = Project("anml-parser", file("anml-parser"))

lazy val fapeActing = Project("fape-acting", file("fape-acting")) dependsOn(svgPlot, fapePlanning, rosScala) aggregate(svgPlot, fapePlanning, rosScala)

lazy val rosScala = Project("ros-scala", file("ros-scala"))

lazy val svgPlot = Project("svg-plot", file("svg-plot"))



packSettings

packMain := Map(
  "planner" -> "fape.Planning",
  "actor" -> "fape.scenarios.generic.Main"
)

packJvmOpts := Map(
  "planner" -> Seq("-ea"),
  "actor" -> Seq("-ea")
)