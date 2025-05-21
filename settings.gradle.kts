rootProject.name = "all"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

// Dynamically create subprojects for each model defined in the properties file
settings.extra.properties.forEach {
    if (it.key.startsWith("model.") && it.key.endsWith(".version")) {
        val project = it.key.substring(6, it.key.length - 8)
        // Skip the "all" project, it is set up in the root gradle file
        if (project != "all") {
            include(project)
            project(":${project}").projectDir = file("models/${project}")
        }
    }
}
