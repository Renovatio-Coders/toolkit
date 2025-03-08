plugins {
    id("org.springframework.boot") version "3.3.8"
    id("io.spring.dependency-management")
    id("java-library")
    kotlin("jvm")
    kotlin("plugin.spring")
    id("maven-publish")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
//    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc") // DataSource 제공
    implementation("mysql:mysql-connector-java:8.0.33") // MySQL 드라이버
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}

group = "com.renovatio.toolkit"
version = System.getenv("VERSION") ?: "0.0.1-SNAPSHOT" // GitHub Actions에서 VERSION 제공 가능

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Renovatio-Coders/toolkit")
            credentials {
                username = System.getenv("GH_USERNAME") ?: ""
                password = System.getenv("GH_PAT") ?: ""
            }
        }
    }
    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])
            groupId = "com.renovatio.toolkit"
            artifactId = "jpa-common"
            version = project.version.toString()
        }
    }
}