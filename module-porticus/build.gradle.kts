dependencies {
    implementation("com.google.guava:guava:21.0")
    compileOnly("ink.ptms.core:v11600:11600")
    compileOnly("net.md_5.bungee:BungeeCord:1")
}

tasks {
    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        archiveClassifier.set("")
        archiveBaseName.set("${archiveBaseName.get()}-shaded")
        dependencies {
            include(dependency("com.google.guava:guava:21.0"))
        }
    }
}