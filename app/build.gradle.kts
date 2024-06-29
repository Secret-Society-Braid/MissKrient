plugins {
    java
    application
    jacoco
    id("org.javamodularity.moduleplugin") version "1.8.15"
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("org.beryx.jlink") version "3.0.1"
  id("misskrient-application-conventions")
}

group = "org.braid.society.secret"
version = "1.0.0"

repositories {
    mavenCentral()
    google()
    maven("https://jitpack.io")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

application {
    mainModule.set("org.braid.society.secret.misskrient")
    mainClass.set("org.braid.society.secret.misskrient.MisskrientApplication")
}

javafx {
    version = "17"
    modules = listOf("javafx.controls", "javafx.fxml")
}

val junitVersion = "5.10.3"
val lombokVersion = "1.18.32"
val jacksonVersion = "2.16.1"
val jakartaVersion = "3.0.0"

dependencies {
    implementation("org.mapstruct:mapstruct-processor:1.5.5.Final")
    implementation("org.projectlombok:lombok:${lombokVersion}")
    implementation("com.google.guava:guava:33.0.0-jre")
    implementation("ch.qos.logback:logback-classic:1.5.3")
    implementation("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
    implementation("com.fasterxml.jackson.core:jackson-annotations:${jacksonVersion}")
    implementation("com.github.uakihir0:misskey4j:0.5.0")
    implementation("jakarta.annotation:jakarta.annotation-api:${jakartaVersion}")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
  implementation(project(":mfmparser"))


    // Test deps
    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
    testImplementation("org.mockito:mockito-junit-jupiter:5.11.0")
    testImplementation("com.google.truth:truth:1.4.2") {
        isTransitive = false
    }

    // annotation processors
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api:${jakartaVersion}")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

jlink {
    addExtraDependencies("org.jetbrains.kotlin:kotlin-stdlib:1.9.21")
    imageZip = project.file("${buildDir}/distributions/app-${javafx.platform.classifier}.zip")
    options = mutableListOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
    launcher {
        name = "app"
    }
}

tasks.jlinkZip {
    group = "distribution"
}

extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}
