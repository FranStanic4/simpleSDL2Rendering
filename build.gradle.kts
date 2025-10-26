plugins {
    id("java")
}

group = "com.folumo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // Core SDL2
    implementation("io.github.libsdl4j:libsdl4j:2.28.4-1.6")
}
