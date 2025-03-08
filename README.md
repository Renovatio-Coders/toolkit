# Toolkit Repository

## 📌 개요

toolkit 리포지토리는 Spring Boot 애플리케이션에서 공통으로 사용할 수 있는 모듈들을 관리하는 멀티 모듈 Gradle 프로젝트입니다. 각 애플리케이션은 필요에 따라 개별 모듈을 의존성으로 추가하여 사용할 수 있습니다.

## 📌 포함된 모듈

현재 toolkit 리포지토리에는 다음과 같은 공통 모듈이 포함되어 있습니다.

| 모듈명 | 설명 |
|--------|------|
| `jpa-common` | JPA 관련 공통 설정 (Hibernate 지원) |
| *(추가 예정)* `logging-common` | 공통 로깅 설정 (Logback, SLF4J) |
| *(추가 예정)* `security-common` | Spring Security 공통 설정 |

각 모듈은 독립적으로 동작하며, 애플리케이션에서 선택적으로 사용할 수 있습니다.

## 📌 1️⃣ 멀티 모듈 Gradle 설정

각 공통 모듈은 toolkit 리포지토리 내에서 Gradle 멀티 모듈로 관리됩니다.

### 🔹 `settings.gradle.kts` 예시

```kotlin
rootProject.name = "toolkit"

// 하위 모듈 등록
include("jpa-common")
// ... 추후 다른 모듈 추가 예정
```

✅ 이렇게 설정하면 각 공통 모듈을 독립적으로 관리하면서도 하나의 레포지토리에서 함께 운영할 수 있습니다.

## 📌 2️⃣ `jpa-common` 모듈 사용법

### 🔹 개요

`jpa-common` 모듈은 Spring Boot 애플리케이션에서 JPA 설정을 공통으로 관리할 수 있도록 제공됩니다. 각 애플리케이션은 `jpa-common`을 의존성으로 추가하여 Hibernate 설정을 동적으로 적용할 수 있습니다.

### 🔹 1) `application.properties` 설정

각 애플리케이션에서 `application.properties` 파일을 이용하여 Hibernate 설정을 변경할 수 있습니다.

#### MySQL 설정
```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

#### PostgreSQL 설정
```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=validate
```

#### Oracle 설정
```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=none
```


### 🔹 2) Gradle 의존성 추가

각 애플리케이션의 `build.gradle.kts`에서 필요한 공통 모듈을 추가하면 사용할 수 있습니다.

```kotlin
dependencies {
    implementation("coders.renovatio.donghang:jpa-common:0.0.1-SNAPSHOT")
}
```

✅ 이제 `jpa-common`이 자동으로 적용되며, 추가적인 JPA 설정 없이 사용할 수 있습니다.

## 📌 3️⃣ 새로운 공통 모듈 추가 방법

toolkit에 새로운 공통 모듈을 추가하려면 다음 단계를 따르면 됩니다.

### 🔹 1) 새로운 모듈 디렉토리 생성

예를 들어, 로깅 설정을 위한 `logging-common` 모듈을 추가하려면:
```sh
cd toolkit
mkdir logging-common
cd logging-common
```

### 🔹 2) `logging-common/build.gradle.kts` 생성

```kotlin
plugins {
    id("org.springframework.boot") version "3.3.8" apply false
    id("io.spring.dependency-management")
    id("java-library")
    kotlin("jvm")
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
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.4.11")
    implementation("org.slf4j:slf4j-api:2.0.9")
}
```

### 🔹 3) `settings.gradle.kts`에 모듈 등록

```kotlin
include("logging-common")
```

✅ 이제 toolkit에서 새로운 공통 모듈을 추가하고 관리할 수 있습니다. 🚀

## 📌 4️⃣ 최종 정리

| 항목 | 설명 |
|------|------|
| **멀티 모듈 Gradle 설정** | `settings.gradle.kts`에서 각 모듈(`jpa-common`, `logging-common` 등) 추가 |
| **`jpa-common` 모듈 사용법** | Hibernate 설정을 `application.properties` 또는 환경 변수로 동적 변경 가능 |
| **Gradle 의존성 추가** | `implementation("coders.renovatio.donghang:jpa-common:0.0.1-SNAPSHOT")` |
| **새로운 공통 모듈 추가 방법** | 모듈 생성 → `build.gradle.kts` 추가 → `settings.gradle.kts`에 등록 |

✅ 이제 `toolkit` 리포지토리는 여러 공통 모듈을 효율적으로 관리할 수 있습니다.

