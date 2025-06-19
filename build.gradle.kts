import org.jreleaser.model.Active

plugins {
    `java-platform`
    `maven-publish`
    alias(libs.plugins.jreleaser)
    id("api-models-aws.publishing-conventions")
}

description = "This module contains the Smithy model (JSON AST) for all AWS services."
extra["displayName"] = "Software :: Amazon :: API :: Models"
subprojects {
    afterEvaluate {
        apply {
            plugin("api-models-aws.model-conventions")
        }
    }
}

// Configuration for the "all" package (which depends on all the subprojects)
dependencies {
    constraints {
        subprojects.forEach {
            api(it)
        }
    }
}

tasks {
    build {
        mustRunAfter(clean)
    }
}

/*
 * Jreleaser (https://jreleaser.org) config.
 */
jreleaser {
    dryrun = false

    // Used for creating a tagged release, uploading files and generating changelog.
    // In the future we can set this up to push release tags to GitHub, but for now it's
    // set up to do nothing.
    // https://jreleaser.org/guide/latest/reference/release/index.html
    release {
        generic {
            enabled = true
            skipRelease = true
        }
    }

    // Used to announce a release to configured announcers.
    // https://jreleaser.org/guide/latest/reference/announce/index.html
    announce {
        active = Active.NEVER
    }

    // Signing configuration.
    // https://jreleaser.org/guide/latest/reference/signing.html
    signing {
        active = Active.ALWAYS
        armored = true
    }

    // Configuration for deploying to Maven Central.
    // https://jreleaser.org/guide/latest/examples/maven/maven-central.html#_gradle
    deploy {
        maven {
            mavenCentral {
                create("maven-central") {
                    active = Active.ALWAYS
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository(rootProject.layout.buildDirectory.dir("staging").get().asFile.path)
                    maxRetries = 100
                    retryDelay = 30
                }
            }
        }
    }
}
