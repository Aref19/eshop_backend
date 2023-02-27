import java.util.regex.Pattern.compile

plugins {
    java
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {

    val keycloakVersion = "18.0.0"

    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


    // Keycloak (Authentication)
    implementation("org.keycloak:keycloak-spring-boot-starter:20.0.3")
    implementation("org.keycloak:keycloak-admin-client:20.0.3")

    //jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    //lombok
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    //mysql
    implementation("mysql:mysql-connector-java:8.0.32")

    implementation("org.hibernate:hibernate-validator:8.0.0.Final")

    //flyway
    compile("org.flywaydb.enterprise:flyway-mysql")
    //jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.codehaus.jackson:jackson-core-asl:1.9.13")
    implementation("org.codehaus.jackson:jackson-mapper-asl:1.9.13")
    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

}

tasks.withType<Test> {
    useJUnitPlatform()
}
