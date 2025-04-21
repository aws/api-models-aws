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
