import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date

plugins {

    val kotlinPluginVersion = "1.4.10" // 10 Sep 2020

    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version kotlinPluginVersion
    kotlin("plugin.spring") version kotlinPluginVersion
    // @Entity classes should have a default (non-arg) constructor to instantiate the objects when retrieving them from the database
    // This plugin generates them for Data classes
    kotlin("plugin.jpa") version kotlinPluginVersion
    id("au.com.dius.pact") version "4.1.8"

}

group = "io.iljapavlovs.cdc.pact-demo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11





repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2:1.4.196")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

//    testImplementation("au.com.dius:pact-jvm-provider-junit5-spring:4.0.10")

    testImplementation("au.com.dius.pact.provider:junit5:4.1.8")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

}

tasks.withType<Test> {
    useJUnitPlatform()


    systemProperty(
        "pact.provider.version",
        "$version.${getGitHash()}.${SimpleDateFormat("MM-dd-yyyy_hh-mm").format(Date())}"
    )
    systemProperty("pact.provider.tag", gitBranch())
    systemProperty("pact.verifier.publishResults", true)
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}


/**
 * Utility function to retrieve the name of the current git branch.
 * Will not work if build tool detaches head after checkout, which some do!
 */
fun gitBranch(): String {
    return try {
        val byteOut = ByteArrayOutputStream()
        project.exec {
            commandLine = "git rev-parse --abbrev-ref HEAD".split(" ")
            standardOutput = byteOut
        }
        String(byteOut.toByteArray()).trim().also {
            if (it == "HEAD")
                logger.warn("Unable to determine current branch: Project is checked out with detached head!")
        }
    } catch (e: Exception) {
        logger.warn("Unable to determine current branch: ${e.message}")
        "Unknown Branch"
    }
}

fun getGitHash(): String {
    val byteOut = ByteArrayOutputStream()
    project.exec {
        commandLine = "git rev-parse --short HEAD".split(" ")
        standardOutput = byteOut
    }
    return String(byteOut.toByteArray()).trim()
}