rootProject.name = "all"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

// Dynamically create subprojects for each directory in models/
fileTree("models/").files.forEach {
    if (it.isDirectory && !it.isHidden) {
        include(it.name)
        project(":${it.name}").projectDir = it
    }
}
