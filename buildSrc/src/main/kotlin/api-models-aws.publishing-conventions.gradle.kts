plugins {
    `maven-publish`
}

group = "software.amazon.api.models"
version = project.property("model.${project.name}.version")!!

publishing {
    repositories {
        // JReleaser's `publish` task publishes to all repositories, so only configure one.
        maven {
            name = "localStaging"
            url = rootProject.layout.buildDirectory.dir("staging").get().asFile.toURI()
        }
    }

    publications {
        create<MavenPublication>("maven") {
            if (project != rootProject) {
                from(components["java"])
            } else {
                from(components["javaPlatform"])
            }
        }
    }
}