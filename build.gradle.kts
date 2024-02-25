plugins {
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

val jdaVersion = "5.0.0-beta.20"

application {
    mainClass = "org.example.Bot"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation("ch.qos.logback:logback-classic:1.3.12")
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.+")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isIncremental = true

    // Set this to the version of java you want to use,
    // the minimum required for JDA is 1.8
    sourceCompatibility = "1.8"
}

tasks.test {
    useJUnitPlatform()
}