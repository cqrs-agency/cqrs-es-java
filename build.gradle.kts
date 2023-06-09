plugins {
    kotlin("jvm") version "1.8.21"
    id("org.jlleitschuh.gradle.ktlint") version "11.4.0"
    application
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    group = "agency.cqrs"
    version = "0.0.1-SNAPSHOT"

    dependencies {
        testImplementation(kotlin("test"))
    }

    repositories {
        mavenCentral()
    }

    tasks.test {
        useJUnitPlatform()
    }

    kotlin {
        jvmToolchain(17)
    }
}

project(":cqrs-es-core") {
    dependencies {
        implementation(project(":cqrs-es-api"))
    }
}
