plugins {
  java
}

repositories {
  mavenCentral()
  google()
}

dependencies {
  constraints {
    implementation("org.apache.commons:commons-text:1.10.8")
  }

  testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
  useJUnitPlatform()
}

extensions.findByName("buildScan")?.withGroovyBuilder {
  setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
  setProperty("termsOfServiceAgree", "yes")
}
