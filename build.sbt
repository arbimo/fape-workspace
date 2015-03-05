name := "fape-worspace"

lazy val fapePlanning = Project("planning", file("planning")) aggregate(graphs, constraints, anml) dependsOn(graphs, constraints, anml)

lazy val graphs = Project("graphs", file("graphs"))

lazy val constraints = Project("constraints", file("constraints")) aggregate(graphs) dependsOn(graphs)

lazy val anml = Project("anml-parser", file("anml-parser"))




packSettings

packMain := Map(
  "planner" -> "fape.Planning"
)

packJvmOpts := Map(
  "planner" -> Seq("-ea")
)