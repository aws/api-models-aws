rootProject.name = "all"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

// Dynamically create subprojects for each directory in models/
file("models/").listFiles()?.forEach {
    include(it.name)
    project(":${it.name}").projectDir = it
}
