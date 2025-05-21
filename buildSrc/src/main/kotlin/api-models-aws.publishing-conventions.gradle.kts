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

            afterEvaluate {
                pom {
                    name.set(project.ext["displayName"].toString())
                    description.set(project.description)
                    url.set("https://github.com/aws/api-models-aws")
                    licenses {
                        license {
                            name.set("Apache License 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                            distribution.set("repo")
                        }
                    }
                    developers {
                        developer {
                            id.set("aws")
                            name.set("AWS")
                            organization.set("Amazon Web Services")
                            organizationUrl.set("https://aws.amazon.com")
                            roles.add("developer")
                        }
                    }
                    scm {
                        url.set("https://github.com/aws/api-models-aws.git")
                    }
                }
            }
        }
    }
}
