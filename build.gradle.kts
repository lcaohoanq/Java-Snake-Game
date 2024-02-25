plugins {
    id("java")
}

group = "com.lcaohoanq" // Replace with your groupId
version = "1.0-SNAPSHOT" // Replace with your desired version

var sourceCompatibility = 18
var targetCompatibility = 18

repositories {
    mavenCentral()
}

dependencies {
    implementation("mysql:mysql-connector-java:8.0.28") // Replace with your dependencies
    testImplementation("junit:junit:4.12") // Replace with your test dependencies
}

// Add tasks for running the application (optional)
task("run") {
    doFirst {
        val mainClass = "runtime.Main"  // Assign mainClass as a local variable
        val classpath = sourceSets["main"].runtimeClasspath  // Access runtimeClasspath correctly

        exec {
            commandLine("java", "-cp", classpath.joinToString(separator = File.pathSeparator), mainClass)  // Pass classpath as a string
        }
    }
}