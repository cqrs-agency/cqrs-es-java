plugins {
    kotlin("jvm") version "1.8.21"
    application
}

allprojects {
    apply(plugin = "kotlin")

    group = "agency.cqrs"
    version = "1.0-SNAPSHOT"

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
