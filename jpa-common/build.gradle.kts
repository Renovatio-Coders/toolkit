plugins {
    id("org.springframework.boot") version "3.3.8"
    id("io.spring.dependency-management")
    id("java-library")
    kotlin("jvm")
    kotlin("plugin.spring")
    id("maven-publish") // 외부 배포 가능하도록 설정
}

group = "coders.renovatio.donghang"
version = "0.0.1-SNAPSHOT"

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

// Maven Local 또는 원격 저장소에 배포 가능하도록 설정
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "coders.renovatio.donghang"
            artifactId = "jpa-common"
            version = "0.0.1-SNAPSHOT"
        }
    }
}
