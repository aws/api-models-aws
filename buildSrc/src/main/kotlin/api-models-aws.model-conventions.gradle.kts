plugins {
    `java-library`
    id("software.amazon.smithy.gradle.smithy-jar")
    id("api-models-aws.publishing-conventions")
}

repositories {
    mavenLocal()
    mavenCentral()
}

//// Workaround per: https://github.com/gradle/gradle/issues/15383
val Project.libs get() = the<org.gradle.accessors.dm.LibrariesForLibs>()

dependencies {
    implementation(libs.smithy.aws.cloudformation.traits)
    implementation(libs.smithy.aws.endpoints)
    implementation(libs.smithy.aws.iam.traits)
    implementation(libs.smithy.aws.smoke.test.model)
    implementation(libs.smithy.aws.traits)
    implementation(libs.smithy.protocol.traits)
    implementation(libs.smithy.model)
    implementation(libs.smithy.rules.engine)
    implementation(libs.smithy.smoke.test.traits)
    implementation(libs.smithy.waiters)
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

tasks {
    build {
        mustRunAfter(clean)
    }
}

