plugins {
  id("misskrient-mfm-parser-conventions")
  `java-library`
  jacoco
}

repositories {
  mavenCentral()
  google()
}

var lombokDepNotation = "org.projectlombok:lombok:1.18.32"
var jacksonVersionNotation = "2.16.1"
var jakartaVersionNotation = "2.1.1"

dependencies {
  implementation(lombokDepNotation)
  implementation("com.google.guava:guava:33.2.0-jre")
  implementation("com.fasterxml.jackson.core:jackson-databind:${jacksonVersionNotation}")
  implementation("com.fasterxml.jackson.core:jackson-annotations:${jacksonVersionNotation}")
  implementation("com.fasterxml.jackson.core:jackson-core:${jacksonVersionNotation}")
  implementation("jakarta.annotation:jakarta.annotation-api:${jakartaVersionNotation}")
  implementation("ch.qos.logback:logback-classic:1.5.3")

  annotationProcessor(lombokDepNotation)
  annotationProcessor("com.fasterxml.jackson.core:jackson-annotations:${jacksonVersionNotation}")
  annotationProcessor("jakarta.annotation:jakarta.annotation-api:${jakartaVersionNotation}")
}

extensions.findByName("buildScan")?.withGroovyBuilder {
  setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
  setProperty("termsOfServiceAgree", "yes")
}
