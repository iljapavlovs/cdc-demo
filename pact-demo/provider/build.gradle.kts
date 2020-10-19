import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    val kotlinPluginVersion = "1.4.10" // 10 Sep 2020

    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version kotlinPluginVersion
    kotlin("plugin.spring") version kotlinPluginVersion
    // @Entity classes should have a default (non-arg) constructor to instantiate the objects when retrieving them from the database
    // This plugin generates them for Data classes
    kotlin("plugin.jpa") version kotlinPluginVersion

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
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
