plugins {
    kotlin("jvm") version "1.7.20"
}

group = "com.ax-h"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val h2Version = "2.1.214"
val exosedVersion = "0.41.1"

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("com.h2database", "h2", h2Version)
    testImplementation("org.jetbrains.exposed", "exposed-core", exosedVersion)
    testImplementation("org.jetbrains.exposed", "exposed-jdbc", exosedVersion)
}

tasks.test {
    useJUnitPlatform()
}
