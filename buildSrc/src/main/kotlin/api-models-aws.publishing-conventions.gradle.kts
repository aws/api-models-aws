plugins {
    `java-library`
    `maven-publish`
    id("software.amazon.smithy.gradle.smithy-jar")
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

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
            from(components["java"])
        }
    }
}

smithy {
    format = false
    smithyBuildConfigs.set(project.objects.fileCollection())
}

sourceSets {
    main {
        smithy {
            srcDir(project.projectDir.path + "/service")
        }
    }
}

dependencies {
    implementation("software.amazon.smithy:smithy-aws-apigateway-traits:1.56.0")
    implementation("software.amazon.smithy:smithy-aws-cloudformation-traits:1.56.0")
    implementation("software.amazon.smithy:smithy-aws-endpoints:1.56.0")
    implementation("software.amazon.smithy:smithy-aws-iam-traits:1.56.0")
    implementation("software.amazon.smithy:smithy-aws-traits:1.56.0")
    implementation("software.amazon.smithy:smithy-protocol-traits:1.56.0")
    implementation("software.amazon.smithy:smithy-model:1.56.0")
    implementation("software.amazon.smithy:smithy-rules-engine:1.56.0")
}