import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    // Pact plugin needed in order to publish pacts (contracts) to Pact Broker
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
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("au.com.dius.pact.consumer:junit5:4.1.8")


//    // add jaxb since it's no longer available in Java 11
//    implementation("javax.xml.bind:jaxb-api:2.3.1")
//
//    // add javassist >= 3.23.1-GA since earlier versions are broken in Java 11
//    // see https://github.com/jboss-javassist/javassist/issues/194
//    implementation("org.javassist:javassist:3.23.1-GA")
//
//    // overriding bytebuddy to newer version that supports Java 11
//    // see https://github.com/raphw/byte-buddy/issues/428
//    implementation("net.bytebuddy:byte-buddy:1.9.12")

}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Publish pact files


pact {
    publish {
        pactDirectory = "$buildDir/pacts"
        pactBrokerUrl = "http://localhost:3000"
    }
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

