plugins {
    `java-library`
    checkstyle
}

group = "uk.co.conoregan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // logging
    implementation("org.slf4j:slf4j-api:2.0.13")

    // testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = "10.17.0"
    configFile = file("config/checkstyle/checkstyle.xml")
}
tasks.checkstyleMain {
    source = fileTree("src/main/java")
}
tasks.checkstyleTest {
    source = fileTree("src/test/java")
}
