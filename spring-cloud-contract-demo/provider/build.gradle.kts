import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.cloud.contract.verifier.config.TestFramework.JUNIT5

//https://github.com/spring-cloud-samples/spring-cloud-contract-samples/blob/master/producer_kotlin_ftw/build.gradle.kts
// https://cloud.spring.io/spring-cloud-contract/reference/html/project-features.html#contract-kotlin
//http://antkorwin.com/cloud/spring_cloud_contract_junit5.html
// gradle - https://cloud.spring.io/spring-cloud-contract/reference/html/gradle-project.html

//buildscript {
//    val verifierVersion: String by extra
//    repositories {
//        mavenCentral()
//        mavenLocal()
//        maven { url = uri("https://repo.spring.io/release") }
//        maven { url = uri("https://repo.spring.io/milestone") }
//        maven { url = uri("https://repo.spring.io/snapshot") }
//    }
//
//    dependencies {
//        classpath("org.springframework.cloud:spring-cloud-contract-spec-kotlin:2.2.4.RELEASE")
//    }
//}

plugins {
    idea
    val kotlinPluginVersion = "1.4.10" // 10 Sep 2020

    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("org.springframework.cloud.contract") version "2.2.4.RELEASE"

//    id("spring-cloud-contract")

    id("maven-publish")


    kotlin("jvm") version kotlinPluginVersion
    kotlin("plugin.spring") version kotlinPluginVersion
    // @Entity classes should have a default (non-arg) constructor to instantiate the objects when retrieving them from the database
    // This plugin generates them for Data classes
    kotlin("plugin.jpa") version kotlinPluginVersion
}

group = "io.iljapavlovs.cdc.spring-cloud-contract-demo"
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
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier:2.2.4.RELEASE")
//    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-spec:2.2.4.RELEASE")

    testImplementation("org.codehaus.groovy:groovy-all:2.4.6")
    testImplementation("io.rest-assured:spring-mock-mvc:4.3.1")
    testImplementation("io.rest-assured:rest-assured-all:4.3.1")
    testImplementation("io.rest-assured:rest-assured-common:4.3.1")
    testImplementation("io.rest-assured:json-path:4.3.1")
    testImplementation("io.rest-assured:xml-path:4.3.1")

    implementation("org.springframework.boot:spring-boot-starter-amqp")


    // Remember to add this for the DSL support in the IDE and on the consumer side
//    testImplementation("org.springframework.cloud:spring-cloud-contract-spec-kotlin")
}

tasks.withType<Test> {
    useJUnitPlatform()
}



contracts {
//    https://docs.spring.io/spring-cloud-contract/docs/2.2.4.RELEASE/reference/html/gradle-project.html#by-convention
    packageWithBaseClasses.set("io.iljapavlovs.cdc.springcloudcontractdemo.provider.contractbase")
//    The mapping we defined above tells Spring Cloud Contract that the tests generated for any contracts it finds
//   in src/test/resources/contracts that contain “userservice” in their path are to be subclassed from our test base class UserServiceBase.
//  We could define more mappings if different tests require different setups (i.e. different base classes).
    baseClassMappings {
        baseClassMapping(
            ".*rest.*",
            "io.iljapavlovs.cdc.springcloudcontractdemo.provider.contractbase.ContractTestRestBase"
        )
        baseClassMapping(
            ".*amqp.*",
            "io.iljapavlovs.cdc.springcloudcontractdemo.provider.contractbase.ContractTestAmqpBase"
        )
    }

    setTestFramework(JUNIT5)

    failOnInProgress.set(false)
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}


