import org.jreleaser.model.Active

plugins {
    `java-platform`
    `maven-publish`
    alias(libs.plugins.jreleaser)
    id("api-models-aws.publishing-conventions")
}

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
            nexus2 {
                create("maven-central") {
                    active = Active.ALWAYS
                    url = "https://aws.oss.sonatype.org/service/local"
                    snapshotUrl = "https://aws.oss.sonatype.org/content/repositories/snapshots"
                    closeRepository.set(true)
                    releaseRepository.set(true)
                    verifyPom.set(true)
                    stagingRepository(rootProject.layout.buildDirectory.dir("staging").get().asFile.path)
                }
            }
        }
    }
}
