plugins {
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("java")
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

val jdaVersion = "5.2.1"

application {
    mainClass = "org.example.Bot"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation("ch.qos.logback:logback-classic:1.5.16")
    testImplementation(platform("org.junit:junit-bom:5.11.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.+")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isIncremental = true

    // Set this to the version of java you want to use,
    // the minimum required for JDA is 1.8
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    archiveFileName.set("birdy.jar")
}

kotlin {
    jvmToolchain(21)
}