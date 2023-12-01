plugins {
    java
    application
    id("org.javamodularity.moduleplugin") version "1.8.12"
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.beryx.jlink") version "2.25.0"
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
}

group = "org.braid.society.secret"
version = "0.0.1"

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

val junitVersion = "5.9.2"
val lombokVersion = "1.18.28"
val jacksonVersion = "2.15.2"
val jakartaVersion = "2.1.1"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    // Artifact deps
    implementation("org.mapstruct:mapstruct-processor:1.3.1.Final")
    implementation("org.projectlombok:lombok:${lombokVersion}")
    implementation("com.google.guava:guava:32.0.1-jre")
    implementation("ch.qos.logback:logback-classic:1.4.8")
    implementation("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
    implementation("com.fasterxml.jackson.core:jackson-annotations:${jacksonVersion}")
    implementation("com.github.uakihir0:misskey4j:0.5.0")
    implementation("jakarta.annotation:jakarta.annotation-api:${jakartaVersion}")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")


    // Test deps
    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
    testImplementation("org.mockito:mockito-junit-jupiter:5.7.0")
    testImplementation("com.google.truth:truth:1.1.5") {
        isTransitive = false
    }

    // annotation processors
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api:${jakartaVersion}")
}

tasks.test {
    useJUnitPlatform()
}

jlink {
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
